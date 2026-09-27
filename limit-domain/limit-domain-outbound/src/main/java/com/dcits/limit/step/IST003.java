package com.dcits.limit.step;

import com.dcits.limit.facade.bo.ST003InputBO;
import com.dcits.limit.facade.bo.ST003OutputBO;

/**
 * ST003 计算限额累计金额 步骤接口。
 *
 * <p>本步骤只读不写，无数据库写入，实现方法不使用本地事务。
 */
public interface IST003 {

    /**
     * 执行计算限额累计金额步骤。
     *
     * @param input 步骤输入
     * @return 步骤输出，含限额累计金额与限额累计笔数
     */
    ST003OutputBO execute(ST003InputBO input);
}
