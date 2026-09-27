package com.dcits.limit.step;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dcits.limit.facade.bo.ST004InputBO;
import com.dcits.limit.facade.bo.ST004OutputBO;
import com.dcits.limit.facade.components.IRbLimitCtrlConfBcc;
import com.dcits.limit.facade.eo.RbLimitCtrlConfEO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * ST004 检查限额 单元测试
 */
@ExtendWith(MockitoExtension.class)
class ST004PbcTest {

    @Mock
    private IRbLimitCtrlConfBcc rbLimitCtrlConfBcc;

    @InjectMocks
    private ST004Pbc st004Pbc;

    /** 构造桩记录：限额控制配置仅设本步骤使用的控制金额、控制笔数字段 */
    private RbLimitCtrlConfEO buildStubConf(String ctrlAmt, Integer ctrlNum) {
        RbLimitCtrlConfEO eo = new RbLimitCtrlConfEO();
        eo.setLimitCtrlAmt(new BigDecimal(ctrlAmt));
        eo.setLimitCtrlNum(ctrlNum);
        return eo;
    }

    /** 构造输入：机构编码351155、场景编码S001，累计金额、累计笔数按参数取值 */
    private ST004InputBO buildInput(String sumAmt, Integer sumNum) {
        ST004InputBO input = new ST004InputBO();
        input.setLimitBranchId("351155");
        input.setLimitSceneNo("S001");
        input.setLimitSumAmt(new BigDecimal(sumAmt));
        input.setLimitSumNum(sumNum);
        return input;
    }

    // 场景：查到配置（控制金额5000.00、控制笔数100），累计金额2500.50未大于控制金额且累计笔数30未大于控制笔数，限额检查结果为“未超限”
    @Test
    void testST004T01() {
        Mockito.lenient().when(rbLimitCtrlConfBcc.findByPrimaryKey("351155", "S001"))
                .thenReturn(buildStubConf("5000.00", 100));

        ST004OutputBO output = st004Pbc.execute(buildInput("2500.50", 30));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertEquals(new BigDecimal("5000.00"), output.getLimitCtrlAmt());
        assertEquals(Integer.valueOf(100), output.getLimitCtrlNum());
        assertEquals(new BigDecimal("2500.50"), output.getLimitSumAmt());
        assertEquals(Integer.valueOf(30), output.get否());
    }

    // 场景：查到配置（控制金额5000.00、控制笔数100），累计金额5000.01大于控制金额，或条件第一支成立，限额检查结果为“超限”
    @Test
    void testST004T02() {
        Mockito.lenient().when(rbLimitCtrlConfBcc.findByPrimaryKey("351155", "S001"))
                .thenReturn(buildStubConf("5000.00", 100));

        ST004OutputBO output = st004Pbc.execute(buildInput("5000.01", 30));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertEquals(new BigDecimal("5000.00"), output.getLimitCtrlAmt());
        assertEquals(Integer.valueOf(100), output.getLimitCtrlNum());
        assertEquals(new BigDecimal("5000.01"), output.getLimitSumAmt());
        assertEquals(Integer.valueOf(30), output.get否());
    }

    // 场景：查到配置（控制金额5000.00、控制笔数100），累计金额2500.50未大于控制金额、累计笔数101大于控制笔数，或条件第二支成立，限额检查结果为“超限”
    @Test
    void testST004T03() {
        Mockito.lenient().when(rbLimitCtrlConfBcc.findByPrimaryKey("351155", "S001"))
                .thenReturn(buildStubConf("5000.00", 100));

        ST004OutputBO output = st004Pbc.execute(buildInput("2500.50", 101));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertEquals(new BigDecimal("5000.00"), output.getLimitCtrlAmt());
        assertEquals(Integer.valueOf(100), output.getLimitCtrlNum());
        assertEquals(new BigDecimal("2500.50"), output.getLimitSumAmt());
        assertEquals(Integer.valueOf(101), output.get否());
    }

    // 场景：查到配置（控制金额5000.00、控制笔数100），累计金额6000.00大于控制金额且累计笔数150大于控制笔数，或条件两支均成立，限额检查结果为“超限”
    @Test
    void testST004T04() {
        Mockito.lenient().when(rbLimitCtrlConfBcc.findByPrimaryKey("351155", "S001"))
                .thenReturn(buildStubConf("5000.00", 100));

        ST004OutputBO output = st004Pbc.execute(buildInput("6000.00", 150));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertEquals(new BigDecimal("5000.00"), output.getLimitCtrlAmt());
        assertEquals(Integer.valueOf(100), output.getLimitCtrlNum());
        assertEquals(new BigDecimal("6000.00"), output.getLimitSumAmt());
        assertEquals(Integer.valueOf(150), output.get否());
    }

    // 场景：边界否定，查到配置（控制金额5000.00、控制笔数100），累计金额5000.00等于控制金额且累计笔数100等于控制笔数，“大于”为严格比较，限额检查结果为“未超限”
    @Test
    void testST004T05() {
        Mockito.lenient().when(rbLimitCtrlConfBcc.findByPrimaryKey("351155", "S001"))
                .thenReturn(buildStubConf("5000.00", 100));

        ST004OutputBO output = st004Pbc.execute(buildInput("5000.00", 100));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertEquals(new BigDecimal("5000.00"), output.getLimitCtrlAmt());
        assertEquals(Integer.valueOf(100), output.getLimitCtrlNum());
        assertEquals(new BigDecimal("5000.00"), output.getLimitSumAmt());
        assertEquals(Integer.valueOf(100), output.get否());
    }

    // 场景：边界否定，查到配置（控制金额5000.50、控制笔数100），累计金额5000.500与控制金额数值相等仅标度不同，金额按数值比较不构成“大于”，限额检查结果为“未超限”
    @Test
    void testST004T06() {
        Mockito.lenient().when(rbLimitCtrlConfBcc.findByPrimaryKey("351155", "S001"))
                .thenReturn(buildStubConf("5000.50", 100));

        ST004OutputBO output = st004Pbc.execute(buildInput("5000.500", 30));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertEquals(new BigDecimal("5000.50"), output.getLimitCtrlAmt());
        assertEquals(Integer.valueOf(100), output.getLimitCtrlNum());
        assertEquals(new BigDecimal("5000.500"), output.getLimitSumAmt());
        assertEquals(Integer.valueOf(30), output.get否());
    }
}
