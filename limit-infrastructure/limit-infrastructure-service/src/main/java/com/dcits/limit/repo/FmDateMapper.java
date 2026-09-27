package com.dcits.limit.repo;

import com.dcits.limit.entity.FmDate;
import com.dcits.limit.entity.FmDateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FmDateMapper {
    long countByExample(FmDateExample example);

    int deleteByExample(FmDateExample example);

    int deleteByPrimaryKey(@Param("company") String company);

    int insert(FmDate row);

    int insertSelective(FmDate row);

    List<FmDate> selectByExample(FmDateExample example);

    FmDate selectByPrimaryKey(@Param("company") String company);

    int updateByExampleSelective(@Param("row") FmDate row, @Param("example") FmDateExample example);

    int updateByExample(@Param("row") FmDate row, @Param("example") FmDateExample example);

    int updateByPrimaryKeySelective(FmDate row);

    int updateByPrimaryKey(FmDate row);
}