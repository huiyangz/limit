package com.dcits.limit.step;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;

import com.dcits.limit.enums.CtrlItemType;
import com.dcits.limit.enums.LimitConvert;
import com.dcits.limit.enums.PeriodType;
import com.dcits.limit.enums.RecordStatus;
import com.dcits.limit.enums.SumType;
import com.dcits.limit.facade.bo.ST003InputBO;
import com.dcits.limit.facade.bo.ST003OutputBO;
import com.dcits.limit.facade.components.IRbLimitCtrlConfBcc;
import com.dcits.limit.facade.components.IRbLimitCtrlCustomInfoBcc;
import com.dcits.limit.facade.components.IRbLimitSceneDefBcc;
import com.dcits.limit.facade.components.IRbLimitSumInfoBcc;
import com.dcits.limit.facade.components.IRbLimitSumJnlBcc;
import com.dcits.limit.facade.eo.RbLimitCtrlConfEO;
import com.dcits.limit.facade.eo.RbLimitCtrlCustomInfoEO;
import com.dcits.limit.facade.eo.RbLimitSceneDefEO;
import com.dcits.limit.facade.eo.RbLimitSumInfoEO;
import com.dcits.limit.facade.eo.RbLimitSumJnlEO;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/** ST003 计算限额累计金额 步骤单元测试（用例来源：outputs/测试用例.md ST003-TC001~TC010） */
@ExtendWith(MockitoExtension.class)
public class ST003PbcTest {

    @Mock
    private IRbLimitCtrlCustomInfoBcc rbLimitCtrlCustomInfoBcc;

    @Mock
    private IRbLimitCtrlConfBcc rbLimitCtrlConfBcc;

    @Mock
    private IRbLimitSumInfoBcc rbLimitSumInfoBcc;

    @Mock
    private IRbLimitSumJnlBcc rbLimitSumJnlBcc;

    @Mock
    private IRbLimitSceneDefBcc rbLimitSceneDefBcc;

    @InjectMocks
    private ST003Pbc st003Pbc;

    // 场景：客户自定义配置命中且控制类型为 N-累计笔数（与配置表 O-单笔金额相异），验证限额信息优先取自定义配置；sumType=1 自然周期，累计信息存量在生效范围内；预期累计金额=2500.50+1000.00、笔数=3+1
    @Test
    public void testST003T01() {
        Mockito.lenient()
                .when(rbLimitCtrlCustomInfoBcc.findByPrimaryKey("6222000000000001", "S3001"))
                .thenReturn(customInfo("6222000000000001", "S3001", "C0000123", CtrlItemType.N));
        Mockito.lenient()
                .when(rbLimitCtrlConfBcc.findByEo(argThat(e -> "S3001".equals(e.getLimitSceneNo()))))
                .thenReturn(Collections
                        .singletonList(ctrlConf("S3001", CtrlItemType.O, SumType.VALUE_1, null, null)));
        Mockito.lenient()
                .when(rbLimitSumInfoBcc.findByPrimaryKey("6222000000000001", "S3001"))
                .thenReturn(sumInfo("6222000000000001", "S3001", "C0000123", "2500.50", 3,
                        date(2020, Calendar.JANUARY, 1), date(2099, Calendar.DECEMBER, 31)));

        ST003OutputBO output = st003Pbc.execute(input("S3001", "1000.00", "6222000000000001", "C0000123"));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertEquals(0, output.getLimitSumAmt().compareTo(new BigDecimal("3500.50")));
        assertEquals(Integer.valueOf(4), output.get否());
    }

    // 场景：自定义配置未命中（返回 null），限额信息回退取配置表（A-累计金额）；sumType=3 指定日期范围，存量记录在生效范围内；预期累计金额=1200.00+800.00、笔数=2+1
    @Test
    public void testST003T02() {
        Mockito.lenient()
                .when(rbLimitCtrlCustomInfoBcc.findByPrimaryKey("C0000456", "S3002"))
                .thenReturn(null);
        Mockito.lenient()
                .when(rbLimitCtrlConfBcc.findByEo(argThat(e -> "S3002".equals(e.getLimitSceneNo()))))
                .thenReturn(Collections
                        .singletonList(ctrlConf("S3002", CtrlItemType.A, SumType.VALUE_3, null, null)));
        Mockito.lenient()
                .when(rbLimitSumInfoBcc.findByPrimaryKey("C0000456", "S3002"))
                .thenReturn(sumInfo("C0000456", "S3002", "C0000456", "1200.00", 2,
                        date(2021, Calendar.JUNE, 1), date(2099, Calendar.JUNE, 30)));

        ST003OutputBO output = st003Pbc.execute(input("S3002", "800.00", "C0000456", "C0000456"));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertEquals(0, output.getLimitSumAmt().compareTo(new BigDecimal("2000.00")));
        assertEquals(Integer.valueOf(3), output.get否());
    }

