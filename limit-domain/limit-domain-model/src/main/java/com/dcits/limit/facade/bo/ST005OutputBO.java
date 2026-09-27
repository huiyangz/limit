package com.dcits.limit.facade.bo;

import com.dcits.common.step.StepResult;
import com.dcits.limit.enums.DealFlow;

/**
 * ST005 处理限额步骤输出BO
 */
public class ST005OutputBO extends StepResult {

	/** 处理方式 */
	private DealFlow dealFlow;

	public DealFlow getDealFlow() {
		return dealFlow;
	}

	public void setDealFlow(DealFlow dealFlow) {
		this.dealFlow = dealFlow;
	}
}
