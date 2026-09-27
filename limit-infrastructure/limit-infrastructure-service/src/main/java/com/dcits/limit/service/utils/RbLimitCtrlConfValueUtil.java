package com.dcits.limit.service.utils;

import com.dcits.limit.entity.RbLimitCtrlConf;
import com.dcits.limit.entity.RbLimitCtrlConfExample;
import com.dcits.limit.facade.eo.RbLimitCtrlConfEO;
import com.dcits.limit.enums.DealFlow;
import com.dcits.limit.enums.CtrlItemType;
import com.dcits.limit.enums.PeriodType;
import com.dcits.limit.enums.LimitBranchId;
import com.dcits.limit.enums.SumType;
import com.dcits.limit.enums.LimitBranchRange;

public final class RbLimitCtrlConfValueUtil {
    private RbLimitCtrlConfValueUtil() {
    }

    public static RbLimitCtrlConfEO entityToEo(RbLimitCtrlConf entity) {
        if (entity == null) {
            return null;
        }
        RbLimitCtrlConfEO eo = new RbLimitCtrlConfEO();
        eo.setDealFlow(DealFlow.byValue(entity.getDealFlow()));
        eo.setLimitCtrlEndDate(entity.getLimitCtrlEndDate());
        eo.setLimitCtrlEndTime(entity.getLimitCtrlEndTime());
        eo.setTempLimitFlag(entity.getTempLimitFlag());
        eo.setCtrlItemType(CtrlItemType.byValue(entity.getCtrlItemType()));
        eo.setLimitCtrlNum(entity.getLimitCtrlNum());
        eo.setLimitCtrlBgnDate(entity.getLimitCtrlBgnDate());
        eo.setPeriodType(PeriodType.byValue(entity.getPeriodType()));
        eo.setPeriodValue(entity.getPeriodValue());
        eo.setLimitBranchId(LimitBranchId.byValue(entity.getLimitBranchId()));
        eo.setLimitCtrlBgnTime(entity.getLimitCtrlBgnTime());
        eo.setSumType(SumType.byValue(entity.getSumType()));
        eo.setCreateTimestamp(entity.getCreateTimestamp());
        eo.setLimitSceneNo(entity.getLimitSceneNo());
        eo.setLastUpdTimestamp(entity.getLastUpdTimestamp());
        eo.setAllowCustomFlag(entity.getAllowCustomFlag());
        eo.setOnlyCustom(entity.getOnlyCustom());
        eo.setLimitBranchRange(LimitBranchRange.byValue(entity.getLimitBranchRange()));
        eo.setValidFlag(entity.getValidFlag());
        eo.setAllowExceedFlag(entity.getAllowExceedFlag());
        eo.setLimitCtrlAmt(entity.getLimitCtrlAmt());
        eo.setTempLimitValidTerm(entity.getTempLimitValidTerm());
        return eo;
    }

    public static RbLimitCtrlConf eoToEntity(RbLimitCtrlConfEO eo) {
        if (eo == null) {
            return null;
        }
        RbLimitCtrlConf entity = new RbLimitCtrlConf();
        entity.setDealFlow(eo.getDealFlow() == null ? null : eo.getDealFlow().getValue());
        entity.setLimitCtrlEndDate(eo.getLimitCtrlEndDate());
        entity.setLimitCtrlEndTime(eo.getLimitCtrlEndTime());
        entity.setTempLimitFlag(eo.getTempLimitFlag());
        entity.setCtrlItemType(eo.getCtrlItemType() == null ? null : eo.getCtrlItemType().getValue());
        entity.setLimitCtrlNum(eo.getLimitCtrlNum());
        entity.setLimitCtrlBgnDate(eo.getLimitCtrlBgnDate());
        entity.setPeriodType(eo.getPeriodType() == null ? null : eo.getPeriodType().getValue());
        entity.setPeriodValue(eo.getPeriodValue());
        entity.setLimitBranchId(eo.getLimitBranchId() == null ? null : eo.getLimitBranchId().getValue());
        entity.setLimitCtrlBgnTime(eo.getLimitCtrlBgnTime());
        entity.setSumType(eo.getSumType() == null ? null : eo.getSumType().getValue());
        entity.setCreateTimestamp(eo.getCreateTimestamp());
        entity.setLimitSceneNo(eo.getLimitSceneNo());
        entity.setLastUpdTimestamp(eo.getLastUpdTimestamp());
        entity.setAllowCustomFlag(eo.getAllowCustomFlag());
        entity.setOnlyCustom(eo.getOnlyCustom());
        entity.setLimitBranchRange(eo.getLimitBranchRange() == null ? null : eo.getLimitBranchRange().getValue());
        entity.setValidFlag(eo.getValidFlag());
        entity.setAllowExceedFlag(eo.getAllowExceedFlag());
        entity.setLimitCtrlAmt(eo.getLimitCtrlAmt());
        entity.setTempLimitValidTerm(eo.getTempLimitValidTerm());
        return entity;
    }

