package com.dcits.limit.facade.bo;

import com.dcits.common.step.StepResult;
import java.math.BigDecimal;
import java.util.Date;

/** ST008 登记累计限额 步骤输出，业务字段来源为登记后的限额累计信息表（RB_LIMIT_SUM_INFO）记录 */
public class ST008OutputBO extends StepResult {
    /** 限额场景编码 */
    private String limitSceneNo;
    /** 限额检查对象值 */
    private String checkObjVal;
    /** 限额累计金额 */
    private BigDecimal limitSumAmt;
    /** 限额累计笔数 */
    private Integer 否;
    /** 生效日期 */
    private Date effectDate;
    /** 失效日期 */
    private Date expireDate;

    public String getLimitSceneNo() {
        return limitSceneNo;
    }

    public void setLimitSceneNo(String limitSceneNo) {
        this.limitSceneNo = limitSceneNo;
    }

    public String getCheckObjVal() {
        return checkObjVal;
    }

    public void setCheckObjVal(String checkObjVal) {
        this.checkObjVal = checkObjVal;
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
}
