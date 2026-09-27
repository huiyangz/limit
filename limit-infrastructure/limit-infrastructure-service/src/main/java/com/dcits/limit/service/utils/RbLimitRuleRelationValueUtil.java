package com.dcits.limit.service.utils;

import com.dcits.limit.entity.RbLimitRuleRelation;
import com.dcits.limit.entity.RbLimitRuleRelationExample;
import com.dcits.limit.facade.eo.RbLimitRuleRelationEO;

public final class RbLimitRuleRelationValueUtil {
    private RbLimitRuleRelationValueUtil() {
    }

    public static RbLimitRuleRelationEO entityToEo(RbLimitRuleRelation entity) {
        if (entity == null) {
            return null;
        }
        RbLimitRuleRelationEO eo = new RbLimitRuleRelationEO();
        eo.setLastUpdTimestamp(entity.getLastUpdTimestamp());
        eo.setCreateTimestamp(entity.getCreateTimestamp());
        eo.setRuleId(entity.getRuleId());
        eo.setRuleRelationExpr(entity.getRuleRelationExpr());
        eo.setLimitSceneNo(entity.getLimitSceneNo());
        eo.setRuleDesc(entity.getRuleDesc());
        return eo;
    }

    public static RbLimitRuleRelation eoToEntity(RbLimitRuleRelationEO eo) {
        if (eo == null) {
            return null;
        }
        RbLimitRuleRelation entity = new RbLimitRuleRelation();
        entity.setLastUpdTimestamp(eo.getLastUpdTimestamp());
        entity.setCreateTimestamp(eo.getCreateTimestamp());
        entity.setRuleId(eo.getRuleId());
        entity.setRuleRelationExpr(eo.getRuleRelationExpr());
        entity.setLimitSceneNo(eo.getLimitSceneNo());
        entity.setRuleDesc(eo.getRuleDesc());
        return entity;
    }

    public static RbLimitRuleRelationExample eoToEntityExample(RbLimitRuleRelationEO eo) {
        if (eo == null) {
            return null;
        }
        RbLimitRuleRelationExample example = new RbLimitRuleRelationExample();
        RbLimitRuleRelationExample.Criteria criteria = example.createCriteria();
        if (eo.getLastUpdTimestamp() != null) criteria.andLastUpdTimestampEqualTo(eo.getLastUpdTimestamp());
        if (eo.getCreateTimestamp() != null) criteria.andCreateTimestampEqualTo(eo.getCreateTimestamp());
        if (eo.getRuleId() != null) criteria.andRuleIdEqualTo(eo.getRuleId());
        if (eo.getRuleRelationExpr() != null) criteria.andRuleRelationExprEqualTo(eo.getRuleRelationExpr());
        if (eo.getLimitSceneNo() != null) criteria.andLimitSceneNoEqualTo(eo.getLimitSceneNo());
        if (eo.getRuleDesc() != null) criteria.andRuleDescEqualTo(eo.getRuleDesc());
        return example;
    }
}