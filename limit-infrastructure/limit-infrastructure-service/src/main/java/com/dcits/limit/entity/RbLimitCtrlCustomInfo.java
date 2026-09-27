package com.dcits.limit.entity;

import java.math.BigDecimal;
import java.util.Date;

public class RbLimitCtrlCustomInfo {
    /** 限额检查对象值 */
    private String checkObjVal;
    /** 限额控制金额 */
    private BigDecimal limitCtrlAmt;
    /** 创建时间戳 */
    private String createTimestamp;
    /** 生效日期 */
    private Date effectDate;
    /** 最后修改时间戳 */
    private String lastUpdTimestamp;
    /** 限额场景编码 */
    private String limitSceneNo;
    /** 限额控制笔数 */
    private Integer limitCtrlNum;
    /** 临时限额标志 */
    private String tempLimitFlag;
    /** 控制项类型 */
    private String ctrlItemType;
    /** 客户号 */
    private String clientNo;
    /** 失效日期 */
    private Date expireDate;

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

    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Date effectDate) {
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

    public String getCtrlItemType() {
        return ctrlItemType;
    }

    public void setCtrlItemType(String ctrlItemType) {
        this.ctrlItemType = ctrlItemType;
    }

    public String getClientNo() {
        return clientNo;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}