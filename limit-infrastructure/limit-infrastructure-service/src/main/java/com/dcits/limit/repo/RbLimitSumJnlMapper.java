package com.dcits.limit.repo;

import com.dcits.limit.entity.RbLimitSumJnl;
import com.dcits.limit.entity.RbLimitSumJnlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RbLimitSumJnlMapper {
    long countByExample(RbLimitSumJnlExample example);

    int deleteByExample(RbLimitSumJnlExample example);

    int deleteByPrimaryKey(@Param("checkObjVal") String checkObjVal, @Param("limitSceneNo") String limitSceneNo, @Param("clientNo") String clientNo);

    int insert(RbLimitSumJnl row);

    int insertSelective(RbLimitSumJnl row);

    List<RbLimitSumJnl> selectByExample(RbLimitSumJnlExample example);

    RbLimitSumJnl selectByPrimaryKey(@Param("checkObjVal") String checkObjVal, @Param("limitSceneNo") String limitSceneNo, @Param("clientNo") String clientNo);

    int updateByExampleSelective(@Param("row") RbLimitSumJnl row, @Param("example") RbLimitSumJnlExample example);

    int updateByExample(@Param("row") RbLimitSumJnl row, @Param("example") RbLimitSumJnlExample example);

    int updateByPrimaryKeySelective(RbLimitSumJnl row);

    int updateByPrimaryKey(RbLimitSumJnl row);
}