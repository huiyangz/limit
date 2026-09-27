package com.dcits.limit.facade.bo;

import com.dcits.common.step.StepResult;
import com.dcits.limit.enums.LimitBranchId;
import com.dcits.limit.enums.LimitBranchRange;

/**
 * ST002 检查账户机构是否可匹配到限额场景配置 的输出BO。
 */
public class ST002OutputBO extends StepResult {

	/** 限额场景编码，取子步骤2或5.1匹配到的启用配置记录 */
	private String limitSceneNo;
	/** 限额机构编码，取子步骤2或5.1匹配到的启用配置记录 */
	private LimitBranchId limitBranchId;
	/** 限额机构范围，取子步骤2或5.1匹配到的启用配置记录 */
	private LimitBranchRange limitBranchRange;
	/** 启用标志，取子步骤2或5.1匹配到的启用配置记录 */
	private String validFlag;
	/** 账号，取输入回显 */
	private String baseAcctNo;
	/** 账户开立行行号，取子步骤1查询结果 */
	private LimitBranchId acctBranch;
	/** 归属机构号，取账户开立行的机构信息记录 */
	private LimitBranchId branch;
	/** 归属上级机构号，取账户开立行的机构信息记录 */
	private LimitBranchId attachedTo;

	public String getLimitSceneNo() {
		return limitSceneNo;
	}

	public void setLimitSceneNo(String limitSceneNo) {
		this.limitSceneNo = limitSceneNo;
	}

	public LimitBranchId getLimitBranchId() {
		return limitBranchId;
	}

	public void setLimitBranchId(LimitBranchId limitBranchId) {
		this.limitBranchId = limitBranchId;
	}

	public LimitBranchRange getLimitBranchRange() {
		return limitBranchRange;
	}

	public void setLimitBranchRange(LimitBranchRange limitBranchRange) {
		this.limitBranchRange = limitBranchRange;
	}

	public String getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	public String getBaseAcctNo() {
		return baseAcctNo;
	}

	public void setBaseAcctNo(String baseAcctNo) {
		this.baseAcctNo = baseAcctNo;
	}

	public LimitBranchId getAcctBranch() {
		return acctBranch;
	}

	public void setAcctBranch(LimitBranchId acctBranch) {
		this.acctBranch = acctBranch;
	}

	public LimitBranchId getBranch() {
		return branch;
	}

	public void setBranch(LimitBranchId branch) {
		this.branch = branch;
	}

	public LimitBranchId getAttachedTo() {
		return attachedTo;
	}

	public void setAttachedTo(LimitBranchId attachedTo) {
		this.attachedTo = attachedTo;
	}
}
