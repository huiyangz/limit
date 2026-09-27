package com.dcits.limit.scenario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dcits.common.task.RespHeader;
import com.dcits.limit.facade.bo.ST001InputBO;
import com.dcits.limit.facade.bo.ST001OutputBO;
import com.dcits.limit.facade.bo.ST002InputBO;
import com.dcits.limit.facade.bo.ST002OutputBO;
import com.dcits.limit.facade.bo.ST003InputBO;
import com.dcits.limit.facade.bo.ST003OutputBO;
import com.dcits.limit.facade.bo.ST004InputBO;
import com.dcits.limit.facade.bo.ST004OutputBO;
import com.dcits.limit.facade.bo.ST005InputBO;
import com.dcits.limit.facade.bo.ST005OutputBO;
import com.dcits.limit.facade.bo.ST006InputBO;
import com.dcits.limit.facade.bo.ST006OutputBO;
import com.dcits.limit.facade.bo.ST007InputBO;
import com.dcits.limit.facade.bo.ST007OutputBO;
import com.dcits.limit.facade.bo.ST008InputBO;
import com.dcits.limit.facade.bo.ST008OutputBO;
import com.dcits.limit.facade.bo.ST009InputBO;
import com.dcits.limit.facade.bo.ST009OutputBO;
import com.dcits.limit.facade.bo.ST010InputBO;
import com.dcits.limit.facade.bo.ST010OutputBO;
import com.dcits.limit.enums.DealFlow;
import com.dcits.limit.enums.LimitBranchId;
import com.dcits.limit.enums.LimitBranchRange;
import com.dcits.limit.step.IST001;
import com.dcits.limit.step.IST002;
import com.dcits.limit.step.IST003;
import com.dcits.limit.step.IST004;
import com.dcits.limit.step.IST005;
import com.dcits.limit.step.IST006;
import com.dcits.limit.step.IST007;
import com.dcits.limit.step.IST008;
import com.dcits.limit.step.IST009;
import com.dcits.limit.step.IST010;
import com.dcits.limit.task.dto.T1S1InputDTO;
import com.dcits.limit.task.dto.T1S1OutputDTO;
import com.dcits.limit.task.scenario.T1S1;

/**
 * T1S1 检查限额 场景单元测试
 */
@ExtendWith(MockitoExtension.class)
public class T1S1Test {

	private static final String FACTOR_NAME = "RULE0001";
	private static final String BASE_ACCT_NO = "200501000001";
	private static final String CLIENT_NO = "C2026001";
	private static final String LIMIT_SCENE_NO = "S001";
	private static final String TRAN_TIMESTAMP = "093045";
	private static final String TRAN_DATE = "2026-09-27";
	private static final String LIMIT_BRANCH_ID = "351001";
	private static final String LIMIT_CHECK_RESULT_NOT_OVER = "未超限";
	private static final BigDecimal TRAN_AMT = new BigDecimal("1000.00");

	@Mock
	private IST001 ist001;
	@Mock
	private IST002 ist002;
	@Mock
	private IST003 ist003;
	@Mock
	private IST004 ist004;
	@Mock
	private IST005 ist005;
	@Mock
	private IST006 ist006;
	@Mock
	private IST007 ist007;
	@Mock
	private IST008 ist008;
	@Mock
	private IST009 ist009;
	@Mock
	private IST010 ist010;

	@InjectMocks
	private T1S1 t1s1;

	/** 各步骤捕获到的真实入参 */
	private static class Captures {
		ST001InputBO st001;
		ST002InputBO st002;
		ST003InputBO st003;
		ST004InputBO st004;
		ST005InputBO st005;
		ST006InputBO st006;
		ST007InputBO st007;
		ST008InputBO st008;
		ST009InputBO st009;
		ST010InputBO st010;
	}

	private static Date date(String day) throws Exception {
		return new SimpleDateFormat("yyyy-MM-dd").parse(day);
	}

	private static Date time(String hhmmss) throws Exception {
		return new SimpleDateFormat("HHmmss").parse(hhmmss);
	}

	private T1S1InputDTO buildInput() throws Exception {
		T1S1InputDTO input = new T1S1InputDTO();
		input.setFactorName(FACTOR_NAME);
		input.setBaseAcctNo(BASE_ACCT_NO);
		input.setTranDate(date(TRAN_DATE));
		input.setTranTimestamp(TRAN_TIMESTAMP);
		input.setLimitSceneNo(LIMIT_SCENE_NO);
		input.setClientNo(CLIENT_NO);
		input.setTranAmt(TRAN_AMT);
		return input;
	}

