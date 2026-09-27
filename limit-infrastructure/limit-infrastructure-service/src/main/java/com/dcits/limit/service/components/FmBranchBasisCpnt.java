package com.dcits.limit.service.components;

import com.dcits.limit.enums.BranchType;
import com.dcits.limit.enums.City;
import com.dcits.limit.enums.Country;
import com.dcits.limit.enums.District;
import com.dcits.limit.enums.HierarchyCode;
import com.dcits.limit.enums.LimitBranchId;
import com.dcits.limit.enums.ProfitCenter;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.dcits.limit.entity.FmBranch;
import com.dcits.limit.entity.FmBranchExample;
import com.dcits.limit.facade.components.IFmBranchBcc;
import com.dcits.limit.facade.eo.FmBranchEO;
import com.dcits.limit.repo.FmBranchMapper;
import com.dcits.limit.service.utils.FmBranchValueUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FmBranchBasisCpnt implements IFmBranchBcc {
    @Autowired
    FmBranchMapper fmBranchMapper;

    @Override
    public long countByEo(FmBranchEO eo) {
        FmBranchExample example = FmBranchValueUtil.eoToEntityExample(eo);
        return fmBranchMapper.countByExample(example);
    }

    @Override
    public int removeByEo(FmBranchEO eo) {
        FmBranchExample example = FmBranchValueUtil.eoToEntityExample(eo);
        return fmBranchMapper.deleteByExample(example);
    }

    @Override
    public int removeByPrimaryKey(String branch) {
        return fmBranchMapper.deleteByPrimaryKey(branch);
    }

    @Override
    public int create(FmBranchEO eo) {
        FmBranch row = FmBranchValueUtil.eoToEntity(eo);
        return fmBranchMapper.insert(row);
    }

    @Override
    public int createSelective(FmBranchEO eo) {
        FmBranch row = FmBranchValueUtil.eoToEntity(eo);
        return fmBranchMapper.insertSelective(row);
    }

    @Override
    public List<FmBranchEO> findByEo(FmBranchEO eo) {
        FmBranchExample example = FmBranchValueUtil.eoToEntityExample(eo);
        List<FmBranchEO> result = new ArrayList<>();
        List<FmBranch> dbResult = fmBranchMapper.selectByExample(example);
        for (FmBranch item : dbResult) {
            result.add(FmBranchValueUtil.entityToEo(item));
        }
        return result;
    }

    @Override
    public FmBranchEO findByPrimaryKey(String branch) {
        return FmBranchValueUtil.entityToEo(fmBranchMapper.selectByPrimaryKey(branch));
    }

    @Override
    public int modifyByPrimaryKeySelective(FmBranchEO eo) {
        FmBranch row = FmBranchValueUtil.eoToEntity(eo);
        return fmBranchMapper.updateByPrimaryKeySelective(row);
    }

    @Override
    public int modifyByPrimaryKey(FmBranchEO eo) {
        FmBranch row = FmBranchValueUtil.eoToEntity(eo);
        return fmBranchMapper.updateByPrimaryKey(row);
    }

    FmBranchEO byBranch(LimitBranchId branch) {
        FmBranchEO eo = new FmBranchEO();
        eo.setBranch(branch);
        return eo;
    }

    /**根据归属机构号查询表《机构信息表(FM_BRANCH)》**/
    public FmBranchEO findByBranch(LimitBranchId branch) {
        List<FmBranchEO> eos = findByEo(byBranch(branch));
        return eos.isEmpty() ? null : eos.get(0);
    }
}