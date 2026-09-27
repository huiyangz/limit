package com.dcits.limit.facade.bo;

import java.util.Date;

import com.dcits.common.step.StepResult;

/**
 * ST001 获取限额场景编码 输出BO
 */
public class ST001OutputBO extends StepResult {

    /** 限额场景编码（来源：限额控制配置 RB_LIMIT_CTRL_CONF） */
    private String limitSceneNo;
    /** 允许自定义标识（来源：限额控制配置 RB_LIMIT_CTRL_CONF） */
    private String allowCustomFlag;
    /** 仅检查客户自定义标志（来源：限额控制配置 RB_LIMIT_CTRL_CONF） */
    private String onlyCustom;
    /** 临时限额标志（来源：限额控制配置 RB_LIMIT_CTRL_CONF） */
    private String tempLimitFlag;
    /** 临时限额有效期（来源：限额控制配置 RB_LIMIT_CTRL_CONF） */
    private String tempLimitValidTerm;
    /** 限额场景编码（来源：限额控制客户自定义配置 RB_LIMIT_CTRL_CUSTOM_INFO） */
    private String customLimitSceneNo;
    /** 临时限额标志（来源：限额控制客户自定义配置 RB_LIMIT_CTRL_CUSTOM_INFO） */
    private String customTempLimitFlag;
    /** 生效日期（来源：限额控制客户自定义配置 RB_LIMIT_CTRL_CUSTOM_INFO） */
    private Date effectDate;
    /** 失效日期（来源：限额控制客户自定义配置 RB_LIMIT_CTRL_CUSTOM_INFO） */
    private Date expireDate;
    /** 账号（输入回显，来源实体：对公存款账户主表 RB_BUS_ACCT） */
    private String baseAcctNo;
    /** 客户号（输入回显，来源实体：客户副本表 FM_CLIENT_COPY） */
    private String clientNo;

    public String getLimitSceneNo() {
        return limitSceneNo;
    }

    public void setLimitSceneNo(String limitSceneNo) {
        this.limitSceneNo = limitSceneNo;
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

    public String getTempLimitFlag() {
        return tempLimitFlag;
    }

    public void setTempLimitFlag(String tempLimitFlag) {
        this.tempLimitFlag = tempLimitFlag;
    }

    public String getTempLimitValidTerm() {
        return tempLimitValidTerm;
    }

    public void setTempLimitValidTerm(String tempLimitValidTerm) {
        this.tempLimitValidTerm = tempLimitValidTerm;
    }

    public String getCustomLimitSceneNo() {
        return customLimitSceneNo;
    }

    public void setCustomLimitSceneNo(String customLimitSceneNo) {
        this.customLimitSceneNo = customLimitSceneNo;
    }

    public String getCustomTempLimitFlag() {
        return customTempLimitFlag;
    }

    public void setCustomTempLimitFlag(String customTempLimitFlag) {
        this.customTempLimitFlag = customTempLimitFlag;
    }

    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getBaseAcctNo() {
        return baseAcctNo;
    }

    public void setBaseAcctNo(String baseAcctNo) {
        this.baseAcctNo = baseAcctNo;
    }

    public String getClientNo() {
        return clientNo;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo;
    }
}
