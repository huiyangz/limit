package com.dcits.limit.facade.bo;

/**
 * ST006 获取累计限额 输入BO
 */
public class ST006InputBO {
    /** 账号 */
    private String baseAcctNo;
    /** 客户号 */
    private String clientNo;
    /** 限额场景编码 */
    private String limitSceneNo;

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
}
