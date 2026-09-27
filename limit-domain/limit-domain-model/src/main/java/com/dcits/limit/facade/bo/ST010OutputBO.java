package com.dcits.limit.facade.bo;

import java.math.BigDecimal;

import com.dcits.common.step.StepResult;

/** 更新累计限额（ST010）输出BO */
public class ST010OutputBO extends StepResult {
    /** 限额累计金额 */
    private BigDecimal limitSumAmt;

    public BigDecimal getLimitSumAmt() {
        return limitSumAmt;
    }

    public void setLimitSumAmt(BigDecimal limitSumAmt) {
        this.limitSumAmt = limitSumAmt;
    }
}
