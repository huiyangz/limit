package com.dcits.limit.step;

import com.dcits.limit.facade.bo.ST010InputBO;
import com.dcits.limit.facade.bo.ST010OutputBO;

/**
 * 更新累计限额（ST010）。
 *
 * 事务要求：本步骤在条件成立时按主键更新本地数据库表【限额累计信息表（RB_LIMIT_SUM_INFO）】，
 * execute 以 Spring 事务（@Transactional）执行；调用方须在本事务语义下使用返回结果。
 * 本步骤无业务失败场景，失败仅由技术异常传播表达。
 */
public interface IST010 {

    /** 更新累计限额：限额检查结果为"未超限"且累计金额或笔数大于0时，按主键更新限额累计金额 */
    ST010OutputBO execute(ST010InputBO input);
}
