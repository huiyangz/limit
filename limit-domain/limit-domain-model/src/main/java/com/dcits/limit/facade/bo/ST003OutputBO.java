package com.dcits.limit.facade.bo;

import com.dcits.common.step.StepResult;
import java.math.BigDecimal;

/** ST003 计算限额累计金额 步骤输出，业务字段为计算出的限额累计金额与限额累计笔数 */
public class ST003OutputBO extends StepResult {
    /** 限额累计金额 */
    private BigDecimal limitSumAmt;
    /** 限额累计笔数 */
    private Integer 否;

    public BigDecimal getLimitSumAmt() {
        return limitSumAmt;
    }

    public void setLimitSumAmt(BigDecimal limitSumAmt) {
        this.limitSumAmt = limitSumAmt;
    }

    public Integer get否() {
        return 否;
    }

    public void set否(Integer 否) {
        this.否 = 否;
    }
}