    // 场景：B-累计笔数/金额 且 sumType=2 滑动窗口（D×36500）；折算方式=1 原币种，流水 3 笔均在有效周期内；预期金额按流水交易金额汇总=600.00+250.50+149.50、笔数=3，不叠加当笔 777.00
    @Test
    public void testST003T03() {
        Mockito.lenient()
                .when(rbLimitCtrlCustomInfoBcc.findByPrimaryKey("6222000000000003", "S3003"))
                .thenReturn(null);
        Mockito.lenient()
                .when(rbLimitCtrlConfBcc.findByEo(argThat(e -> "S3003".equals(e.getLimitSceneNo()))))
                .thenReturn(Collections.singletonList(
                        ctrlConf("S3003", CtrlItemType.B, SumType.VALUE_2, PeriodType.D, "36500")));
        Mockito.lenient()
                .when(rbLimitSceneDefBcc.findByPrimaryKey("S3003"))
                .thenReturn(sceneDef("S3003", LimitConvert.VALUE_1));
        Mockito.lenient()
                .when(rbLimitSumJnlBcc.findByEo(argThat(e -> "6222000000000003".equals(e.getCheckObjVal())
                        && "S3003".equals(e.getLimitSceneNo()) && "C0000789".equals(e.getClientNo()))))
                .thenReturn(Arrays.asList(
                        journal("6222000000000003", "S3003", "C0000789", "600.00", null,
                                date(2026, Calendar.JANUARY, 10)),
                        journal("6222000000000003", "S3003", "C0000789", "250.50", null,
                                date(2026, Calendar.MAY, 20)),
                        journal("6222000000000003", "S3003", "C0000789", "149.50", null,
                                date(2026, Calendar.SEPTEMBER, 21))));

        ST003OutputBO output = st003Pbc.execute(input("S3003", "777.00", "6222000000000003", "C0000789"));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertEquals(0, output.getLimitSumAmt().compareTo(new BigDecimal("1000.00")));
        assertEquals(Integer.valueOf(3), output.get否());
    }

    // 场景：B-累计笔数/金额 且 sumType=2 滑动窗口（D×36500）；折算方式=2 折算，流水 3 笔均在有效周期内；预期金额按限额折算金额汇总=400.00+50.25+99.75、笔数=3，交易金额不参与
    @Test
    public void testST003T04() {
        Mockito.lenient()
                .when(rbLimitCtrlCustomInfoBcc.findByPrimaryKey("C0000321", "S3004"))
                .thenReturn(null);
        Mockito.lenient()
                .when(rbLimitCtrlConfBcc.findByEo(argThat(e -> "S3004".equals(e.getLimitSceneNo()))))
                .thenReturn(Collections.singletonList(
                        ctrlConf("S3004", CtrlItemType.B, SumType.VALUE_2, PeriodType.D, "36500")));
        Mockito.lenient()
                .when(rbLimitSceneDefBcc.findByPrimaryKey("S3004"))
                .thenReturn(sceneDef("S3004", LimitConvert.VALUE_2));
        Mockito.lenient()
                .when(rbLimitSumJnlBcc.findByEo(argThat(e -> "C0000321".equals(e.getCheckObjVal())
                        && "S3004".equals(e.getLimitSceneNo()) && "C0000321".equals(e.getClientNo()))))
                .thenReturn(Arrays.asList(
                        journal("C0000321", "S3004", "C0000321", "500.00", "400.00",
                                date(2026, Calendar.FEBRUARY, 10)),
                        journal("C0000321", "S3004", "C0000321", "300.00", "50.25",
                                date(2026, Calendar.JUNE, 15)),
                        journal("C0000321", "S3004", "C0000321", "200.00", "99.75",
                                date(2026, Calendar.SEPTEMBER, 5))));

        ST003OutputBO output = st003Pbc.execute(input("S3004", "888.00", "C0000321", "C0000321"));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertEquals(0, output.getLimitSumAmt().compareTo(new BigDecimal("550.00")));
        assertEquals(Integer.valueOf(3), output.get否());
    }

