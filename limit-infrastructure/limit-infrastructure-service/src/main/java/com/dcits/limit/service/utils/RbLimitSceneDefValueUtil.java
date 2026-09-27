package com.dcits.limit.service.utils;

import com.dcits.limit.entity.RbLimitSceneDef;
import com.dcits.limit.entity.RbLimitSceneDefExample;
import com.dcits.limit.facade.eo.RbLimitSceneDefEO;
import com.dcits.limit.enums.TranCcy;
import com.dcits.limit.enums.CheckObjType;
import com.dcits.limit.enums.LimitConvert;
import com.dcits.limit.enums.LimitMainType;

public final class RbLimitSceneDefValueUtil {
    private RbLimitSceneDefValueUtil() {
    }

    public static RbLimitSceneDefEO entityToEo(RbLimitSceneDef entity) {
        if (entity == null) {
            return null;
        }
        RbLimitSceneDefEO eo = new RbLimitSceneDefEO();
        eo.setLimitCcy(TranCcy.byValue(entity.getLimitCcy()));
        eo.setCheckObjType(CheckObjType.byValue(entity.getCheckObjType()));
        eo.setLastUpdTimestamp(entity.getLastUpdTimestamp());
        eo.setLimitSceneDesc(entity.getLimitSceneDesc());
        eo.setCreateTimestamp(entity.getCreateTimestamp());
        eo.setValidFlag(entity.getValidFlag());
        eo.setLimitConvert(LimitConvert.byValue(entity.getLimitConvert()));
        eo.setLimitMainType(LimitMainType.byValue(entity.getLimitMainType()));
        eo.setLimitSceneNo(entity.getLimitSceneNo());
        return eo;
    }

    public static RbLimitSceneDef eoToEntity(RbLimitSceneDefEO eo) {
        if (eo == null) {
            return null;
        }
        RbLimitSceneDef entity = new RbLimitSceneDef();
        entity.setLimitCcy(eo.getLimitCcy() == null ? null : eo.getLimitCcy().getValue());
        entity.setCheckObjType(eo.getCheckObjType() == null ? null : eo.getCheckObjType().getValue());
        entity.setLastUpdTimestamp(eo.getLastUpdTimestamp());
        entity.setLimitSceneDesc(eo.getLimitSceneDesc());
        entity.setCreateTimestamp(eo.getCreateTimestamp());
        entity.setValidFlag(eo.getValidFlag());
        entity.setLimitConvert(eo.getLimitConvert() == null ? null : eo.getLimitConvert().getValue());
        entity.setLimitMainType(eo.getLimitMainType() == null ? null : eo.getLimitMainType().getValue());
        entity.setLimitSceneNo(eo.getLimitSceneNo());
        return entity;
    }

    public static RbLimitSceneDefExample eoToEntityExample(RbLimitSceneDefEO eo) {
        if (eo == null) {
            return null;
        }
        RbLimitSceneDefExample example = new RbLimitSceneDefExample();
        RbLimitSceneDefExample.Criteria criteria = example.createCriteria();
        if (eo.getLimitCcy() != null) criteria.andLimitCcyEqualTo(eo.getLimitCcy().getValue());
        if (eo.getCheckObjType() != null) criteria.andCheckObjTypeEqualTo(eo.getCheckObjType().getValue());
        if (eo.getLastUpdTimestamp() != null) criteria.andLastUpdTimestampEqualTo(eo.getLastUpdTimestamp());
        if (eo.getLimitSceneDesc() != null) criteria.andLimitSceneDescEqualTo(eo.getLimitSceneDesc());
        if (eo.getCreateTimestamp() != null) criteria.andCreateTimestampEqualTo(eo.getCreateTimestamp());
        if (eo.getValidFlag() != null) criteria.andValidFlagEqualTo(eo.getValidFlag());
        if (eo.getLimitConvert() != null) criteria.andLimitConvertEqualTo(eo.getLimitConvert().getValue());
        if (eo.getLimitMainType() != null) criteria.andLimitMainTypeEqualTo(eo.getLimitMainType().getValue());
        if (eo.getLimitSceneNo() != null) criteria.andLimitSceneNoEqualTo(eo.getLimitSceneNo());
        return example;
    }
}