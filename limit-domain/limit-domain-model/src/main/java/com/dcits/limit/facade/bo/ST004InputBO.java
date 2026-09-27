package com.dcits.limit.facade.bo;

import java.math.BigDecimal;

/**
 * ST004 检查限额 输入BO
 */
public class ST004InputBO {
    /** 限额场景编码 */
    private String limitSceneNo;
    /** 限额累计金额 */
    private BigDecimal limitSumAmt;
    /** 限额累计笔数 */
    private Integer limitSumNum;
    /** 限额机构编码 */
    private String limitBranchId;

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

    public String getLimitBranchId() {
        return limitBranchId;
    }

    public void setLimitBranchId(String limitBranchId) {
        this.limitBranchId = limitBranchId;
    }
}
