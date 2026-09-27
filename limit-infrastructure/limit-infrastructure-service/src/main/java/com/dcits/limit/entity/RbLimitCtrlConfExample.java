package com.dcits.limit.entity;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.util.Date;

public class RbLimitCtrlConfExample {
    protected String orderByClause;
    protected boolean distinct;
    protected List<Criteria> oredCriteria;

    public RbLimitCtrlConfExample() {
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

        public Criteria andDealFlowEqualTo(String value) {
            addCriterion("DEAL_FLOW =", value, "dealFlow");
            return (Criteria) this;
        }

        public Criteria andLimitCtrlEndDateEqualTo(Date value) {
            addCriterion("LIMIT_CTRL_END_DATE =", value, "limitCtrlEndDate");
            return (Criteria) this;
        }

        public Criteria andLimitCtrlEndTimeEqualTo(Date value) {
            addCriterion("LIMIT_CTRL_END_TIME =", value, "limitCtrlEndTime");
            return (Criteria) this;
        }

        public Criteria andTempLimitFlagEqualTo(String value) {
            addCriterion("TEMP_LIMIT_FLAG =", value, "tempLimitFlag");
            return (Criteria) this;
        }

        public Criteria andCtrlItemTypeEqualTo(String value) {
            addCriterion("CTRL_ITEM_TYPE =", value, "ctrlItemType");
            return (Criteria) this;
        }

        public Criteria andLimitCtrlNumEqualTo(Integer value) {
            addCriterion("LIMIT_CTRL_NUM =", value, "limitCtrlNum");
            return (Criteria) this;
        }

        public Criteria andLimitCtrlBgnDateEqualTo(Date value) {
            addCriterion("LIMIT_CTRL_BGN_DATE =", value, "limitCtrlBgnDate");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeEqualTo(String value) {
            addCriterion("PERIOD_TYPE =", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodValueEqualTo(String value) {
            addCriterion("PERIOD_VALUE =", value, "periodValue");
            return (Criteria) this;
        }

        public Criteria andLimitBranchIdEqualTo(String value) {
            addCriterion("LIMIT_BRANCH_ID =", value, "limitBranchId");
            return (Criteria) this;
        }

        public Criteria andLimitCtrlBgnTimeEqualTo(Date value) {
            addCriterion("LIMIT_CTRL_BGN_TIME =", value, "limitCtrlBgnTime");
            return (Criteria) this;
        }

        public Criteria andSumTypeEqualTo(String value) {
            addCriterion("SUM_TYPE =", value, "sumType");
            return (Criteria) this;
        }

        public Criteria andCreateTimestampEqualTo(String value) {
            addCriterion("CREATE_TIMESTAMP =", value, "createTimestamp");
            return (Criteria) this;
        }

        public Criteria andLimitSceneNoEqualTo(String value) {
            addCriterion("LIMIT_SCENE_NO =", value, "limitSceneNo");
            return (Criteria) this;
        }

        public Criteria andLastUpdTimestampEqualTo(String value) {
            addCriterion("LAST_UPD_TIMESTAMP =", value, "lastUpdTimestamp");
            return (Criteria) this;
        }

        public Criteria andAllowCustomFlagEqualTo(String value) {
            addCriterion("ALLOW_CUSTOM_FLAG =", value, "allowCustomFlag");
            return (Criteria) this;
        }

        public Criteria andOnlyCustomEqualTo(String value) {
            addCriterion("ONLY_CUSTOM =", value, "onlyCustom");
            return (Criteria) this;
        }

        public Criteria andLimitBranchRangeEqualTo(String value) {
            addCriterion("LIMIT_BRANCH_RANGE =", value, "limitBranchRange");
            return (Criteria) this;
        }

        public Criteria andValidFlagEqualTo(String value) {
            addCriterion("VALID_FLAG =", value, "validFlag");
            return (Criteria) this;
        }

        public Criteria andAllowExceedFlagEqualTo(String value) {
            addCriterion("ALLOW_EXCEED_FLAG =", value, "allowExceedFlag");
            return (Criteria) this;
        }

        public Criteria andLimitCtrlAmtEqualTo(BigDecimal value) {
            addCriterion("LIMIT_CTRL_AMT =", value, "limitCtrlAmt");
            return (Criteria) this;
        }

        public Criteria andTempLimitValidTermEqualTo(String value) {
            addCriterion("TEMP_LIMIT_VALID_TERM =", value, "tempLimitValidTerm");
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