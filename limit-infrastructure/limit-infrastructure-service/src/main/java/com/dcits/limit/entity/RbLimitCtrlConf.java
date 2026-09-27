package com.dcits.limit.entity;

import java.math.BigDecimal;
import java.util.Date;

public class RbLimitCtrlConf {
    /** 处理方式 */
    private String dealFlow;
    /** 限额控制结束日期 */
    private Date limitCtrlEndDate;
    /** 限额控制结束时间 */
    private Date limitCtrlEndTime;
    /** 临时限额标志 */
    private String tempLimitFlag;
    /** 控制项类型 */
    private String ctrlItemType;
    /** 限额控制笔数 */
    private Integer limitCtrlNum;
    /** 限额控制开始日期 */
    private Date limitCtrlBgnDate;
    /** 期限类型 */
    private String periodType;
    /** 周期值 */
    private String periodValue;
    /** 限额机构编码 */
    private String limitBranchId;
    /** 限额控制开始时间 */
    private Date limitCtrlBgnTime;
    /** 累计类型 */
    private String sumType;
    /** 创建时间戳 */
    private String createTimestamp;
    /** 限额场景编码 */
    private String limitSceneNo;
    /** 最后修改时间戳 */
    private String lastUpdTimestamp;
    /** 允许自定义标识 */
    private String allowCustomFlag;
    /** 仅检查客户自定义标志 */
    private String onlyCustom;
    /** 限额机构范围 */
    private String limitBranchRange;
    /** 启用标志 */
    private String validFlag;
    /** 自定义限额允许超过阈值标志 */
    private String allowExceedFlag;
    /** 限额控制金额 */
    private BigDecimal limitCtrlAmt;
    /** 临时限额有效期 */
    private String tempLimitValidTerm;

    public String getDealFlow() {
        return dealFlow;
    }

    public void setDealFlow(String dealFlow) {
        this.dealFlow = dealFlow;
    }

    public Date getLimitCtrlEndDate() {
        return limitCtrlEndDate;
    }

    public void setLimitCtrlEndDate(Date limitCtrlEndDate) {
        this.limitCtrlEndDate = limitCtrlEndDate;
    }

    public Date getLimitCtrlEndTime() {
        return limitCtrlEndTime;
    }

    public void setLimitCtrlEndTime(Date limitCtrlEndTime) {
        this.limitCtrlEndTime = limitCtrlEndTime;
    }

    public String getTempLimitFlag() {
        return tempLimitFlag;
    }

    public void setTempLimitFlag(String tempLimitFlag) {
        this.tempLimitFlag = tempLimitFlag;
    }

    public String getCtrlItemType() {
        return ctrlItemType;
    }

    public void setCtrlItemType(String ctrlItemType) {
        this.ctrlItemType = ctrlItemType;
    }

    public Integer getLimitCtrlNum() {
        return limitCtrlNum;
    }

    public void setLimitCtrlNum(Integer limitCtrlNum) {
        this.limitCtrlNum = limitCtrlNum;
    }

    public Date getLimitCtrlBgnDate() {
        return limitCtrlBgnDate;
    }

    public void setLimitCtrlBgnDate(Date limitCtrlBgnDate) {
        this.limitCtrlBgnDate = limitCtrlBgnDate;
    }

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    public String getPeriodValue() {
        return periodValue;
    }

    public void setPeriodValue(String periodValue) {
        this.periodValue = periodValue;
    }

    public String getLimitBranchId() {
        return limitBranchId;
    }

    public void setLimitBranchId(String limitBranchId) {
        this.limitBranchId = limitBranchId;
    }

    public Date getLimitCtrlBgnTime() {
        return limitCtrlBgnTime;
    }

    public void setLimitCtrlBgnTime(Date limitCtrlBgnTime) {
        this.limitCtrlBgnTime = limitCtrlBgnTime;
    }

    public String getSumType() {
        return sumType;
    }

    public void setSumType(String sumType) {
        this.sumType = sumType;
    }

    public String getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(String createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getLimitSceneNo() {
        return limitSceneNo;
    }

    public void setLimitSceneNo(String limitSceneNo) {
        this.limitSceneNo = limitSceneNo;
    }

    public String getLastUpdTimestamp() {
        return lastUpdTimestamp;
    }

    public void setLastUpdTimestamp(String lastUpdTimestamp) {
        this.lastUpdTimestamp = lastUpdTimestamp;
    }

    public String getAllowCustomFlag() {
        return allowCustomFlag;
    }

    public void setAllowCustomFlag(String allowCustomFlag) {
        this.allowCustomFlag = allowCustomFlag;
    }

    public String getOnlyCustom() {
        return onlyCustom;
    }

    public void setOnlyCustom(String onlyCustom) {
        this.onlyCustom = onlyCustom;
    }

    public String getLimitBranchRange() {
        return limitBranchRange;
    }

    public void setLimitBranchRange(String limitBranchRange) {
        this.limitBranchRange = limitBranchRange;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getAllowExceedFlag() {
        return allowExceedFlag;
    }

    public void setAllowExceedFlag(String allowExceedFlag) {
        this.allowExceedFlag = allowExceedFlag;
    }

    public BigDecimal getLimitCtrlAmt() {
        return limitCtrlAmt;
    }

    public void setLimitCtrlAmt(BigDecimal limitCtrlAmt) {
        this.limitCtrlAmt = limitCtrlAmt;
    }

    public String getTempLimitValidTerm() {
        return tempLimitValidTerm;
    }

    public void setTempLimitValidTerm(String tempLimitValidTerm) {
        this.tempLimitValidTerm = tempLimitValidTerm;
    }
}