	/** 按 T1S1-TC001 桩配置对 10 个步骤设置成功应答，并在应答前捕获真实入参 */
	private Captures stubAllStepsSuccess() throws Exception {
		Captures c = new Captures();

		Mockito.lenient().when(ist001.execute(Mockito.any(ST001InputBO.class))).thenAnswer(invocation -> {
			c.st001 = invocation.getArgument(0);
			ST001OutputBO out = new ST001OutputBO();
			out.setSucceed(true);
			out.setLimitSceneNo(LIMIT_SCENE_NO);
			out.setAllowCustomFlag("N");
			out.setOnlyCustom("N");
			out.setBaseAcctNo(BASE_ACCT_NO);
			out.setClientNo(CLIENT_NO);
			return out;
		});

		Mockito.lenient().when(ist002.execute(Mockito.any(ST002InputBO.class))).thenAnswer(invocation -> {
			c.st002 = invocation.getArgument(0);
			ST002OutputBO out = new ST002OutputBO();
			out.setSucceed(true);
			out.setLimitSceneNo("S002");
			out.setLimitBranchId(LimitBranchId.VALUE_351001);
			out.setLimitBranchRange(LimitBranchRange.A);
			out.setValidFlag("Y");
			out.setBaseAcctNo(BASE_ACCT_NO);
			out.setAcctBranch(LimitBranchId.VALUE_351001);
			out.setBranch(LimitBranchId.VALUE_351001);
			out.setAttachedTo(LimitBranchId.VALUE_351010);
			return out;
		});

		Mockito.lenient().when(ist003.execute(Mockito.any(ST003InputBO.class))).thenAnswer(invocation -> {
			c.st003 = invocation.getArgument(0);
			ST003OutputBO out = new ST003OutputBO();
			out.setSucceed(true);
			out.setLimitSumAmt(new BigDecimal("3500.50"));
			out.set否(3);
			return out;
		});

		Mockito.lenient().when(ist004.execute(Mockito.any(ST004InputBO.class))).thenAnswer(invocation -> {
			c.st004 = invocation.getArgument(0);
			ST004OutputBO out = new ST004OutputBO();
			out.setSucceed(true);
			out.setLimitCtrlAmt(new BigDecimal("5000.00"));
			out.setLimitCtrlNum(100);
			out.setLimitSumAmt(new BigDecimal("3500.50"));
			out.set否(3);
			return out;
		});

		Mockito.lenient().when(ist005.execute(Mockito.any(ST005InputBO.class))).thenAnswer(invocation -> {
			c.st005 = invocation.getArgument(0);
			ST005OutputBO out = new ST005OutputBO();
			out.setSucceed(true);
			out.setDealFlow(DealFlow.D);
			return out;
		});

		Mockito.lenient().when(ist006.execute(Mockito.any(ST006InputBO.class))).thenAnswer(invocation -> {
			c.st006 = invocation.getArgument(0);
			ST006OutputBO out = new ST006OutputBO();
			out.setSucceed(true);
			out.setClientNo(CLIENT_NO);
			out.setLimitSceneNo(LIMIT_SCENE_NO);
			out.setLimitSumAmt(new BigDecimal("2500.50"));
			out.setLimitSumNum(2);
			return out;
		});

		Mockito.lenient().when(ist007.execute(Mockito.any(ST007InputBO.class))).thenAnswer(invocation -> {
			c.st007 = invocation.getArgument(0);
			ST007OutputBO out = new ST007OutputBO();
			out.setSucceed(true);
			out.setLimitSceneNo("S007");
			out.setRuleRelationExpr(FACTOR_NAME);
			out.setValidFlag("Y");
			out.setCheckResult("已匹配到限额场景");
			return out;
		});

		Mockito.lenient().when(ist008.execute(Mockito.any(ST008InputBO.class))).thenAnswer(invocation -> {
			c.st008 = invocation.getArgument(0);
			ST008OutputBO out = new ST008OutputBO();
			out.setSucceed(true);
			out.setLimitSceneNo(LIMIT_SCENE_NO);
			out.setCheckObjVal(BASE_ACCT_NO);
			out.setLimitSumAmt(new BigDecimal("1000.00"));
			out.set否(1);
			out.setEffectDate(date(TRAN_DATE));
			out.setExpireDate(date("2027-09-27"));
			return out;
		});

		Mockito.lenient().when(ist009.execute(Mockito.any(ST009InputBO.class))).thenAnswer(invocation -> {
			c.st009 = invocation.getArgument(0);
			ST009OutputBO out = new ST009OutputBO();
			out.setSucceed(true);
			out.setLimitSceneNo(LIMIT_SCENE_NO);
			out.setLimitCtrlBgnDate(date("2026-01-01"));
			out.setLimitCtrlEndDate(date("2026-12-31"));
			out.setLimitCtrlBgnTime(time("090000"));
			out.setLimitCtrlEndTime(time("170000"));
			return out;
		});

		Mockito.lenient().when(ist010.execute(Mockito.any(ST010InputBO.class))).thenAnswer(invocation -> {
			c.st010 = invocation.getArgument(0);
			ST010OutputBO out = new ST010OutputBO();
			out.setSucceed(true);
			out.setLimitSumAmt(new BigDecimal("3500.50"));
			return out;
		});

		return c;
	}

