package com.dcits.limit.step;

import java.math.BigDecimal;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dcits.limit.facade.bo.ST006InputBO;
import com.dcits.limit.facade.bo.ST006OutputBO;
import com.dcits.limit.facade.components.IRbLimitSumInfoBcc;
import com.dcits.limit.facade.eo.RbLimitSumInfoEO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * ST006 获取累计限额 单元测试
 */
@ExtendWith(MockitoExtension.class)
class ST006PbcTest {

    @Mock
    private IRbLimitSumInfoBcc rbLimitSumInfoBcc;

    @InjectMocks
    private ST006Pbc st006Pbc;

    /** 构造输入：账号、客户号、限额场景编码三字段均必填 */
    private ST006InputBO buildInput(String baseAcctNo, String clientNo, String limitSceneNo) {
        ST006InputBO input = new ST006InputBO();
        input.setBaseAcctNo(baseAcctNo);
        input.setClientNo(clientNo);
        input.setLimitSceneNo(limitSceneNo);
        return input;
    }

    /** 构造命中的限额累计信息记录桩数据；EO 的限额累计笔数属性实际命名为"否" */
    private RbLimitSumInfoEO buildRecord(String clientNo, String limitSceneNo, String limitSumAmt,
            Integer limitSumNum) {
        RbLimitSumInfoEO eo = new RbLimitSumInfoEO();
        eo.setClientNo(clientNo);
        eo.setLimitSceneNo(limitSceneNo);
        eo.setLimitSumAmt(new BigDecimal(limitSumAmt));
        eo.set否(limitSumNum);
        return eo;
    }

    // 场景：按客户号C20260927001+限额场景编码S001查询命中一条累计记录，成功返回记录上的客户号、限额场景编码、限额累计金额50000.00与限额累计笔数3
    @Test
    void testST006T01() {
        final RbLimitSumInfoEO[] captured = new RbLimitSumInfoEO[1];
        Mockito.lenient().when(rbLimitSumInfoBcc.findByEo(Mockito.argThat(eo -> {
            captured[0] = eo;
            return true;
        }))).thenReturn(Collections.singletonList(
                buildRecord("C20260927001", "S001", "50000.00", 3)));

        ST006OutputBO output = st006Pbc.execute(buildInput("2000010000001", "C20260927001", "S001"));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertEquals("C20260927001", output.getClientNo());
        assertEquals("S001", output.getLimitSceneNo());
        assertEquals(new BigDecimal("50000.00"), output.getLimitSumAmt());
        assertEquals(3, output.getLimitSumNum());
        assertEquals("C20260927001", captured[0].getClientNo());
        assertEquals("S001", captured[0].getLimitSceneNo());
        assertNull(captured[0].getCheckObjVal());
    }

    // 场景：按客户号C20260927002+限额场景编码S002查询无匹配记录，成功返回且限额累计金额、限额累计笔数为空，客户号、限额场景编码无记录来源亦为空
    @Test
    void testST006T02() {
        final RbLimitSumInfoEO[] captured = new RbLimitSumInfoEO[1];
        Mockito.lenient().when(rbLimitSumInfoBcc.findByEo(Mockito.argThat(eo -> {
            captured[0] = eo;
            return true;
        }))).thenReturn(Collections.emptyList());

        ST006OutputBO output = st006Pbc.execute(buildInput("2000010000002", "C20260927002", "S002"));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertNull(output.getLimitSumAmt());
        assertNull(output.getLimitSumNum());
        assertNull(output.getClientNo());
        assertNull(output.getLimitSceneNo());
        assertEquals("C20260927002", captured[0].getClientNo());
        assertEquals("S002", captured[0].getLimitSceneNo());
    }

    // 场景：按客户号C20260927003+限额场景编码S003查询命中一条累计记录，限额累计金额0.00、限额累计笔数0，零值如实返回，与无匹配的空值区分
    @Test
    void testST006T03() {
        final RbLimitSumInfoEO[] captured = new RbLimitSumInfoEO[1];
        Mockito.lenient().when(rbLimitSumInfoBcc.findByEo(Mockito.argThat(eo -> {
            captured[0] = eo;
            return true;
        }))).thenReturn(Collections.singletonList(
                buildRecord("C20260927003", "S003", "0.00", 0)));

        ST006OutputBO output = st006Pbc.execute(buildInput("2000010000003", "C20260927003", "S003"));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertEquals(new BigDecimal("0.00"), output.getLimitSumAmt());
        assertEquals(0, output.getLimitSumNum());
        assertEquals("C20260927003", output.getClientNo());
        assertEquals("S003", output.getLimitSceneNo());
        assertEquals("C20260927003", captured[0].getClientNo());
        assertEquals("S003", captured[0].getLimitSceneNo());
        assertNull(captured[0].getCheckObjVal());
    }
}
