package com.dcits.limit.facade.eo;

import com.dcits.limit.enums.CtrlItemType;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RbLimitCtrlCustomInfoEO {
    /** 限额检查对象值 */
    @NotNull
    private String checkObjVal;
    /** 限额控制金额 */
    private BigDecimal limitCtrlAmt;
    /** 创建时间戳 */
    @NotNull
    private String createTimestamp;
    /** 生效日期 */
    private java.util.Date effectDate;
    /** 最后修改时间戳 */
    @NotNull
    private String lastUpdTimestamp;
    /** 限额场景编码 */
    @NotNull
    private String limitSceneNo;
    /** 限额控制笔数 */
    private Integer limitCtrlNum;
    /** 临时限额标志 */
    private String tempLimitFlag;
    /** 控制项类型 */
    private CtrlItemType ctrlItemType;
    /** 客户号 */
    @NotNull
    private String clientNo;
    /** 失效日期 */
    private java.util.Date expireDate;

    public String getCheckObjVal() {
        return checkObjVal;
    }

    public void setCheckObjVal(String checkObjVal) {
        this.checkObjVal = checkObjVal;
    }

    public BigDecimal getLimitCtrlAmt() {
        return limitCtrlAmt;
    }

    public void setLimitCtrlAmt(BigDecimal limitCtrlAmt) {
        this.limitCtrlAmt = limitCtrlAmt;
    }

    public String getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(String createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public java.util.Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(java.util.Date effectDate) {
        this.effectDate = effectDate;
    }

    public String getLastUpdTimestamp() {
        return lastUpdTimestamp;
    }

    public void setLastUpdTimestamp(String lastUpdTimestamp) {
        this.lastUpdTimestamp = lastUpdTimestamp;
    }

    public String getLimitSceneNo() {
        return limitSceneNo;
    }

    public void setLimitSceneNo(String limitSceneNo) {
        this.limitSceneNo = limitSceneNo;
    }

    public Integer getLimitCtrlNum() {
        return limitCtrlNum;
    }

    public void setLimitCtrlNum(Integer limitCtrlNum) {
        this.limitCtrlNum = limitCtrlNum;
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

    public String getClientNo() {
        return clientNo;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo;
    }

    public java.util.Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(java.util.Date expireDate) {
        this.expireDate = expireDate;
    }
}