    // 场景：客户自定义配置命中且控制类型为 O-单笔金额；无需计算累计限额；预期成功结束，限额累计金额、笔数均为 null
    @Test
    public void testST003T05() {
        Mockito.lenient()
                .when(rbLimitCtrlCustomInfoBcc.findByPrimaryKey("6222000000000005", "S3005"))
                .thenReturn(customInfo("6222000000000005", "S3005", "C0000125", CtrlItemType.O));
        Mockito.lenient()
                .when(rbLimitCtrlConfBcc.findByEo(argThat(e -> "S3005".equals(e.getLimitSceneNo()))))
                .thenReturn(Collections
                        .singletonList(ctrlConf("S3005", CtrlItemType.O, SumType.VALUE_1, null, null)));

        ST003OutputBO output = st003Pbc.execute(input("S3005", "500.00", "6222000000000005", "C0000125"));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertNull(output.getLimitSumAmt());
        assertNull(output.get否());
    }

    // 场景：N-累计笔数 且 sumType=4 指定时间范围；累计信息表查无记录（返回 null）按不存在处理；预期累计金额=交易金额 300.00、笔数=1
    @Test
    public void testST003T06() {
        Mockito.lenient()
                .when(rbLimitCtrlCustomInfoBcc.findByPrimaryKey("6222000000000006", "S3006"))
                .thenReturn(customInfo("6222000000000006", "S3006", "C0000126", CtrlItemType.N));
        Mockito.lenient()
                .when(rbLimitCtrlConfBcc.findByEo(argThat(e -> "S3006".equals(e.getLimitSceneNo()))))
                .thenReturn(Collections
                        .singletonList(ctrlConf("S3006", CtrlItemType.N, SumType.VALUE_4, null, null)));
        Mockito.lenient()
                .when(rbLimitSumInfoBcc.findByPrimaryKey("6222000000000006", "S3006"))
                .thenReturn(null);

        ST003OutputBO output = st003Pbc.execute(input("S3006", "300.00", "6222000000000006", "C0000126"));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertEquals(0, output.getLimitSumAmt().compareTo(new BigDecimal("300.00")));
        assertEquals(Integer.valueOf(1), output.get否());
    }

    // 场景：A-累计金额 且 sumType=5 指定日期+时间范围；存量记录存在但系统日期已大于等于失效日期（2019-01-01~2020-01-01 已失效），视为不存在；预期累计金额=450.00、笔数=1
    @Test
    public void testST003T07() {
        Mockito.lenient()
                .when(rbLimitCtrlCustomInfoBcc.findByPrimaryKey("C0000777", "S3007"))
                .thenReturn(null);
        Mockito.lenient()
                .when(rbLimitCtrlConfBcc.findByEo(argThat(e -> "S3007".equals(e.getLimitSceneNo()))))
                .thenReturn(Collections
                        .singletonList(ctrlConf("S3007", CtrlItemType.A, SumType.VALUE_5, null, null)));
        Mockito.lenient()
                .when(rbLimitSumInfoBcc.findByPrimaryKey("C0000777", "S3007"))
                .thenReturn(sumInfo("C0000777", "S3007", "C0000777", "9999.00", 9,
                        date(2019, Calendar.JANUARY, 1), date(2020, Calendar.JANUARY, 1)));

        ST003OutputBO output = st003Pbc.execute(input("S3007", "450.00", "C0000777", "C0000777"));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertEquals(0, output.getLimitSumAmt().compareTo(new BigDecimal("450.00")));
        assertEquals(Integer.valueOf(1), output.get否());
    }

