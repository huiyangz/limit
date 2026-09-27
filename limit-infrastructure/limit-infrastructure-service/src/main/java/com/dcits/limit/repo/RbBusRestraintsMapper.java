package com.dcits.limit.repo;

import com.dcits.limit.entity.RbBusRestraints;
import com.dcits.limit.entity.RbBusRestraintsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RbBusRestraintsMapper {
    long countByExample(RbBusRestraintsExample example);

    int deleteByExample(RbBusRestraintsExample example);

    int deleteByPrimaryKey(@Param("resSeqNo") String resSeqNo);

    int insert(RbBusRestraints row);

    int insertSelective(RbBusRestraints row);

    List<RbBusRestraints> selectByExample(RbBusRestraintsExample example);

    RbBusRestraints selectByPrimaryKey(@Param("resSeqNo") String resSeqNo);

    int updateByExampleSelective(@Param("row") RbBusRestraints row, @Param("example") RbBusRestraintsExample example);

    int updateByExample(@Param("row") RbBusRestraints row, @Param("example") RbBusRestraintsExample example);

    int updateByPrimaryKeySelective(RbBusRestraints row);

    int updateByPrimaryKey(RbBusRestraints row);
}