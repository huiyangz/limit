package com.dcits.limit.step;

import com.dcits.limit.facade.bo.ST004InputBO;
import com.dcits.limit.facade.bo.ST004OutputBO;

/**
 * ST004 检查限额 步骤接口
 *
 * <p>按限额机构编码、限额场景编码查询限额控制配置，取限额控制金额与限额控制笔数，
 * 与限额累计金额、限额累计笔数比较得出限额检查结果（超限/未超限）。
 * 本步骤为只读查询，无本地数据库写入，无事务要求；无业务失败场景，失败仅由技术异常传播表达。</p>
 */
public interface IST004 {

    /**
     * 执行检查限额
     *
     * @param input 输入BO（limitSceneNo、limitSumAmt、limitSumNum、limitBranchId 均必填）
     * @return 限额控制金额、限额控制笔数为查询所得；限额累计金额、限额累计笔数为输入回显
     */
    ST004OutputBO execute(ST004InputBO input);
}
