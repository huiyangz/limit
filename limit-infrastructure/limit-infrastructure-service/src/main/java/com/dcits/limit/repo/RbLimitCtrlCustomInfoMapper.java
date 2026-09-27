package com.dcits.limit.repo;

import com.dcits.limit.entity.RbLimitCtrlCustomInfo;
import com.dcits.limit.entity.RbLimitCtrlCustomInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RbLimitCtrlCustomInfoMapper {
    long countByExample(RbLimitCtrlCustomInfoExample example);

    int deleteByExample(RbLimitCtrlCustomInfoExample example);

    int deleteByPrimaryKey(@Param("checkObjVal") String checkObjVal, @Param("limitSceneNo") String limitSceneNo);

    int insert(RbLimitCtrlCustomInfo row);

    int insertSelective(RbLimitCtrlCustomInfo row);

    List<RbLimitCtrlCustomInfo> selectByExample(RbLimitCtrlCustomInfoExample example);

    RbLimitCtrlCustomInfo selectByPrimaryKey(@Param("checkObjVal") String checkObjVal, @Param("limitSceneNo") String limitSceneNo);

    int updateByExampleSelective(@Param("row") RbLimitCtrlCustomInfo row, @Param("example") RbLimitCtrlCustomInfoExample example);

    int updateByExample(@Param("row") RbLimitCtrlCustomInfo row, @Param("example") RbLimitCtrlCustomInfoExample example);

    int updateByPrimaryKeySelective(RbLimitCtrlCustomInfo row);

    int updateByPrimaryKey(RbLimitCtrlCustomInfo row);
}