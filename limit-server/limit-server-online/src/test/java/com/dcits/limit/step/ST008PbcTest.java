package com.dcits.limit.step;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

import com.dcits.limit.enums.CheckObjType;
import com.dcits.limit.enums.PeriodType;
import com.dcits.limit.facade.bo.ST008InputBO;
import com.dcits.limit.facade.bo.ST008OutputBO;
import com.dcits.limit.facade.components.IRbLimitCtrlConfBcc;
import com.dcits.limit.facade.components.IRbLimitSceneDefBcc;
import com.dcits.limit.facade.components.IRbLimitSumInfoBcc;
import com.dcits.limit.facade.eo.RbLimitCtrlConfEO;
import com.dcits.limit.facade.eo.RbLimitSceneDefEO;
import com.dcits.limit.facade.eo.RbLimitSumInfoEO;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.museframework.component.sequence.trace.ITransIdGenerator;

/** ST008 登记累计限额 步骤单元测试（用例来源：outputs/测试用例.md ST008-TC001~TC005） */
@ExtendWith(MockitoExtension.class)
public class ST008PbcTest {

    @Mock
    private IRbLimitSceneDefBcc rbLimitSceneDefBcc;

    @Mock
    private IRbLimitCtrlConfBcc rbLimitCtrlConfBcc;

    @Mock
    private IRbLimitSumInfoBcc rbLimitSumInfoBcc;

    @Mock
    private ITransIdGenerator transIdGenerator;

    @InjectMocks
    private ST008Pbc st008Pbc;

    // 场景：检查结果“未超限”且累计金额、笔数均为0（首次累计），场景检查对象类型为 ACCT，周期 D×1；预期登记成功，检查对象值取账号，失效日期=系统日期+1天
    @Test
    public void testST008T01() {
        RbLimitSceneDefEO sceneDef = new RbLimitSceneDefEO();
        sceneDef.setLimitSceneNo("S0001");
        sceneDef.setCheckObjType(CheckObjType.ACCT);
        Mockito.lenient().when(rbLimitSceneDefBcc.findByPrimaryKey("S0001")).thenReturn(sceneDef);

        RbLimitCtrlConfEO ctrlConf = new RbLimitCtrlConfEO();
        ctrlConf.setLimitSceneNo("S0001");
        ctrlConf.setPeriodType(PeriodType.D);
        ctrlConf.setPeriodValue("1");
        Mockito.lenient()
                .when(rbLimitCtrlConfBcc.findByEo(argThat(e -> "S0001".equals(e.getLimitSceneNo()))))
                .thenReturn(Collections.singletonList(ctrlConf));

        Mockito.lenient().when(transIdGenerator.nextId()).thenReturn("10001");
        Mockito.lenient().when(rbLimitSumInfoBcc.createSelective(any(RbLimitSumInfoEO.class))).thenReturn(1);

        ST008InputBO input = new ST008InputBO();
        input.setCheckResult("未超限");
        input.setBaseAcctNo("6222000000000001");
        input.setClientNo("C0000123");
        input.setTranAmt(new BigDecimal("1000.00"));
        input.setLimitSceneNo("S0001");
        input.setRunDate(new GregorianCalendar(2026, Calendar.SEPTEMBER, 27).getTime());
        input.setLimitSumAmt(new BigDecimal("0"));
        input.set否(Integer.valueOf(0));

        ST008OutputBO output = st008Pbc.execute(input);

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertEquals("S0001", output.getLimitSceneNo());
        assertEquals("6222000000000001", output.getCheckObjVal());
        assertEquals(0, output.getLimitSumAmt().compareTo(new BigDecimal("1000.00")));
        assertEquals(Integer.valueOf(1), output.get否());
        assertEquals(input.getRunDate(), output.getEffectDate());
        assertEquals(new GregorianCalendar(2026, Calendar.SEPTEMBER, 28).getTime(), output.getExpireDate());

        ArgumentCaptor<RbLimitSumInfoEO> sumInfoCaptor = ArgumentCaptor.forClass(RbLimitSumInfoEO.class);
        Mockito.verify(rbLimitSumInfoBcc).createSelective(sumInfoCaptor.capture());
        RbLimitSumInfoEO sumInfo = sumInfoCaptor.getValue();
        assertEquals("S0001", sumInfo.getLimitSceneNo());
        assertEquals("6222000000000001", sumInfo.getCheckObjVal());
        assertEquals(0, sumInfo.getLimitSumAmt().compareTo(new BigDecimal("1000.00")));
        assertEquals(Integer.valueOf(1), sumInfo.get否());
        assertEquals(input.getRunDate(), sumInfo.getEffectDate());
        assertEquals(new GregorianCalendar(2026, Calendar.SEPTEMBER, 28).getTime(), sumInfo.getExpireDate());
    }

