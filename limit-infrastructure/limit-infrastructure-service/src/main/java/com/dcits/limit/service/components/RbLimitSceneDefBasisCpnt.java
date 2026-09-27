package com.dcits.limit.service.components;

import com.dcits.limit.enums.CheckObjType;
import com.dcits.limit.enums.LimitConvert;
import com.dcits.limit.enums.LimitMainType;
import com.dcits.limit.enums.TranCcy;
import java.util.ArrayList;
import java.util.List;

import com.dcits.limit.entity.RbLimitSceneDef;
import com.dcits.limit.entity.RbLimitSceneDefExample;
import com.dcits.limit.facade.components.IRbLimitSceneDefBcc;
import com.dcits.limit.facade.eo.RbLimitSceneDefEO;
import com.dcits.limit.repo.RbLimitSceneDefMapper;
import com.dcits.limit.service.utils.RbLimitSceneDefValueUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbLimitSceneDefBasisCpnt implements IRbLimitSceneDefBcc {
    @Autowired
    RbLimitSceneDefMapper rbLimitSceneDefMapper;

    @Override
    public long countByEo(RbLimitSceneDefEO eo) {
        RbLimitSceneDefExample example = RbLimitSceneDefValueUtil.eoToEntityExample(eo);
        return rbLimitSceneDefMapper.countByExample(example);
    }

    @Override
    public int removeByEo(RbLimitSceneDefEO eo) {
        RbLimitSceneDefExample example = RbLimitSceneDefValueUtil.eoToEntityExample(eo);
        return rbLimitSceneDefMapper.deleteByExample(example);
    }

    @Override
    public int removeByPrimaryKey(String limitSceneNo) {
        return rbLimitSceneDefMapper.deleteByPrimaryKey(limitSceneNo);
    }

    @Override
    public int create(RbLimitSceneDefEO eo) {
        RbLimitSceneDef row = RbLimitSceneDefValueUtil.eoToEntity(eo);
        return rbLimitSceneDefMapper.insert(row);
    }

    @Override
    public int createSelective(RbLimitSceneDefEO eo) {
        RbLimitSceneDef row = RbLimitSceneDefValueUtil.eoToEntity(eo);
        return rbLimitSceneDefMapper.insertSelective(row);
    }

    @Override
    public List<RbLimitSceneDefEO> findByEo(RbLimitSceneDefEO eo) {
        RbLimitSceneDefExample example = RbLimitSceneDefValueUtil.eoToEntityExample(eo);
        List<RbLimitSceneDefEO> result = new ArrayList<>();
        List<RbLimitSceneDef> dbResult = rbLimitSceneDefMapper.selectByExample(example);
        for (RbLimitSceneDef item : dbResult) {
            result.add(RbLimitSceneDefValueUtil.entityToEo(item));
        }
        return result;
    }

    @Override
    public RbLimitSceneDefEO findByPrimaryKey(String limitSceneNo) {
        return RbLimitSceneDefValueUtil.entityToEo(rbLimitSceneDefMapper.selectByPrimaryKey(limitSceneNo));
    }

    @Override
    public int modifyByPrimaryKeySelective(RbLimitSceneDefEO eo) {
        RbLimitSceneDef row = RbLimitSceneDefValueUtil.eoToEntity(eo);
        return rbLimitSceneDefMapper.updateByPrimaryKeySelective(row);
    }

    @Override
    public int modifyByPrimaryKey(RbLimitSceneDefEO eo) {
        RbLimitSceneDef row = RbLimitSceneDefValueUtil.eoToEntity(eo);
        return rbLimitSceneDefMapper.updateByPrimaryKey(row);
    }
}