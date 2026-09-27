package com.dcits.limit.step;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dcits.limit.facade.bo.ST001InputBO;
import com.dcits.limit.facade.bo.ST001OutputBO;
import com.dcits.limit.facade.components.IRbLimitCtrlConfBcc;
import com.dcits.limit.facade.components.IRbLimitCtrlCustomInfoBcc;
import com.dcits.limit.facade.eo.RbLimitCtrlConfEO;
import com.dcits.limit.facade.eo.RbLimitCtrlCustomInfoEO;

/**
 * ST001 获取限额场景编码 单元测试
 */
@ExtendWith(MockitoExtension.class)
public class ST001PbcTest {

    private static final String BASE_ACCT_NO = "20000123456789";
    private static final String CLIENT_NO = "C10002345";
    private static final String LIMIT_SCENE_NO = "LS0001";

    @Mock
    private IRbLimitCtrlConfBcc rbLimitCtrlConfBcc;

    @Mock
    private IRbLimitCtrlCustomInfoBcc rbLimitCtrlCustomInfoBcc;

    @InjectMocks
    private ST001Pbc st001Pbc;

    private static Date date(int year, int month, int day) {
        return new GregorianCalendar(year, month, day).getTime();
    }

    private ST001InputBO buildInput(Date tranDate) {
        ST001InputBO input = new ST001InputBO();
        input.setBaseAcctNo(BASE_ACCT_NO);
        input.setClientNo(CLIENT_NO);
        input.setLimitSceneNo(LIMIT_SCENE_NO);
        input.setTranDate(tranDate);
        return input;
    }

    private RbLimitCtrlConfEO buildConf(String allowCustomFlag, String onlyCustom) {
        RbLimitCtrlConfEO conf = new RbLimitCtrlConfEO();
        conf.setLimitSceneNo(LIMIT_SCENE_NO);
        conf.setAllowCustomFlag(allowCustomFlag);
        conf.setOnlyCustom(onlyCustom);
        conf.setTempLimitFlag("Y");
        conf.setTempLimitValidTerm("30");
        return conf;
    }

    private RbLimitCtrlCustomInfoEO buildCustom(String tempLimitFlag, Date effectDate, Date expireDate) {
        RbLimitCtrlCustomInfoEO custom = new RbLimitCtrlCustomInfoEO();
        custom.setLimitSceneNo(LIMIT_SCENE_NO);
        custom.setClientNo(CLIENT_NO);
        custom.setCheckObjVal(CLIENT_NO);
        custom.setTempLimitFlag(tempLimitFlag);
        custom.setEffectDate(effectDate);
        custom.setExpireDate(expireDate);
        return custom;
    }

    private void stubConfQuery(RbLimitCtrlConfEO conf) {
        Mockito.lenient().when(rbLimitCtrlConfBcc.findByEo(Mockito.argThat(
                        e -> e != null && LIMIT_SCENE_NO.equals(e.getLimitSceneNo()))))
                .thenReturn(List.of(conf));
    }

    private void stubCustomQuery(List<RbLimitCtrlCustomInfoEO> customList) {
        Mockito.lenient().when(rbLimitCtrlCustomInfoBcc.findByEo(Mockito.argThat(
                        e -> e != null && LIMIT_SCENE_NO.equals(e.getLimitSceneNo())
                                && CLIENT_NO.equals(e.getClientNo()))))
                .thenReturn(customList);
    }

    private void assertSuccess(ST001OutputBO output) {
        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
    }

    private void assertEcho(ST001OutputBO output) {
        assertEquals(BASE_ACCT_NO, output.getBaseAcctNo());
        assertEquals(CLIENT_NO, output.getClientNo());
    }

    private void assertConfFields(ST001OutputBO output, String allowCustomFlag, String onlyCustom) {
        assertEquals(LIMIT_SCENE_NO, output.getLimitSceneNo());
        assertEquals(allowCustomFlag, output.getAllowCustomFlag());
        assertEquals(onlyCustom, output.getOnlyCustom());
        assertEquals("Y", output.getTempLimitFlag());
        assertEquals("30", output.getTempLimitValidTerm());
    }

