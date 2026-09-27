package com.dcits.limit.facade.eo;

import com.dcits.limit.enums.AcctInternalKeyType;
import com.dcits.limit.enums.LimitBranchId;
import com.dcits.limit.enums.PeriodType;
import com.dcits.limit.enums.ResAcctRange;
import com.dcits.limit.enums.RestraintLevel;
import com.dcits.limit.enums.RestraintSource;
import com.dcits.limit.enums.RestraintsStatus;
import com.dcits.limit.enums.RestraintType;
import com.dcits.limit.enums.SourceModule;
import com.dcits.limit.enums.ThawDocumentType2;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RbBusRestraintsEO {
    /** 其他解冻执法人证件号码2 */
    private String thawOthDocumentId2;
    /** 解冻执法人1证件类型2 */
    private ThawDocumentType2 thawDocumentType2;
    /** 有权机关名称 */
    private String deductionJudiciaryName;
    /** 最后修改时间戳 */
    @NotNull
    private String lastUpdTimestamp;
    /** 交易机构号 */
    private LimitBranchId tranBranch;
    /** 存期期限 */
    private String term;
    /** 执法人证件类型2 */
    private ThawDocumentType2 judiciaryDocumentType2;
    /** 冻结机关法律文书号 */
    private String resLawNo;
    /** 开始日期 */
    @NotNull
    private java.util.Date startDate;
    /** 交易金额 */
    private BigDecimal tranAmt;
    /** 执法人2证件号码2 */
    private String judiciaryOthDocumentId2;
    /** 抵质押标志 */
    private String collatInd;
    /** 执法人1证件类型 */
    private ThawDocumentType2 judiciaryDocumentType;
    /** 账户内部键值 */
    @NotNull
    private Integer internalKey;
    /** 授权柜员号 */
    private String authUserId;
    /** 交易柜员号 */
    private String userId;
    /** 源模块 */
    private SourceModule sourceModule;
    /** 冻结机关名称 */
    private String restraintJudiciaryName;
    /** 其他解冻执法人证件类型 */
    private ThawDocumentType2 thawOthDocumentType;
    /** 清算日期 */
    private java.util.Date settlementDate;
    /** 其他解冻执法人姓名 */
    private String thawOthOfficerName;
    /** 执法人2证件类型2 */
    private ThawDocumentType2 judiciaryOthDocumentType2;
    /** 解冻机关法律文书号 */
    private String releaseLawNo;
    /** 限制级别 */
    private RestraintLevel restraintLevel;
    /** 限制结束时间 */
    private String restraintEndTime;
    /** 扣划法律文书类型名称 */
    private String deductionLawType;
    /** 周期类型 */
    private PeriodType termType;
    /** 限制编号 */
    @NotNull
    private String resSeqNo;
    /** 是否司法预冻结标志 */
    private String preFrozenFlag;
    /** 执法人2证件号码 */
    private String judiciaryOthDocumentId;
    /** 解冻执法人1证件类型1 */
    private ThawDocumentType2 thawDocumentType;
    /** 渠道流水号 */
    @NotNull
    private String channelSeqNo;
    /** 执法人证件号码2 */
    private String judiciaryDocumentId2;
    /** 账户限制类型 */
    @NotNull
    private RestraintType restraintType;
    /** 交易代码 */
    private String programId;
    /** 限制金额 */
    private BigDecimal pledgedAmt;
    /** 其他解冻执法人证件类型2 */
    private ThawDocumentType2 thawOthDocumentType2;
    /** 执法人1证件号码 */
    private String judiciaryDocumentId;
    /** 执法人1名称 */
    private String judiciaryOfficerName;
    /** 原始业务申请编号 */
    private String originalApplicationId;
    /** 账户内部键类型 */
    private AcctInternalKeyType acctInternalKeyType;
    /** 业务申请编号 */
    private String businessApplicationId;
    /** 限制账户层级 */
    private ResAcctRange resAcctRange;
    /** 其他解冻执法人证件号码 */
    private String thawOthDocumentId;
    /** 解冻执法人1证件号码2 */
    private String thawDocumentId2;
    /** 账号 */
    private String baseAcctNo;
    /** 冲正标志 */
    private String reversalFlag;
    /** 解冻执法人1姓名 */
    private String thawOfficerName;
    /** 解冻机关名称 */
    private String releaseJudiciaryName;
    /** 创建时间戳 */
    @NotNull
    private String createTimestamp;
    /** 限制来源 */
    private RestraintSource restraintSource;
    /** 冻结级别 */
    private String resPriority;
    /** 执法人2名称 */
    private String judiciaryOthOfficerName;
    /** 渠道日期 */
    @NotNull
    private java.util.Date channelDate;
    /** 继续冻结标志 */
    private String continueFreezeFlag;
    /** 解冻执法人1证件号码 */
    private String thawDocumentId;
    /** 执法人2证件类型 */
    private ThawDocumentType2 judiciaryOthDocumentType;
    /** 扣划法律文书号 */
    private String deductionLawNo;
    /** 有权机关冻结标志 */
    private String ahBuFlag;
    /** 轮候冻结序号 */
    private String waitSeq;
    /** 交易参考号 */
    private String reference;
    /** 限制状态 */
    private RestraintsStatus restraintsStatus;
    /** 法人 */
    @NotNull
    private String company;
    /** 交易日期 */
    @NotNull
    private java.util.Date tranDate;
    /** 结束日期 */
    @NotNull
    private java.util.Date endDate;

    public String getThawOthDocumentId2() {
        return thawOthDocumentId2;
    }

    public void setThawOthDocumentId2(String thawOthDocumentId2) {
        this.thawOthDocumentId2 = thawOthDocumentId2;
    }

    public ThawDocumentType2 getThawDocumentType2() {
        return thawDocumentType2;
    }

    public void setThawDocumentType2(ThawDocumentType2 thawDocumentType2) {
        this.thawDocumentType2 = thawDocumentType2;
    }

    public String getDeductionJudiciaryName() {
        return deductionJudiciaryName;
    }

    public void setDeductionJudiciaryName(String deductionJudiciaryName) {
        this.deductionJudiciaryName = deductionJudiciaryName;
    }

    public String getLastUpdTimestamp() {
        return lastUpdTimestamp;
    }

    public void setLastUpdTimestamp(String lastUpdTimestamp) {
        this.lastUpdTimestamp = lastUpdTimestamp;
    }

    public LimitBranchId getTranBranch() {
        return tranBranch;
    }

    public void setTranBranch(LimitBranchId tranBranch) {
        this.tranBranch = tranBranch;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public ThawDocumentType2 getJudiciaryDocumentType2() {
        return judiciaryDocumentType2;
    }

    public void setJudiciaryDocumentType2(ThawDocumentType2 judiciaryDocumentType2) {
        this.judiciaryDocumentType2 = judiciaryDocumentType2;
    }

    public String getResLawNo() {
        return resLawNo;
    }

    public void setResLawNo(String resLawNo) {
        this.resLawNo = resLawNo;
    }

    public java.util.Date getStartDate() {
        return startDate;
    }

    public void setStartDate(java.util.Date startDate) {
        this.startDate = startDate;
    }

    public BigDecimal getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(BigDecimal tranAmt) {
        this.tranAmt = tranAmt;
    }

    public String getJudiciaryOthDocumentId2() {
        return judiciaryOthDocumentId2;
    }

    public void setJudiciaryOthDocumentId2(String judiciaryOthDocumentId2) {
        this.judiciaryOthDocumentId2 = judiciaryOthDocumentId2;
    }

    public String getCollatInd() {
        return collatInd;
    }

    public void setCollatInd(String collatInd) {
        this.collatInd = collatInd;
    }

    public ThawDocumentType2 getJudiciaryDocumentType() {
        return judiciaryDocumentType;
    }

    public void setJudiciaryDocumentType(ThawDocumentType2 judiciaryDocumentType) {
        this.judiciaryDocumentType = judiciaryDocumentType;
    }

    public Integer getInternalKey() {
        return internalKey;
    }

    public void setInternalKey(Integer internalKey) {
        this.internalKey = internalKey;
    }

    public String getAuthUserId() {
        return authUserId;
    }

    public void setAuthUserId(String authUserId) {
        this.authUserId = authUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public SourceModule getSourceModule() {
        return sourceModule;
    }

    public void setSourceModule(SourceModule sourceModule) {
        this.sourceModule = sourceModule;
    }

    public String getRestraintJudiciaryName() {
        return restraintJudiciaryName;
    }

    public void setRestraintJudiciaryName(String restraintJudiciaryName) {
        this.restraintJudiciaryName = restraintJudiciaryName;
    }

    public ThawDocumentType2 getThawOthDocumentType() {
        return thawOthDocumentType;
    }

    public void setThawOthDocumentType(ThawDocumentType2 thawOthDocumentType) {
        this.thawOthDocumentType = thawOthDocumentType;
    }

    public java.util.Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(java.util.Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getThawOthOfficerName() {
        return thawOthOfficerName;
    }

    public void setThawOthOfficerName(String thawOthOfficerName) {
        this.thawOthOfficerName = thawOthOfficerName;
    }

    public ThawDocumentType2 getJudiciaryOthDocumentType2() {
        return judiciaryOthDocumentType2;
    }

    public void setJudiciaryOthDocumentType2(ThawDocumentType2 judiciaryOthDocumentType2) {
        this.judiciaryOthDocumentType2 = judiciaryOthDocumentType2;
    }

    public String getReleaseLawNo() {
        return releaseLawNo;
    }

    public void setReleaseLawNo(String releaseLawNo) {
        this.releaseLawNo = releaseLawNo;
    }

    public RestraintLevel getRestraintLevel() {
        return restraintLevel;
    }

    public void setRestraintLevel(RestraintLevel restraintLevel) {
        this.restraintLevel = restraintLevel;
    }

    public String getRestraintEndTime() {
        return restraintEndTime;
    }

    public void setRestraintEndTime(String restraintEndTime) {
        this.restraintEndTime = restraintEndTime;
    }

    public String getDeductionLawType() {
        return deductionLawType;
    }

    public void setDeductionLawType(String deductionLawType) {
        this.deductionLawType = deductionLawType;
    }

    public PeriodType getTermType() {
        return termType;
    }

    public void setTermType(PeriodType termType) {
        this.termType = termType;
    }

    public String getResSeqNo() {
        return resSeqNo;
    }

    public void setResSeqNo(String resSeqNo) {
        this.resSeqNo = resSeqNo;
    }

    public String getPreFrozenFlag() {
        return preFrozenFlag;
    }

    public void setPreFrozenFlag(String preFrozenFlag) {
        this.preFrozenFlag = preFrozenFlag;
    }

    public String getJudiciaryOthDocumentId() {
        return judiciaryOthDocumentId;
    }

    public void setJudiciaryOthDocumentId(String judiciaryOthDocumentId) {
        this.judiciaryOthDocumentId = judiciaryOthDocumentId;
    }

    public ThawDocumentType2 getThawDocumentType() {
        return thawDocumentType;
    }

    public void setThawDocumentType(ThawDocumentType2 thawDocumentType) {
        this.thawDocumentType = thawDocumentType;
    }

    public String getChannelSeqNo() {
        return channelSeqNo;
    }

    public void setChannelSeqNo(String channelSeqNo) {
        this.channelSeqNo = channelSeqNo;
    }

    public String getJudiciaryDocumentId2() {
        return judiciaryDocumentId2;
    }

    public void setJudiciaryDocumentId2(String judiciaryDocumentId2) {
        this.judiciaryDocumentId2 = judiciaryDocumentId2;
    }

    public RestraintType getRestraintType() {
        return restraintType;
    }

    public void setRestraintType(RestraintType restraintType) {
        this.restraintType = restraintType;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public BigDecimal getPledgedAmt() {
        return pledgedAmt;
    }

    public void setPledgedAmt(BigDecimal pledgedAmt) {
        this.pledgedAmt = pledgedAmt;
    }

    public ThawDocumentType2 getThawOthDocumentType2() {
        return thawOthDocumentType2;
    }

    public void setThawOthDocumentType2(ThawDocumentType2 thawOthDocumentType2) {
        this.thawOthDocumentType2 = thawOthDocumentType2;
    }

    public String getJudiciaryDocumentId() {
        return judiciaryDocumentId;
    }

    public void setJudiciaryDocumentId(String judiciaryDocumentId) {
        this.judiciaryDocumentId = judiciaryDocumentId;
    }

    public String getJudiciaryOfficerName() {
        return judiciaryOfficerName;
    }

    public void setJudiciaryOfficerName(String judiciaryOfficerName) {
        this.judiciaryOfficerName = judiciaryOfficerName;
    }

    public String getOriginalApplicationId() {
        return originalApplicationId;
    }

    public void setOriginalApplicationId(String originalApplicationId) {
        this.originalApplicationId = originalApplicationId;
    }

    public AcctInternalKeyType getAcctInternalKeyType() {
        return acctInternalKeyType;
    }

    public void setAcctInternalKeyType(AcctInternalKeyType acctInternalKeyType) {
        this.acctInternalKeyType = acctInternalKeyType;
    }

    public String getBusinessApplicationId() {
        return businessApplicationId;
    }

    public void setBusinessApplicationId(String businessApplicationId) {
        this.businessApplicationId = businessApplicationId;
    }

    public ResAcctRange getResAcctRange() {
        return resAcctRange;
    }

    public void setResAcctRange(ResAcctRange resAcctRange) {
        this.resAcctRange = resAcctRange;
    }

    public String getThawOthDocumentId() {
        return thawOthDocumentId;
    }

    public void setThawOthDocumentId(String thawOthDocumentId) {
        this.thawOthDocumentId = thawOthDocumentId;
    }

    public String getThawDocumentId2() {
        return thawDocumentId2;
    }

    public void setThawDocumentId2(String thawDocumentId2) {
        this.thawDocumentId2 = thawDocumentId2;
    }

    public String getBaseAcctNo() {
        return baseAcctNo;
    }

    public void setBaseAcctNo(String baseAcctNo) {
        this.baseAcctNo = baseAcctNo;
    }

    public String getReversalFlag() {
        return reversalFlag;
    }

    public void setReversalFlag(String reversalFlag) {
        this.reversalFlag = reversalFlag;
    }

    public String getThawOfficerName() {
        return thawOfficerName;
    }

    public void setThawOfficerName(String thawOfficerName) {
        this.thawOfficerName = thawOfficerName;
    }

    public String getReleaseJudiciaryName() {
        return releaseJudiciaryName;
    }

    public void setReleaseJudiciaryName(String releaseJudiciaryName) {
        this.releaseJudiciaryName = releaseJudiciaryName;
    }

    public String getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(String createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public RestraintSource getRestraintSource() {
        return restraintSource;
    }

    public void setRestraintSource(RestraintSource restraintSource) {
        this.restraintSource = restraintSource;
    }

    public String getResPriority() {
        return resPriority;
    }

    public void setResPriority(String resPriority) {
        this.resPriority = resPriority;
    }

    public String getJudiciaryOthOfficerName() {
        return judiciaryOthOfficerName;
    }

    public void setJudiciaryOthOfficerName(String judiciaryOthOfficerName) {
        this.judiciaryOthOfficerName = judiciaryOthOfficerName;
    }

    public java.util.Date getChannelDate() {
        return channelDate;
    }

    public void setChannelDate(java.util.Date channelDate) {
        this.channelDate = channelDate;
    }

    public String getContinueFreezeFlag() {
        return continueFreezeFlag;
    }

    public void setContinueFreezeFlag(String continueFreezeFlag) {
        this.continueFreezeFlag = continueFreezeFlag;
    }

    public String getThawDocumentId() {
        return thawDocumentId;
    }

    public void setThawDocumentId(String thawDocumentId) {
        this.thawDocumentId = thawDocumentId;
    }

    public ThawDocumentType2 getJudiciaryOthDocumentType() {
        return judiciaryOthDocumentType;
    }

    public void setJudiciaryOthDocumentType(ThawDocumentType2 judiciaryOthDocumentType) {
        this.judiciaryOthDocumentType = judiciaryOthDocumentType;
    }

    public String getDeductionLawNo() {
        return deductionLawNo;
    }

    public void setDeductionLawNo(String deductionLawNo) {
        this.deductionLawNo = deductionLawNo;
    }

    public String getAhBuFlag() {
        return ahBuFlag;
    }

    public void setAhBuFlag(String ahBuFlag) {
        this.ahBuFlag = ahBuFlag;
    }

    public String getWaitSeq() {
        return waitSeq;
    }

    public void setWaitSeq(String waitSeq) {
        this.waitSeq = waitSeq;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public RestraintsStatus getRestraintsStatus() {
        return restraintsStatus;
    }

    public void setRestraintsStatus(RestraintsStatus restraintsStatus) {
        this.restraintsStatus = restraintsStatus;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public java.util.Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(java.util.Date tranDate) {
        this.tranDate = tranDate;
    }

    public java.util.Date getEndDate() {
        return endDate;
    }

    public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
    }
}