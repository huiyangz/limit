package com.dcits.limit.service.utils;

import com.dcits.limit.entity.FmBranch;
import com.dcits.limit.entity.FmBranchExample;
import com.dcits.limit.facade.eo.FmBranchEO;
import com.dcits.limit.enums.LimitBranchId;
import com.dcits.limit.enums.City;
import com.dcits.limit.enums.BranchType;
import com.dcits.limit.enums.District;
import com.dcits.limit.enums.HierarchyCode;
import com.dcits.limit.enums.District;
import com.dcits.limit.enums.LimitBranchId;
import com.dcits.limit.enums.Country;
import com.dcits.limit.enums.ProfitCenter;
import com.dcits.limit.enums.LimitBranchId;

public final class FmBranchValueUtil {
    private FmBranchValueUtil() {
    }

    public static FmBranchEO entityToEo(FmBranch entity) {
        if (entity == null) {
            return null;
        }
        FmBranchEO eo = new FmBranchEO();
        eo.setBranch(LimitBranchId.byValue(entity.getBranch()));
        eo.setAreaCode(entity.getAreaCode());
        eo.setFxOrganCode(entity.getFxOrganCode());
        eo.setEodFlag(entity.getEodFlag());
        eo.setFtaFlag(entity.getFtaFlag());
        eo.setCity(City.byValue(entity.getCity()));
        eo.setBranchType(BranchType.byValue(entity.getBranchType()));
        eo.setFtaCode(entity.getFtaCode());
        eo.setStartDate(entity.getStartDate());
        eo.setBranchName(entity.getBranchName());
        eo.setIpAddr(entity.getIpAddr());
        eo.setPbocFinancingNo(entity.getPbocFinancingNo());
        eo.setDistrict(District.byValue(entity.getDistrict()));
        eo.setCreateDate(entity.getCreateDate());
        eo.setCityBranchFlag(entity.getCityBranchFlag());
        eo.setHierarchyCode(HierarchyCode.byValue(entity.getHierarchyCode()));
        eo.setBranchShort(entity.getBranchShort());
        eo.setPostalCode(entity.getPostalCode());
        eo.setCompany(entity.getCompany());
        eo.setEndDate(entity.getEndDate());
        eo.setState(District.byValue(entity.getState()));
        eo.setAttachedTo(LimitBranchId.byValue(entity.getAttachedTo()));
        eo.setCountry(Country.byValue(entity.getCountry()));
        eo.setProfitCenter(ProfitCenter.byValue(entity.getProfitCenter()));
        eo.setSubBranchCode(LimitBranchId.byValue(entity.getSubBranchCode()));
        eo.setTranTimestamp(entity.getTranTimestamp());
        eo.setInternalClient(entity.getInternalClient());
        return eo;
    }

    public static FmBranch eoToEntity(FmBranchEO eo) {
        if (eo == null) {
            return null;
        }
        FmBranch entity = new FmBranch();
        entity.setBranch(eo.getBranch() == null ? null : eo.getBranch().getValue());
        entity.setAreaCode(eo.getAreaCode());
        entity.setFxOrganCode(eo.getFxOrganCode());
        entity.setEodFlag(eo.getEodFlag());
        entity.setFtaFlag(eo.getFtaFlag());
        entity.setCity(eo.getCity() == null ? null : eo.getCity().getValue());
        entity.setBranchType(eo.getBranchType() == null ? null : eo.getBranchType().getValue());
        entity.setFtaCode(eo.getFtaCode());
        entity.setStartDate(eo.getStartDate());
        entity.setBranchName(eo.getBranchName());
        entity.setIpAddr(eo.getIpAddr());
        entity.setPbocFinancingNo(eo.getPbocFinancingNo());
        entity.setDistrict(eo.getDistrict() == null ? null : eo.getDistrict().getValue());
        entity.setCreateDate(eo.getCreateDate());
        entity.setCityBranchFlag(eo.getCityBranchFlag());
        entity.setHierarchyCode(eo.getHierarchyCode() == null ? null : eo.getHierarchyCode().getValue());
        entity.setBranchShort(eo.getBranchShort());
        entity.setPostalCode(eo.getPostalCode());
        entity.setCompany(eo.getCompany());
        entity.setEndDate(eo.getEndDate());
        entity.setState(eo.getState() == null ? null : eo.getState().getValue());
        entity.setAttachedTo(eo.getAttachedTo() == null ? null : eo.getAttachedTo().getValue());
        entity.setCountry(eo.getCountry() == null ? null : eo.getCountry().getValue());
        entity.setProfitCenter(eo.getProfitCenter() == null ? null : eo.getProfitCenter().getValue());
        entity.setSubBranchCode(eo.getSubBranchCode() == null ? null : eo.getSubBranchCode().getValue());
        entity.setTranTimestamp(eo.getTranTimestamp());
        entity.setInternalClient(eo.getInternalClient());
        return entity;
    }

