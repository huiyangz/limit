package com.dcits.limit.step;

import com.dcits.limit.facade.bo.ST009InputBO;
import com.dcits.limit.facade.bo.ST009OutputBO;

/**
 * ST009 检查限额场景配置是否有效 步骤接口
 *
 * <p>按限额机构编码、限额场景编码查询限额控制配置，检查交易日期、交易时间是否在控制区间内。
 * 本步骤为只读查询，无本地数据库写入，无事务要求；无业务失败场景，失败仅由技术异常传播表达。</p>
 */
public interface IST009 {

    /**
     * 执行检查限额场景配置是否有效
     *
     * @param input 输入BO（tranDate、tranTimestamp、limitBranchId、limitSceneNo 均必填）
     * @return 检查通过时返回限额场景编码及四个控制区间字段；检查不通过时五个输出字段全部为空
     */
    ST009OutputBO execute(ST009InputBO input);
}
