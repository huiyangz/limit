package com.dcits.limit.facade.eo;

import jakarta.validation.constraints.NotNull;

public class FmDateEO {
    /** 半年末日期 */
    @NotNull
    private java.util.Date halfEndDate;
    /** 季末日期 */
    @NotNull
    private java.util.Date qurEndDate;
    /** 账户日终阶段标志 */
    @NotNull
    private String acctEodFlag;
    /** 本月月末日期 */
    @NotNull
    private java.util.Date mthEndDate;
    /** 下一运行日期 */
    @NotNull
    private java.util.Date nextRunDate;
    /** 法人 */
    @NotNull
    private String company;
    /** 上一运行日期 */
    @NotNull
    private java.util.Date lastRunDate;
    /** 最后修改时间戳 */
    @NotNull
    private String lastUpdTimestamp;
    /** 核心运行日期 */
    @NotNull
    private java.util.Date runDate;
    /** 创建时间戳 */
    @NotNull
    private String createTimestamp;
    /** 本年年末日期 */
    @NotNull
    private java.util.Date yrEndDate;
    /** 联机批量挂起标志 */
    private String suspendFlag;
    /** 系统所处的阶段 */
    @NotNull
    private String systemPhase;

    public java.util.Date getHalfEndDate() {
        return halfEndDate;
    }

    public void setHalfEndDate(java.util.Date halfEndDate) {
        this.halfEndDate = halfEndDate;
    }

    public java.util.Date getQurEndDate() {
        return qurEndDate;
    }

    public void setQurEndDate(java.util.Date qurEndDate) {
        this.qurEndDate = qurEndDate;
    }

    public String getAcctEodFlag() {
        return acctEodFlag;
    }

    public void setAcctEodFlag(String acctEodFlag) {
        this.acctEodFlag = acctEodFlag;
    }

    public java.util.Date getMthEndDate() {
        return mthEndDate;
    }

    public void setMthEndDate(java.util.Date mthEndDate) {
        this.mthEndDate = mthEndDate;
    }

    public java.util.Date getNextRunDate() {
        return nextRunDate;
    }

    public void setNextRunDate(java.util.Date nextRunDate) {
        this.nextRunDate = nextRunDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public java.util.Date getLastRunDate() {
        return lastRunDate;
    }

    public void setLastRunDate(java.util.Date lastRunDate) {
        this.lastRunDate = lastRunDate;
    }

    public String getLastUpdTimestamp() {
        return lastUpdTimestamp;
    }

    public void setLastUpdTimestamp(String lastUpdTimestamp) {
        this.lastUpdTimestamp = lastUpdTimestamp;
    }

    public java.util.Date getRunDate() {
        return runDate;
    }

    public void setRunDate(java.util.Date runDate) {
        this.runDate = runDate;
    }

    public String getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(String createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public java.util.Date getYrEndDate() {
        return yrEndDate;
    }

    public void setYrEndDate(java.util.Date yrEndDate) {
        this.yrEndDate = yrEndDate;
    }

    public String getSuspendFlag() {
        return suspendFlag;
    }

    public void setSuspendFlag(String suspendFlag) {
        this.suspendFlag = suspendFlag;
    }

    public String getSystemPhase() {
        return systemPhase;
    }

    public void setSystemPhase(String systemPhase) {
        this.systemPhase = systemPhase;
    }
}