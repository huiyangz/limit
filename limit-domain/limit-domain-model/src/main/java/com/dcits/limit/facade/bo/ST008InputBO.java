package com.dcits.limit.facade.bo;

import java.math.BigDecimal;
import java.util.Date;

/** ST008 登记累计限额 步骤输入 */
public class ST008InputBO {
    /** 限额检查结果 */
    private String checkResult;
    /** 账号 */
    private String baseAcctNo;
    /** 客户号 */
    private String clientNo;
    /** 交易金额 */
    private BigDecimal tranAmt;
    /** 限额场景编码 */
    private String limitSceneNo;
    /** 核心运行日期 */
    private Date runDate;
    /** 限额累计金额 */
    private BigDecimal limitSumAmt;
    /** 限额累计笔数 */
    private Integer 否;

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
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

    public BigDecimal getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(BigDecimal tranAmt) {
        this.tranAmt = tranAmt;
    }

    public String getLimitSceneNo() {
        return limitSceneNo;
    }

    public void setLimitSceneNo(String limitSceneNo) {
        this.limitSceneNo = limitSceneNo;
    }

    public Date getRunDate() {
        return runDate;
    }

    public void setRunDate(Date runDate) {
        this.runDate = runDate;
    }

    public BigDecimal getLimitSumAmt() {
        return limitSumAmt;
    }

    public void setLimitSumAmt(BigDecimal limitSumAmt) {
        this.limitSumAmt = limitSumAmt;
    }

    public Integer get否() {
        return 否;
    }

    public void set否(Integer 否) {
        this.否 = 否;
    }
}