    // 场景：检查结果“未超限”，累计金额为数值0（0.00 标度差异）而笔数=2，“金额等于0”支路成立；场景检查对象类型为 CUST，周期 D×7；预期检查对象值取客户号，失效日期=系统日期+7天
    @Test
    public void testST008T02() {
        RbLimitSceneDefEO sceneDef = new RbLimitSceneDefEO();
        sceneDef.setLimitSceneNo("S0002");
        sceneDef.setCheckObjType(CheckObjType.CUST);
        Mockito.lenient().when(rbLimitSceneDefBcc.findByPrimaryKey("S0002")).thenReturn(sceneDef);

        RbLimitCtrlConfEO ctrlConf = new RbLimitCtrlConfEO();
        ctrlConf.setLimitSceneNo("S0002");
        ctrlConf.setPeriodType(PeriodType.D);
        ctrlConf.setPeriodValue("7");
        Mockito.lenient()
                .when(rbLimitCtrlConfBcc.findByEo(argThat(e -> "S0002".equals(e.getLimitSceneNo()))))
                .thenReturn(Collections.singletonList(ctrlConf));

        Mockito.lenient().when(transIdGenerator.nextId()).thenReturn("10002");
        Mockito.lenient().when(rbLimitSumInfoBcc.createSelective(any(RbLimitSumInfoEO.class))).thenReturn(1);

        ST008InputBO input = new ST008InputBO();
        input.setCheckResult("未超限");
        input.setBaseAcctNo("6222000000000002");
        input.setClientNo("C0000456");
        input.setTranAmt(new BigDecimal("2500.50"));
        input.setLimitSceneNo("S0002");
        input.setRunDate(new GregorianCalendar(2026, Calendar.SEPTEMBER, 27).getTime());
        input.setLimitSumAmt(new BigDecimal("0.00"));
        input.set否(Integer.valueOf(2));

        ST008OutputBO output = st008Pbc.execute(input);

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertEquals("S0002", output.getLimitSceneNo());
        assertEquals("C0000456", output.getCheckObjVal());
        assertEquals(0, output.getLimitSumAmt().compareTo(new BigDecimal("2500.50")));
        assertEquals(Integer.valueOf(1), output.get否());
        assertEquals(input.getRunDate(), output.getEffectDate());
        assertEquals(new GregorianCalendar(2026, Calendar.OCTOBER, 4).getTime(), output.getExpireDate());

        ArgumentCaptor<RbLimitSumInfoEO> sumInfoCaptor = ArgumentCaptor.forClass(RbLimitSumInfoEO.class);
        Mockito.verify(rbLimitSumInfoBcc).createSelective(sumInfoCaptor.capture());
        RbLimitSumInfoEO sumInfo = sumInfoCaptor.getValue();
        assertEquals("S0002", sumInfo.getLimitSceneNo());
        assertEquals("C0000456", sumInfo.getCheckObjVal());
        assertEquals(0, sumInfo.getLimitSumAmt().compareTo(new BigDecimal("2500.50")));
        assertEquals(Integer.valueOf(1), sumInfo.get否());
        assertEquals(input.getRunDate(), sumInfo.getEffectDate());
        assertEquals(new GregorianCalendar(2026, Calendar.OCTOBER, 4).getTime(), sumInfo.getExpireDate());
    }

