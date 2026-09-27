package com.dcits.limit.step;

import com.dcits.limit.facade.bo.ST006InputBO;
import com.dcits.limit.facade.bo.ST006OutputBO;

/**
 * ST006 获取累计限额。
 *
 * <p>只读查询步骤：根据客户号以及限额场景编码查询【限额累计信息表（RB_LIMIT_SUM_INFO）】，
 * 按该条件至多匹配一条记录，返回其限额累计金额、限额累计笔数；
 * 无匹配记录时成功返回，限额累计金额、限额累计笔数为空。
 * 本步骤无本地数据库写入，无事务要求。</p>
 */
public interface IST006 {

    /**
     * 执行 ST006：获取累计限额。
     * 命中记录时返回记录上的客户号、限额场景编码、限额累计金额、限额累计笔数；
     * 无匹配记录时按成功返回，四个业务输出均为空。
     *
     * @param input 输入BO，账号、客户号、限额场景编码必填
     * @return 输出BO，本步骤无业务失败场景，失败仅由技术异常传播表达
     */
    ST006OutputBO execute(ST006InputBO input);
}
