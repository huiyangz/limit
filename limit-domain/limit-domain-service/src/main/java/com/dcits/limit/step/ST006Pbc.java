package com.dcits.limit.step;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dcits.limit.facade.bo.ST006InputBO;
import com.dcits.limit.facade.bo.ST006OutputBO;
import com.dcits.limit.facade.components.IRbLimitSumInfoBcc;
import com.dcits.limit.facade.eo.RbLimitSumInfoEO;

/**
 * ST006 获取累计限额 步骤实现
 *
 * <p>步骤描述1 获取累计限额：根据{客户号}以及[限额场景编码]查询【限额累计信息表（RB_LIMIT_SUM_INFO）】
 * 获取其对应的限额累计金额、限额累计笔数；按该条件至多匹配一条记录，无匹配记录时成功返回，
 * 限额累计金额、限额累计笔数为空。本步骤无业务失败场景，失败仅由技术异常传播表达。</p>
 */
@Service
public class ST006Pbc implements IST006 {

    private final IRbLimitSumInfoBcc rbLimitSumInfoBcc;

    public ST006Pbc(IRbLimitSumInfoBcc rbLimitSumInfoBcc) {
        this.rbLimitSumInfoBcc = rbLimitSumInfoBcc;
    }

    /**
     * 子步骤1 获取累计限额：以{客户号}+[限额场景编码]为组合条件（非主键查询，不携带限额检查对象值）
     * 查询【限额累计信息表 RB_LIMIT_SUM_INFO】，命中时取该记录的客户号、限额场景编码、
     * 限额累计金额、限额累计笔数；无匹配记录时业务输出均为空，正常完成按成功返回。
     * EO 的限额累计笔数属性实际命名为"否"，按真实访问器取值。
     */
    @Override
    public ST006OutputBO execute(ST006InputBO input) {
        validateInput(input);
        ST006OutputBO output = new ST006OutputBO();

        RbLimitSumInfoEO condition = new RbLimitSumInfoEO();
        condition.setClientNo(input.getClientNo());
        condition.setLimitSceneNo(input.getLimitSceneNo());
        List<RbLimitSumInfoEO> records = rbLimitSumInfoBcc.findByEo(condition);
        // SPEC 约定按该条件至多匹配一条记录，命中即取该条
        if (!records.isEmpty()) {
            RbLimitSumInfoEO record = records.get(0);
            output.setClientNo(record.getClientNo());
            output.setLimitSceneNo(record.getLimitSceneNo());
            output.setLimitSumAmt(record.getLimitSumAmt());
            output.setLimitSumNum(record.get否());
        }
        // 无匹配记录时成功返回，限额累计金额、限额累计笔数为空；本步骤无业务失败场景
        output.setSucceed(true);
        return output;
    }

    /** 输入必填校验：不满足时按技术异常抛出，本步骤无业务失败场景 */
    private static void validateInput(ST006InputBO input) {
        if (input == null) {
            throw new IllegalArgumentException("ST006输入BO不能为空");
        }
        if (isBlank(input.getBaseAcctNo())) {
            throw new IllegalArgumentException("账号不能为空");
        }
        if (isBlank(input.getClientNo())) {
            throw new IllegalArgumentException("客户号不能为空");
        }
        if (isBlank(input.getLimitSceneNo())) {
            throw new IllegalArgumentException("限额场景编码不能为空");
        }
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