	/** 断言 10 个步骤捕获入参的接线（金额/笔数按用例传入期望值，其余同 T1S1-TC001）及响应头成功 */
	private void assertSuccess(Captures c, BigDecimal expectedLimitSumAmt, Integer expectedLimitSumNum,
			RespHeader header, T1S1OutputDTO output) throws Exception {
		assertNotNull(c.st001);
		assertEquals(BASE_ACCT_NO, c.st001.getBaseAcctNo());
		assertEquals(CLIENT_NO, c.st001.getClientNo());
		assertEquals(LIMIT_SCENE_NO, c.st001.getLimitSceneNo());
		assertEquals(date(TRAN_DATE), c.st001.getTranDate());

		assertNotNull(c.st002);
		assertEquals(BASE_ACCT_NO, c.st002.getBaseAcctNo());

		assertNotNull(c.st003);
		assertEquals(LIMIT_SCENE_NO, c.st003.getLimitSceneNo());
		assertEquals(TRAN_AMT, c.st003.getTranAmt());
		assertEquals(BASE_ACCT_NO, c.st003.getCheckObjVal());
		assertEquals(CLIENT_NO, c.st003.getClientNo());

		assertNotNull(c.st004);
		assertEquals(LIMIT_SCENE_NO, c.st004.getLimitSceneNo());
		assertEquals(expectedLimitSumAmt, c.st004.getLimitSumAmt());
		assertEquals(expectedLimitSumNum, c.st004.getLimitSumNum());
		assertEquals(LIMIT_BRANCH_ID, c.st004.getLimitBranchId());

		assertNotNull(c.st005);
		assertEquals(LIMIT_SCENE_NO, c.st005.getLimitSceneNo());

		assertNotNull(c.st006);
		assertEquals(BASE_ACCT_NO, c.st006.getBaseAcctNo());
		assertEquals(CLIENT_NO, c.st006.getClientNo());
		assertEquals(LIMIT_SCENE_NO, c.st006.getLimitSceneNo());

		assertNotNull(c.st007);
		assertEquals(FACTOR_NAME, c.st007.getFactorName());

		assertNotNull(c.st008);
		assertEquals(LIMIT_CHECK_RESULT_NOT_OVER, c.st008.getCheckResult());
		assertEquals(BASE_ACCT_NO, c.st008.getBaseAcctNo());
		assertEquals(CLIENT_NO, c.st008.getClientNo());
		assertEquals(TRAN_AMT, c.st008.getTranAmt());
		assertEquals(LIMIT_SCENE_NO, c.st008.getLimitSceneNo());
		assertEquals(date(TRAN_DATE), c.st008.getRunDate());
		assertEquals(expectedLimitSumAmt, c.st008.getLimitSumAmt());
		assertEquals(expectedLimitSumNum, c.st008.get否());

		assertNotNull(c.st009);
		assertEquals(date(TRAN_DATE), c.st009.getTranDate());
		assertEquals(TRAN_TIMESTAMP, c.st009.getTranTimestamp());
		assertEquals(LIMIT_BRANCH_ID, c.st009.getLimitBranchId());
		assertEquals(LIMIT_SCENE_NO, c.st009.getLimitSceneNo());

		assertNotNull(c.st010);
		assertEquals(LIMIT_CHECK_RESULT_NOT_OVER, c.st010.getLimitCheckResult());
		assertEquals(BASE_ACCT_NO, c.st010.getBaseAcctNo());
		assertEquals(LIMIT_SCENE_NO, c.st010.getLimitSceneNo());
		assertEquals(CLIENT_NO, c.st010.getClientNo());
		assertEquals(expectedLimitSumAmt, c.st010.getLimitSumAmt());
		assertEquals(expectedLimitSumNum, c.st010.getLimitSumNum());

		assertTrue(header.isSucceed());
		assertNull(header.getErrorCode());
		assertNull(header.getErrorMessage());
		assertNotNull(output);
	}

	// 场景：T1S1-TC001 全部 10 个步骤成功；验证场景输入到各步骤入参的接线、ST002 枚举机构编码转 String、ST003 计算值向 ST004/ST008/ST010 传递及响应头成功；预期 header 成功、错误字段为 null、输出非 null
	@Test
	public void testT1S1T01() throws Exception {
		Captures c = stubAllStepsSuccess();

		RespHeader header = new RespHeader();
		T1S1OutputDTO output = t1s1.execute(header, buildInput());

		assertSuccess(c, new BigDecimal("3500.50"), 3, header, output);
	}

