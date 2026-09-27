package com.dcits.limit.service.components;

import com.dcits.limit.enums.AcctCcy;
import com.dcits.limit.enums.AcctNatureNo;
import com.dcits.limit.enums.AcctRiskLevel;
import com.dcits.limit.enums.AcctStatus;
import com.dcits.limit.enums.AcctVerifyFlag;
import com.dcits.limit.enums.AcctVerifyResult;
import com.dcits.limit.enums.AllDepInd;
import com.dcits.limit.enums.AllDraInd;
import com.dcits.limit.enums.AllDraRange;
import com.dcits.limit.enums.AnnualStatus;
import com.dcits.limit.enums.AutoRenewInd;
import com.dcits.limit.enums.BalType;
import com.dcits.limit.enums.CheckCertificateType;
import com.dcits.limit.enums.ClientType;
import com.dcits.limit.enums.DepositNature;
import com.dcits.limit.enums.FarmerFlag;
import com.dcits.limit.enums.FixedCall;
import com.dcits.limit.enums.IntIndFlag;
import com.dcits.limit.enums.LimitBranchId;
import com.dcits.limit.enums.ManageType;
import com.dcits.limit.enums.OsaFlag;
import com.dcits.limit.enums.PeriodType;
import com.dcits.limit.enums.RbAcctType;
import com.dcits.limit.enums.RbBusAcctPurpose;
import com.dcits.limit.enums.RenewMethod;
import com.dcits.limit.enums.SimpleAcct;
import com.dcits.limit.enums.SourceType;
import com.dcits.limit.enums.SpecAcctFlag;
import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.dcits.limit.entity.RbBusAcct;
import com.dcits.limit.entity.RbBusAcctExample;
import com.dcits.limit.facade.components.IRbBusAcctBcc;
import com.dcits.limit.facade.eo.RbBusAcctEO;
import com.dcits.limit.repo.RbBusAcctMapper;
import com.dcits.limit.service.utils.RbBusAcctValueUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbBusAcctBasisCpnt implements IRbBusAcctBcc {
    @Autowired
    RbBusAcctMapper rbBusAcctMapper;

    @Override
    public long countByEo(RbBusAcctEO eo) {
        RbBusAcctExample example = RbBusAcctValueUtil.eoToEntityExample(eo);
        return rbBusAcctMapper.countByExample(example);
    }

    @Override
    public int removeByEo(RbBusAcctEO eo) {
        RbBusAcctExample example = RbBusAcctValueUtil.eoToEntityExample(eo);
        return rbBusAcctMapper.deleteByExample(example);
    }

    @Override
    public int removeByPrimaryKey(Integer internalKey) {
        return rbBusAcctMapper.deleteByPrimaryKey(internalKey);
    }

    @Override
    public int create(RbBusAcctEO eo) {
        RbBusAcct row = RbBusAcctValueUtil.eoToEntity(eo);
        return rbBusAcctMapper.insert(row);
    }

    @Override
    public int createSelective(RbBusAcctEO eo) {
        RbBusAcct row = RbBusAcctValueUtil.eoToEntity(eo);
        return rbBusAcctMapper.insertSelective(row);
    }

    @Override
    public List<RbBusAcctEO> findByEo(RbBusAcctEO eo) {
        RbBusAcctExample example = RbBusAcctValueUtil.eoToEntityExample(eo);
        List<RbBusAcctEO> result = new ArrayList<>();
        List<RbBusAcct> dbResult = rbBusAcctMapper.selectByExample(example);
        for (RbBusAcct item : dbResult) {
            result.add(RbBusAcctValueUtil.entityToEo(item));
        }
        return result;
    }

    @Override
    public RbBusAcctEO findByPrimaryKey(Integer internalKey) {
        return RbBusAcctValueUtil.entityToEo(rbBusAcctMapper.selectByPrimaryKey(internalKey));
    }

    @Override
    public int modifyByPrimaryKeySelective(RbBusAcctEO eo) {
        RbBusAcct row = RbBusAcctValueUtil.eoToEntity(eo);
        return rbBusAcctMapper.updateByPrimaryKeySelective(row);
    }

    @Override
    public int modifyByPrimaryKey(RbBusAcctEO eo) {
        RbBusAcct row = RbBusAcctValueUtil.eoToEntity(eo);
        return rbBusAcctMapper.updateByPrimaryKey(row);
    }
}