package com.dcits.limit.facade.bo;

/**
 * ST002 检查账户机构是否可匹配到限额场景配置 的输入BO。
 */
public class ST002InputBO {

	/** 账号 */
	private String baseAcctNo;

	public String getBaseAcctNo() {
		return baseAcctNo;
	}

	public void setBaseAcctNo(String baseAcctNo) {
		this.baseAcctNo = baseAcctNo;
	}
}