	// 场景：T1S1-TC002 ST007 查无规则关系记录返回"未匹配到限额场景"（其余桩同 TC001）；ST007 检查结果无提前结束条件，预期 ST008-ST010 仍按序执行且限额场景编码继续取场景输入 "S001"
	@Test
	public void testT1S1T02() throws Exception {
		Captures c = stubAllStepsSuccess();
		Mockito.lenient().when(ist007.execute(Mockito.any(ST007InputBO.class))).thenAnswer(invocation -> {
			c.st007 = invocation.getArgument(0);
			ST007OutputBO out = new ST007OutputBO();
			out.setSucceed(true);
			out.setCheckResult("未匹配到限额场景");
			return out;
		});

		RespHeader header = new RespHeader();
		T1S1OutputDTO output = t1s1.execute(header, buildInput());

		assertSuccess(c, new BigDecimal("3500.50"), 3, header, output);
	}

	// 场景：T1S1-TC003 ST009 未查询到配置记录、检查不通过（五个输出字段全空，其余桩同 TC001）；SPEC 已接受结论"校验不通过时 ST010 仍会继续更新"，预期 ST010 照常执行且接线不变
	@Test
	public void testT1S1T03() throws Exception {
		Captures c = stubAllStepsSuccess();
		Mockito.lenient().when(ist009.execute(Mockito.any(ST009InputBO.class))).thenAnswer(invocation -> {
			c.st009 = invocation.getArgument(0);
			ST009OutputBO out = new ST009OutputBO();
			out.setSucceed(true);
			return out;
		});

		RespHeader header = new RespHeader();
		T1S1OutputDTO output = t1s1.execute(header, buildInput());

		assertSuccess(c, new BigDecimal("3500.50"), 3, header, output);
	}

	// 场景：T1S1-TC004 ST001 返回空场景编码、ST005 查询无记录 dealFlow=null、ST006 无匹配记录输出全空、ST003 无既有累计返回交易金额/1 笔；空结果穿透，预期后续步骤继续按场景输入与 ST003 计算值（1000.00/1）接线
	@Test
	public void testT1S1T04() throws Exception {
		Captures c = stubAllStepsSuccess();

		Mockito.lenient().when(ist001.execute(Mockito.any(ST001InputBO.class))).thenAnswer(invocation -> {
			c.st001 = invocation.getArgument(0);
			ST001OutputBO out = new ST001OutputBO();
			out.setSucceed(true);
			out.setAllowCustomFlag("Y");
			out.setOnlyCustom("N");
			out.setBaseAcctNo(BASE_ACCT_NO);
			out.setClientNo(CLIENT_NO);
			return out;
		});

		Mockito.lenient().when(ist003.execute(Mockito.any(ST003InputBO.class))).thenAnswer(invocation -> {
			c.st003 = invocation.getArgument(0);
			ST003OutputBO out = new ST003OutputBO();
			out.setSucceed(true);
			out.setLimitSumAmt(new BigDecimal("1000.00"));
			out.set否(1);
			return out;
		});

		Mockito.lenient().when(ist004.execute(Mockito.any(ST004InputBO.class))).thenAnswer(invocation -> {
			c.st004 = invocation.getArgument(0);
			ST004OutputBO out = new ST004OutputBO();
			out.setSucceed(true);
			out.setLimitCtrlAmt(new BigDecimal("5000.00"));
			out.setLimitCtrlNum(100);
			out.setLimitSumAmt(new BigDecimal("1000.00"));
			out.set否(1);
			return out;
		});

		Mockito.lenient().when(ist005.execute(Mockito.any(ST005InputBO.class))).thenAnswer(invocation -> {
			c.st005 = invocation.getArgument(0);
			ST005OutputBO out = new ST005OutputBO();
			out.setSucceed(true);
			return out;
		});

		Mockito.lenient().when(ist006.execute(Mockito.any(ST006InputBO.class))).thenAnswer(invocation -> {
			c.st006 = invocation.getArgument(0);
			ST006OutputBO out = new ST006OutputBO();
			out.setSucceed(true);
			return out;
		});

		Mockito.lenient().when(ist010.execute(Mockito.any(ST010InputBO.class))).thenAnswer(invocation -> {
			c.st010 = invocation.getArgument(0);
			ST010OutputBO out = new ST010OutputBO();
			out.setSucceed(true);
			out.setLimitSumAmt(new BigDecimal("1000.00"));
			return out;
		});

		RespHeader header = new RespHeader();
		T1S1OutputDTO output = t1s1.execute(header, buildInput());

		assertSuccess(c, new BigDecimal("1000.00"), 1, header, output);
	}
}
