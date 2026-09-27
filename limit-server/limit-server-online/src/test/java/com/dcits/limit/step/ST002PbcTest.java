package com.dcits.limit.step;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.lenient;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dcits.limit.enums.HierarchyCode;
import com.dcits.limit.enums.LimitBranchId;
import com.dcits.limit.enums.LimitBranchRange;
import com.dcits.limit.facade.bo.ST002InputBO;
import com.dcits.limit.facade.bo.ST002OutputBO;
import com.dcits.limit.facade.components.IFmBranchBcc;
import com.dcits.limit.facade.components.IRbBusAcctBcc;
import com.dcits.limit.facade.components.IRbLimitCtrlConfBcc;
import com.dcits.limit.facade.eo.FmBranchEO;
import com.dcits.limit.facade.eo.RbBusAcctEO;
import com.dcits.limit.facade.eo.RbLimitCtrlConfEO;

/**
 * ST002 检查账户机构是否可匹配到限额场景配置 单元测试。
 * 用例来源：outputs/测试用例.md（ST002-TC001~TC005）。
 */
@ExtendWith(MockitoExtension.class)
class ST002PbcTest {

	@Mock
	private IRbBusAcctBcc rbBusAcctBcc;

	@Mock
	private IRbLimitCtrlConfBcc rbLimitCtrlConfBcc;

	@Mock
	private IFmBranchBcc fmBranchBcc;

	@InjectMocks
	private ST002Pbc st002Pbc;

	private static RbBusAcctEO acct(String baseAcctNo, LimitBranchId acctBranch) {
		RbBusAcctEO eo = new RbBusAcctEO();
		eo.setBaseAcctNo(baseAcctNo);
		eo.setAcctBranch(acctBranch);
		return eo;
	}

	private static RbLimitCtrlConfEO config(LimitBranchId limitBranchId, String limitSceneNo,
			LimitBranchRange limitBranchRange) {
		RbLimitCtrlConfEO eo = new RbLimitCtrlConfEO();
		eo.setLimitBranchId(limitBranchId);
		eo.setLimitSceneNo(limitSceneNo);
		eo.setLimitBranchRange(limitBranchRange);
		eo.setValidFlag("Y");
		return eo;
	}

	private static FmBranchEO branch(LimitBranchId branch, LimitBranchId attachedTo, HierarchyCode hierarchyCode) {
		FmBranchEO eo = new FmBranchEO();
		eo.setBranch(branch);
		eo.setAttachedTo(attachedTo);
		eo.setHierarchyCode(hierarchyCode);
		return eo;
	}

	private static ST002InputBO input(String baseAcctNo) {
		ST002InputBO input = new ST002InputBO();
		input.setBaseAcctNo(baseAcctNo);
		return input;
	}

	// 场景：账户开立行存在启用限额配置，子步骤2直接命中，子步骤3不跳转立即返回（P1），机构信息表不触达，branch/attachedTo 为 null
	@Test
	void testST002T01() {
		lenient().when(rbBusAcctBcc.findByEo(argThat(eo -> eo != null && "2000020001".equals(eo.getBaseAcctNo()))))
				.thenReturn(List.of(acct("2000020001", LimitBranchId.VALUE_359102)));
		lenient().when(rbLimitCtrlConfBcc.findByEo(argThat(eo -> eo != null && eo.getLimitBranchId() == LimitBranchId.VALUE_359102
				&& "Y".equals(eo.getValidFlag()))))
				.thenReturn(List.of(config(LimitBranchId.VALUE_359102, "LS001", LimitBranchRange.B)));

		ST002OutputBO out = st002Pbc.execute(input("2000020001"));

		assertTrue(out.isSucceed());
		assertNull(out.getErrorCode());
		assertNull(out.getErrorMessage());
		assertEquals("LS001", out.getLimitSceneNo());
		assertEquals(LimitBranchId.VALUE_359102, out.getLimitBranchId());
		assertEquals(LimitBranchRange.B, out.getLimitBranchRange());
		assertEquals("Y", out.getValidFlag());
		assertEquals("2000020001", out.getBaseAcctNo());
		assertEquals(LimitBranchId.VALUE_359102, out.getAcctBranch());
		assertNull(out.getBranch());
		assertNull(out.getAttachedTo());
	}