    public static FmBranchExample eoToEntityExample(FmBranchEO eo) {
        if (eo == null) {
            return null;
        }
        FmBranchExample example = new FmBranchExample();
        FmBranchExample.Criteria criteria = example.createCriteria();
        if (eo.getBranch() != null) criteria.andBranchEqualTo(eo.getBranch().getValue());
        if (eo.getAreaCode() != null) criteria.andAreaCodeEqualTo(eo.getAreaCode());
        if (eo.getFxOrganCode() != null) criteria.andFxOrganCodeEqualTo(eo.getFxOrganCode());
        if (eo.getEodFlag() != null) criteria.andEodFlagEqualTo(eo.getEodFlag());
        if (eo.getFtaFlag() != null) criteria.andFtaFlagEqualTo(eo.getFtaFlag());
        if (eo.getCity() != null) criteria.andCityEqualTo(eo.getCity().getValue());
        if (eo.getBranchType() != null) criteria.andBranchTypeEqualTo(eo.getBranchType().getValue());
        if (eo.getFtaCode() != null) criteria.andFtaCodeEqualTo(eo.getFtaCode());
        if (eo.getStartDate() != null) criteria.andStartDateEqualTo(eo.getStartDate());
        if (eo.getBranchName() != null) criteria.andBranchNameEqualTo(eo.getBranchName());
        if (eo.getIpAddr() != null) criteria.andIpAddrEqualTo(eo.getIpAddr());
        if (eo.getPbocFinancingNo() != null) criteria.andPbocFinancingNoEqualTo(eo.getPbocFinancingNo());
        if (eo.getDistrict() != null) criteria.andDistrictEqualTo(eo.getDistrict().getValue());
        if (eo.getCreateDate() != null) criteria.andCreateDateEqualTo(eo.getCreateDate());
        if (eo.getCityBranchFlag() != null) criteria.andCityBranchFlagEqualTo(eo.getCityBranchFlag());
        if (eo.getHierarchyCode() != null) criteria.andHierarchyCodeEqualTo(eo.getHierarchyCode().getValue());
        if (eo.getBranchShort() != null) criteria.andBranchShortEqualTo(eo.getBranchShort());
        if (eo.getPostalCode() != null) criteria.andPostalCodeEqualTo(eo.getPostalCode());
        if (eo.getCompany() != null) criteria.andCompanyEqualTo(eo.getCompany());
        if (eo.getEndDate() != null) criteria.andEndDateEqualTo(eo.getEndDate());
        if (eo.getState() != null) criteria.andStateEqualTo(eo.getState().getValue());
        if (eo.getAttachedTo() != null) criteria.andAttachedToEqualTo(eo.getAttachedTo().getValue());
        if (eo.getCountry() != null) criteria.andCountryEqualTo(eo.getCountry().getValue());
        if (eo.getProfitCenter() != null) criteria.andProfitCenterEqualTo(eo.getProfitCenter().getValue());
        if (eo.getSubBranchCode() != null) criteria.andSubBranchCodeEqualTo(eo.getSubBranchCode().getValue());
        if (eo.getTranTimestamp() != null) criteria.andTranTimestampEqualTo(eo.getTranTimestamp());
        if (eo.getInternalClient() != null) criteria.andInternalClientEqualTo(eo.getInternalClient());
        return example;
    }
}