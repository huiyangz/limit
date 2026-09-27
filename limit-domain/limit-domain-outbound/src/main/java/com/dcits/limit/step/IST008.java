package com.dcits.limit.step;

import com.dcits.limit.facade.bo.ST008InputBO;
import com.dcits.limit.facade.bo.ST008OutputBO;

/**
 * ST008 登记累计限额 步骤接口。
 *
 * <p>登记路径包含限额累计信息表（RB_LIMIT_SUM_INFO）的新增写入，实现方法使用 Spring 本地事务
 * （org.springframework.transaction.annotation.Transactional）；调用方须按本地事务契约使用本步骤，
 * 本步骤不承诺跨组件原子事务。
 */
public interface IST008 {

    /**
     * 执行登记累计限额步骤。
     *
     * @param input 步骤输入
     * @return 步骤输出，含登记后的限额累计信息业务字段
     */
    ST008OutputBO execute(ST008InputBO input);
}
