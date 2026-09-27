package com.dcits.limit.facade.bo;

import java.math.BigDecimal;

/** 更新累计限额（ST010）输入BO */
public class ST010InputBO {
    /** 限额检查结果 */
    private String limitCheckResult;
    /** 账号（账号即限额检查对象值） */
    private String baseAcctNo;
    /** 限额场景编码 */
    private String limitSceneNo;
    /** 客户号 */
    private String clientNo;
    /** 限额累计金额 */
    private BigDecimal limitSumAmt;
    /** 限额累计笔数 */
    private Integer limitSumNum;

    public String getLimitCheckResult() {
        return limitCheckResult;
    }

    public void setLimitCheckResult(String limitCheckResult) {
        this.limitCheckResult = limitCheckResult;
    }

    public String getBaseAcctNo() {
        return baseAcctNo;
    }

    public void setBaseAcctNo(String baseAcctNo) {
        this.baseAcctNo = baseAcctNo;
    }

    public String getLimitSceneNo() {
        return limitSceneNo;
    }

    public void setLimitSceneNo(String limitSceneNo) {
        this.limitSceneNo = limitSceneNo;
    }

    public String getClientNo() {
        return clientNo;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo;
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
