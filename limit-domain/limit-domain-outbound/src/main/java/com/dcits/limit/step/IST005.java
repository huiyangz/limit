package com.dcits.limit.step;

import com.dcits.limit.facade.bo.ST005InputBO;
import com.dcits.limit.facade.bo.ST005OutputBO;

/**
 * ST005 处理限额步骤接口。
 *
 * <p>事务要求：本步骤仅查询【限额控制配置】（RB_LIMIT_CTRL_CONF），无本地数据库写入，
 * 不要求调用方提供事务。</p>
 */
public interface IST005 {

	/**
	 * 处理限额：根据限额场景编码查询【限额控制配置】获取处理方式并返回检查结果
	 * （拒绝/提醒/授权）；查询无记录时本步骤返回成功且处理方式为空。
	 *
	 * @param input 步骤输入，limitSceneNo 必填
	 * @return 步骤输出，succeed=true 时 dealFlow 为检查结果对应的处理方式，查询无记录时为 null
	 */
	ST005OutputBO execute(ST005InputBO input);
}
