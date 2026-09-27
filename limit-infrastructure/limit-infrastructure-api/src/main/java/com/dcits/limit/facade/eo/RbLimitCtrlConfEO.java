package com.dcits.limit.facade.eo;

import com.dcits.limit.enums.CtrlItemType;
import com.dcits.limit.enums.DealFlow;
import com.dcits.limit.enums.LimitBranchId;
import com.dcits.limit.enums.LimitBranchRange;
import com.dcits.limit.enums.PeriodType;
import com.dcits.limit.enums.SumType;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RbLimitCtrlConfEO {
    /** 处理方式 */
    private DealFlow dealFlow;
    /** 限额控制结束日期 */
    private java.util.Date limitCtrlEndDate;
    /** 限额控制结束时间 */
    private java.util.Date limitCtrlEndTime;
    /** 临时限额标志 */
    private String tempLimitFlag;
    /** 控制项类型 */
    private CtrlItemType ctrlItemType;
    /** 限额控制笔数 */
    private Integer limitCtrlNum;
    /** 限额控制开始日期 */
    private java.util.Date limitCtrlBgnDate;
    /** 期限类型 */
    private PeriodType periodType;
    /** 周期值 */
    private String periodValue;
    /** 限额机构编码 */
    @NotNull
    private LimitBranchId limitBranchId;
    /** 限额控制开始时间 */
    private java.util.Date limitCtrlBgnTime;
    /** 累计类型 */
    private SumType sumType;
    /** 创建时间戳 */
    @NotNull
    private String createTimestamp;
    /** 限额场景编码 */
    @NotNull
    private String limitSceneNo;
    /** 最后修改时间戳 */
    @NotNull
    private String lastUpdTimestamp;
    /** 允许自定义标识 */
    @NotNull
    private String allowCustomFlag;
    /** 仅检查客户自定义标志 */
    private String onlyCustom;
    /** 限额机构范围 */
    @NotNull
    private LimitBranchRange limitBranchRange;
    /** 启用标志 */
    private String validFlag;
    /** 自定义限额允许超过阈值标志 */
    private String allowExceedFlag;
    /** 限额控制金额 */
    private BigDecimal limitCtrlAmt;
    /** 临时限额有效期 */
    private String tempLimitValidTerm;

    public DealFlow getDealFlow() {
        return dealFlow;
    }

    public void setDealFlow(DealFlow dealFlow) {
        this.dealFlow = dealFlow;
    }

    public java.util.Date getLimitCtrlEndDate() {
        return limitCtrlEndDate;
    }

    public void setLimitCtrlEndDate(java.util.Date limitCtrlEndDate) {
        this.limitCtrlEndDate = limitCtrlEndDate;
    }

    public java.util.Date getLimitCtrlEndTime() {
        return limitCtrlEndTime;
    }

    public void setLimitCtrlEndTime(java.util.Date limitCtrlEndTime) {
        this.limitCtrlEndTime = limitCtrlEndTime;
    }

    public String getTempLimitFlag() {
        return tempLimitFlag;
    }

    public void setTempLimitFlag(String tempLimitFlag) {
        this.tempLimitFlag = tempLimitFlag;
    }

    public CtrlItemType getCtrlItemType() {
        return ctrlItemType;
    }

    public void setCtrlItemType(CtrlItemType ctrlItemType) {
        this.ctrlItemType = ctrlItemType;
    }

    public Integer getLimitCtrlNum() {
        return limitCtrlNum;
    }

    public void setLimitCtrlNum(Integer limitCtrlNum) {
        this.limitCtrlNum = limitCtrlNum;
    }

    public java.util.Date getLimitCtrlBgnDate() {
        return limitCtrlBgnDate;
    }

    public void setLimitCtrlBgnDate(java.util.Date limitCtrlBgnDate) {
        this.limitCtrlBgnDate = limitCtrlBgnDate;
    }

    public PeriodType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(PeriodType periodType) {
        this.periodType = periodType;
    }

    public String getPeriodValue() {
        return periodValue;
    }

    public void setPeriodValue(String periodValue) {
        this.periodValue = periodValue;
    }

    public LimitBranchId getLimitBranchId() {
        return limitBranchId;
    }

    public void setLimitBranchId(LimitBranchId limitBranchId) {
        this.limitBranchId = limitBranchId;
    }

    public java.util.Date getLimitCtrlBgnTime() {
        return limitCtrlBgnTime;
    }

    public void setLimitCtrlBgnTime(java.util.Date limitCtrlBgnTime) {
        this.limitCtrlBgnTime = limitCtrlBgnTime;
    }

    public SumType getSumType() {
        return sumType;
    }

    public void setSumType(SumType sumType) {
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

    public LimitBranchRange getLimitBranchRange() {
        return limitBranchRange;
    }

    public void setLimitBranchRange(LimitBranchRange limitBranchRange) {
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