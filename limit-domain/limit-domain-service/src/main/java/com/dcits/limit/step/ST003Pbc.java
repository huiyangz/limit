package com.dcits.limit.step;

import com.dcits.limit.enums.CtrlItemType;
import com.dcits.limit.enums.LimitConvert;
import com.dcits.limit.enums.PeriodType;
import com.dcits.limit.enums.SumType;
import com.dcits.limit.facade.bo.ST003InputBO;
import com.dcits.limit.facade.bo.ST003OutputBO;
import com.dcits.limit.facade.components.IRbLimitCtrlConfBcc;
import com.dcits.limit.facade.components.IRbLimitCtrlCustomInfoBcc;
import com.dcits.limit.facade.components.IRbLimitSceneDefBcc;
import com.dcits.limit.facade.components.IRbLimitSumInfoBcc;
import com.dcits.limit.facade.components.IRbLimitSumJnlBcc;
import com.dcits.limit.facade.eo.RbLimitCtrlConfEO;
import com.dcits.limit.facade.eo.RbLimitCtrlCustomInfoEO;
import com.dcits.limit.facade.eo.RbLimitSceneDefEO;
import com.dcits.limit.facade.eo.RbLimitSumInfoEO;
import com.dcits.limit.facade.eo.RbLimitSumJnlEO;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * ST003 计算限额累计金额 步骤实现。
 *
 * <p>子步骤1 获取限额信息：按{限额检查对象值}、{限额场景编码}优先查询限额控制客户自定义配置
 * （RB_LIMIT_CTRL_CUSTOM_INFO），未命中回退限额控制配置（RB_LIMIT_CTRL_CONF），
 * 累计类型代码一律取自限额控制配置中该场景的配置。</p>
 *
 * <p>子步骤2 限额控制类型为 O-单笔金额时无需计算累计限额，业务输出保持空，正常成功结束；
 * 为 N-累计笔数、A-累计金额、B-累计笔数/金额时按累计类型代码计算：</p>
 *
 * <p>子步骤3 累计类型代码为 1-自然周期、3-指定日期范围、4-指定时间范围、5-指定日期+时间范围时，
 * 按主键查询限额累计信息表（RB_LIMIT_SUM_INFO）并校验生效范围（系统日期大于等于生效日期且小于失效日期）：
 * 在生效范围内返回限额累计金额=存量累计金额+{交易金额}、限额累计笔数=存量笔数+1；
 * 查无或不在生效范围按不存在处理，返回限额累计金额={交易金额}、限额累计笔数=1。</p>
 *
 * <p>子步骤4、5 累计类型代码为 2-滑动窗口时，按{限额检查对象值}、{限额场景编码}、{客户号}
 * 查询滑动流水表（RB_LIMIT_SUM_JNL），取有效周期内（按配置期限类型、周期值划定，当前仅定义按日 D
 * 的窗口语义）的流水，按限额场景定义表（RB_LIMIT_SCENE_DEF）的限额折算方式汇总：
 * 原币种汇总交易金额、折算汇总限额折算金额，限额累计笔数=交易笔数之和；
 * 有效周期内无流水按不存在处理，返回限额累计金额={交易金额}、限额累计笔数=1。
 * 本步骤无业务失败场景，失败仅由技术异常传播表达。</p>
 */
@Service
public class ST003Pbc implements IST003 {

    /** 累计信息不存在 / 有效周期内无流水时的限额累计笔数 */
    private static final Integer INIT_COUNT = Integer.valueOf(1);

    private final IRbLimitCtrlCustomInfoBcc rbLimitCtrlCustomInfoBcc;
    private final IRbLimitCtrlConfBcc rbLimitCtrlConfBcc;
    private final IRbLimitSumInfoBcc rbLimitSumInfoBcc;
    private final IRbLimitSumJnlBcc rbLimitSumJnlBcc;
    private final IRbLimitSceneDefBcc rbLimitSceneDefBcc;

