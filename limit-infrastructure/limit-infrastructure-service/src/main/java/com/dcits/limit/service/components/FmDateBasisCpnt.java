package com.dcits.limit.service.components;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.dcits.limit.entity.FmDate;
import com.dcits.limit.entity.FmDateExample;
import com.dcits.limit.facade.components.IFmDateBcc;
import com.dcits.limit.facade.eo.FmDateEO;
import com.dcits.limit.repo.FmDateMapper;
import com.dcits.limit.service.utils.FmDateValueUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FmDateBasisCpnt implements IFmDateBcc {
    @Autowired
    FmDateMapper fmDateMapper;

    @Override
    public long countByEo(FmDateEO eo) {
        FmDateExample example = FmDateValueUtil.eoToEntityExample(eo);
        return fmDateMapper.countByExample(example);
    }

    @Override
    public int removeByEo(FmDateEO eo) {
        FmDateExample example = FmDateValueUtil.eoToEntityExample(eo);
        return fmDateMapper.deleteByExample(example);
    }

    @Override
    public int removeByPrimaryKey(String company) {
        return fmDateMapper.deleteByPrimaryKey(company);
    }

    @Override
    public int create(FmDateEO eo) {
        FmDate row = FmDateValueUtil.eoToEntity(eo);
        return fmDateMapper.insert(row);
    }

    @Override
    public int createSelective(FmDateEO eo) {
        FmDate row = FmDateValueUtil.eoToEntity(eo);
        return fmDateMapper.insertSelective(row);
    }

    @Override
    public List<FmDateEO> findByEo(FmDateEO eo) {
        FmDateExample example = FmDateValueUtil.eoToEntityExample(eo);
        List<FmDateEO> result = new ArrayList<>();
        List<FmDate> dbResult = fmDateMapper.selectByExample(example);
        for (FmDate item : dbResult) {
            result.add(FmDateValueUtil.entityToEo(item));
        }
        return result;
    }

    @Override
    public FmDateEO findByPrimaryKey(String company) {
        return FmDateValueUtil.entityToEo(fmDateMapper.selectByPrimaryKey(company));
    }

    @Override
    public int modifyByPrimaryKeySelective(FmDateEO eo) {
        FmDate row = FmDateValueUtil.eoToEntity(eo);
        return fmDateMapper.updateByPrimaryKeySelective(row);
    }

    @Override
    public int modifyByPrimaryKey(FmDateEO eo) {
        FmDate row = FmDateValueUtil.eoToEntity(eo);
        return fmDateMapper.updateByPrimaryKey(row);
    }
}