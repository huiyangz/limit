package com.dcits.limit.service.components;

import com.dcits.limit.enums.CtrlItemType;
import com.dcits.limit.enums.DealFlow;
import com.dcits.limit.enums.LimitBranchId;
import com.dcits.limit.enums.LimitBranchRange;
import com.dcits.limit.enums.PeriodType;
import com.dcits.limit.enums.SumType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.dcits.limit.entity.RbLimitCtrlConf;
import com.dcits.limit.entity.RbLimitCtrlConfExample;
import com.dcits.limit.facade.components.IRbLimitCtrlConfBcc;
import com.dcits.limit.facade.eo.RbLimitCtrlConfEO;
import com.dcits.limit.repo.RbLimitCtrlConfMapper;
import com.dcits.limit.service.utils.RbLimitCtrlConfValueUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbLimitCtrlConfBasisCpnt implements IRbLimitCtrlConfBcc {
    @Autowired
    RbLimitCtrlConfMapper rbLimitCtrlConfMapper;

    @Override
    public long countByEo(RbLimitCtrlConfEO eo) {
        RbLimitCtrlConfExample example = RbLimitCtrlConfValueUtil.eoToEntityExample(eo);
        return rbLimitCtrlConfMapper.countByExample(example);
    }

    @Override
    public int removeByEo(RbLimitCtrlConfEO eo) {
        RbLimitCtrlConfExample example = RbLimitCtrlConfValueUtil.eoToEntityExample(eo);
        return rbLimitCtrlConfMapper.deleteByExample(example);
    }

    @Override
    public int removeByPrimaryKey(String limitBranchId, String limitSceneNo) {
        return rbLimitCtrlConfMapper.deleteByPrimaryKey(limitBranchId, limitSceneNo);
    }

    @Override
    public int create(RbLimitCtrlConfEO eo) {
        RbLimitCtrlConf row = RbLimitCtrlConfValueUtil.eoToEntity(eo);
        return rbLimitCtrlConfMapper.insert(row);
    }

    @Override
    public int createSelective(RbLimitCtrlConfEO eo) {
        RbLimitCtrlConf row = RbLimitCtrlConfValueUtil.eoToEntity(eo);
        return rbLimitCtrlConfMapper.insertSelective(row);
    }

    @Override
    public List<RbLimitCtrlConfEO> findByEo(RbLimitCtrlConfEO eo) {
        RbLimitCtrlConfExample example = RbLimitCtrlConfValueUtil.eoToEntityExample(eo);
        List<RbLimitCtrlConfEO> result = new ArrayList<>();
        List<RbLimitCtrlConf> dbResult = rbLimitCtrlConfMapper.selectByExample(example);
        for (RbLimitCtrlConf item : dbResult) {
            result.add(RbLimitCtrlConfValueUtil.entityToEo(item));
        }
        return result;
    }

    @Override
    public RbLimitCtrlConfEO findByPrimaryKey(String limitBranchId, String limitSceneNo) {
        return RbLimitCtrlConfValueUtil.entityToEo(rbLimitCtrlConfMapper.selectByPrimaryKey(limitBranchId, limitSceneNo));
    }

    @Override
    public int modifyByPrimaryKeySelective(RbLimitCtrlConfEO eo) {
        RbLimitCtrlConf row = RbLimitCtrlConfValueUtil.eoToEntity(eo);
        return rbLimitCtrlConfMapper.updateByPrimaryKeySelective(row);
    }

    @Override
    public int modifyByPrimaryKey(RbLimitCtrlConfEO eo) {
        RbLimitCtrlConf row = RbLimitCtrlConfValueUtil.eoToEntity(eo);
        return rbLimitCtrlConfMapper.updateByPrimaryKey(row);
    }
}