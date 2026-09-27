package com.dcits.limit.repo;

import com.dcits.limit.entity.RbLimitSumInfo;
import com.dcits.limit.entity.RbLimitSumInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RbLimitSumInfoMapper {
    long countByExample(RbLimitSumInfoExample example);

    int deleteByExample(RbLimitSumInfoExample example);

    int deleteByPrimaryKey(@Param("checkObjVal") String checkObjVal, @Param("limitSceneNo") String limitSceneNo);

    int insert(RbLimitSumInfo row);

    int insertSelective(RbLimitSumInfo row);

    List<RbLimitSumInfo> selectByExample(RbLimitSumInfoExample example);

    RbLimitSumInfo selectByPrimaryKey(@Param("checkObjVal") String checkObjVal, @Param("limitSceneNo") String limitSceneNo);

    int updateByExampleSelective(@Param("row") RbLimitSumInfo row, @Param("example") RbLimitSumInfoExample example);

    int updateByExample(@Param("row") RbLimitSumInfo row, @Param("example") RbLimitSumInfoExample example);

    int updateByPrimaryKeySelective(RbLimitSumInfo row);

    int updateByPrimaryKey(RbLimitSumInfo row);
}