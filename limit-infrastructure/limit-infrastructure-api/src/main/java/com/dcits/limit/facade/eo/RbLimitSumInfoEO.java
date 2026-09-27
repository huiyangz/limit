package com.dcits.limit.facade.eo;

import com.dcits.limit.enums.TranCcy;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RbLimitSumInfoEO {
    /** 限额累计描述 */
    private String limitSumContent;
    /** 生效日期 */
    private java.util.Date effectDate;
    /** 限额检查对象值 */
    @NotNull
    private String checkObjVal;
    /** 失效日期 */
    private java.util.Date expireDate;
    /** 限额累计金额 */
    private BigDecimal limitSumAmt;
    /** 限额累计笔数 */
    private Integer 否;
    /** 客户号 */
    @NotNull
    private String clientNo;
    /** 创建时间戳 */
    @NotNull
    private String createTimestamp;
    /** 原交易参考号 */
    private String preReference;
    /** 限额场景编码 */
    @NotNull
    private String limitSceneNo;
    /** 交易币种 */
    private TranCcy tranCcy;
    /** 交易参考号 */
    @NotNull
    private String reference;
    /** 最后修改时间戳 */
    @NotNull
    private String lastUpdTimestamp;

    public String getLimitSumContent() {
        return limitSumContent;
    }

    public void setLimitSumContent(String limitSumContent) {
        this.limitSumContent = limitSumContent;
    }

    public java.util.Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(java.util.Date effectDate) {
        this.effectDate = effectDate;
    }

    public String getCheckObjVal() {
        return checkObjVal;
    }

    public void setCheckObjVal(String checkObjVal) {
        this.checkObjVal = checkObjVal;
    }

    public java.util.Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(java.util.Date expireDate) {
        this.expireDate = expireDate;
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

    public String getClientNo() {
        return clientNo;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo;
    }

    public String getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(String createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getPreReference() {
        return preReference;
    }

    public void setPreReference(String preReference) {
        this.preReference = preReference;
    }

    public String getLimitSceneNo() {
        return limitSceneNo;
    }

    public void setLimitSceneNo(String limitSceneNo) {
        this.limitSceneNo = limitSceneNo;
    }

    public TranCcy getTranCcy() {
        return tranCcy;
    }

    public void setTranCcy(TranCcy tranCcy) {
        this.tranCcy = tranCcy;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getLastUpdTimestamp() {
        return lastUpdTimestamp;
    }

    public void setLastUpdTimestamp(String lastUpdTimestamp) {
        this.lastUpdTimestamp = lastUpdTimestamp;
    }
}