	// 场景：开立行无启用配置，子步骤3跳转获取上级机构集合；按机构层级从大到小遍历，首个上级分行命中即中断，总行不再参与匹配（P2）
	@Test
	void testST002T02() {
		lenient().when(rbBusAcctBcc.findByEo(argThat(eo -> eo != null && "2000020001".equals(eo.getBaseAcctNo()))))
				.thenReturn(List.of(acct("2000020001", LimitBranchId.VALUE_359102)));
		lenient().when(rbLimitCtrlConfBcc.findByEo(argThat(eo -> eo != null && eo.getLimitBranchId() == LimitBranchId.VALUE_359102
				&& "Y".equals(eo.getValidFlag())))).thenReturn(List.of());
		lenient().when(rbLimitCtrlConfBcc.findByEo(argThat(eo -> eo != null && eo.getLimitBranchId() == LimitBranchId.VALUE_359001
				&& "Y".equals(eo.getValidFlag()))))
				.thenReturn(List.of(config(LimitBranchId.VALUE_359001, "LS002", LimitBranchRange.C)));
		// 顺序鉴别桩：若实现误按层级从小到大遍历将命中总行 LS004 导致断言失败
		lenient().when(rbLimitCtrlConfBcc.findByEo(argThat(eo -> eo != null && eo.getLimitBranchId() == LimitBranchId.VALUE_351001
				&& "Y".equals(eo.getValidFlag()))))
				.thenReturn(List.of(config(LimitBranchId.VALUE_351001, "LS004", LimitBranchRange.F)));
		lenient().when(fmBranchBcc.findByBranch(LimitBranchId.VALUE_359102))
				.thenReturn(branch(LimitBranchId.VALUE_359102, LimitBranchId.VALUE_359001, HierarchyCode.VALUE_2));
		lenient().when(fmBranchBcc.findByBranch(LimitBranchId.VALUE_359001))
				.thenReturn(branch(LimitBranchId.VALUE_359001, LimitBranchId.VALUE_351001, HierarchyCode.VALUE_1));
		lenient().when(fmBranchBcc.findByBranch(LimitBranchId.VALUE_351001))
				.thenReturn(branch(LimitBranchId.VALUE_351001, null, HierarchyCode.VALUE_0));

		ST002OutputBO out = st002Pbc.execute(input("2000020001"));

		assertTrue(out.isSucceed());
		assertNull(out.getErrorCode());
		assertNull(out.getErrorMessage());
		assertEquals("LS002", out.getLimitSceneNo());
		assertEquals(LimitBranchId.VALUE_359001, out.getLimitBranchId());
		assertEquals(LimitBranchRange.C, out.getLimitBranchRange());
		assertEquals("Y", out.getValidFlag());
		assertEquals("2000020001", out.getBaseAcctNo());
		assertEquals(LimitBranchId.VALUE_359102, out.getAcctBranch());
		assertEquals(LimitBranchId.VALUE_359102, out.getBranch());
		assertEquals(LimitBranchId.VALUE_359001, out.getAttachedTo());
	}

	// 场景：跳转后首个上级分行无启用配置，子步骤5.2继续遍历，第二上级总行命中并中断返回（P3）
	@Test
	void testST002T03() {
		lenient().when(rbBusAcctBcc.findByEo(argThat(eo -> eo != null && "2000020001".equals(eo.getBaseAcctNo()))))
				.thenReturn(List.of(acct("2000020001", LimitBranchId.VALUE_359102)));
		lenient().when(rbLimitCtrlConfBcc.findByEo(argThat(eo -> eo != null && eo.getLimitBranchId() == LimitBranchId.VALUE_359102
				&& "Y".equals(eo.getValidFlag())))).thenReturn(List.of());
		lenient().when(rbLimitCtrlConfBcc.findByEo(argThat(eo -> eo != null && eo.getLimitBranchId() == LimitBranchId.VALUE_359001
				&& "Y".equals(eo.getValidFlag())))).thenReturn(List.of());
		lenient().when(rbLimitCtrlConfBcc.findByEo(argThat(eo -> eo != null && eo.getLimitBranchId() == LimitBranchId.VALUE_351001
				&& "Y".equals(eo.getValidFlag()))))
				.thenReturn(List.of(config(LimitBranchId.VALUE_351001, "LS003", LimitBranchRange.F)));
		lenient().when(fmBranchBcc.findByBranch(LimitBranchId.VALUE_359102))
				.thenReturn(branch(LimitBranchId.VALUE_359102, LimitBranchId.VALUE_359001, HierarchyCode.VALUE_2));
		lenient().when(fmBranchBcc.findByBranch(LimitBranchId.VALUE_359001))
				.thenReturn(branch(LimitBranchId.VALUE_359001, LimitBranchId.VALUE_351001, HierarchyCode.VALUE_1));
		lenient().when(fmBranchBcc.findByBranch(LimitBranchId.VALUE_351001))
				.thenReturn(branch(LimitBranchId.VALUE_351001, null, HierarchyCode.VALUE_0));

		ST002OutputBO out = st002Pbc.execute(input("2000020001"));

		assertTrue(out.isSucceed());
		assertNull(out.getErrorCode());
		assertNull(out.getErrorMessage());
		assertEquals("LS003", out.getLimitSceneNo());
		assertEquals(LimitBranchId.VALUE_351001, out.getLimitBranchId());
		assertEquals(LimitBranchRange.F, out.getLimitBranchRange());
		assertEquals("Y", out.getValidFlag());
		assertEquals("2000020001", out.getBaseAcctNo());
		assertEquals(LimitBranchId.VALUE_359102, out.getAcctBranch());
		assertEquals(LimitBranchId.VALUE_359102, out.getBranch());
		assertEquals(LimitBranchId.VALUE_359001, out.getAttachedTo());
	}

