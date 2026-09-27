package com.dcits.limit.repo;

import com.dcits.limit.entity.FmClientCopy;
import com.dcits.limit.entity.FmClientCopyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FmClientCopyMapper {
    long countByExample(FmClientCopyExample example);

    int deleteByExample(FmClientCopyExample example);

    int deleteByPrimaryKey(@Param("clientNo") String clientNo);

    int insert(FmClientCopy row);

    int insertSelective(FmClientCopy row);

    List<FmClientCopy> selectByExample(FmClientCopyExample example);

    FmClientCopy selectByPrimaryKey(@Param("clientNo") String clientNo);

    int updateByExampleSelective(@Param("row") FmClientCopy row, @Param("example") FmClientCopyExample example);

    int updateByExample(@Param("row") FmClientCopy row, @Param("example") FmClientCopyExample example);

    int updateByPrimaryKeySelective(FmClientCopy row);

    int updateByPrimaryKey(FmClientCopy row);
}