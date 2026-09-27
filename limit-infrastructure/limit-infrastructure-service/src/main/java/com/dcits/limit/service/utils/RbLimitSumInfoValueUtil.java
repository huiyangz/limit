package com.dcits.limit.service.utils;

import com.dcits.limit.entity.RbLimitSumInfo;
import com.dcits.limit.entity.RbLimitSumInfoExample;
import com.dcits.limit.facade.eo.RbLimitSumInfoEO;
import com.dcits.limit.enums.TranCcy;

public final class RbLimitSumInfoValueUtil {
    private RbLimitSumInfoValueUtil() {
    }

    public static RbLimitSumInfoEO entityToEo(RbLimitSumInfo entity) {
        if (entity == null) {
            return null;
        }
        RbLimitSumInfoEO eo = new RbLimitSumInfoEO();
        eo.setLimitSumContent(entity.getLimitSumContent());
        eo.setEffectDate(entity.getEffectDate());
        eo.setCheckObjVal(entity.getCheckObjVal());
        eo.setExpireDate(entity.getExpireDate());
        eo.setLimitSumAmt(entity.getLimitSumAmt());
        eo.set否(entity.get否());
        eo.setClientNo(entity.getClientNo());
        eo.setCreateTimestamp(entity.getCreateTimestamp());
        eo.setPreReference(entity.getPreReference());
        eo.setLimitSceneNo(entity.getLimitSceneNo());
        eo.setTranCcy(TranCcy.byValue(entity.getTranCcy()));
        eo.setReference(entity.getReference());
        eo.setLastUpdTimestamp(entity.getLastUpdTimestamp());
        return eo;
    }

    public static RbLimitSumInfo eoToEntity(RbLimitSumInfoEO eo) {
        if (eo == null) {
            return null;
        }
        RbLimitSumInfo entity = new RbLimitSumInfo();
        entity.setLimitSumContent(eo.getLimitSumContent());
        entity.setEffectDate(eo.getEffectDate());
        entity.setCheckObjVal(eo.getCheckObjVal());
        entity.setExpireDate(eo.getExpireDate());
        entity.setLimitSumAmt(eo.getLimitSumAmt());
        entity.set否(eo.get否());
        entity.setClientNo(eo.getClientNo());
        entity.setCreateTimestamp(eo.getCreateTimestamp());
        entity.setPreReference(eo.getPreReference());
        entity.setLimitSceneNo(eo.getLimitSceneNo());
        entity.setTranCcy(eo.getTranCcy() == null ? null : eo.getTranCcy().getValue());
        entity.setReference(eo.getReference());
        entity.setLastUpdTimestamp(eo.getLastUpdTimestamp());
        return entity;
    }

    public static RbLimitSumInfoExample eoToEntityExample(RbLimitSumInfoEO eo) {
        if (eo == null) {
            return null;
        }
        RbLimitSumInfoExample example = new RbLimitSumInfoExample();
        RbLimitSumInfoExample.Criteria criteria = example.createCriteria();
        if (eo.getLimitSumContent() != null) criteria.andLimitSumContentEqualTo(eo.getLimitSumContent());
        if (eo.getEffectDate() != null) criteria.andEffectDateEqualTo(eo.getEffectDate());
        if (eo.getCheckObjVal() != null) criteria.andCheckObjValEqualTo(eo.getCheckObjVal());
        if (eo.getExpireDate() != null) criteria.andExpireDateEqualTo(eo.getExpireDate());
        if (eo.getLimitSumAmt() != null) criteria.andLimitSumAmtEqualTo(eo.getLimitSumAmt());
        if (eo.get否() != null) criteria.and否EqualTo(eo.get否());
        if (eo.getClientNo() != null) criteria.andClientNoEqualTo(eo.getClientNo());
        if (eo.getCreateTimestamp() != null) criteria.andCreateTimestampEqualTo(eo.getCreateTimestamp());
        if (eo.getPreReference() != null) criteria.andPreReferenceEqualTo(eo.getPreReference());
        if (eo.getLimitSceneNo() != null) criteria.andLimitSceneNoEqualTo(eo.getLimitSceneNo());
        if (eo.getTranCcy() != null) criteria.andTranCcyEqualTo(eo.getTranCcy().getValue());
        if (eo.getReference() != null) criteria.andReferenceEqualTo(eo.getReference());
        if (eo.getLastUpdTimestamp() != null) criteria.andLastUpdTimestampEqualTo(eo.getLastUpdTimestamp());
        return example;
    }
}