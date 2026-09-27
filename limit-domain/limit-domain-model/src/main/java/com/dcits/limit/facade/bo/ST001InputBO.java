package com.dcits.limit.facade.bo;

import java.util.Date;

/**
 * ST001 获取限额场景编码 输入BO
 */
public class ST001InputBO {

    /** 账号 */
    private String baseAcctNo;
    /** 客户号 */
    private String clientNo;
    /** 限额场景编码 */
    private String limitSceneNo;
    /** 交易日期 */
    private Date tranDate;

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

    public String getLimitSceneNo() {
        return limitSceneNo;
    }

    public void setLimitSceneNo(String limitSceneNo) {
        this.limitSceneNo = limitSceneNo;
    }

    public Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }
}
