package com.dcits.limit.service.components;

import com.dcits.limit.enums.LimitBranchId;
import com.dcits.limit.enums.RecordStatus;
import com.dcits.limit.enums.SourceType;
import com.dcits.limit.enums.SumType;
import com.dcits.limit.enums.TranCcy;
import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.dcits.limit.entity.RbLimitSumJnl;
import com.dcits.limit.entity.RbLimitSumJnlExample;
import com.dcits.limit.facade.components.IRbLimitSumJnlBcc;
import com.dcits.limit.facade.eo.RbLimitSumJnlEO;
import com.dcits.limit.repo.RbLimitSumJnlMapper;
import com.dcits.limit.service.utils.RbLimitSumJnlValueUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbLimitSumJnlBasisCpnt implements IRbLimitSumJnlBcc {
    @Autowired
    RbLimitSumJnlMapper rbLimitSumJnlMapper;

    @Override
    public long countByEo(RbLimitSumJnlEO eo) {
        RbLimitSumJnlExample example = RbLimitSumJnlValueUtil.eoToEntityExample(eo);
        return rbLimitSumJnlMapper.countByExample(example);
    }

    @Override
    public int removeByEo(RbLimitSumJnlEO eo) {
        RbLimitSumJnlExample example = RbLimitSumJnlValueUtil.eoToEntityExample(eo);
        return rbLimitSumJnlMapper.deleteByExample(example);
    }

    @Override
    public int removeByPrimaryKey(String checkObjVal, String limitSceneNo, String clientNo) {
        return rbLimitSumJnlMapper.deleteByPrimaryKey(checkObjVal, limitSceneNo, clientNo);
    }

    @Override
    public int create(RbLimitSumJnlEO eo) {
        RbLimitSumJnl row = RbLimitSumJnlValueUtil.eoToEntity(eo);
        return rbLimitSumJnlMapper.insert(row);
    }

    @Override
    public int createSelective(RbLimitSumJnlEO eo) {
        RbLimitSumJnl row = RbLimitSumJnlValueUtil.eoToEntity(eo);
        return rbLimitSumJnlMapper.insertSelective(row);
    }

    @Override
    public List<RbLimitSumJnlEO> findByEo(RbLimitSumJnlEO eo) {
        RbLimitSumJnlExample example = RbLimitSumJnlValueUtil.eoToEntityExample(eo);
        List<RbLimitSumJnlEO> result = new ArrayList<>();
        List<RbLimitSumJnl> dbResult = rbLimitSumJnlMapper.selectByExample(example);
        for (RbLimitSumJnl item : dbResult) {
            result.add(RbLimitSumJnlValueUtil.entityToEo(item));
        }
        return result;
    }

    @Override
    public RbLimitSumJnlEO findByPrimaryKey(String checkObjVal, String limitSceneNo, String clientNo) {
        return RbLimitSumJnlValueUtil.entityToEo(rbLimitSumJnlMapper.selectByPrimaryKey(checkObjVal, limitSceneNo, clientNo));
    }

    @Override
    public int modifyByPrimaryKeySelective(RbLimitSumJnlEO eo) {
        RbLimitSumJnl row = RbLimitSumJnlValueUtil.eoToEntity(eo);
        return rbLimitSumJnlMapper.updateByPrimaryKeySelective(row);
    }

    @Override
    public int modifyByPrimaryKey(RbLimitSumJnlEO eo) {
        RbLimitSumJnl row = RbLimitSumJnlValueUtil.eoToEntity(eo);
        return rbLimitSumJnlMapper.updateByPrimaryKey(row);
    }
}