    // 场景：A-累计金额 且 sumType=1 自然周期；存量记录存在但系统日期小于生效日期（2098-01-01 未生效），视为不存在；预期累计金额=260.00、笔数=1
    @Test
    public void testST003T08() {
        Mockito.lenient()
                .when(rbLimitCtrlCustomInfoBcc.findByPrimaryKey("6222000000000008", "S3008"))
                .thenReturn(customInfo("6222000000000008", "S3008", "C0000128", CtrlItemType.A));
        Mockito.lenient()
                .when(rbLimitCtrlConfBcc.findByEo(argThat(e -> "S3008".equals(e.getLimitSceneNo()))))
                .thenReturn(Collections
                        .singletonList(ctrlConf("S3008", CtrlItemType.A, SumType.VALUE_1, null, null)));
        Mockito.lenient()
                .when(rbLimitSumInfoBcc.findByPrimaryKey("6222000000000008", "S3008"))
                .thenReturn(sumInfo("6222000000000008", "S3008", "C0000128", "123.00", 2,
                        date(2098, Calendar.JANUARY, 1), date(2099, Calendar.JANUARY, 1)));

        ST003OutputBO output = st003Pbc.execute(input("S3008", "260.00", "6222000000000008", "C0000128"));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertEquals(0, output.getLimitSumAmt().compareTo(new BigDecimal("260.00")));
        assertEquals(Integer.valueOf(1), output.get否());
    }

    // 场景：B-累计笔数/金额 且 sumType=2 滑动窗口（D×30）；滑动流水表查询返回空集合，有效周期内不存在；预期累计金额=交易金额 150.00、笔数=1
    @Test
    public void testST003T09() {
        Mockito.lenient()
                .when(rbLimitCtrlCustomInfoBcc.findByPrimaryKey("6222000000000009", "S3009"))
                .thenReturn(null);
        Mockito.lenient()
                .when(rbLimitCtrlConfBcc.findByEo(argThat(e -> "S3009".equals(e.getLimitSceneNo()))))
                .thenReturn(Collections.singletonList(
                        ctrlConf("S3009", CtrlItemType.B, SumType.VALUE_2, PeriodType.D, "30")));
        Mockito.lenient()
                .when(rbLimitSceneDefBcc.findByPrimaryKey("S3009"))
                .thenReturn(sceneDef("S3009", LimitConvert.VALUE_1));
        Mockito.lenient()
                .when(rbLimitSumJnlBcc.findByEo(argThat(e -> "6222000000000009".equals(e.getCheckObjVal())
                        && "S3009".equals(e.getLimitSceneNo()) && "C0000129".equals(e.getClientNo()))))
                .thenReturn(Collections.emptyList());

        ST003OutputBO output = st003Pbc.execute(input("S3009", "150.00", "6222000000000009", "C0000129"));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertEquals(0, output.getLimitSumAmt().compareTo(new BigDecimal("150.00")));
        assertEquals(Integer.valueOf(1), output.get否());
    }

    // 场景：B-累计笔数/金额 且 sumType=2 滑动窗口（D×30）；流水表有 2 条记录但交易日期（2020 年）均在有效周期外；预期周期外流水不计入，累计金额=交易金额 660.00、笔数=1
    @Test
    public void testST003T10() {
        Mockito.lenient()
                .when(rbLimitCtrlCustomInfoBcc.findByPrimaryKey("C0000999", "S3010"))
                .thenReturn(null);
        Mockito.lenient()
                .when(rbLimitCtrlConfBcc.findByEo(argThat(e -> "S3010".equals(e.getLimitSceneNo()))))
                .thenReturn(Collections.singletonList(
                        ctrlConf("S3010", CtrlItemType.B, SumType.VALUE_2, PeriodType.D, "30")));
        Mockito.lenient()
                .when(rbLimitSceneDefBcc.findByPrimaryKey("S3010"))
                .thenReturn(sceneDef("S3010", LimitConvert.VALUE_1));
        Mockito.lenient()
                .when(rbLimitSumJnlBcc.findByEo(argThat(e -> "C0000999".equals(e.getCheckObjVal())
                        && "S3010".equals(e.getLimitSceneNo()) && "C0000999".equals(e.getClientNo()))))
                .thenReturn(Arrays.asList(
                        journal("C0000999", "S3010", "C0000999", "500.00", null,
                                date(2020, Calendar.JANUARY, 10)),
                        journal("C0000999", "S3010", "C0000999", "200.00", null,
                                date(2020, Calendar.FEBRUARY, 15))));

        ST003OutputBO output = st003Pbc.execute(input("S3010", "660.00", "C0000999", "C0000999"));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertEquals(0, output.getLimitSumAmt().compareTo(new BigDecimal("660.00")));
        assertEquals(Integer.valueOf(1), output.get否());
    }