    public ST003Pbc(IRbLimitCtrlCustomInfoBcc rbLimitCtrlCustomInfoBcc,
            IRbLimitCtrlConfBcc rbLimitCtrlConfBcc, IRbLimitSumInfoBcc rbLimitSumInfoBcc,
            IRbLimitSumJnlBcc rbLimitSumJnlBcc, IRbLimitSceneDefBcc rbLimitSceneDefBcc) {
        this.rbLimitCtrlCustomInfoBcc = rbLimitCtrlCustomInfoBcc;
        this.rbLimitCtrlConfBcc = rbLimitCtrlConfBcc;
        this.rbLimitSumInfoBcc = rbLimitSumInfoBcc;
        this.rbLimitSumJnlBcc = rbLimitSumJnlBcc;
        this.rbLimitSceneDefBcc = rbLimitSceneDefBcc;
    }

    @Override
    public ST003OutputBO execute(ST003InputBO input) {
        ST003OutputBO output = new ST003OutputBO();
        // 子步骤1 获取限额信息：优先取客户自定义配置，未命中回退配置表；累计类型代码取自配置表
        RbLimitCtrlCustomInfoEO customInfo = rbLimitCtrlCustomInfoBcc
                .findByPrimaryKey(input.getCheckObjVal(), input.getLimitSceneNo());
        RbLimitCtrlConfEO ctrlConf = findCtrlConf(input.getLimitSceneNo());
        CtrlItemType ctrlItemType = customInfo != null ? customInfo.getCtrlItemType()
                : ctrlConf.getCtrlItemType();
        // 子步骤2 限额控制类型为 O-单笔金额：无需计算累计限额，正常成功结束
        if (CtrlItemType.O.equals(ctrlItemType)) {
            output.setSucceed(true);
            return output;
        }
        if (!isAccumulateType(ctrlItemType)) {
            throw new IllegalArgumentException(
                    "不支持的限额控制类型: " + (ctrlItemType == null ? null : ctrlItemType.getValue()));
        }
        if (SumType.VALUE_2.equals(ctrlConf.getSumType())) {
            // 子步骤4、5 累计类型代码 2-滑动窗口：按有效周期内滑动流水汇总
            calcBySlidingWindow(input, ctrlConf, output);
        } else if (isFixedRangeSumType(ctrlConf.getSumType())) {
            // 子步骤3 累计类型代码 1/3/4/5：按限额累计信息表存量加当笔累计
            calcBySumInfo(input, output);
        } else {
            throw new IllegalArgumentException(
                    "不支持的累计类型代码: " + (ctrlConf.getSumType() == null ? null
                            : ctrlConf.getSumType().getValue()));
        }
        output.setSucceed(true);
        return output;
    }

    /**
     * 子步骤1 按限额场景编码查询限额控制配置（RB_LIMIT_CTRL_CONF），
     * 同场景仅一条配置（ST005），取查询结果首条。
     */
    private RbLimitCtrlConfEO findCtrlConf(String limitSceneNo) {
        RbLimitCtrlConfEO condition = new RbLimitCtrlConfEO();
        condition.setLimitSceneNo(limitSceneNo);
        List<RbLimitCtrlConfEO> configs = rbLimitCtrlConfBcc.findByEo(condition);
        return configs.get(0);
    }

    /** 限额控制类型是否为 N-累计笔数、A-累计金额、B-累计笔数/金额。 */
    private static boolean isAccumulateType(CtrlItemType ctrlItemType) {
        return CtrlItemType.N.equals(ctrlItemType) || CtrlItemType.A.equals(ctrlItemType)
                || CtrlItemType.B.equals(ctrlItemType);
    }

    /** 累计类型代码是否为 1-自然周期、3-指定日期范围、4-指定时间范围、5-指定日期+时间范围。 */
    private static boolean isFixedRangeSumType(SumType sumType) {
        return SumType.VALUE_1.equals(sumType) || SumType.VALUE_3.equals(sumType)
                || SumType.VALUE_4.equals(sumType) || SumType.VALUE_5.equals(sumType);
    }

