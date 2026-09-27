package com.dcits.limit.facade.bo;

import com.dcits.common.step.StepResult;
import java.math.BigDecimal;

/**
 * ST004 检查限额 步骤输出
 *
 * <p>limitCtrlAmt、limitCtrlNum 来源为限额控制配置（RB_LIMIT_CTRL_CONF）查询所得；
 * limitSumAmt、否（限额累计笔数，字段名按已接受的需求处理结论沿用原文）为输入回显。</p>
 */
public class ST004OutputBO extends StepResult {
    /** 限额控制金额 */
    private BigDecimal limitCtrlAmt;
    /** 限额控制笔数 */
    private Integer limitCtrlNum;
    /** 限额累计金额 */
    private BigDecimal limitSumAmt;
    /** 限额累计笔数 */
    private Integer 否;

    public BigDecimal getLimitCtrlAmt() {
        return limitCtrlAmt;
    }

    public void setLimitCtrlAmt(BigDecimal limitCtrlAmt) {
        this.limitCtrlAmt = limitCtrlAmt;
    }

    public Integer getLimitCtrlNum() {
        return limitCtrlNum;
    }

    public void setLimitCtrlNum(Integer limitCtrlNum) {
        this.limitCtrlNum = limitCtrlNum;
    }

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
