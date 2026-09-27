package com.dcits.limit.facade.bo;

import com.dcits.common.step.StepResult;

/**
 * ST007 匹配限额场景 输出BO
 */
public class ST007OutputBO extends StepResult {
    /** 限额场景编码（匹配到的限额场景编码） */
    private String limitSceneNo;
    /** 关系规则表达式 */
    private String ruleRelationExpr;
    /** 启用标志 */
    private String validFlag;
    /** 检查结果：已匹配到限额场景 / 未匹配到限额场景 */
    private String checkResult;

    public String getLimitSceneNo() {
        return limitSceneNo;
    }

    public void setLimitSceneNo(String limitSceneNo) {
        this.limitSceneNo = limitSceneNo;
    }

    public String getRuleRelationExpr() {
        return ruleRelationExpr;
    }

    public void setRuleRelationExpr(String ruleRelationExpr) {
        this.ruleRelationExpr = ruleRelationExpr;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }
}
