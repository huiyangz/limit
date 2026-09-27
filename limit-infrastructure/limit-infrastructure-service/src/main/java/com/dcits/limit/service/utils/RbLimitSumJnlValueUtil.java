package com.dcits.limit.service.utils;

import com.dcits.limit.entity.RbLimitSumJnl;
import com.dcits.limit.entity.RbLimitSumJnlExample;
import com.dcits.limit.facade.eo.RbLimitSumJnlEO;
import com.dcits.limit.enums.TranCcy;
import com.dcits.limit.enums.LimitBranchId;
import com.dcits.limit.enums.SourceType;
import com.dcits.limit.enums.RecordStatus;
import com.dcits.limit.enums.SumType;

public final class RbLimitSumJnlValueUtil {
    private RbLimitSumJnlValueUtil() {
    }

    public static RbLimitSumJnlEO entityToEo(RbLimitSumJnl entity) {
        if (entity == null) {
            return null;
        }
        RbLimitSumJnlEO eo = new RbLimitSumJnlEO();
        eo.setPreReference(entity.getPreReference());
        eo.setTranCcy(TranCcy.byValue(entity.getTranCcy()));
        eo.setTranBranch(LimitBranchId.byValue(entity.getTranBranch()));
        eo.setCheckObjVal(entity.getCheckObjVal());
        eo.setLimitSceneNo(entity.getLimitSceneNo());
        eo.setTranChannel(SourceType.byValue(entity.getTranChannel()));
        eo.setValidFlag(entity.getValidFlag());
        eo.setRecordStatus(RecordStatus.byValue(entity.getRecordStatus()));
        eo.setTranDate(entity.getTranDate());
        eo.setLimitCtrlInfo(entity.getLimitCtrlInfo());
        eo.setCreateTimestamp(entity.getCreateTimestamp());
        eo.setLimitConvertAmt(entity.getLimitConvertAmt());
        eo.setClientNo(entity.getClientNo());
        eo.setLastUpdTimestamp(entity.getLastUpdTimestamp());
        eo.setReference(entity.getReference());
        eo.setTranAmt(entity.getTranAmt());
        eo.setSumType(SumType.byValue(entity.getSumType()));
        return eo;
    }

    public static RbLimitSumJnl eoToEntity(RbLimitSumJnlEO eo) {
        if (eo == null) {
            return null;
        }
        RbLimitSumJnl entity = new RbLimitSumJnl();
        entity.setPreReference(eo.getPreReference());
        entity.setTranCcy(eo.getTranCcy() == null ? null : eo.getTranCcy().getValue());
        entity.setTranBranch(eo.getTranBranch() == null ? null : eo.getTranBranch().getValue());
        entity.setCheckObjVal(eo.getCheckObjVal());
        entity.setLimitSceneNo(eo.getLimitSceneNo());
        entity.setTranChannel(eo.getTranChannel() == null ? null : eo.getTranChannel().getValue());
        entity.setValidFlag(eo.getValidFlag());
        entity.setRecordStatus(eo.getRecordStatus() == null ? null : eo.getRecordStatus().getValue());
        entity.setTranDate(eo.getTranDate());
        entity.setLimitCtrlInfo(eo.getLimitCtrlInfo());
        entity.setCreateTimestamp(eo.getCreateTimestamp());
        entity.setLimitConvertAmt(eo.getLimitConvertAmt());
        entity.setClientNo(eo.getClientNo());
        entity.setLastUpdTimestamp(eo.getLastUpdTimestamp());
        entity.setReference(eo.getReference());
        entity.setTranAmt(eo.getTranAmt());
        entity.setSumType(eo.getSumType() == null ? null : eo.getSumType().getValue());
        return entity;
    }

    public static RbLimitSumJnlExample eoToEntityExample(RbLimitSumJnlEO eo) {
        if (eo == null) {
            return null;
        }
        RbLimitSumJnlExample example = new RbLimitSumJnlExample();
        RbLimitSumJnlExample.Criteria criteria = example.createCriteria();
        if (eo.getPreReference() != null) criteria.andPreReferenceEqualTo(eo.getPreReference());
        if (eo.getTranCcy() != null) criteria.andTranCcyEqualTo(eo.getTranCcy().getValue());
        if (eo.getTranBranch() != null) criteria.andTranBranchEqualTo(eo.getTranBranch().getValue());
        if (eo.getCheckObjVal() != null) criteria.andCheckObjValEqualTo(eo.getCheckObjVal());
        if (eo.getLimitSceneNo() != null) criteria.andLimitSceneNoEqualTo(eo.getLimitSceneNo());
        if (eo.getTranChannel() != null) criteria.andTranChannelEqualTo(eo.getTranChannel().getValue());
        if (eo.getValidFlag() != null) criteria.andValidFlagEqualTo(eo.getValidFlag());
        if (eo.getRecordStatus() != null) criteria.andRecordStatusEqualTo(eo.getRecordStatus().getValue());
        if (eo.getTranDate() != null) criteria.andTranDateEqualTo(eo.getTranDate());
        if (eo.getLimitCtrlInfo() != null) criteria.andLimitCtrlInfoEqualTo(eo.getLimitCtrlInfo());
        if (eo.getCreateTimestamp() != null) criteria.andCreateTimestampEqualTo(eo.getCreateTimestamp());
        if (eo.getLimitConvertAmt() != null) criteria.andLimitConvertAmtEqualTo(eo.getLimitConvertAmt());
        if (eo.getClientNo() != null) criteria.andClientNoEqualTo(eo.getClientNo());
        if (eo.getLastUpdTimestamp() != null) criteria.andLastUpdTimestampEqualTo(eo.getLastUpdTimestamp());
        if (eo.getReference() != null) criteria.andReferenceEqualTo(eo.getReference());
        if (eo.getTranAmt() != null) criteria.andTranAmtEqualTo(eo.getTranAmt());
        if (eo.getSumType() != null) criteria.andSumTypeEqualTo(eo.getSumType().getValue());
        return example;
    }
}