    private void assertCustomFields(ST001OutputBO output, String tempLimitFlag, Date effectDate, Date expireDate) {
        assertEquals(LIMIT_SCENE_NO, output.getCustomLimitSceneNo());
        assertEquals(tempLimitFlag, output.getCustomTempLimitFlag());
        assertEquals(effectDate, output.getEffectDate());
        assertEquals(expireDate, output.getExpireDate());
    }

    private void assertCustomFieldsNull(ST001OutputBO output) {
        assertNull(output.getCustomLimitSceneNo());
        assertNull(output.getCustomTempLimitFlag());
        assertNull(output.getEffectDate());
        assertNull(output.getExpireDate());
    }

    // TC001：限额控制配置表无记录，子步骤1 直接返回限额场景编码为空，成功且全部业务字段为 null，账号客户号回显
    @Test
    public void testST001T01() {
        Mockito.lenient().when(rbLimitCtrlConfBcc.findByEo(Mockito.argThat(
                        e -> e != null && LIMIT_SCENE_NO.equals(e.getLimitSceneNo()))))
                .thenReturn(Collections.emptyList());

        ST001OutputBO output = st001Pbc.execute(buildInput(date(2026, Calendar.SEPTEMBER, 27)));

        assertSuccess(output);
        assertNull(output.getLimitSceneNo());
        assertNull(output.getAllowCustomFlag());
        assertNull(output.getOnlyCustom());
        assertNull(output.getTempLimitFlag());
        assertNull(output.getTempLimitValidTerm());
        assertCustomFieldsNull(output);
        assertEcho(output);
    }

    // TC002：允许自定义标识=N，子步骤2 直接返回限额场景编码，不跳转《获取自定义限额》，自定义 4 字段为 null
    @Test
    public void testST001T02() {
        stubConfQuery(buildConf("N", "N"));

        ST001OutputBO output = st001Pbc.execute(buildInput(date(2026, Calendar.SEPTEMBER, 27)));

        assertSuccess(output);
        assertConfFields(output, "N", "N");
        assertCustomFieldsNull(output);
        assertEcho(output);
    }

    // TC003：允许自定义标识=Y 跳转《获取自定义限额》；自定义限额存在且临时限额标志=N，返回限额场景编码（子步骤4.2）
    @Test
    public void testST001T03() {
        stubConfQuery(buildConf("Y", "N"));
        stubCustomQuery(List.of(buildCustom("N",
                date(2026, Calendar.MARCH, 15), date(2026, Calendar.DECEMBER, 31))));

        ST001OutputBO output = st001Pbc.execute(buildInput(date(2026, Calendar.SEPTEMBER, 27)));

        assertSuccess(output);
        assertConfFields(output, "Y", "N");
        assertEquals("Y", output.getTempLimitFlag());
        assertEquals("N", output.getCustomTempLimitFlag());
        assertCustomFields(output, "N",
                date(2026, Calendar.MARCH, 15), date(2026, Calendar.DECEMBER, 31));
        assertEcho(output);
    }

    // TC004：自定义限额存在、临时限额标志=Y、交易日期小于失效日期，返回限额场景编码（子步骤4.1 第二句）
    @Test
    public void testST001T04() {
        stubConfQuery(buildConf("Y", "N"));
        stubCustomQuery(List.of(buildCustom("Y",
                date(2026, Calendar.MARCH, 15), date(2026, Calendar.DECEMBER, 31))));

        ST001OutputBO output = st001Pbc.execute(buildInput(date(2026, Calendar.SEPTEMBER, 27)));

        assertSuccess(output);
        assertConfFields(output, "Y", "N");
        assertCustomFields(output, "Y",
                date(2026, Calendar.MARCH, 15), date(2026, Calendar.DECEMBER, 31));
        assertEcho(output);
    }

    // TC005：自定义限额存在、临时限额标志=Y、交易日期大于失效日期，返回限额场景编码为空（子步骤4.1 第一句，仍为步骤成功）
    @Test
    public void testST001T05() {
        stubConfQuery(buildConf("Y", "N"));
        stubCustomQuery(List.of(buildCustom("Y",
                date(2026, Calendar.JANUARY, 10), date(2026, Calendar.AUGUST, 31))));

        ST001OutputBO output = st001Pbc.execute(buildInput(date(2026, Calendar.SEPTEMBER, 27)));

        assertSuccess(output);
        assertNull(output.getLimitSceneNo());
        assertEquals("Y", output.getAllowCustomFlag());
        assertEquals("N", output.getOnlyCustom());
        assertEquals("Y", output.getTempLimitFlag());
        assertEquals("30", output.getTempLimitValidTerm());
        assertCustomFields(output, "Y",
                date(2026, Calendar.JANUARY, 10), date(2026, Calendar.AUGUST, 31));
        assertEcho(output);
    }

