package com.dcits.limit.facade.bo;

import java.util.Date;
import com.dcits.common.step.StepResult;

/**
 * ST009 检查限额场景配置是否有效 输出BO
 */
public class ST009OutputBO extends StepResult {
    /** 限额场景编码 */
    private String limitSceneNo;
    /** 限额控制开始日期 */
    private Date limitCtrlBgnDate;
    /** 限额控制结束日期 */
    private Date limitCtrlEndDate;
    /** 限额控制开始时间 */
    private Date limitCtrlBgnTime;
    /** 限额控制结束时间 */
    private Date limitCtrlEndTime;

    public String getLimitSceneNo() {
        return limitSceneNo;
    }

    public void setLimitSceneNo(String limitSceneNo) {
        this.limitSceneNo = limitSceneNo;
    }

    public Date getLimitCtrlBgnDate() {
        return limitCtrlBgnDate;
    }

    public void setLimitCtrlBgnDate(Date limitCtrlBgnDate) {
        this.limitCtrlBgnDate = limitCtrlBgnDate;
    }

    public Date getLimitCtrlEndDate() {
        return limitCtrlEndDate;
    }

    public void setLimitCtrlEndDate(Date limitCtrlEndDate) {
        this.limitCtrlEndDate = limitCtrlEndDate;
    }

    public Date getLimitCtrlBgnTime() {
        return limitCtrlBgnTime;
    }

    public void setLimitCtrlBgnTime(Date limitCtrlBgnTime) {
        this.limitCtrlBgnTime = limitCtrlBgnTime;
    }

    public Date getLimitCtrlEndTime() {
        return limitCtrlEndTime;
    }

    public void setLimitCtrlEndTime(Date limitCtrlEndTime) {
        this.limitCtrlEndTime = limitCtrlEndTime;
    }
}