    /**
     * 子步骤3 按主键查询限额累计信息表（RB_LIMIT_SUM_INFO）并校验生效范围
     * （系统日期大于等于生效日期且小于失效日期）：在生效范围内时限额累计金额=存量累计金额+{交易金额}、
     * 限额累计笔数=存量笔数+1；查无或不在生效范围按不存在处理，限额累计金额={交易金额}、限额累计笔数=1。
     */
    private void calcBySumInfo(ST003InputBO input, ST003OutputBO output) {
        RbLimitSumInfoEO sumInfo = rbLimitSumInfoBcc.findByPrimaryKey(input.getCheckObjVal(),
                input.getLimitSceneNo());
        Date systemDate = new Date();
        boolean inEffect = sumInfo != null && !systemDate.before(sumInfo.getEffectDate())
                && systemDate.before(sumInfo.getExpireDate());
        if (inEffect) {
            output.setLimitSumAmt(sumInfo.getLimitSumAmt().add(input.getTranAmt()));
            output.set否(Integer.valueOf(sumInfo.get否().intValue() + 1));
        } else {
            output.setLimitSumAmt(input.getTranAmt());
            output.set否(INIT_COUNT);
        }
    }

    /**
     * 子步骤4、5 按{限额检查对象值}、{限额场景编码}、{客户号}查询滑动流水表（RB_LIMIT_SUM_JNL），
     * 取有效周期内的流水：按限额场景定义的限额折算方式汇总金额（原币种汇总交易金额、折算汇总限额折算金额），
     * 限额累计笔数=所有交易笔数之和；有效周期内无流水按不存在处理，
     * 限额累计金额={交易金额}、限额累计笔数=1。
     */
    private void calcBySlidingWindow(ST003InputBO input, RbLimitCtrlConfEO ctrlConf,
            ST003OutputBO output) {
        RbLimitSumJnlEO condition = new RbLimitSumJnlEO();
        condition.setCheckObjVal(input.getCheckObjVal());
        condition.setLimitSceneNo(input.getLimitSceneNo());
        condition.setClientNo(input.getClientNo());
        List<RbLimitSumJnlEO> journals = rbLimitSumJnlBcc.findByEo(condition);
        if (journals.isEmpty()) {
            output.setLimitSumAmt(input.getTranAmt());
            output.set否(INIT_COUNT);
            return;
        }
        RbLimitSceneDefEO sceneDef = rbLimitSceneDefBcc.findByPrimaryKey(input.getLimitSceneNo());
        LimitConvert limitConvert = sceneDef.getLimitConvert();
        Date windowStart = calcWindowStart(ctrlConf.getPeriodType(), ctrlConf.getPeriodValue());
        Date systemDate = new Date();
        BigDecimal sumAmt = BigDecimal.ZERO;
        int count = 0;
        for (RbLimitSumJnlEO journal : journals) {
            Date tranDate = journal.getTranDate();
            if (tranDate.after(windowStart) && !tranDate.after(systemDate)) {
                sumAmt = sumAmt.add(resolveJournalAmt(journal, limitConvert));
                count++;
            }
        }
        if (count > 0) {
            output.setLimitSumAmt(sumAmt);
            output.set否(Integer.valueOf(count));
        } else {
            output.setLimitSumAmt(input.getTranAmt());
            output.set否(INIT_COUNT);
        }
    }

    /**
     * 有效周期起点=系统日期减去期限类型、周期值换算的窗口长度（开区间边界）。
     * 与累计信息的生效范围语义对称：交易日 T 的流水在系统日期 S 有效当且仅当 S∈[T, T+窗口)，
     * 即 T 大于 S-窗口且 T 小于等于 S。当前仅定义按日（D）的窗口换算，其余期限类型明确报错。
     */
    private Date calcWindowStart(PeriodType periodType, String periodValue) {
        if (periodType != PeriodType.D) {
            throw new IllegalArgumentException(
                    "有效周期换算语义未定义的期限类型: "
                            + (periodType == null ? null : periodType.getValue()));
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -Integer.parseInt(periodValue));
        return calendar.getTime();
    }

    /**
     * 子步骤5 按限额场景定义的限额折算方式确定单笔流水参与汇总的金额：
     * 原币种（VALUE_1）取交易金额，折算（VALUE_2）取限额折算金额，其他取值明确报错。
     */
    private static BigDecimal resolveJournalAmt(RbLimitSumJnlEO journal, LimitConvert limitConvert) {
        if (LimitConvert.VALUE_1.equals(limitConvert)) {
            return journal.getTranAmt();
        }
        if (LimitConvert.VALUE_2.equals(limitConvert)) {
            return journal.getLimitConvertAmt();
        }
        throw new IllegalArgumentException(
                "不支持的限额折算方式: " + (limitConvert == null ? null : limitConvert.getValue()));
    }
}
