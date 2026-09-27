package com.dcits.limit.facade.bo;

import java.math.BigDecimal;

/** ST003 计算限额累计金额 步骤输入 */
public class ST003InputBO {
    /** 限额场景编码 */
    private String limitSceneNo;
    /** 交易金额 */
    private BigDecimal tranAmt;
    /** 限额检查对象值 */
    private String checkObjVal;
    /** 客户号 */
    private String clientNo;

    public String getLimitSceneNo() {
        return limitSceneNo;
    }

    public void setLimitSceneNo(String limitSceneNo) {
        this.limitSceneNo = limitSceneNo;
    }

    public BigDecimal getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(BigDecimal tranAmt) {
        this.tranAmt = tranAmt;
    }

    public String getCheckObjVal() {
        return checkObjVal;
    }

    public void setCheckObjVal(String checkObjVal) {
        this.checkObjVal = checkObjVal;
    }

    public String getClientNo() {
        return clientNo;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo;
    }
}
