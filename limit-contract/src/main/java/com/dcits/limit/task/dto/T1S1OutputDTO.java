package com.dcits.limit.task.dto;

/**
 * T1S1 检查限额 输出DTO
 */
public class T1S1OutputDTO {

	/** 限额检查结果（非必填；输出取值来源与取值域未确认，见 SPEC 已接受结论） */
	private String limitCheckResult;

	public String getLimitCheckResult() {
		return limitCheckResult;
	}

	public void setLimitCheckResult(String limitCheckResult) {
		this.limitCheckResult = limitCheckResult;
	}
}
