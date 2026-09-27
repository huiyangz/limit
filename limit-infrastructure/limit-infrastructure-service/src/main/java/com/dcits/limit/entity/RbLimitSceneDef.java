package com.dcits.limit.entity;

public class RbLimitSceneDef {
    /** 限制币种 */
    private String limitCcy;
    /** 检查对象类型 */
    private String checkObjType;
    /** 最后修改时间戳 */
    private String lastUpdTimestamp;
    /** 限额场景描述 */
    private String limitSceneDesc;
    /** 创建时间戳 */
    private String createTimestamp;
    /** 启用标志 */
    private String validFlag;
    /** 限额折算方式 */
    private String limitConvert;
    /** 限额大类 */
    private String limitMainType;
    /** 限额场景编码 */
    private String limitSceneNo;

    public String getLimitCcy() {
        return limitCcy;
    }

    public void setLimitCcy(String limitCcy) {
        this.limitCcy = limitCcy;
    }

    public String getCheckObjType() {
        return checkObjType;
    }

    public void setCheckObjType(String checkObjType) {
        this.checkObjType = checkObjType;
    }

    public String getLastUpdTimestamp() {
        return lastUpdTimestamp;
    }

    public void setLastUpdTimestamp(String lastUpdTimestamp) {
        this.lastUpdTimestamp = lastUpdTimestamp;
    }

    public String getLimitSceneDesc() {
        return limitSceneDesc;
    }

    public void setLimitSceneDesc(String limitSceneDesc) {
        this.limitSceneDesc = limitSceneDesc;
    }

    public String getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(String createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getLimitConvert() {
        return limitConvert;
    }

    public void setLimitConvert(String limitConvert) {
        this.limitConvert = limitConvert;
    }

    public String getLimitMainType() {
        return limitMainType;
    }

    public void setLimitMainType(String limitMainType) {
        this.limitMainType = limitMainType;
    }

    public String getLimitSceneNo() {
        return limitSceneNo;
    }

    public void setLimitSceneNo(String limitSceneNo) {
        this.limitSceneNo = limitSceneNo;
    }
}