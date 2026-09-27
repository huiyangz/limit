package com.dcits.limit.step;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dcits.limit.facade.bo.ST010InputBO;
import com.dcits.limit.facade.bo.ST010OutputBO;
import com.dcits.limit.facade.components.IRbLimitSumInfoBcc;
import com.dcits.limit.facade.eo.RbLimitSumInfoEO;

/**
 * ST010 更新累计限额 单元测试。
 * 条件：限额检查结果为"未超限"，且（限额累计金额>0 或 限额累计笔数>0）；
 * 条件成立按主键（限额检查对象值=账号、限额场景编码）更新限额累计金额，不成立跳过更新正常返回；
 * 无业务失败场景，失败仅由技术异常传播表达。
 */
@ExtendWith(MockitoExtension.class)
public class ST010PbcTest {

    @Mock
    private IRbLimitSumInfoBcc rbLimitSumInfoBcc;

    @InjectMocks
    private ST010Pbc st010Pbc;

    private ST010InputBO buildInput() {
        ST010InputBO input = new ST010InputBO();
        input.setLimitCheckResult("未超限");
        input.setBaseAcctNo("20000200012345678");
        input.setLimitSceneNo("LS0001");
        input.setClientNo("C0000001");
        input.setLimitSumAmt(new BigDecimal("1000.50"));
        input.setLimitSumNum(3);
        return input;
    }

    // 场景：限额检查结果"未超限"，金额与笔数均大于0（OR两分支同时成立），按主键更新限额累计金额——主成功路径
    @Test
    public void testST010T01() {
        RbLimitSumInfoEO[] captured = new RbLimitSumInfoEO[1];
        lenient().when(rbLimitSumInfoBcc.modifyByPrimaryKeySelective(any(RbLimitSumInfoEO.class)))
                .thenAnswer(invocation -> {
                    captured[0] = invocation.getArgument(0);
                    return 1;
                });

        ST010OutputBO output = st010Pbc.execute(buildInput());

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertEquals(0, new BigDecimal("1000.50").compareTo(output.getLimitSumAmt()));
        assertEquals("20000200012345678", captured[0].getCheckObjVal());
        assertEquals("LS0001", captured[0].getLimitSceneNo());
        assertEquals(0, new BigDecimal("1000.50").compareTo(captured[0].getLimitSumAmt()));
    }

    // 场景：限额检查结果"未超限"，金额大于0而笔数为0（OR金额分支单独成立），仍执行主键更新
    @Test
    public void testST010T02() {
        RbLimitSumInfoEO[] captured = new RbLimitSumInfoEO[1];
        lenient().when(rbLimitSumInfoBcc.modifyByPrimaryKeySelective(any(RbLimitSumInfoEO.class)))
                .thenAnswer(invocation -> {
                    captured[0] = invocation.getArgument(0);
                    return 1;
                });

        ST010InputBO input = buildInput();
        input.setLimitSumNum(0);

        ST010OutputBO output = st010Pbc.execute(input);

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertEquals(0, new BigDecimal("1000.50").compareTo(output.getLimitSumAmt()));
        assertEquals("20000200012345678", captured[0].getCheckObjVal());
        assertEquals("LS0001", captured[0].getLimitSceneNo());
        assertEquals(0, new BigDecimal("1000.50").compareTo(captured[0].getLimitSumAmt()));
    }

    // 场景：限额检查结果"未超限"，金额为0而笔数大于0（OR笔数分支单独成立），执行更新且写入金额0
    @Test
    public void testST010T03() {
        RbLimitSumInfoEO[] captured = new RbLimitSumInfoEO[1];
        lenient().when(rbLimitSumInfoBcc.modifyByPrimaryKeySelective(any(RbLimitSumInfoEO.class)))
                .thenAnswer(invocation -> {
                    captured[0] = invocation.getArgument(0);
                    return 1;
                });

        ST010InputBO input = buildInput();
        input.setLimitSumAmt(new BigDecimal("0.00"));
        input.setLimitSumNum(1);

        ST010OutputBO output = st010Pbc.execute(input);

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertEquals(0, BigDecimal.ZERO.compareTo(output.getLimitSumAmt()));
        assertEquals("20000200012345678", captured[0].getCheckObjVal());
        assertEquals("LS0001", captured[0].getLimitSceneNo());
        assertEquals(0, BigDecimal.ZERO.compareTo(captured[0].getLimitSumAmt()));
    }

    // 场景：限额检查结果"未超限"，但金额与笔数均为0（OR条件边界不成立），不执行更新，步骤正常完成
    @Test
    public void testST010T04() {
        ST010InputBO input = buildInput();
        input.setLimitSumAmt(new BigDecimal("0.00"));
        input.setLimitSumNum(0);

        ST010OutputBO output = st010Pbc.execute(input);

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertNull(output.getLimitSumAmt());
    }

    // 场景：限额检查结果非"未超限"（以"超限"为代表值），金额与笔数均大于0，等值条件不成立，不执行更新，步骤正常完成
    @Test
    public void testST010T05() {
        ST010InputBO input = buildInput();
        input.setLimitCheckResult("超限");

        ST010OutputBO output = st010Pbc.execute(input);

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertNull(output.getLimitSumAmt());
    }
}
