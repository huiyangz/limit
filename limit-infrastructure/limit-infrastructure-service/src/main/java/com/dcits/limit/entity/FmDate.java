package com.dcits.limit.entity;

import java.util.Date;

public class FmDate {
    /** 半年末日期 */
    private Date halfEndDate;
    /** 季末日期 */
    private Date qurEndDate;
    /** 账户日终阶段标志 */
    private String acctEodFlag;
    /** 本月月末日期 */
    private Date mthEndDate;
    /** 下一运行日期 */
    private Date nextRunDate;
    /** 法人 */
    private String company;
    /** 上一运行日期 */
    private Date lastRunDate;
    /** 最后修改时间戳 */
    private String lastUpdTimestamp;
    /** 核心运行日期 */
    private Date runDate;
    /** 创建时间戳 */
    private String createTimestamp;
    /** 本年年末日期 */
    private Date yrEndDate;
    /** 联机批量挂起标志 */
    private String suspendFlag;
    /** 系统所处的阶段 */
    private String systemPhase;

    public Date getHalfEndDate() {
        return halfEndDate;
    }

    public void setHalfEndDate(Date halfEndDate) {
        this.halfEndDate = halfEndDate;
    }

    public Date getQurEndDate() {
        return qurEndDate;
    }

    public void setQurEndDate(Date qurEndDate) {
        this.qurEndDate = qurEndDate;
    }

    public String getAcctEodFlag() {
        return acctEodFlag;
    }

    public void setAcctEodFlag(String acctEodFlag) {
        this.acctEodFlag = acctEodFlag;
    }

    public Date getMthEndDate() {
        return mthEndDate;
    }

    public void setMthEndDate(Date mthEndDate) {
        this.mthEndDate = mthEndDate;
    }

    public Date getNextRunDate() {
        return nextRunDate;
    }

    public void setNextRunDate(Date nextRunDate) {
        this.nextRunDate = nextRunDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getLastRunDate() {
        return lastRunDate;
    }

    public void setLastRunDate(Date lastRunDate) {
        this.lastRunDate = lastRunDate;
    }

    public String getLastUpdTimestamp() {
        return lastUpdTimestamp;
    }

    public void setLastUpdTimestamp(String lastUpdTimestamp) {
        this.lastUpdTimestamp = lastUpdTimestamp;
    }

    public Date getRunDate() {
        return runDate;
    }

    public void setRunDate(Date runDate) {
        this.runDate = runDate;
    }

    public String getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(String createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Date getYrEndDate() {
        return yrEndDate;
    }

    public void setYrEndDate(Date yrEndDate) {
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