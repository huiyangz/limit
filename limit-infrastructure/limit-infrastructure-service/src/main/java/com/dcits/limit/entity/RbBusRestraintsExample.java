package com.dcits.limit.entity;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.util.Date;

public class RbBusRestraintsExample {
    protected String orderByClause;
    protected boolean distinct;
    protected List<Criteria> oredCriteria;

    public RbBusRestraintsExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.isEmpty()) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        return new Criteria();
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return !criteria.isEmpty();
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andThawOthDocumentId2EqualTo(String value) {
            addCriterion("THAW_OTH_DOCUMENT_ID2 =", value, "thawOthDocumentId2");
            return (Criteria) this;
        }

        public Criteria andThawDocumentType2EqualTo(String value) {
            addCriterion("THAW_DOCUMENT_TYPE2 =", value, "thawDocumentType2");
            return (Criteria) this;
        }

        public Criteria andDeductionJudiciaryNameEqualTo(String value) {
            addCriterion("DEDUCTION_JUDICIARY_NAME =", value, "deductionJudiciaryName");
            return (Criteria) this;
        }

        public Criteria andLastUpdTimestampEqualTo(String value) {
            addCriterion("LAST_UPD_TIMESTAMP =", value, "lastUpdTimestamp");
            return (Criteria) this;
        }

        public Criteria andTranBranchEqualTo(String value) {
            addCriterion("TRAN_BRANCH =", value, "tranBranch");
            return (Criteria) this;
        }

        public Criteria andTermEqualTo(String value) {
            addCriterion("TERM =", value, "term");
            return (Criteria) this;
        }

        public Criteria andJudiciaryDocumentType2EqualTo(String value) {
            addCriterion("JUDICIARY_DOCUMENT_TYPE2 =", value, "judiciaryDocumentType2");
            return (Criteria) this;
        }

        public Criteria andResLawNoEqualTo(String value) {
            addCriterion("RES_LAW_NO =", value, "resLawNo");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(Date value) {
            addCriterion("START_DATE =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andTranAmtEqualTo(BigDecimal value) {
            addCriterion("TRAN_AMT =", value, "tranAmt");
            return (Criteria) this;
        }

        public Criteria andJudiciaryOthDocumentId2EqualTo(String value) {
            addCriterion("JUDICIARY_OTH_DOCUMENT_ID2 =", value, "judiciaryOthDocumentId2");
            return (Criteria) this;
        }

        public Criteria andCollatIndEqualTo(String value) {
            addCriterion("COLLAT_IND =", value, "collatInd");
            return (Criteria) this;
        }

        public Criteria andJudiciaryDocumentTypeEqualTo(String value) {
            addCriterion("JUDICIARY_DOCUMENT_TYPE =", value, "judiciaryDocumentType");
            return (Criteria) this;
        }

        public Criteria andInternalKeyEqualTo(Integer value) {
            addCriterion("INTERNAL_KEY =", value, "internalKey");
            return (Criteria) this;
        }

        public Criteria andAuthUserIdEqualTo(String value) {
            addCriterion("AUTH_USER_ID =", value, "authUserId");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andSourceModuleEqualTo(String value) {
            addCriterion("SOURCE_MODULE =", value, "sourceModule");
            return (Criteria) this;
        }

        public Criteria andRestraintJudiciaryNameEqualTo(String value) {
            addCriterion("RESTRAINT_JUDICIARY_NAME =", value, "restraintJudiciaryName");
            return (Criteria) this;
        }

        public Criteria andThawOthDocumentTypeEqualTo(String value) {
            addCriterion("THAW_OTH_DOCUMENT_TYPE =", value, "thawOthDocumentType");
            return (Criteria) this;
        }

        public Criteria andSettlementDateEqualTo(Date value) {
            addCriterion("SETTLEMENT_DATE =", value, "settlementDate");
            return (Criteria) this;
        }

        public Criteria andThawOthOfficerNameEqualTo(String value) {
            addCriterion("THAW_OTH_OFFICER_NAME =", value, "thawOthOfficerName");
            return (Criteria) this;
        }

        public Criteria andJudiciaryOthDocumentType2EqualTo(String value) {
            addCriterion("JUDICIARY_OTH_DOCUMENT_TYPE2 =", value, "judiciaryOthDocumentType2");
            return (Criteria) this;
        }

        public Criteria andReleaseLawNoEqualTo(String value) {
            addCriterion("RELEASE_LAW_NO =", value, "releaseLawNo");
            return (Criteria) this;
        }

        public Criteria andRestraintLevelEqualTo(String value) {
            addCriterion("RESTRAINT_LEVEL =", value, "restraintLevel");
            return (Criteria) this;
        }

        public Criteria andRestraintEndTimeEqualTo(String value) {
            addCriterion("RESTRAINT_END_TIME =", value, "restraintEndTime");
            return (Criteria) this;
        }

        public Criteria andDeductionLawTypeEqualTo(String value) {
            addCriterion("DEDUCTION_LAW_TYPE =", value, "deductionLawType");
            return (Criteria) this;
        }

        public Criteria andTermTypeEqualTo(String value) {
            addCriterion("TERM_TYPE =", value, "termType");
            return (Criteria) this;
        }

        public Criteria andResSeqNoEqualTo(String value) {
            addCriterion("RES_SEQ_NO =", value, "resSeqNo");
            return (Criteria) this;
        }

        public Criteria andPreFrozenFlagEqualTo(String value) {
            addCriterion("PRE_FROZEN_FLAG =", value, "preFrozenFlag");
            return (Criteria) this;
        }

        public Criteria andJudiciaryOthDocumentIdEqualTo(String value) {
            addCriterion("JUDICIARY_OTH_DOCUMENT_ID =", value, "judiciaryOthDocumentId");
            return (Criteria) this;
        }

        public Criteria andThawDocumentTypeEqualTo(String value) {
            addCriterion("THAW_DOCUMENT_TYPE =", value, "thawDocumentType");
            return (Criteria) this;
        }

        public Criteria andChannelSeqNoEqualTo(String value) {
            addCriterion("CHANNEL_SEQ_NO =", value, "channelSeqNo");
            return (Criteria) this;
        }

        public Criteria andJudiciaryDocumentId2EqualTo(String value) {
            addCriterion("JUDICIARY_DOCUMENT_ID2 =", value, "judiciaryDocumentId2");
            return (Criteria) this;
        }

        public Criteria andRestraintTypeEqualTo(String value) {
            addCriterion("RESTRAINT_TYPE =", value, "restraintType");
            return (Criteria) this;
        }

        public Criteria andProgramIdEqualTo(String value) {
            addCriterion("PROGRAM_ID =", value, "programId");
            return (Criteria) this;
        }

        public Criteria andPledgedAmtEqualTo(BigDecimal value) {
            addCriterion("PLEDGED_AMT =", value, "pledgedAmt");
            return (Criteria) this;
        }

        public Criteria andThawOthDocumentType2EqualTo(String value) {
            addCriterion("THAW_OTH_DOCUMENT_TYPE2 =", value, "thawOthDocumentType2");
            return (Criteria) this;
        }

        public Criteria andJudiciaryDocumentIdEqualTo(String value) {
            addCriterion("JUDICIARY_DOCUMENT_ID =", value, "judiciaryDocumentId");
            return (Criteria) this;
        }

        public Criteria andJudiciaryOfficerNameEqualTo(String value) {
            addCriterion("JUDICIARY_OFFICER_NAME =", value, "judiciaryOfficerName");
            return (Criteria) this;
        }

        public Criteria andOriginalApplicationIdEqualTo(String value) {
            addCriterion("ORIGINAL_APPLICATION_ID =", value, "originalApplicationId");
            return (Criteria) this;
        }

        public Criteria andAcctInternalKeyTypeEqualTo(String value) {
            addCriterion("ACCT_INTERNAL_KEY_TYPE =", value, "acctInternalKeyType");
            return (Criteria) this;
        }

        public Criteria andBusinessApplicationIdEqualTo(String value) {
            addCriterion("BUSINESS_APPLICATION_ID =", value, "businessApplicationId");
            return (Criteria) this;
        }

        public Criteria andResAcctRangeEqualTo(String value) {
            addCriterion("RES_ACCT_RANGE =", value, "resAcctRange");
            return (Criteria) this;
        }

        public Criteria andThawOthDocumentIdEqualTo(String value) {
            addCriterion("THAW_OTH_DOCUMENT_ID =", value, "thawOthDocumentId");
            return (Criteria) this;
        }

        public Criteria andThawDocumentId2EqualTo(String value) {
            addCriterion("THAW_DOCUMENT_ID2 =", value, "thawDocumentId2");
            return (Criteria) this;
        }

        public Criteria andBaseAcctNoEqualTo(String value) {
            addCriterion("BASE_ACCT_NO =", value, "baseAcctNo");
            return (Criteria) this;
        }

        public Criteria andReversalFlagEqualTo(String value) {
            addCriterion("REVERSAL_FLAG =", value, "reversalFlag");
            return (Criteria) this;
        }

        public Criteria andThawOfficerNameEqualTo(String value) {
            addCriterion("THAW_OFFICER_NAME =", value, "thawOfficerName");
            return (Criteria) this;
        }

        public Criteria andReleaseJudiciaryNameEqualTo(String value) {
            addCriterion("RELEASE_JUDICIARY_NAME =", value, "releaseJudiciaryName");
            return (Criteria) this;
        }

        public Criteria andCreateTimestampEqualTo(String value) {
            addCriterion("CREATE_TIMESTAMP =", value, "createTimestamp");
            return (Criteria) this;
        }

        public Criteria andRestraintSourceEqualTo(String value) {
            addCriterion("RESTRAINT_SOURCE =", value, "restraintSource");
            return (Criteria) this;
        }

        public Criteria andResPriorityEqualTo(String value) {
            addCriterion("RES_PRIORITY =", value, "resPriority");
            return (Criteria) this;
        }

        public Criteria andJudiciaryOthOfficerNameEqualTo(String value) {
            addCriterion("JUDICIARY_OTH_OFFICER_NAME =", value, "judiciaryOthOfficerName");
            return (Criteria) this;
        }

        public Criteria andChannelDateEqualTo(Date value) {
            addCriterion("CHANNEL_DATE =", value, "channelDate");
            return (Criteria) this;
        }

        public Criteria andContinueFreezeFlagEqualTo(String value) {
            addCriterion("CONTINUE_FREEZE_FLAG =", value, "continueFreezeFlag");
            return (Criteria) this;
        }

        public Criteria andThawDocumentIdEqualTo(String value) {
            addCriterion("THAW_DOCUMENT_ID =", value, "thawDocumentId");
            return (Criteria) this;
        }

        public Criteria andJudiciaryOthDocumentTypeEqualTo(String value) {
            addCriterion("JUDICIARY_OTH_DOCUMENT_TYPE =", value, "judiciaryOthDocumentType");
            return (Criteria) this;
        }

        public Criteria andDeductionLawNoEqualTo(String value) {
            addCriterion("DEDUCTION_LAW_NO =", value, "deductionLawNo");
            return (Criteria) this;
        }

        public Criteria andAhBuFlagEqualTo(String value) {
            addCriterion("AH_BU_FLAG =", value, "ahBuFlag");
            return (Criteria) this;
        }

        public Criteria andWaitSeqEqualTo(String value) {
            addCriterion("WAIT_SEQ =", value, "waitSeq");
            return (Criteria) this;
        }

        public Criteria andReferenceEqualTo(String value) {
            addCriterion("REFERENCE =", value, "reference");
            return (Criteria) this;
        }

        public Criteria andRestraintsStatusEqualTo(String value) {
            addCriterion("RESTRAINTS_STATUS =", value, "restraintsStatus");
            return (Criteria) this;
        }

        public Criteria andCompanyEqualTo(String value) {
            addCriterion("COMPANY =", value, "company");
            return (Criteria) this;
        }

        public Criteria andTranDateEqualTo(Date value) {
            addCriterion("TRAN_DATE =", value, "tranDate");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Date value) {
            addCriterion("END_DATE =", value, "endDate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;
        private Object value;
        private Object secondValue;
        private boolean noValue;
        private boolean singleValue;
        private boolean betweenValue;
        private boolean listValue;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        protected Criterion(String condition) {
            this.condition = condition;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value) {
            this.condition = condition;
            this.value = value;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.betweenValue = true;
        }
    }
}