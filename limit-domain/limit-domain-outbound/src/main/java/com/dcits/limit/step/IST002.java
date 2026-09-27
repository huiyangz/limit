package com.dcits.limit.step;

import com.dcits.limit.facade.bo.ST002InputBO;
import com.dcits.limit.facade.bo.ST002OutputBO;

/**
 * ST002 检查账户机构是否可匹配到限额场景配置。
 *
 * <p>只读查询步骤：按账号取账户开立行行号，查其启用限额场景配置；
 * 未命中时跳转获取上级机构集合，按机构层级从大到小继续匹配。本步骤无本地数据库写入，无事务要求。</p>
 */
public interface IST002 {

	/**
	 * 执行 ST002：检查账户机构是否可匹配到限额场景配置。
	 * 命中时返回启用的限额场景配置记录相关输出；未命中按成功返回，限额场景相关输出为 null。
	 *
	 * @param input 输入BO，账号必填
	 * @return 输出BO，本步骤无业务失败场景，失败仅由技术异常传播表达
	 */
	ST002OutputBO execute(ST002InputBO input);
}
