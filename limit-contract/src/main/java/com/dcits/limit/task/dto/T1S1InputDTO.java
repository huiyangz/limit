package com.dcits.limit.task.dto;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.validation.constraints.NotNull;

/**
 * T1S1 检查限额 输入DTO
 */
public class T1S1InputDTO {

	/** 因子名称 */
	@NotNull
	private String factorName;
	/** 账号 */
	@NotNull
	private String baseAcctNo;
	/** 交易日期 */
	@NotNull
	private Date tranDate;
	/** 交易时间戳 */
	@NotNull
	private String tranTimestamp;
	/** 限额场景编码 */
	@NotNull
	private String limitSceneNo;
	/** 客户号 */
	@NotNull
	private String clientNo;
	/** 交易金额 */
	@NotNull
	private BigDecimal tranAmt;

	public String getFactorName() {
		return factorName;
	}

	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}

	public String getBaseAcctNo() {
		return baseAcctNo;
	}

	public void setBaseAcctNo(String baseAcctNo) {
		this.baseAcctNo = baseAcctNo;
	}

	public Date getTranDate() {
		return tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	public String getTranTimestamp() {
		return tranTimestamp;
	}

	public void setTranTimestamp(String tranTimestamp) {
		this.tranTimestamp = tranTimestamp;
	}

	public String getLimitSceneNo() {
		return limitSceneNo;
	}

	public void setLimitSceneNo(String limitSceneNo) {
		this.limitSceneNo = limitSceneNo;
	}

	public String getClientNo() {
		return clientNo;
	}

	public void setClientNo(String clientNo) {
		this.clientNo = clientNo;
	}

	public BigDecimal getTranAmt() {
		return tranAmt;
	}

	public void setTranAmt(BigDecimal tranAmt) {
		this.tranAmt = tranAmt;
	}
}
