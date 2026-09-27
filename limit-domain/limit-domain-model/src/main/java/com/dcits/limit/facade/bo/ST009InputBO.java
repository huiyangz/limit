package com.dcits.limit.facade.bo;

import java.util.Date;

/**
 * ST009 检查限额场景配置是否有效 输入BO
 */
public class ST009InputBO {
    /** 交易日期 */
    private Date tranDate;
    /** 交易时间（格式HHmmss） */
    private String tranTimestamp;
    /** 限额机构编码 */
    private String limitBranchId;
    /** 限额场景编码 */
    private String limitSceneNo;

    public Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }

    public String getTranTimestamp() {
        return tranTimestamp;
    }

    public void setTranTimestamp(String tranTimestamp) {
        this.tranTimestamp = tranTimestamp;
    }

    public String getLimitBranchId() {
        return limitBranchId;
    }

    public void setLimitBranchId(String limitBranchId) {
        this.limitBranchId = limitBranchId;
    }

    public String getLimitSceneNo() {
        return limitSceneNo;
    }

    public void setLimitSceneNo(String limitSceneNo) {
        this.limitSceneNo = limitSceneNo;
    }
}
