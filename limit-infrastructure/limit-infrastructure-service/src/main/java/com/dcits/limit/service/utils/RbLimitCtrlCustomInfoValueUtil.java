package com.dcits.limit.service.utils;

import com.dcits.limit.entity.RbLimitCtrlCustomInfo;
import com.dcits.limit.entity.RbLimitCtrlCustomInfoExample;
import com.dcits.limit.facade.eo.RbLimitCtrlCustomInfoEO;
import com.dcits.limit.enums.CtrlItemType;

public final class RbLimitCtrlCustomInfoValueUtil {
    private RbLimitCtrlCustomInfoValueUtil() {
    }

    public static RbLimitCtrlCustomInfoEO entityToEo(RbLimitCtrlCustomInfo entity) {
        if (entity == null) {
            return null;
        }
        RbLimitCtrlCustomInfoEO eo = new RbLimitCtrlCustomInfoEO();
        eo.setCheckObjVal(entity.getCheckObjVal());
        eo.setLimitCtrlAmt(entity.getLimitCtrlAmt());
        eo.setCreateTimestamp(entity.getCreateTimestamp());
        eo.setEffectDate(entity.getEffectDate());
        eo.setLastUpdTimestamp(entity.getLastUpdTimestamp());
        eo.setLimitSceneNo(entity.getLimitSceneNo());
        eo.setLimitCtrlNum(entity.getLimitCtrlNum());
        eo.setTempLimitFlag(entity.getTempLimitFlag());
        eo.setCtrlItemType(CtrlItemType.byValue(entity.getCtrlItemType()));
        eo.setClientNo(entity.getClientNo());
        eo.setExpireDate(entity.getExpireDate());
        return eo;
    }

    public static RbLimitCtrlCustomInfo eoToEntity(RbLimitCtrlCustomInfoEO eo) {
        if (eo == null) {
            return null;
        }
        RbLimitCtrlCustomInfo entity = new RbLimitCtrlCustomInfo();
        entity.setCheckObjVal(eo.getCheckObjVal());
        entity.setLimitCtrlAmt(eo.getLimitCtrlAmt());
        entity.setCreateTimestamp(eo.getCreateTimestamp());
        entity.setEffectDate(eo.getEffectDate());
        entity.setLastUpdTimestamp(eo.getLastUpdTimestamp());
        entity.setLimitSceneNo(eo.getLimitSceneNo());
        entity.setLimitCtrlNum(eo.getLimitCtrlNum());
        entity.setTempLimitFlag(eo.getTempLimitFlag());
        entity.setCtrlItemType(eo.getCtrlItemType() == null ? null : eo.getCtrlItemType().getValue());
        entity.setClientNo(eo.getClientNo());
        entity.setExpireDate(eo.getExpireDate());
        return entity;
    }

    public static RbLimitCtrlCustomInfoExample eoToEntityExample(RbLimitCtrlCustomInfoEO eo) {
        if (eo == null) {
            return null;
        }
        RbLimitCtrlCustomInfoExample example = new RbLimitCtrlCustomInfoExample();
        RbLimitCtrlCustomInfoExample.Criteria criteria = example.createCriteria();
        if (eo.getCheckObjVal() != null) criteria.andCheckObjValEqualTo(eo.getCheckObjVal());
        if (eo.getLimitCtrlAmt() != null) criteria.andLimitCtrlAmtEqualTo(eo.getLimitCtrlAmt());
        if (eo.getCreateTimestamp() != null) criteria.andCreateTimestampEqualTo(eo.getCreateTimestamp());
        if (eo.getEffectDate() != null) criteria.andEffectDateEqualTo(eo.getEffectDate());
        if (eo.getLastUpdTimestamp() != null) criteria.andLastUpdTimestampEqualTo(eo.getLastUpdTimestamp());
        if (eo.getLimitSceneNo() != null) criteria.andLimitSceneNoEqualTo(eo.getLimitSceneNo());
        if (eo.getLimitCtrlNum() != null) criteria.andLimitCtrlNumEqualTo(eo.getLimitCtrlNum());
        if (eo.getTempLimitFlag() != null) criteria.andTempLimitFlagEqualTo(eo.getTempLimitFlag());
        if (eo.getCtrlItemType() != null) criteria.andCtrlItemTypeEqualTo(eo.getCtrlItemType().getValue());
        if (eo.getClientNo() != null) criteria.andClientNoEqualTo(eo.getClientNo());
        if (eo.getExpireDate() != null) criteria.andExpireDateEqualTo(eo.getExpireDate());
        return example;
    }
}