    /** 构造步骤输入：限额场景编码、交易金额、限额检查对象值、客户号 */
    private static ST003InputBO input(String limitSceneNo, String tranAmt, String checkObjVal,
            String clientNo) {
        ST003InputBO input = new ST003InputBO();
        input.setLimitSceneNo(limitSceneNo);
        input.setTranAmt(new BigDecimal(tranAmt));
        input.setCheckObjVal(checkObjVal);
        input.setClientNo(clientNo);
        return input;
    }

    /** 构造限额控制客户自定义配置记录 */
    private static RbLimitCtrlCustomInfoEO customInfo(String checkObjVal, String limitSceneNo,
            String clientNo, CtrlItemType ctrlItemType) {
        RbLimitCtrlCustomInfoEO customInfo = new RbLimitCtrlCustomInfoEO();
        customInfo.setCheckObjVal(checkObjVal);
        customInfo.setLimitSceneNo(limitSceneNo);
        customInfo.setClientNo(clientNo);
        customInfo.setCtrlItemType(ctrlItemType);
        return customInfo;
    }

    /** 构造限额控制配置记录（滑动窗口场景带期限类型、周期值，其余传 null） */
    private static RbLimitCtrlConfEO ctrlConf(String limitSceneNo, CtrlItemType ctrlItemType,
            SumType sumType, PeriodType periodType, String periodValue) {
        RbLimitCtrlConfEO ctrlConf = new RbLimitCtrlConfEO();
        ctrlConf.setLimitSceneNo(limitSceneNo);
        ctrlConf.setCtrlItemType(ctrlItemType);
        ctrlConf.setSumType(sumType);
        ctrlConf.setPeriodType(periodType);
        ctrlConf.setPeriodValue(periodValue);
        return ctrlConf;
    }

    /** 构造限额累计信息表存量记录 */
    private static RbLimitSumInfoEO sumInfo(String checkObjVal, String limitSceneNo, String clientNo,
            String limitSumAmt, int count, Date effectDate, Date expireDate) {
        RbLimitSumInfoEO sumInfo = new RbLimitSumInfoEO();
        sumInfo.setCheckObjVal(checkObjVal);
        sumInfo.setLimitSceneNo(limitSceneNo);
        sumInfo.setClientNo(clientNo);
        sumInfo.setLimitSumAmt(new BigDecimal(limitSumAmt));
        sumInfo.set否(Integer.valueOf(count));
        sumInfo.setEffectDate(effectDate);
        sumInfo.setExpireDate(expireDate);
        return sumInfo;
    }

    /** 构造限额场景定义记录 */
    private static RbLimitSceneDefEO sceneDef(String limitSceneNo, LimitConvert limitConvert) {
        RbLimitSceneDefEO sceneDef = new RbLimitSceneDefEO();
        sceneDef.setLimitSceneNo(limitSceneNo);
        sceneDef.setLimitConvert(limitConvert);
        return sceneDef;
    }

    /** 构造滑动流水表记录：记录状态固定为生效（A） */
    private static RbLimitSumJnlEO journal(String checkObjVal, String limitSceneNo, String clientNo,
            String tranAmt, String limitConvertAmt, Date tranDate) {
        RbLimitSumJnlEO journal = new RbLimitSumJnlEO();
        journal.setCheckObjVal(checkObjVal);
        journal.setLimitSceneNo(limitSceneNo);
        journal.setClientNo(clientNo);
        journal.setTranAmt(tranAmt == null ? null : new BigDecimal(tranAmt));
        journal.setLimitConvertAmt(limitConvertAmt == null ? null : new BigDecimal(limitConvertAmt));
        journal.setTranDate(tranDate);
        journal.setRecordStatus(RecordStatus.A);
        return journal;
    }

    /** 按年、月（Calendar 月常量）、日构造日期 */
    private static Date date(int year, int month, int day) {
        return new GregorianCalendar(year, month, day).getTime();
    }
}
