package com.dcits.limit.step;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dcits.limit.facade.bo.ST001InputBO;
import com.dcits.limit.facade.bo.ST001OutputBO;
import com.dcits.limit.facade.components.IRbLimitCtrlConfBcc;
import com.dcits.limit.facade.components.IRbLimitCtrlCustomInfoBcc;
import com.dcits.limit.facade.eo.RbLimitCtrlConfEO;
import com.dcits.limit.facade.eo.RbLimitCtrlCustomInfoEO;

/**
 * ST001 获取限额场景编码
 */
@Service
public class ST001Pbc implements IST001 {

    private final IRbLimitCtrlConfBcc rbLimitCtrlConfBcc;
    private final IRbLimitCtrlCustomInfoBcc rbLimitCtrlCustomInfoBcc;

    public ST001Pbc(IRbLimitCtrlConfBcc rbLimitCtrlConfBcc,
                    IRbLimitCtrlCustomInfoBcc rbLimitCtrlCustomInfoBcc) {
        this.rbLimitCtrlConfBcc = rbLimitCtrlConfBcc;
        this.rbLimitCtrlCustomInfoBcc = rbLimitCtrlCustomInfoBcc;
    }

    @Override
    public ST001OutputBO execute(ST001InputBO input) {
        ST001OutputBO output = new ST001OutputBO();
        // 账号、客户号来源实体未在本步骤查询，按输入回显
        output.setBaseAcctNo(input.getBaseAcctNo());
        output.setClientNo(input.getClientNo());

        // 子步骤1 获取允许自定义标识：根据[限额场景编码]查询【限额控制配置表】
        RbLimitCtrlConfEO confQuery = new RbLimitCtrlConfEO();
        confQuery.setLimitSceneNo(input.getLimitSceneNo());
        List<RbLimitCtrlConfEO> confList = rbLimitCtrlConfBcc.findByEo(confQuery);
        if (confList == null || confList.isEmpty()) {
            // 【限额控制配置表】无记录，返回[限额场景编码]为空，正常结束
            output.setSucceed(true);
            return output;
        }
        RbLimitCtrlConfEO conf = confList.get(0);
        output.setLimitSceneNo(conf.getLimitSceneNo());
        output.setAllowCustomFlag(conf.getAllowCustomFlag());
        output.setOnlyCustom(conf.getOnlyCustom());
        output.setTempLimitFlag(conf.getTempLimitFlag());
        output.setTempLimitValidTerm(conf.getTempLimitValidTerm());

        // 子步骤2 获取限额信息：[允许自定义标识]为"N-否"时返回[限额场景编码]，否则跳转至《获取自定义限额》
        if ("N".equals(conf.getAllowCustomFlag())) {
            output.setSucceed(true);
            return output;
        }

        // 子步骤3 获取自定义限额：查询【限额控制客户自定义配置表】[限额场景编码]等于{限额场景编码}、
        // [客户号]等于{客户号}的记录，取[生效日期]小于等于{交易日期}中生效日期最大的一条
        RbLimitCtrlCustomInfoEO customQuery = new RbLimitCtrlCustomInfoEO();
        customQuery.setLimitSceneNo(input.getLimitSceneNo());
        customQuery.setClientNo(input.getClientNo());
        List<RbLimitCtrlCustomInfoEO> customList = rbLimitCtrlCustomInfoBcc.findByEo(customQuery);
        RbLimitCtrlCustomInfoEO custom = selectEffectiveCustom(customList, input.getTranDate());

        // 子步骤4 获取限额值
        if (custom == null) {
            // 子步骤4.3/4.4：[自定义限额]不存在，[仅检查客户自定义标志]为"Y-是"时返回[限额场景编码]为空，
            // 为"N-否"时返回[限额场景编码]
            if ("Y".equals(conf.getOnlyCustom())) {
                output.setLimitSceneNo(null);
            }
            output.setSucceed(true);
            return output;
        }
        output.setCustomLimitSceneNo(custom.getLimitSceneNo());
        output.setCustomTempLimitFlag(custom.getTempLimitFlag());
        output.setEffectDate(custom.getEffectDate());
        output.setExpireDate(custom.getExpireDate());
        // 子步骤4.1：[自定义限额]存在且[临时限额标志]为"Y-是"、{交易日期}大于[失效日期]时返回[限额场景编码]为空；
        // {交易日期}小于等于[失效日期]时返回[限额场景编码]；子步骤4.2：[临时限额标志]为"N-否"时返回[限额场景编码]
        if ("Y".equals(custom.getTempLimitFlag()) && input.getTranDate().after(custom.getExpireDate())) {
            output.setLimitSceneNo(null);
        }
        output.setSucceed(true);
        return output;
    }

    /**
     * 子步骤3：筛选[生效日期]小于等于{交易日期}的自定义限额记录，多条时取[生效日期]最大的一条；
     * 无符合条件记录时返回 null，表示自定义限额不存在
     */
    private RbLimitCtrlCustomInfoEO selectEffectiveCustom(List<RbLimitCtrlCustomInfoEO> customList, Date tranDate) {
        RbLimitCtrlCustomInfoEO selected = null;
        if (customList == null) {
            return null;
        }
        for (RbLimitCtrlCustomInfoEO item : customList) {
            // 生效日期不满足"小于等于交易日期"的记录不参与取最大
            if (item.getEffectDate() == null || item.getEffectDate().after(tranDate)) {
                continue;
            }
            if (selected == null || item.getEffectDate().after(selected.getEffectDate())) {
                selected = item;
            }
        }
        return selected;
    }
}
