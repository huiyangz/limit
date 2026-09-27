package com.dcits.limit.repo;

import com.dcits.limit.entity.RbLimitRuleRelation;
import com.dcits.limit.entity.RbLimitRuleRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RbLimitRuleRelationMapper {
    long countByExample(RbLimitRuleRelationExample example);

    int deleteByExample(RbLimitRuleRelationExample example);

    int deleteByPrimaryKey(@Param("ruleId") String ruleId);

    int insert(RbLimitRuleRelation row);

    int insertSelective(RbLimitRuleRelation row);

    List<RbLimitRuleRelation> selectByExample(RbLimitRuleRelationExample example);

    RbLimitRuleRelation selectByPrimaryKey(@Param("ruleId") String ruleId);

    int updateByExampleSelective(@Param("row") RbLimitRuleRelation row, @Param("example") RbLimitRuleRelationExample example);

    int updateByExample(@Param("row") RbLimitRuleRelation row, @Param("example") RbLimitRuleRelationExample example);

    int updateByPrimaryKeySelective(RbLimitRuleRelation row);

    int updateByPrimaryKey(RbLimitRuleRelation row);
}