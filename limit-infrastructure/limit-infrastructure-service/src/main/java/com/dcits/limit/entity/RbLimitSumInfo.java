package com.dcits.limit.entity;

import java.math.BigDecimal;
import java.util.Date;

public class RbLimitSumInfo {
    /** 限额累计描述 */
    private String limitSumContent;
    /** 生效日期 */
    private Date effectDate;
    /** 限额检查对象值 */
    private String checkObjVal;
    /** 失效日期 */
    private Date expireDate;
    /** 限额累计金额 */
    private BigDecimal limitSumAmt;
    /** 限额累计笔数 */
    private Integer 否;
    /** 客户号 */
    private String clientNo;
    /** 创建时间戳 */
    private String createTimestamp;
    /** 原交易参考号 */
    private String preReference;
    /** 限额场景编码 */
    private String limitSceneNo;
    /** 交易币种 */
    private String tranCcy;
    /** 交易参考号 */
    private String reference;
    /** 最后修改时间戳 */
    private String lastUpdTimestamp;

    public String getLimitSumContent() {
        return limitSumContent;
    }

    public void setLimitSumContent(String limitSumContent) {
        this.limitSumContent = limitSumContent;
    }

    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    public String getCheckObjVal() {
        return checkObjVal;
    }

    public void setCheckObjVal(String checkObjVal) {
        this.checkObjVal = checkObjVal;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
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

    public String getTranCcy() {
        return tranCcy;
    }

    public void setTranCcy(String tranCcy) {
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