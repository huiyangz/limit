package com.dcits.limit.facade.eo;

import com.dcits.limit.enums.CheckObjType;
import com.dcits.limit.enums.LimitConvert;
import com.dcits.limit.enums.LimitMainType;
import com.dcits.limit.enums.TranCcy;
import jakarta.validation.constraints.NotNull;

public class RbLimitSceneDefEO {
    /** 限制币种 */
    private TranCcy limitCcy;
    /** 检查对象类型 */
    private CheckObjType checkObjType;
    /** 最后修改时间戳 */
    @NotNull
    private String lastUpdTimestamp;
    /** 限额场景描述 */
    private String limitSceneDesc;
    /** 创建时间戳 */
    @NotNull
    private String createTimestamp;
    /** 启用标志 */
    private String validFlag;
    /** 限额折算方式 */
    private LimitConvert limitConvert;
    /** 限额大类 */
    private LimitMainType limitMainType;
    /** 限额场景编码 */
    @NotNull
    private String limitSceneNo;

    public TranCcy getLimitCcy() {
        return limitCcy;
    }

    public void setLimitCcy(TranCcy limitCcy) {
        this.limitCcy = limitCcy;
    }

    public CheckObjType getCheckObjType() {
        return checkObjType;
    }

    public void setCheckObjType(CheckObjType checkObjType) {
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

    public LimitConvert getLimitConvert() {
        return limitConvert;
    }

    public void setLimitConvert(LimitConvert limitConvert) {
        this.limitConvert = limitConvert;
    }

    public LimitMainType getLimitMainType() {
        return limitMainType;
    }

    public void setLimitMainType(LimitMainType limitMainType) {
        this.limitMainType = limitMainType;
    }

    public String getLimitSceneNo() {
        return limitSceneNo;
    }

    public void setLimitSceneNo(String limitSceneNo) {
        this.limitSceneNo = limitSceneNo;
    }
}