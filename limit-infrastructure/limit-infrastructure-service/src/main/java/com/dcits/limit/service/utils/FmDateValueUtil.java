package com.dcits.limit.service.utils;

import com.dcits.limit.entity.FmDate;
import com.dcits.limit.entity.FmDateExample;
import com.dcits.limit.facade.eo.FmDateEO;

public final class FmDateValueUtil {
    private FmDateValueUtil() {
    }

    public static FmDateEO entityToEo(FmDate entity) {
        if (entity == null) {
            return null;
        }
        FmDateEO eo = new FmDateEO();
        eo.setHalfEndDate(entity.getHalfEndDate());
        eo.setQurEndDate(entity.getQurEndDate());
        eo.setAcctEodFlag(entity.getAcctEodFlag());
        eo.setMthEndDate(entity.getMthEndDate());
        eo.setNextRunDate(entity.getNextRunDate());
        eo.setCompany(entity.getCompany());
        eo.setLastRunDate(entity.getLastRunDate());
        eo.setLastUpdTimestamp(entity.getLastUpdTimestamp());
        eo.setRunDate(entity.getRunDate());
        eo.setCreateTimestamp(entity.getCreateTimestamp());
        eo.setYrEndDate(entity.getYrEndDate());
        eo.setSuspendFlag(entity.getSuspendFlag());
        eo.setSystemPhase(entity.getSystemPhase());
        return eo;
    }

    public static FmDate eoToEntity(FmDateEO eo) {
        if (eo == null) {
            return null;
        }
        FmDate entity = new FmDate();
        entity.setHalfEndDate(eo.getHalfEndDate());
        entity.setQurEndDate(eo.getQurEndDate());
        entity.setAcctEodFlag(eo.getAcctEodFlag());
        entity.setMthEndDate(eo.getMthEndDate());
        entity.setNextRunDate(eo.getNextRunDate());
        entity.setCompany(eo.getCompany());
        entity.setLastRunDate(eo.getLastRunDate());
        entity.setLastUpdTimestamp(eo.getLastUpdTimestamp());
        entity.setRunDate(eo.getRunDate());
        entity.setCreateTimestamp(eo.getCreateTimestamp());
        entity.setYrEndDate(eo.getYrEndDate());
        entity.setSuspendFlag(eo.getSuspendFlag());
        entity.setSystemPhase(eo.getSystemPhase());
        return entity;
    }

    public static FmDateExample eoToEntityExample(FmDateEO eo) {
        if (eo == null) {
            return null;
        }
        FmDateExample example = new FmDateExample();
        FmDateExample.Criteria criteria = example.createCriteria();
        if (eo.getHalfEndDate() != null) criteria.andHalfEndDateEqualTo(eo.getHalfEndDate());
        if (eo.getQurEndDate() != null) criteria.andQurEndDateEqualTo(eo.getQurEndDate());
        if (eo.getAcctEodFlag() != null) criteria.andAcctEodFlagEqualTo(eo.getAcctEodFlag());
        if (eo.getMthEndDate() != null) criteria.andMthEndDateEqualTo(eo.getMthEndDate());
        if (eo.getNextRunDate() != null) criteria.andNextRunDateEqualTo(eo.getNextRunDate());
        if (eo.getCompany() != null) criteria.andCompanyEqualTo(eo.getCompany());
        if (eo.getLastRunDate() != null) criteria.andLastRunDateEqualTo(eo.getLastRunDate());
        if (eo.getLastUpdTimestamp() != null) criteria.andLastUpdTimestampEqualTo(eo.getLastUpdTimestamp());
        if (eo.getRunDate() != null) criteria.andRunDateEqualTo(eo.getRunDate());
        if (eo.getCreateTimestamp() != null) criteria.andCreateTimestampEqualTo(eo.getCreateTimestamp());
        if (eo.getYrEndDate() != null) criteria.andYrEndDateEqualTo(eo.getYrEndDate());
        if (eo.getSuspendFlag() != null) criteria.andSuspendFlagEqualTo(eo.getSuspendFlag());
        if (eo.getSystemPhase() != null) criteria.andSystemPhaseEqualTo(eo.getSystemPhase());
        return example;
    }
}