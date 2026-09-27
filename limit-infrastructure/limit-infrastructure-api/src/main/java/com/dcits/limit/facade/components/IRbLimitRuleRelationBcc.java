package com.dcits.limit.facade.components;

import java.util.List;

import com.dcits.limit.facade.eo.RbLimitRuleRelationEO;

/*实体表【限额规则关系(RB_LIMIT_RULE_RELATION)】数据服务接口*/
public interface IRbLimitRuleRelationBcc {
    /** count数据库表记录根据入参com.dcits.limit.facade.eo.RbLimitRuleRelationEO中的属性字段组合 **/
    long countByEo(RbLimitRuleRelationEO eo);

    /** remove数据库表记录根据入参com.dcits.limit.facade.eo.RbLimitRuleRelationEO中的属性字段组合 **/
    int removeByEo(RbLimitRuleRelationEO eo);

    /** remove 根据主键: 黑名单检查规则编号 **/
    int removeByPrimaryKey(String ruleId);

    int create(RbLimitRuleRelationEO eo);

    /** create数据库表记录，主键和EO对象中不允许为空的字段必填，其它可为空字段可选填，执行时根据com.dcits.limit.facade.eo.RbLimitRuleRelationEO中不为空的属性写入数据库**/
    int createSelective(RbLimitRuleRelationEO eo);

    /** find数据库表记录根据入参com.dcits.limit.facade.eo.RbLimitRuleRelationEO中的属性字段组合 **/
    List<RbLimitRuleRelationEO> findByEo(RbLimitRuleRelationEO eo);

    /** find 根据主键: 黑名单检查规则编号 **/
    RbLimitRuleRelationEO findByPrimaryKey(String ruleId);

    /**  根据主键: 黑名单检查规则编号执行更新记录操作，仅更新入参com.dcits.limit.facade.eo.RbLimitRuleRelationEO中不为空的属性字段 **/
    int modifyByPrimaryKeySelective(RbLimitRuleRelationEO eo);

    /** modify 根据主键: 黑名单检查规则编号 **/
    int modifyByPrimaryKey(RbLimitRuleRelationEO eo);
}