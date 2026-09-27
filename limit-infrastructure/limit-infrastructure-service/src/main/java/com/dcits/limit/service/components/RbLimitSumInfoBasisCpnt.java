package com.dcits.limit.service.components;

import com.dcits.limit.enums.TranCcy;
import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.dcits.limit.entity.RbLimitSumInfo;
import com.dcits.limit.entity.RbLimitSumInfoExample;
import com.dcits.limit.facade.components.IRbLimitSumInfoBcc;
import com.dcits.limit.facade.eo.RbLimitSumInfoEO;
import com.dcits.limit.repo.RbLimitSumInfoMapper;
import com.dcits.limit.service.utils.RbLimitSumInfoValueUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbLimitSumInfoBasisCpnt implements IRbLimitSumInfoBcc {
    @Autowired
    RbLimitSumInfoMapper rbLimitSumInfoMapper;

    @Override
    public long countByEo(RbLimitSumInfoEO eo) {
        RbLimitSumInfoExample example = RbLimitSumInfoValueUtil.eoToEntityExample(eo);
        return rbLimitSumInfoMapper.countByExample(example);
    }

    @Override
    public int removeByEo(RbLimitSumInfoEO eo) {
        RbLimitSumInfoExample example = RbLimitSumInfoValueUtil.eoToEntityExample(eo);
        return rbLimitSumInfoMapper.deleteByExample(example);
    }

    @Override
    public int removeByPrimaryKey(String checkObjVal, String limitSceneNo) {
        return rbLimitSumInfoMapper.deleteByPrimaryKey(checkObjVal, limitSceneNo);
    }

    @Override
    public int create(RbLimitSumInfoEO eo) {
        RbLimitSumInfo row = RbLimitSumInfoValueUtil.eoToEntity(eo);
        return rbLimitSumInfoMapper.insert(row);
    }

    @Override
    public int createSelective(RbLimitSumInfoEO eo) {
        RbLimitSumInfo row = RbLimitSumInfoValueUtil.eoToEntity(eo);
        return rbLimitSumInfoMapper.insertSelective(row);
    }

    @Override
    public List<RbLimitSumInfoEO> findByEo(RbLimitSumInfoEO eo) {
        RbLimitSumInfoExample example = RbLimitSumInfoValueUtil.eoToEntityExample(eo);
        List<RbLimitSumInfoEO> result = new ArrayList<>();
        List<RbLimitSumInfo> dbResult = rbLimitSumInfoMapper.selectByExample(example);
        for (RbLimitSumInfo item : dbResult) {
            result.add(RbLimitSumInfoValueUtil.entityToEo(item));
        }
        return result;
    }

    @Override
    public RbLimitSumInfoEO findByPrimaryKey(String checkObjVal, String limitSceneNo) {
        return RbLimitSumInfoValueUtil.entityToEo(rbLimitSumInfoMapper.selectByPrimaryKey(checkObjVal, limitSceneNo));
    }

    @Override
    public int modifyByPrimaryKeySelective(RbLimitSumInfoEO eo) {
        RbLimitSumInfo row = RbLimitSumInfoValueUtil.eoToEntity(eo);
        return rbLimitSumInfoMapper.updateByPrimaryKeySelective(row);
    }

    @Override
    public int modifyByPrimaryKey(RbLimitSumInfoEO eo) {
        RbLimitSumInfo row = RbLimitSumInfoValueUtil.eoToEntity(eo);
        return rbLimitSumInfoMapper.updateByPrimaryKey(row);
    }
}