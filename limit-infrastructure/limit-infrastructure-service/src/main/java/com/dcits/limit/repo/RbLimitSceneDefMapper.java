package com.dcits.limit.repo;

import com.dcits.limit.entity.RbLimitSceneDef;
import com.dcits.limit.entity.RbLimitSceneDefExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RbLimitSceneDefMapper {
    long countByExample(RbLimitSceneDefExample example);

    int deleteByExample(RbLimitSceneDefExample example);

    int deleteByPrimaryKey(@Param("limitSceneNo") String limitSceneNo);

    int insert(RbLimitSceneDef row);

    int insertSelective(RbLimitSceneDef row);

    List<RbLimitSceneDef> selectByExample(RbLimitSceneDefExample example);

    RbLimitSceneDef selectByPrimaryKey(@Param("limitSceneNo") String limitSceneNo);

    int updateByExampleSelective(@Param("row") RbLimitSceneDef row, @Param("example") RbLimitSceneDefExample example);

    int updateByExample(@Param("row") RbLimitSceneDef row, @Param("example") RbLimitSceneDefExample example);

    int updateByPrimaryKeySelective(RbLimitSceneDef row);

    int updateByPrimaryKey(RbLimitSceneDef row);
}