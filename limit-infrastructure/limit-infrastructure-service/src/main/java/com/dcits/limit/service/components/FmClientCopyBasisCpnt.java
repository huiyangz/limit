package com.dcits.limit.service.components;

import com.dcits.limit.enums.CategoryType;
import com.dcits.limit.enums.City;
import com.dcits.limit.enums.ClassLevel;
import com.dcits.limit.enums.ClientClass;
import com.dcits.limit.enums.ClientIndicator;
import com.dcits.limit.enums.ClientStatus;
import com.dcits.limit.enums.ClientType;
import com.dcits.limit.enums.ClientVerificationResult;
import com.dcits.limit.enums.ContactType;
import com.dcits.limit.enums.Country;
import com.dcits.limit.enums.CountryLoc;
import com.dcits.limit.enums.CrRating;
import com.dcits.limit.enums.District;
import com.dcits.limit.enums.Education;
import com.dcits.limit.enums.Industry;
import com.dcits.limit.enums.IndustryLevel;
import com.dcits.limit.enums.LimitBranchId;
import com.dcits.limit.enums.Nation;
import com.dcits.limit.enums.OccupationCode;
import com.dcits.limit.enums.Sex;
import com.dcits.limit.enums.SpokenLanguage;
import com.dcits.limit.enums.TaxFlag;
import com.dcits.limit.enums.TaxResidentFlag;
import com.dcits.limit.enums.ThawDocumentType2;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.dcits.limit.entity.FmClientCopy;
import com.dcits.limit.entity.FmClientCopyExample;
import com.dcits.limit.facade.components.IFmClientCopyBcc;
import com.dcits.limit.facade.eo.FmClientCopyEO;
import com.dcits.limit.repo.FmClientCopyMapper;
import com.dcits.limit.service.utils.FmClientCopyValueUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FmClientCopyBasisCpnt implements IFmClientCopyBcc {
    @Autowired
    FmClientCopyMapper fmClientCopyMapper;

    @Override
    public long countByEo(FmClientCopyEO eo) {
        FmClientCopyExample example = FmClientCopyValueUtil.eoToEntityExample(eo);
        return fmClientCopyMapper.countByExample(example);
    }

    @Override
    public int removeByEo(FmClientCopyEO eo) {
        FmClientCopyExample example = FmClientCopyValueUtil.eoToEntityExample(eo);
        return fmClientCopyMapper.deleteByExample(example);
    }

    @Override
    public int removeByPrimaryKey(String clientNo) {
        return fmClientCopyMapper.deleteByPrimaryKey(clientNo);
    }

    @Override
    public int create(FmClientCopyEO eo) {
        FmClientCopy row = FmClientCopyValueUtil.eoToEntity(eo);
        return fmClientCopyMapper.insert(row);
    }

    @Override
    public int createSelective(FmClientCopyEO eo) {
        FmClientCopy row = FmClientCopyValueUtil.eoToEntity(eo);
        return fmClientCopyMapper.insertSelective(row);
    }

    @Override
    public List<FmClientCopyEO> findByEo(FmClientCopyEO eo) {
        FmClientCopyExample example = FmClientCopyValueUtil.eoToEntityExample(eo);
        List<FmClientCopyEO> result = new ArrayList<>();
        List<FmClientCopy> dbResult = fmClientCopyMapper.selectByExample(example);
        for (FmClientCopy item : dbResult) {
            result.add(FmClientCopyValueUtil.entityToEo(item));
        }
        return result;
    }

    @Override
    public FmClientCopyEO findByPrimaryKey(String clientNo) {
        return FmClientCopyValueUtil.entityToEo(fmClientCopyMapper.selectByPrimaryKey(clientNo));
    }

    @Override
    public int modifyByPrimaryKeySelective(FmClientCopyEO eo) {
        FmClientCopy row = FmClientCopyValueUtil.eoToEntity(eo);
        return fmClientCopyMapper.updateByPrimaryKeySelective(row);
    }

    @Override
    public int modifyByPrimaryKey(FmClientCopyEO eo) {
        FmClientCopy row = FmClientCopyValueUtil.eoToEntity(eo);
        return fmClientCopyMapper.updateByPrimaryKey(row);
    }
}