    public static RbLimitCtrlConfExample eoToEntityExample(RbLimitCtrlConfEO eo) {
        if (eo == null) {
            return null;
        }
        RbLimitCtrlConfExample example = new RbLimitCtrlConfExample();
        RbLimitCtrlConfExample.Criteria criteria = example.createCriteria();
        if (eo.getDealFlow() != null) criteria.andDealFlowEqualTo(eo.getDealFlow().getValue());
        if (eo.getLimitCtrlEndDate() != null) criteria.andLimitCtrlEndDateEqualTo(eo.getLimitCtrlEndDate());
        if (eo.getLimitCtrlEndTime() != null) criteria.andLimitCtrlEndTimeEqualTo(eo.getLimitCtrlEndTime());
        if (eo.getTempLimitFlag() != null) criteria.andTempLimitFlagEqualTo(eo.getTempLimitFlag());
        if (eo.getCtrlItemType() != null) criteria.andCtrlItemTypeEqualTo(eo.getCtrlItemType().getValue());
        if (eo.getLimitCtrlNum() != null) criteria.andLimitCtrlNumEqualTo(eo.getLimitCtrlNum());
        if (eo.getLimitCtrlBgnDate() != null) criteria.andLimitCtrlBgnDateEqualTo(eo.getLimitCtrlBgnDate());
        if (eo.getPeriodType() != null) criteria.andPeriodTypeEqualTo(eo.getPeriodType().getValue());
        if (eo.getPeriodValue() != null) criteria.andPeriodValueEqualTo(eo.getPeriodValue());
        if (eo.getLimitBranchId() != null) criteria.andLimitBranchIdEqualTo(eo.getLimitBranchId().getValue());
        if (eo.getLimitCtrlBgnTime() != null) criteria.andLimitCtrlBgnTimeEqualTo(eo.getLimitCtrlBgnTime());
        if (eo.getSumType() != null) criteria.andSumTypeEqualTo(eo.getSumType().getValue());
        if (eo.getCreateTimestamp() != null) criteria.andCreateTimestampEqualTo(eo.getCreateTimestamp());
        if (eo.getLimitSceneNo() != null) criteria.andLimitSceneNoEqualTo(eo.getLimitSceneNo());
        if (eo.getLastUpdTimestamp() != null) criteria.andLastUpdTimestampEqualTo(eo.getLastUpdTimestamp());
        if (eo.getAllowCustomFlag() != null) criteria.andAllowCustomFlagEqualTo(eo.getAllowCustomFlag());
        if (eo.getOnlyCustom() != null) criteria.andOnlyCustomEqualTo(eo.getOnlyCustom());
        if (eo.getLimitBranchRange() != null) criteria.andLimitBranchRangeEqualTo(eo.getLimitBranchRange().getValue());
        if (eo.getValidFlag() != null) criteria.andValidFlagEqualTo(eo.getValidFlag());
        if (eo.getAllowExceedFlag() != null) criteria.andAllowExceedFlagEqualTo(eo.getAllowExceedFlag());
        if (eo.getLimitCtrlAmt() != null) criteria.andLimitCtrlAmtEqualTo(eo.getLimitCtrlAmt());
        if (eo.getTempLimitValidTerm() != null) criteria.andTempLimitValidTermEqualTo(eo.getTempLimitValidTerm());
        return example;
    }
}