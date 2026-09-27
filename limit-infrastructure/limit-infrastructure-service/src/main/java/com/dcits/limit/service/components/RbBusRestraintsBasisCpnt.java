package com.dcits.limit.service.components;

import com.dcits.limit.enums.AcctInternalKeyType;
import com.dcits.limit.enums.LimitBranchId;
import com.dcits.limit.enums.PeriodType;
import com.dcits.limit.enums.ResAcctRange;
import com.dcits.limit.enums.RestraintLevel;
import com.dcits.limit.enums.RestraintSource;
import com.dcits.limit.enums.RestraintsStatus;
import com.dcits.limit.enums.RestraintType;
import com.dcits.limit.enums.SourceModule;
import com.dcits.limit.enums.ThawDocumentType2;
import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.dcits.limit.entity.RbBusRestraints;
import com.dcits.limit.entity.RbBusRestraintsExample;
import com.dcits.limit.facade.components.IRbBusRestraintsBcc;
import com.dcits.limit.facade.eo.RbBusRestraintsEO;
import com.dcits.limit.repo.RbBusRestraintsMapper;
import com.dcits.limit.service.utils.RbBusRestraintsValueUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbBusRestraintsBasisCpnt implements IRbBusRestraintsBcc {
    @Autowired
    RbBusRestraintsMapper rbBusRestraintsMapper;

    @Override
    public long countByEo(RbBusRestraintsEO eo) {
        RbBusRestraintsExample example = RbBusRestraintsValueUtil.eoToEntityExample(eo);
        return rbBusRestraintsMapper.countByExample(example);
    }

    @Override
    public int removeByEo(RbBusRestraintsEO eo) {
        RbBusRestraintsExample example = RbBusRestraintsValueUtil.eoToEntityExample(eo);
        return rbBusRestraintsMapper.deleteByExample(example);
    }

    @Override
    public int removeByPrimaryKey(String resSeqNo) {
        return rbBusRestraintsMapper.deleteByPrimaryKey(resSeqNo);
    }

    @Override
    public int create(RbBusRestraintsEO eo) {
        RbBusRestraints row = RbBusRestraintsValueUtil.eoToEntity(eo);
        return rbBusRestraintsMapper.insert(row);
    }

    @Override
    public int createSelective(RbBusRestraintsEO eo) {
        RbBusRestraints row = RbBusRestraintsValueUtil.eoToEntity(eo);
        return rbBusRestraintsMapper.insertSelective(row);
    }

    @Override
    public List<RbBusRestraintsEO> findByEo(RbBusRestraintsEO eo) {
        RbBusRestraintsExample example = RbBusRestraintsValueUtil.eoToEntityExample(eo);
        List<RbBusRestraintsEO> result = new ArrayList<>();
        List<RbBusRestraints> dbResult = rbBusRestraintsMapper.selectByExample(example);
        for (RbBusRestraints item : dbResult) {
            result.add(RbBusRestraintsValueUtil.entityToEo(item));
        }
        return result;
    }

    @Override
    public RbBusRestraintsEO findByPrimaryKey(String resSeqNo) {
        return RbBusRestraintsValueUtil.entityToEo(rbBusRestraintsMapper.selectByPrimaryKey(resSeqNo));
    }

    @Override
    public int modifyByPrimaryKeySelective(RbBusRestraintsEO eo) {
        RbBusRestraints row = RbBusRestraintsValueUtil.eoToEntity(eo);
        return rbBusRestraintsMapper.updateByPrimaryKeySelective(row);
    }

    @Override
    public int modifyByPrimaryKey(RbBusRestraintsEO eo) {
        RbBusRestraints row = RbBusRestraintsValueUtil.eoToEntity(eo);
        return rbBusRestraintsMapper.updateByPrimaryKey(row);
    }
}