package com.dcits.limit.step;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.limit.facade.bo.ST010InputBO;
import com.dcits.limit.facade.bo.ST010OutputBO;
import com.dcits.limit.facade.components.IRbLimitSumInfoBcc;
import com.dcits.limit.facade.eo.RbLimitSumInfoEO;

/**
 * 更新累计限额（ST010）。
 *
 * 若限额检查结果为"未超限"，且限额累计金额大于0或者限额累计笔数大于0，
 * 则根据账号（账号即限额检查对象值）与限额场景编码按主键更新
 * 【限额累计信息表（RB_LIMIT_SUM_INFO）】的限额累计金额为输入的限额累计金额；
 * 条件不成立时不执行更新，步骤正常完成。本步骤无业务失败场景，失败仅由技术异常传播表达。
 */
@Service
public class ST010Pbc implements IST010 {

    /** 限额检查结果取值：未超限 */
    private static final String LIMIT_CHECK_RESULT_NOT_EXCEEDED = "未超限";

    @Autowired
    private IRbLimitSumInfoBcc rbLimitSumInfoBcc;

    /**
     * 子步骤1 更新累计限额：条件成立时按主键（限额检查对象值=账号、限额场景编码）
     * 选择性更新限额累计金额，仅写入限额累计金额字段；条件不成立时跳过更新，正常返回。
     * 更新影响行数SPEC未定义处理，不据此判定业务失败。
     */
    @Override
    @Transactional
    public ST010OutputBO execute(ST010InputBO input) {
        ST010OutputBO output = new ST010OutputBO();
        boolean needUpdate = LIMIT_CHECK_RESULT_NOT_EXCEEDED.equals(input.getLimitCheckResult())
                && (input.getLimitSumAmt().compareTo(BigDecimal.ZERO) > 0 || input.getLimitSumNum() > 0);
        if (needUpdate) {
            RbLimitSumInfoEO eo = new RbLimitSumInfoEO();
            eo.setCheckObjVal(input.getBaseAcctNo());
            eo.setLimitSceneNo(input.getLimitSceneNo());
            eo.setLimitSumAmt(input.getLimitSumAmt());
            rbLimitSumInfoBcc.modifyByPrimaryKeySelective(eo);
            output.setLimitSumAmt(input.getLimitSumAmt());
        }
        output.setSucceed(true);
        return output;
    }
}