    // TC006：边界：交易日期等于失效日期且临时限额标志=Y，按"小于等于失效日期"返回限额场景编码（子步骤4.1 边界）
    @Test
    public void testST001T06() {
        stubConfQuery(buildConf("Y", "N"));
        stubCustomQuery(List.of(buildCustom("Y",
                date(2026, Calendar.JUNE, 1), date(2026, Calendar.SEPTEMBER, 27))));

        ST001OutputBO output = st001Pbc.execute(buildInput(date(2026, Calendar.SEPTEMBER, 27)));

        assertSuccess(output);
        assertConfFields(output, "Y", "N");
        assertCustomFields(output, "Y",
                date(2026, Calendar.JUNE, 1), date(2026, Calendar.SEPTEMBER, 27));
        assertEcho(output);
    }

    // TC007：自定义限额不存在且仅检查客户自定义标志=Y，返回限额场景编码为空（子步骤4.3）
    @Test
    public void testST001T07() {
        stubConfQuery(buildConf("Y", "Y"));
        stubCustomQuery(Collections.emptyList());

        ST001OutputBO output = st001Pbc.execute(buildInput(date(2026, Calendar.SEPTEMBER, 27)));

        assertSuccess(output);
        assertNull(output.getLimitSceneNo());
        assertEquals("Y", output.getAllowCustomFlag());
        assertEquals("Y", output.getOnlyCustom());
        assertEquals("Y", output.getTempLimitFlag());
        assertEquals("30", output.getTempLimitValidTerm());
        assertCustomFieldsNull(output);
        assertEcho(output);
    }

    // TC008：自定义限额不存在且仅检查客户自定义标志=N，返回限额场景编码（子步骤4.4）
    @Test
    public void testST001T08() {
        stubConfQuery(buildConf("Y", "N"));
        stubCustomQuery(Collections.emptyList());

        ST001OutputBO output = st001Pbc.execute(buildInput(date(2026, Calendar.SEPTEMBER, 27)));

        assertSuccess(output);
        assertConfFields(output, "Y", "N");
        assertCustomFieldsNull(output);
        assertEcho(output);
    }

    // TC009：多条自定义限额记录（含生效日期等于交易日期的边界记录）过滤后取生效日期最大一条，临时限额标志=Y 且未超失效日期，返回限额场景编码
    @Test
    public void testST001T09() {
        stubConfQuery(buildConf("Y", "N"));
        stubCustomQuery(List.of(
                buildCustom("N", date(2026, Calendar.JANUARY, 10), date(2026, Calendar.JUNE, 30)),
                buildCustom("N", date(2026, Calendar.MAY, 20), date(2026, Calendar.NOVEMBER, 30)),
                buildCustom("Y", date(2026, Calendar.SEPTEMBER, 27), date(2026, Calendar.DECEMBER, 31))));

        ST001OutputBO output = st001Pbc.execute(buildInput(date(2026, Calendar.SEPTEMBER, 27)));

        assertSuccess(output);
        assertConfFields(output, "Y", "N");
        assertCustomFields(output, "Y",
                date(2026, Calendar.SEPTEMBER, 27), date(2026, Calendar.DECEMBER, 31));
        assertEcho(output);
    }

    // TC010：仅有生效日期大于交易日期的自定义记录，被过滤后视为自定义限额不存在，仅检查客户自定义标志=N 返回限额场景编码
    @Test
    public void testST001T10() {
        stubConfQuery(buildConf("Y", "N"));
        stubCustomQuery(List.of(buildCustom("Y",
                date(2026, Calendar.OCTOBER, 15), date(2026, Calendar.AUGUST, 31))));

        ST001OutputBO output = st001Pbc.execute(buildInput(date(2026, Calendar.SEPTEMBER, 27)));

        assertSuccess(output);
        assertConfFields(output, "Y", "N");
        assertCustomFieldsNull(output);
        assertEcho(output);
    }
}
