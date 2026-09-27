package com.dcits.limit.step;

import com.dcits.limit.facade.bo.ST007InputBO;
import com.dcits.limit.facade.bo.ST007OutputBO;

/**
 * ST007 匹配限额场景 步骤接口
 *
 * <p>根据业务场景因子（即限额规则关系表主键 ruleId）获取规则关系表达式，
 * 再按表达式获取限额场景编码列表，遍历查询限额场景定义表中启用标志为 Y 的配置数据：
 * 命中任一启用场景时返回检查结果"已匹配到限额场景"，否则返回"未匹配到限额场景"。
 * 本步骤仅查询本地数据，无数据库写操作，无事务要求；无业务失败场景，失败仅由技术异常传播表达。</p>
 */
public interface IST007 {
    /**
     * 匹配限额场景
     *
     * @param input 输入BO，factorName 为业务场景因子（必填）
     * @return 输出BO，checkResult 为检查结果；命中时携带 limitSceneNo / ruleRelationExpr / validFlag
     */
    ST007OutputBO execute(ST007InputBO input);
}
