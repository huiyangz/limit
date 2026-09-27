package com.dcits.limit.service.components;

import com.dcits.limit.enums.CtrlItemType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.dcits.limit.entity.RbLimitCtrlCustomInfo;
import com.dcits.limit.entity.RbLimitCtrlCustomInfoExample;
import com.dcits.limit.facade.components.IRbLimitCtrlCustomInfoBcc;
import com.dcits.limit.facade.eo.RbLimitCtrlCustomInfoEO;
import com.dcits.limit.repo.RbLimitCtrlCustomInfoMapper;
import com.dcits.limit.service.utils.RbLimitCtrlCustomInfoValueUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbLimitCtrlCustomInfoBasisCpnt implements IRbLimitCtrlCustomInfoBcc {
    @Autowired
    RbLimitCtrlCustomInfoMapper rbLimitCtrlCustomInfoMapper;

    @Override
    public long countByEo(RbLimitCtrlCustomInfoEO eo) {
        RbLimitCtrlCustomInfoExample example = RbLimitCtrlCustomInfoValueUtil.eoToEntityExample(eo);
        return rbLimitCtrlCustomInfoMapper.countByExample(example);
    }

    @Override
    public int removeByEo(RbLimitCtrlCustomInfoEO eo) {
        RbLimitCtrlCustomInfoExample example = RbLimitCtrlCustomInfoValueUtil.eoToEntityExample(eo);
        return rbLimitCtrlCustomInfoMapper.deleteByExample(example);
    }

    @Override
    public int removeByPrimaryKey(String checkObjVal, String limitSceneNo) {
        return rbLimitCtrlCustomInfoMapper.deleteByPrimaryKey(checkObjVal, limitSceneNo);
    }

    @Override
    public int create(RbLimitCtrlCustomInfoEO eo) {
        RbLimitCtrlCustomInfo row = RbLimitCtrlCustomInfoValueUtil.eoToEntity(eo);
        return rbLimitCtrlCustomInfoMapper.insert(row);
    }

    @Override
    public int createSelective(RbLimitCtrlCustomInfoEO eo) {
        RbLimitCtrlCustomInfo row = RbLimitCtrlCustomInfoValueUtil.eoToEntity(eo);
        return rbLimitCtrlCustomInfoMapper.insertSelective(row);
    }

    @Override
    public List<RbLimitCtrlCustomInfoEO> findByEo(RbLimitCtrlCustomInfoEO eo) {
        RbLimitCtrlCustomInfoExample example = RbLimitCtrlCustomInfoValueUtil.eoToEntityExample(eo);
        List<RbLimitCtrlCustomInfoEO> result = new ArrayList<>();
        List<RbLimitCtrlCustomInfo> dbResult = rbLimitCtrlCustomInfoMapper.selectByExample(example);
        for (RbLimitCtrlCustomInfo item : dbResult) {
            result.add(RbLimitCtrlCustomInfoValueUtil.entityToEo(item));
        }
        return result;
    }

    @Override
    public RbLimitCtrlCustomInfoEO findByPrimaryKey(String checkObjVal, String limitSceneNo) {
        return RbLimitCtrlCustomInfoValueUtil.entityToEo(rbLimitCtrlCustomInfoMapper.selectByPrimaryKey(checkObjVal, limitSceneNo));
    }

    @Override
    public int modifyByPrimaryKeySelective(RbLimitCtrlCustomInfoEO eo) {
        RbLimitCtrlCustomInfo row = RbLimitCtrlCustomInfoValueUtil.eoToEntity(eo);
        return rbLimitCtrlCustomInfoMapper.updateByPrimaryKeySelective(row);
    }

    @Override
    public int modifyByPrimaryKey(RbLimitCtrlCustomInfoEO eo) {
        RbLimitCtrlCustomInfo row = RbLimitCtrlCustomInfoValueUtil.eoToEntity(eo);
        return rbLimitCtrlCustomInfoMapper.updateByPrimaryKey(row);
    }
}