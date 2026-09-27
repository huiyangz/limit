package com.dcits.limit.step;

import com.dcits.limit.enums.CheckObjType;
import com.dcits.limit.enums.PeriodType;
import com.dcits.limit.facade.bo.ST008InputBO;
import com.dcits.limit.facade.bo.ST008OutputBO;
import com.dcits.limit.facade.components.IRbLimitCtrlConfBcc;
import com.dcits.limit.facade.components.IRbLimitSceneDefBcc;
import com.dcits.limit.facade.components.IRbLimitSumInfoBcc;
import com.dcits.limit.facade.eo.RbLimitCtrlConfEO;
import com.dcits.limit.facade.eo.RbLimitSceneDefEO;
import com.dcits.limit.facade.eo.RbLimitSumInfoEO;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.museframework.component.sequence.trace.ITransIdGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ST008 登记累计限额 步骤实现。
 *
 * <p>子步骤1 登记累计限额：若限额检查结果为“未超限”，且限额累计金额等于0或者限额累计笔数等于0，
 * 则登记限额累计信息（RB_LIMIT_SUM_INFO）：限额场景编码取输入限额场景编码；限额检查对象值按限额场景定义表
 * （RB_LIMIT_SCENE_DEF）配置的检查对象类型取账号（ACCT）或客户号（CUST）；限额累计金额取交易金额；
 * 限额累计笔数置1；生效日期取系统日期；失效日期为系统日期加限额控制配置（RB_LIMIT_CTRL_CONF）的
 * 期限类型、周期值；其余字段按系统规则自动生成。本步骤无业务失败场景，不登记路径按正常成功结束。
 */
@Service
public class ST008Pbc implements IST008 {

    /** 限额检查结果取值：未超限 */
    private static final String CHECK_RESULT_NOT_EXCEEDED = "未超限";

    /** 登记时限额累计笔数固定取1 */
    private static final Integer REGISTER_COUNT = Integer.valueOf(1);

    /** 系统规则自动生成字符串时间戳的格式（14位） */
    private static final String TIMESTAMP_PATTERN = "yyyyMMddHHmmss";

    private final IRbLimitSceneDefBcc rbLimitSceneDefBcc;
    private final IRbLimitCtrlConfBcc rbLimitCtrlConfBcc;
    private final IRbLimitSumInfoBcc rbLimitSumInfoBcc;
    private final ITransIdGenerator transIdGenerator;

    public ST008Pbc(IRbLimitSceneDefBcc rbLimitSceneDefBcc, IRbLimitCtrlConfBcc rbLimitCtrlConfBcc,
            IRbLimitSumInfoBcc rbLimitSumInfoBcc, ITransIdGenerator transIdGenerator) {
        this.rbLimitSceneDefBcc = rbLimitSceneDefBcc;
        this.rbLimitCtrlConfBcc = rbLimitCtrlConfBcc;
        this.rbLimitSumInfoBcc = rbLimitSumInfoBcc;
        this.transIdGenerator = transIdGenerator;
    }

    @Override
    @Transactional
    public ST008OutputBO execute(ST008InputBO input) {
        ST008OutputBO output = new ST008OutputBO();
        // 子步骤1 登记累计限额：未超限，且限额累计金额等于0或限额累计笔数等于0时登记，否则正常结束不登记
        if (CHECK_RESULT_NOT_EXCEEDED.equals(input.getCheckResult())
                && (input.getLimitSumAmt().compareTo(BigDecimal.ZERO) == 0
                        || Integer.valueOf(0).equals(input.get否()))) {
            registerLimitSumInfo(input, output);
        }
        output.setSucceed(true);
        return output;
    }

    /**
     * 子步骤1 登记累计限额：组装并写入限额累计信息（RB_LIMIT_SUM_INFO），登记字段回填步骤输出。
     */
    private void registerLimitSumInfo(ST008InputBO input, ST008OutputBO output) {
        RbLimitSceneDefEO sceneDef = rbLimitSceneDefBcc.findByPrimaryKey(input.getLimitSceneNo());
        String checkObjVal = resolveCheckObjVal(sceneDef.getCheckObjType(), input);
        Date expireDate = calcExpireDate(input.getRunDate(), input.getLimitSceneNo());

        RbLimitSumInfoEO sumInfo = new RbLimitSumInfoEO();
        sumInfo.setLimitSceneNo(input.getLimitSceneNo());
        sumInfo.setCheckObjVal(checkObjVal);
        sumInfo.setLimitSumAmt(input.getTranAmt());
        sumInfo.set否(REGISTER_COUNT);
        sumInfo.setEffectDate(input.getRunDate());
        sumInfo.setExpireDate(expireDate);
        // 其他字段按系统规则自动生成：客户号取输入客户号，时间戳为当前系统时间，交易参考号取序列生成器
        sumInfo.setClientNo(input.getClientNo());
        String timestamp = new SimpleDateFormat(TIMESTAMP_PATTERN).format(new Date());
        sumInfo.setCreateTimestamp(timestamp);
        sumInfo.setLastUpdTimestamp(timestamp);
        sumInfo.setReference(transIdGenerator.nextId());
        rbLimitSumInfoBcc.createSelective(sumInfo);

        output.setLimitSceneNo(sumInfo.getLimitSceneNo());
        output.setCheckObjVal(sumInfo.getCheckObjVal());
        output.setLimitSumAmt(sumInfo.getLimitSumAmt());
        output.set否(sumInfo.get否());
        output.setEffectDate(sumInfo.getEffectDate());
        output.setExpireDate(sumInfo.getExpireDate());
    }

    /**
     * 按限额场景定义配置的检查对象类型取限额检查对象值：账户级别（ACCT）取账号，客户级别（CUST）取客户号。
     * 本步骤不支持其他检查对象类型，遇到时明确报错。
     */
    private String resolveCheckObjVal(CheckObjType checkObjType, ST008InputBO input) {
        if (CheckObjType.ACCT.equals(checkObjType)) {
            return input.getBaseAcctNo();
        }
        if (CheckObjType.CUST.equals(checkObjType)) {
            return input.getClientNo();
        }
        throw new IllegalArgumentException(
                "本步骤不支持的限额检查对象类型: " + (checkObjType == null ? null : checkObjType.getValue()));
    }

    /**
     * 失效日期=系统日期+限额控制配置（按限额场景编码查询）的期限类型、周期值。
     * 当前仅定义按日（D）加天的叠加语义，其余期限类型的叠加语义未定义，遇到时明确报错。
     */
    private Date calcExpireDate(Date runDate, String limitSceneNo) {
        RbLimitCtrlConfEO query = new RbLimitCtrlConfEO();
        query.setLimitSceneNo(limitSceneNo);
        List<RbLimitCtrlConfEO> ctrlConfList = rbLimitCtrlConfBcc.findByEo(query);
        RbLimitCtrlConfEO ctrlConf = ctrlConfList.get(0);
        PeriodType periodType = ctrlConf.getPeriodType();
        if (periodType != PeriodType.D) {
            throw new IllegalArgumentException(
                    "日期叠加语义未定义的期限类型: " + (periodType == null ? null : periodType.getValue()));
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(runDate);
        calendar.add(Calendar.DAY_OF_MONTH, Integer.parseInt(ctrlConf.getPeriodValue()));
        return calendar.getTime();
    }
}