	// 场景：开立行与全部上级（分行、总行）均无启用配置，遍历结束，子步骤5.3返回空限额场景编码，按成功返回（P4）
	@Test
	void testST002T04() {
		lenient().when(rbBusAcctBcc.findByEo(argThat(eo -> eo != null && "2000020001".equals(eo.getBaseAcctNo()))))
				.thenReturn(List.of(acct("2000020001", LimitBranchId.VALUE_359102)));
		lenient().when(rbLimitCtrlConfBcc.findByEo(argThat(eo -> eo != null && eo.getLimitBranchId() == LimitBranchId.VALUE_359102
				&& "Y".equals(eo.getValidFlag())))).thenReturn(List.of());
		lenient().when(rbLimitCtrlConfBcc.findByEo(argThat(eo -> eo != null && eo.getLimitBranchId() == LimitBranchId.VALUE_359001
				&& "Y".equals(eo.getValidFlag())))).thenReturn(List.of());
		lenient().when(rbLimitCtrlConfBcc.findByEo(argThat(eo -> eo != null && eo.getLimitBranchId() == LimitBranchId.VALUE_351001
				&& "Y".equals(eo.getValidFlag())))).thenReturn(List.of());
		lenient().when(fmBranchBcc.findByBranch(LimitBranchId.VALUE_359102))
				.thenReturn(branch(LimitBranchId.VALUE_359102, LimitBranchId.VALUE_359001, HierarchyCode.VALUE_2));
		lenient().when(fmBranchBcc.findByBranch(LimitBranchId.VALUE_359001))
				.thenReturn(branch(LimitBranchId.VALUE_359001, LimitBranchId.VALUE_351001, HierarchyCode.VALUE_1));
		lenient().when(fmBranchBcc.findByBranch(LimitBranchId.VALUE_351001))
				.thenReturn(branch(LimitBranchId.VALUE_351001, null, HierarchyCode.VALUE_0));

		ST002OutputBO out = st002Pbc.execute(input("2000020001"));

		assertTrue(out.isSucceed());
		assertNull(out.getErrorCode());
		assertNull(out.getErrorMessage());
		assertNull(out.getLimitSceneNo());
		assertNull(out.getLimitBranchId());
		assertNull(out.getLimitBranchRange());
		assertNull(out.getValidFlag());
		assertEquals("2000020001", out.getBaseAcctNo());
		assertEquals(LimitBranchId.VALUE_359102, out.getAcctBranch());
		assertEquals(LimitBranchId.VALUE_359102, out.getBranch());
		assertEquals(LimitBranchId.VALUE_359001, out.getAttachedTo());
	}

	// 场景：账户开立行即总行且无启用配置，上溯得到空上级机构集合（attachedTo=null），不进入遍历，子步骤5.3返回空（P5）
	@Test
	void testST002T05() {
		lenient().when(rbBusAcctBcc.findByEo(argThat(eo -> eo != null && "2000020002".equals(eo.getBaseAcctNo()))))
				.thenReturn(List.of(acct("2000020002", LimitBranchId.VALUE_351001)));
		lenient().when(rbLimitCtrlConfBcc.findByEo(argThat(eo -> eo != null && eo.getLimitBranchId() == LimitBranchId.VALUE_351001
				&& "Y".equals(eo.getValidFlag())))).thenReturn(List.of());
		lenient().when(fmBranchBcc.findByBranch(LimitBranchId.VALUE_351001))
				.thenReturn(branch(LimitBranchId.VALUE_351001, null, HierarchyCode.VALUE_0));

		ST002OutputBO out = st002Pbc.execute(input("2000020002"));

		assertTrue(out.isSucceed());
		assertNull(out.getErrorCode());
		assertNull(out.getErrorMessage());
		assertNull(out.getLimitSceneNo());
		assertNull(out.getLimitBranchId());
		assertNull(out.getLimitBranchRange());
		assertNull(out.getValidFlag());
		assertEquals("2000020002", out.getBaseAcctNo());
		assertEquals(LimitBranchId.VALUE_351001, out.getAcctBranch());
		assertEquals(LimitBranchId.VALUE_351001, out.getBranch());
		assertNull(out.getAttachedTo());
	}
}
