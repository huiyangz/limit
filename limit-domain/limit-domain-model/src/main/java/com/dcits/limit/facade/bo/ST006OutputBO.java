package com.dcits.limit.facade.bo;

import java.math.BigDecimal;

import com.dcits.common.step.StepResult;

/**
 * ST006 获取累计限额 输出BO
 */
public class ST006OutputBO extends StepResult {
    /** 客户号，取命中的限额累计信息记录；无匹配记录时为空 */
    private String clientNo;
    /** 限额场景编码，取命中的限额累计信息记录；无匹配记录时为空 */
    private String limitSceneNo;
    /** 限额累计金额，取命中的限额累计信息记录；无匹配记录时为空 */
    private BigDecimal limitSumAmt;
    /** 限额累计笔数，取命中的限额累计信息记录；无匹配记录时为空 */
    private Integer limitSumNum;

    public String getClientNo() {
        return clientNo;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo;
    }

    public String getLimitSceneNo() {
        return limitSceneNo;
    }

    public void setLimitSceneNo(String limitSceneNo) {
        this.limitSceneNo = limitSceneNo;
    }

    public BigDecimal getLimitSumAmt() {
        return limitSumAmt;
    }

    public void setLimitSumAmt(BigDecimal limitSumAmt) {
        this.limitSumAmt = limitSumAmt;
    }

    public Integer getLimitSumNum() {
        return limitSumNum;
    }

    public void setLimitSumNum(Integer limitSumNum) {
        this.limitSumNum = limitSumNum;
    }
}
