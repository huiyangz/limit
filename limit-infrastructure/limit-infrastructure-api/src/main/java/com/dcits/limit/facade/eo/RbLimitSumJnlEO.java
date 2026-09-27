package com.dcits.limit.facade.eo;

import com.dcits.limit.enums.LimitBranchId;
import com.dcits.limit.enums.RecordStatus;
import com.dcits.limit.enums.SourceType;
import com.dcits.limit.enums.SumType;
import com.dcits.limit.enums.TranCcy;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RbLimitSumJnlEO {
    /** 原交易参考号 */
    private String preReference;
    /** 交易币种 */
    private TranCcy tranCcy;
    /** 交易机构号 */
    private LimitBranchId tranBranch;
    /** 限额检查对象值 */
    @NotNull
    private String checkObjVal;
    /** 限额场景编码 */
    @NotNull
    private String limitSceneNo;
    /** 交易渠道编号 */
    private SourceType tranChannel;
    /** 启用标志 */
    private String validFlag;
    /** 记录状态 */
    @NotNull
    private RecordStatus recordStatus;
    /** 交易日期 */
    private java.util.Date tranDate;
    /** 限额控制信息 */
    private String limitCtrlInfo;
    /** 创建时间戳 */
    @NotNull
    private String createTimestamp;
    /** 限额折算金额 */
    private BigDecimal limitConvertAmt;
    /** 客户号 */
    @NotNull
    private String clientNo;
    /** 最后修改时间戳 */
    @NotNull
    private String lastUpdTimestamp;
    /** 交易参考号 */
    @NotNull
    private String reference;
    /** 交易金额 */
    private BigDecimal tranAmt;
    /** 累计类型 */
    private SumType sumType;

    public String getPreReference() {
        return preReference;
    }

    public void setPreReference(String preReference) {
        this.preReference = preReference;
    }

    public TranCcy getTranCcy() {
        return tranCcy;
    }

    public void setTranCcy(TranCcy tranCcy) {
        this.tranCcy = tranCcy;
    }

    public LimitBranchId getTranBranch() {
        return tranBranch;
    }

    public void setTranBranch(LimitBranchId tranBranch) {
        this.tranBranch = tranBranch;
    }

    public String getCheckObjVal() {
        return checkObjVal;
    }

    public void setCheckObjVal(String checkObjVal) {
        this.checkObjVal = checkObjVal;
    }

    public String getLimitSceneNo() {
        return limitSceneNo;
    }

    public void setLimitSceneNo(String limitSceneNo) {
        this.limitSceneNo = limitSceneNo;
    }

    public SourceType getTranChannel() {
        return tranChannel;
    }

    public void setTranChannel(SourceType tranChannel) {
        this.tranChannel = tranChannel;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public RecordStatus getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }

    public java.util.Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(java.util.Date tranDate) {
        this.tranDate = tranDate;
    }

    public String getLimitCtrlInfo() {
        return limitCtrlInfo;
    }

    public void setLimitCtrlInfo(String limitCtrlInfo) {
        this.limitCtrlInfo = limitCtrlInfo;
    }

    public String getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(String createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public BigDecimal getLimitConvertAmt() {
        return limitConvertAmt;
    }

    public void setLimitConvertAmt(BigDecimal limitConvertAmt) {
        this.limitConvertAmt = limitConvertAmt;
    }

    public String getClientNo() {
        return clientNo;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo;
    }

    public String getLastUpdTimestamp() {
        return lastUpdTimestamp;
    }

    public void setLastUpdTimestamp(String lastUpdTimestamp) {
        this.lastUpdTimestamp = lastUpdTimestamp;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public BigDecimal getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(BigDecimal tranAmt) {
        this.tranAmt = tranAmt;
    }

    public SumType getSumType() {
        return sumType;
    }

    public void setSumType(SumType sumType) {
        this.sumType = sumType;
    }
}