package com.dcits.limit.step;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.limit.facade.bo.ST007InputBO;
import com.dcits.limit.facade.bo.ST007OutputBO;
import com.dcits.limit.facade.components.IRbLimitRuleRelationBcc;
import com.dcits.limit.facade.components.IRbLimitSceneDefBcc;
import com.dcits.limit.facade.eo.RbLimitRuleRelationEO;
import com.dcits.limit.facade.eo.RbLimitSceneDefEO;

/**
 * ST007 匹配限额场景 步骤实现
 */
@Service
public class ST007Pbc implements IST007 {

    /** 启用标志：Y 表示启用 */
    private static final String VALID_FLAG_ENABLED = "Y";
    /** 检查结果：已匹配到限额场景 */
    private static final String CHECK_RESULT_MATCHED = "已匹配到限额场景";
    /** 检查结果：未匹配到限额场景 */
    private static final String CHECK_RESULT_NOT_MATCHED = "未匹配到限额场景";

    @Autowired
    private IRbLimitRuleRelationBcc rbLimitRuleRelationBcc;

    @Autowired
    private IRbLimitSceneDefBcc rbLimitSceneDefBcc;

    @Override
    public ST007OutputBO execute(ST007InputBO input) {
        ST007OutputBO output = new ST007OutputBO();

        // 子步骤1 获取规则关系表达式：业务场景因子即限额规则关系表主键 ruleId，结果唯一；查无记录时直接返回未匹配
        RbLimitRuleRelationEO ruleRelation = rbLimitRuleRelationBcc.findByPrimaryKey(input.getFactorName());
        if (ruleRelation == null) {
            output.setCheckResult(CHECK_RESULT_NOT_MATCHED);
            output.setSucceed(true);
            return output;
        }
        output.setRuleRelationExpr(ruleRelation.getRuleRelationExpr());

        // 子步骤2 获取限额场景编码列表：按关系规则表达式查询限额规则关系表
        RbLimitRuleRelationEO queryEo = new RbLimitRuleRelationEO();
        queryEo.setRuleRelationExpr(ruleRelation.getRuleRelationExpr());
        List<RbLimitRuleRelationEO> sceneRelations = rbLimitRuleRelationBcc.findByEo(queryEo);

        // 子步骤3 检查是否匹配到限额场景：遍历限额场景编码列表，查询限额场景定义表中启用标志为 Y 的配置数据
        String matchedSceneNo = null;
        String matchedValidFlag = null;
        if (sceneRelations != null) {
            for (RbLimitRuleRelationEO sceneRelation : sceneRelations) {
                RbLimitSceneDefEO defQueryEo = new RbLimitSceneDefEO();
                defQueryEo.setLimitSceneNo(sceneRelation.getLimitSceneNo());
                defQueryEo.setValidFlag(VALID_FLAG_ENABLED);
                List<RbLimitSceneDefEO> configData = rbLimitSceneDefBcc.findByEo(defQueryEo);
                if (configData != null && !configData.isEmpty()) {
                    // 配置数据不为空，中断本次遍历
                    matchedSceneNo = sceneRelation.getLimitSceneNo();
                    matchedValidFlag = configData.get(0).getValidFlag();
                    break;
                }
            }
        }

        if (matchedSceneNo != null) {
            output.setLimitSceneNo(matchedSceneNo);
            output.setValidFlag(matchedValidFlag);
            output.setCheckResult(CHECK_RESULT_MATCHED);
        } else {
            // 遍历结束（含限额场景编码列表为空）且均未命中启用配置
            output.setCheckResult(CHECK_RESULT_NOT_MATCHED);
        }
        output.setSucceed(true);
        return output;
    }
}
