package com.dcits;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 既定外部系统接口 mock。
 *
 * 依据知识《外部接口清单》逐条实现：
 * 1. 产品管理 · 查询产品信息     GET /productManagement/queryProductInfo
 * 2. 产品管理 · 查询产品利率信息 GET /productManagement/queryProductInterestRate
 * 3. 基础公共 · 生成账号         GET /basicCommon/genAcctNo
 * 4. 贷款 · 计算账号当日放款金额合计 GET /loan/calcAcctDailyLoanAmt
 *
 * 查表类接口（1、2）的源表 MB_PROD_DEFINE / MB_PROD_INT 不在本组件工程模型内，
 * 工程内无对应 Bcc 表访问组件，按“查不到返回空串”处理；
 * 不查表类接口（3、4）按清单返回拼装值或固定值。只 logger.debug，不抛异常。
 */
@RestController
public class MockExternalTask {

	private static final Logger logger = LoggerFactory.getLogger(MockExternalTask.class);

	/** 生成账号 mock 的固定前缀 */
	private static final String ACCT_NO_PREFIX = "MK";

	/** 生成账号 mock 的序号发生器 */
	private static final AtomicLong ACCT_NO_SEQ = new AtomicLong(1);

	/**
	 * 产品管理 · 查询产品信息：按「产品编号 + 参数KEY值」查产品定义表取属性值。
	 * 产品定义表 MB_PROD_DEFINE 不在本组件工程内，无 Bcc 可查，返回空串。
	 */
	@GetMapping("/productManagement/queryProductInfo")
	public Map<String, Object> queryProductInfo(@RequestParam(name = "prodNo") String prodNo,
			@RequestParam(name = "attrKey") String attrKey) {
		logger.debug("queryProductInfo mock: prodNo={}, attrKey={}, 产品定义表 MB_PROD_DEFINE 不在本组件工程内，返回空串", prodNo, attrKey);
		return Map.ofEntries(
				Map.entry("acctType", ""),
				Map.entry("withdrawalTypeList", Collections.<String>emptyList()),
				Map.entry("ccyList", Collections.<String>emptyList()),
				Map.entry("allowSuspendFlag", ""),
				Map.entry("allDepFlag", ""),
				Map.entry("allDraFlag", ""),
				Map.entry("clientType", ""),
				Map.entry("inlandOffshoreFlag", ""),
				Map.entry("branchList", Collections.<String>emptyList()),
				Map.entry("acctAttr", ""));
	}

	/**
	 * 产品管理 · 查询产品利率信息：按「产品编号」查产品利率信息表取首条记录。
	 * 产品利率信息表 MB_PROD_INT 不在本组件工程内，无 Bcc 可查，返回空串。
	 */
	@GetMapping("/productManagement/queryProductInterestRate")
	public Map<String, Object> queryProductInterestRate(@RequestParam(name = "prodNo") String prodNo) {
		logger.debug("queryProductInterestRate mock: prodNo={}, 产品利率信息表 MB_PROD_INT 不在本组件工程内，返回空串", prodNo);
		return Map.of(
				"intTypeList", Collections.<String>emptyList(),
				"prodIntRate", "",
				"maxExecRate", "",
				"minExecRate", "");
	}

	/**
	 * 基础公共 · 生成账号：不查表，按「账号生成规则类型 + 交易机构 + 产品编号」
	 * 以 固定前缀 + 交易机构 + 序号 拼装账号返回。
	 */
	@GetMapping("/basicCommon/genAcctNo")
	public Map<String, Object> genAcctNo(@RequestParam(name = "acctGenRuleType") String acctGenRuleType,
			@RequestParam(name = "branch") String branch,
			@RequestParam(name = "prodNo", required = false) String prodNo) {
		String acctNo = ACCT_NO_PREFIX + branch + String.format("%08d", ACCT_NO_SEQ.getAndIncrement());
		logger.debug("genAcctNo mock: acctGenRuleType={}, branch={}, prodNo={}, acctNo={}", acctGenRuleType, branch,
				prodNo, acctNo);
		return Map.of("acctNo", acctNo);
	}

	/**
	 * 贷款 · 计算账号当日放款金额合计：不查表，按账号返回固定值 0.00。
	 */
	@GetMapping("/loan/calcAcctDailyLoanAmt")
	public Map<String, Object> calcAcctDailyLoanAmt(@RequestParam(name = "acctNo") String acctNo) {
		logger.debug("calcAcctDailyLoanAmt mock: acctNo={}, 当日累计透支额度固定返回 0.00", acctNo);
		return Map.of("dailyOverdraftAmt", "0.00");
	}
}
