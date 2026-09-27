package com.dcits.limit.step;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dcits.limit.enums.DealFlow;
import com.dcits.limit.facade.bo.ST005InputBO;
import com.dcits.limit.facade.bo.ST005OutputBO;
import com.dcits.limit.facade.components.IRbLimitCtrlConfBcc;
import com.dcits.limit.facade.eo.RbLimitCtrlConfEO;

@ExtendWith(MockitoExtension.class)
public class ST005PbcTest {

	@Mock
	private IRbLimitCtrlConfBcc rbLimitCtrlConfBcc;

	@InjectMocks
	private ST005Pbc st005Pbc;

	// 场景：按限额场景编码查到唯一限额控制配置，处理方式为“拒绝”（走子步骤1→子步骤2a）；预期：步骤成功，错误字段为 null，检查结果“拒绝”（dealFlow=DealFlow.B）
	@Test
	public void testST005T01() {
		RbLimitCtrlConfEO conf = buildConf("SC0001", DealFlow.B);
		AtomicReference<RbLimitCtrlConfEO> capturedQuery = new AtomicReference<>();
		stubFindByEo(capturedQuery, Collections.singletonList(conf));

		ST005InputBO input = new ST005InputBO();
		input.setLimitSceneNo("SC0001");

		ST005OutputBO output = st005Pbc.execute(input);

		assertTrue(output.isSucceed());
		assertNull(output.getErrorCode());
		assertNull(output.getErrorMessage());
		assertEquals(DealFlow.B, output.getDealFlow());
		assertEquals("SC0001", capturedQuery.get().getLimitSceneNo());
	}

	// 场景：按限额场景编码查到唯一限额控制配置，处理方式为“提醒”（走子步骤1→子步骤2b）；预期：步骤成功，错误字段为 null，检查结果“提醒”（dealFlow=DealFlow.D）
	@Test
	public void testST005T02() {
		RbLimitCtrlConfEO conf = buildConf("SC0002", DealFlow.D);
		AtomicReference<RbLimitCtrlConfEO> capturedQuery = new AtomicReference<>();
		stubFindByEo(capturedQuery, Collections.singletonList(conf));

		ST005InputBO input = new ST005InputBO();
		input.setLimitSceneNo("SC0002");

		ST005OutputBO output = st005Pbc.execute(input);

		assertTrue(output.isSucceed());
		assertNull(output.getErrorCode());
		assertNull(output.getErrorMessage());
		assertEquals(DealFlow.D, output.getDealFlow());
		assertEquals("SC0002", capturedQuery.get().getLimitSceneNo());
	}

	// 场景：按限额场景编码查到唯一限额控制配置，处理方式为“授权”（走子步骤1→子步骤2c）；预期：步骤成功，错误字段为 null，检查结果“授权”（dealFlow=DealFlow.A）
	@Test
	public void testST005T03() {
		RbLimitCtrlConfEO conf = buildConf("SC0003", DealFlow.A);
		AtomicReference<RbLimitCtrlConfEO> capturedQuery = new AtomicReference<>();
		stubFindByEo(capturedQuery, Collections.singletonList(conf));

		ST005InputBO input = new ST005InputBO();
		input.setLimitSceneNo("SC0003");

		ST005OutputBO output = st005Pbc.execute(input);

		assertTrue(output.isSucceed());
		assertNull(output.getErrorCode());
		assertNull(output.getErrorMessage());
		assertEquals(DealFlow.A, output.getDealFlow());
		assertEquals("SC0003", capturedQuery.get().getLimitSceneNo());
	}

	// 场景：按限额场景编码查询限额控制配置无记录（子步骤1 提前返回，不进入子步骤2）；预期：步骤成功，错误字段为 null，处理方式为空（dealFlow=null）
	@Test
	public void testST005T04() {
		AtomicReference<RbLimitCtrlConfEO> capturedQuery = new AtomicReference<>();
		stubFindByEo(capturedQuery, Collections.emptyList());

		ST005InputBO input = new ST005InputBO();
		input.setLimitSceneNo("SC0004");

		ST005OutputBO output = st005Pbc.execute(input);

		assertTrue(output.isSucceed());
		assertNull(output.getErrorCode());
		assertNull(output.getErrorMessage());
		assertNull(output.getDealFlow());
		assertEquals("SC0004", capturedQuery.get().getLimitSceneNo());
	}

	/** 构造限额控制配置记录：限额场景编码 + 处理方式 */
	private RbLimitCtrlConfEO buildConf(String limitSceneNo, DealFlow dealFlow) {
		RbLimitCtrlConfEO conf = new RbLimitCtrlConfEO();
		conf.setLimitSceneNo(limitSceneNo);
		conf.setDealFlow(dealFlow);
		return conf;
	}

	/** 设桩 findByEo：记录收到的查询 EO 并返回给定结果列表 */
	private void stubFindByEo(AtomicReference<RbLimitCtrlConfEO> capturedQuery, List<RbLimitCtrlConfEO> result) {
		Mockito.lenient().when(rbLimitCtrlConfBcc.findByEo(ArgumentMatchers.any(RbLimitCtrlConfEO.class)))
				.thenAnswer(invocation -> {
					capturedQuery.set(invocation.getArgument(0));
					return result;
				});
	}
}
