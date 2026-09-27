package com.dcits.limit.step;

import com.dcits.limit.facade.bo.ST001InputBO;
import com.dcits.limit.facade.bo.ST001OutputBO;

/**
 * ST001 获取限额场景编码 步骤接口
 */
public interface IST001 {

    /**
     * 获取限额场景编码：根据限额场景编码查询限额控制配置，
     * 按允许自定义标识决定是否查询客户自定义限额并返回有效限额场景编码。
     * 本步骤仅查询本地数据，无事务要求。
     */
    ST001OutputBO execute(ST001InputBO input);
}
