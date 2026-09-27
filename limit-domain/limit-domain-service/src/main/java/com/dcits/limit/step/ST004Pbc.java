package com.dcits.limit.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dcits.limit.facade.bo.ST004InputBO;
import com.dcits.limit.facade.bo.ST004OutputBO;
import com.dcits.limit.facade.components.IRbLimitCtrlConfBcc;
import com.dcits.limit.facade.eo.RbLimitCtrlConfEO;

/**
 * ST004 检查限额 步骤实现
 *
 * <p>步骤描述1 获取限额控制值：根据[限额机构编码]和[限额场景编码]查询【限额控制配置】
 * 获取对应的限额控制金额和限额控制笔数。</p>
 *
 * <p>步骤描述2 检查限额：若[限额累计金额]大于[限额控制金额]或[限额累计笔数]大于[限额控制笔数]，
 * 则限额检查结果为“超限”，否则为“未超限”。限额检查结果未定义输出承载字段（SPEC 输出表无该字段，
 * 已接受的需求处理结论未补充业务取值），不猜测字段名新增输出，仅记录日志。</p>
 */
@Service
public class ST004Pbc implements IST004 {

    private static final Logger LOGGER = LoggerFactory.getLogger(ST004Pbc.class);

    /** 限额检查结果取值：超限 */
    private static final String CHECK_RESULT_EXCEEDED = "超限";

    /** 限额检查结果取值：未超限 */
    private static final String CHECK_RESULT_NOT_EXCEEDED = "未超限";

    private final IRbLimitCtrlConfBcc rbLimitCtrlConfBcc;

    public ST004Pbc(IRbLimitCtrlConfBcc rbLimitCtrlConfBcc) {
        this.rbLimitCtrlConfBcc = rbLimitCtrlConfBcc;
    }

    @Override
    public ST004OutputBO execute(ST004InputBO input) {
        validateInput(input);
        ST004OutputBO output = new ST004OutputBO();

        // 步骤描述1 获取限额控制值：根据[限额机构编码]和[限额场景编码]查询【限额控制配置】
        RbLimitCtrlConfEO conf =
                rbLimitCtrlConfBcc.findByPrimaryKey(input.getLimitBranchId(), input.getLimitSceneNo());
        output.setLimitCtrlAmt(conf.getLimitCtrlAmt());
        output.setLimitCtrlNum(conf.getLimitCtrlNum());

        // 步骤描述2 检查限额：累计金额大于控制金额或累计笔数大于控制笔数为“超限”，否则为“未超限”
        String checkResult = isExceeded(input, conf) ? CHECK_RESULT_EXCEEDED : CHECK_RESULT_NOT_EXCEEDED;
        LOGGER.info("ST004限额检查结果={}，限额机构编码={}，限额场景编码={}，限额累计金额={}，限额控制金额={}，限额累计笔数={}，限额控制笔数={}",
                checkResult, input.getLimitBranchId(), input.getLimitSceneNo(),
                input.getLimitSumAmt(), conf.getLimitCtrlAmt(), input.getLimitSumNum(), conf.getLimitCtrlNum());

        // 限额累计金额、限额累计笔数（输出字段“否”）为输入回显
        output.setLimitSumAmt(input.getLimitSumAmt());
        output.set否(input.getLimitSumNum());

        output.setSucceed(true);
        return output;
    }

    /** 限额累计金额大于限额控制金额，或限额累计笔数大于限额控制笔数（“大于”为严格比较，金额按数值比较） */
    private boolean isExceeded(ST004InputBO input, RbLimitCtrlConfEO conf) {
        return input.getLimitSumAmt().compareTo(conf.getLimitCtrlAmt()) > 0
                || input.getLimitSumNum() > conf.getLimitCtrlNum();
    }

    /** 输入必填校验：不满足时按技术异常抛出，本步骤无业务失败场景 */
    private static void validateInput(ST004InputBO input) {
        if (input == null) {
            throw new IllegalArgumentException("ST004输入BO不能为空");
        }
        if (isBlank(input.getLimitBranchId())) {
            throw new IllegalArgumentException("限额机构编码不能为空");
        }
        if (isBlank(input.getLimitSceneNo())) {
            throw new IllegalArgumentException("限额场景编码不能为空");
        }
        if (input.getLimitSumAmt() == null) {
            throw new IllegalArgumentException("限额累计金额不能为空");
        }
        if (input.getLimitSumNum() == null) {
            throw new IllegalArgumentException("限额累计笔数不能为空");
        }
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