    // 场景：检查结果“未超限”，累计金额=3200.00 非0 而笔数=0，“笔数等于0”支路成立；账户级别场景，系统日期为月末 2026-09-30，周期 D×1；预期登记成功，失效日期跨月为 2026-10-01
    @Test
    public void testST008T03() {
        RbLimitSceneDefEO sceneDef = new RbLimitSceneDefEO();
        sceneDef.setLimitSceneNo("S0003");
        sceneDef.setCheckObjType(CheckObjType.ACCT);
        Mockito.lenient().when(rbLimitSceneDefBcc.findByPrimaryKey("S0003")).thenReturn(sceneDef);

        RbLimitCtrlConfEO ctrlConf = new RbLimitCtrlConfEO();
        ctrlConf.setLimitSceneNo("S0003");
        ctrlConf.setPeriodType(PeriodType.D);
        ctrlConf.setPeriodValue("1");
        Mockito.lenient()
                .when(rbLimitCtrlConfBcc.findByEo(argThat(e -> "S0003".equals(e.getLimitSceneNo()))))
                .thenReturn(Collections.singletonList(ctrlConf));

        Mockito.lenient().when(transIdGenerator.nextId()).thenReturn("10003");
        Mockito.lenient().when(rbLimitSumInfoBcc.createSelective(any(RbLimitSumInfoEO.class))).thenReturn(1);

        ST008InputBO input = new ST008InputBO();
        input.setCheckResult("未超限");
        input.setBaseAcctNo("6222000000000003");
        input.setClientNo("C0000789");
        input.setTranAmt(new BigDecimal("500.00"));
        input.setLimitSceneNo("S0003");
        input.setRunDate(new GregorianCalendar(2026, Calendar.SEPTEMBER, 30).getTime());
        input.setLimitSumAmt(new BigDecimal("3200.00"));
        input.set否(Integer.valueOf(0));

        ST008OutputBO output = st008Pbc.execute(input);

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertEquals("S0003", output.getLimitSceneNo());
        assertEquals("6222000000000003", output.getCheckObjVal());
        assertEquals(0, output.getLimitSumAmt().compareTo(new BigDecimal("500.00")));
        assertEquals(Integer.valueOf(1), output.get否());
        assertEquals(input.getRunDate(), output.getEffectDate());
        assertEquals(new GregorianCalendar(2026, Calendar.OCTOBER, 1).getTime(), output.getExpireDate());

        ArgumentCaptor<RbLimitSumInfoEO> sumInfoCaptor = ArgumentCaptor.forClass(RbLimitSumInfoEO.class);
        Mockito.verify(rbLimitSumInfoBcc).createSelective(sumInfoCaptor.capture());
        RbLimitSumInfoEO sumInfo = sumInfoCaptor.getValue();
        assertEquals("S0003", sumInfo.getLimitSceneNo());
        assertEquals("6222000000000003", sumInfo.getCheckObjVal());
        assertEquals(0, sumInfo.getLimitSumAmt().compareTo(new BigDecimal("500.00")));
        assertEquals(Integer.valueOf(1), sumInfo.get否());
        assertEquals(input.getRunDate(), sumInfo.getEffectDate());
        assertEquals(new GregorianCalendar(2026, Calendar.OCTOBER, 1).getTime(), sumInfo.getExpireDate());
    }

    // 场景：限额检查结果为“超限”（非“未超限”），即使累计金额与笔数均为0也不登记；预期成功结束，六个输出业务字段均为null
    @Test
    public void testST008T04() {
        ST008InputBO input = new ST008InputBO();
        input.setCheckResult("超限");
        input.setBaseAcctNo("6222000000000001");
        input.setClientNo("C0000123");
        input.setTranAmt(new BigDecimal("1000.00"));
        input.setLimitSceneNo("S0001");
        input.setRunDate(new GregorianCalendar(2026, Calendar.SEPTEMBER, 27).getTime());
        input.setLimitSumAmt(new BigDecimal("0"));
        input.set否(Integer.valueOf(0));

        ST008OutputBO output = st008Pbc.execute(input);

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertNull(output.getLimitSceneNo());
        assertNull(output.getCheckObjVal());
        assertNull(output.getLimitSumAmt());
        assertNull(output.get否());
        assertNull(output.getEffectDate());
        assertNull(output.getExpireDate());
    }

    // 场景：检查结果“未超限”但累计金额=1500.00、累计笔数=3 均非0，“等于0”两支路均不成立；预期成功结束不登记，六个输出业务字段均为null
    @Test
    public void testST008T05() {
        ST008InputBO input = new ST008InputBO();
        input.setCheckResult("未超限");
        input.setBaseAcctNo("6222000000000001");
        input.setClientNo("C0000123");
        input.setTranAmt(new BigDecimal("800.00"));
        input.setLimitSceneNo("S0001");
        input.setRunDate(new GregorianCalendar(2026, Calendar.SEPTEMBER, 27).getTime());
        input.setLimitSumAmt(new BigDecimal("1500.00"));
        input.set否(Integer.valueOf(3));

        ST008OutputBO output = st008Pbc.execute(input);

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertNull(output.getLimitSceneNo());
        assertNull(output.getCheckObjVal());
        assertNull(output.getLimitSumAmt());
        assertNull(output.get否());
        assertNull(output.getEffectDate());
        assertNull(output.getExpireDate());
    }
}
