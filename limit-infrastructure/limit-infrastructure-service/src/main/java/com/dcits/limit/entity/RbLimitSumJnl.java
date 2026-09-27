package com.dcits.limit.entity;

import java.math.BigDecimal;
import java.util.Date;

public class RbLimitSumJnl {
    /** 原交易参考号 */
    private String preReference;
    /** 交易币种 */
    private String tranCcy;
    /** 交易机构号 */
    private String tranBranch;
    /** 限额检查对象值 */
    private String checkObjVal;
    /** 限额场景编码 */
    private String limitSceneNo;
    /** 交易渠道编号 */
    private String tranChannel;
    /** 启用标志 */
    private String validFlag;
    /** 记录状态 */
    private String recordStatus;
    /** 交易日期 */
    private Date tranDate;
    /** 限额控制信息 */
    private String limitCtrlInfo;
    /** 创建时间戳 */
    private String createTimestamp;
    /** 限额折算金额 */
    private BigDecimal limitConvertAmt;
    /** 客户号 */
    private String clientNo;
    /** 最后修改时间戳 */
    private String lastUpdTimestamp;
    /** 交易参考号 */
    private String reference;
    /** 交易金额 */
    private BigDecimal tranAmt;
    /** 累计类型 */
    private String sumType;

    public String getPreReference() {
        return preReference;
    }

    public void setPreReference(String preReference) {
        this.preReference = preReference;
    }

    public String getTranCcy() {
        return tranCcy;
    }

    public void setTranCcy(String tranCcy) {
        this.tranCcy = tranCcy;
    }

    public String getTranBranch() {
        return tranBranch;
    }

    public void setTranBranch(String tranBranch) {
        this.tranBranch = tranBranch;
    }

    public String getCheckObjVal() {
        return checkObjVal;
    }

    public void setCheckObjVal(String checkObjVal) {
        this.checkObjVal = checkObjVal;
    }

    public String getLimitSceneNo() {
        return limitSceneNo;
    }

    public void setLimitSceneNo(String limitSceneNo) {
        this.limitSceneNo = limitSceneNo;
    }

    public String getTranChannel() {
        return tranChannel;
    }

    public void setTranChannel(String tranChannel) {
        this.tranChannel = tranChannel;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }

    public String getLimitCtrlInfo() {
        return limitCtrlInfo;
    }

    public void setLimitCtrlInfo(String limitCtrlInfo) {
        this.limitCtrlInfo = limitCtrlInfo;
    }

    public String getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(String createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public BigDecimal getLimitConvertAmt() {
        return limitConvertAmt;
    }

    public void setLimitConvertAmt(BigDecimal limitConvertAmt) {
        this.limitConvertAmt = limitConvertAmt;
    }

    public String getClientNo() {
        return clientNo;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo;
    }

    public String getLastUpdTimestamp() {
        return lastUpdTimestamp;
    }

    public void setLastUpdTimestamp(String lastUpdTimestamp) {
        this.lastUpdTimestamp = lastUpdTimestamp;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public BigDecimal getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(BigDecimal tranAmt) {
        this.tranAmt = tranAmt;
    }

    public String getSumType() {
        return sumType;
    }

    public void setSumType(String sumType) {
        this.sumType = sumType;
    }
}