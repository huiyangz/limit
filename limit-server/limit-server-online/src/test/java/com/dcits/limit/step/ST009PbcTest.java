package com.dcits.limit.step;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dcits.limit.facade.bo.ST009InputBO;
import com.dcits.limit.facade.bo.ST009OutputBO;
import com.dcits.limit.facade.components.IRbLimitCtrlConfBcc;
import com.dcits.limit.facade.eo.RbLimitCtrlConfEO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * ST009 检查限额场景配置是否有效 单元测试
 */
@ExtendWith(MockitoExtension.class)
class ST009PbcTest {

    @Mock
    private IRbLimitCtrlConfBcc rbLimitCtrlConfBcc;

    @InjectMocks
    private ST009Pbc st009Pbc;

    /** 构造桩记录统一值：limitSceneNo=S001，控制日期[2026-01-01,2026-12-31]，控制时间[08:30:00,17:30:00] */
    private RbLimitCtrlConfEO buildStubConf() throws ParseException {
        RbLimitCtrlConfEO eo = new RbLimitCtrlConfEO();
        eo.setLimitSceneNo("S001");
        eo.setLimitCtrlBgnDate(new SimpleDateFormat("yyyyMMdd").parse("20260101"));
        eo.setLimitCtrlEndDate(new SimpleDateFormat("yyyyMMdd").parse("20261231"));
        eo.setLimitCtrlBgnTime(new SimpleDateFormat("HH:mm:ss").parse("08:30:00"));
        eo.setLimitCtrlEndTime(new SimpleDateFormat("HH:mm:ss").parse("17:30:00"));
        return eo;
    }

    /** 构造输入：交易日期按yyyyMMdd解析，机构编码351155、场景编码S001 */
    private ST009InputBO buildInput(String tranDateText, String tranTimestamp) throws ParseException {
        ST009InputBO input = new ST009InputBO();
        input.setTranDate(new SimpleDateFormat("yyyyMMdd").parse(tranDateText));
        input.setTranTimestamp(tranTimestamp);
        input.setLimitBranchId("351155");
        input.setLimitSceneNo("S001");
        return input;
    }

    // 场景：查到配置，交易日期2026-06-15在[2026-01-01,2026-12-31]内且交易时间09:30:00在[08:30:00,17:30:00]内，检查通过，返回场景编码及四个控制区间字段
    @Test
    void testST009T01() throws ParseException {
        Mockito.lenient().when(rbLimitCtrlConfBcc.findByPrimaryKey("351155", "S001"))
                .thenReturn(buildStubConf());

        ST009OutputBO output = st009Pbc.execute(buildInput("20260615", "093000"));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertEquals("S001", output.getLimitSceneNo());
        assertEquals(new SimpleDateFormat("yyyyMMdd").parse("20260101"), output.getLimitCtrlBgnDate());
        assertEquals(new SimpleDateFormat("yyyyMMdd").parse("20261231"), output.getLimitCtrlEndDate());
        assertEquals(new SimpleDateFormat("HH:mm:ss").parse("08:30:00"), output.getLimitCtrlBgnTime());
        assertEquals(new SimpleDateFormat("HH:mm:ss").parse("17:30:00"), output.getLimitCtrlEndTime());
    }

    // 场景：边界（下界），交易日期等于开始日期2026-01-01且交易时间083000等于开始时间08:30:00，闭区间视为范围内，检查通过
    @Test
    void testST009T02() throws ParseException {
        Mockito.lenient().when(rbLimitCtrlConfBcc.findByPrimaryKey("351155", "S001"))
                .thenReturn(buildStubConf());

        ST009OutputBO output = st009Pbc.execute(buildInput("20260101", "083000"));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertEquals("S001", output.getLimitSceneNo());
        assertEquals(new SimpleDateFormat("yyyyMMdd").parse("20260101"), output.getLimitCtrlBgnDate());
        assertEquals(new SimpleDateFormat("yyyyMMdd").parse("20261231"), output.getLimitCtrlEndDate());
        assertEquals(new SimpleDateFormat("HH:mm:ss").parse("08:30:00"), output.getLimitCtrlBgnTime());
        assertEquals(new SimpleDateFormat("HH:mm:ss").parse("17:30:00"), output.getLimitCtrlEndTime());
    }

    // 场景：边界（上界），交易日期等于结束日期2026-12-31且交易时间173000等于结束时间17:30:00，闭区间视为范围内，检查通过
    @Test
    void testST009T03() throws ParseException {
        Mockito.lenient().when(rbLimitCtrlConfBcc.findByPrimaryKey("351155", "S001"))
                .thenReturn(buildStubConf());

        ST009OutputBO output = st009Pbc.execute(buildInput("20261231", "173000"));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertEquals("S001", output.getLimitSceneNo());
        assertEquals(new SimpleDateFormat("yyyyMMdd").parse("20260101"), output.getLimitCtrlBgnDate());
        assertEquals(new SimpleDateFormat("yyyyMMdd").parse("20261231"), output.getLimitCtrlEndDate());
        assertEquals(new SimpleDateFormat("HH:mm:ss").parse("08:30:00"), output.getLimitCtrlBgnTime());
        assertEquals(new SimpleDateFormat("HH:mm:ss").parse("17:30:00"), output.getLimitCtrlEndTime());
    }

    // 场景：查无配置，按主键未查询到限额控制配置记录，按检查不通过处理，五个输出字段全部为空，步骤仍成功返回
    @Test
    void testST009T04() throws ParseException {
        Mockito.lenient().when(rbLimitCtrlConfBcc.findByPrimaryKey("351155", "S001"))
                .thenReturn(null);

        ST009OutputBO output = st009Pbc.execute(buildInput("20260615", "093000"));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertNull(output.getLimitSceneNo());
        assertNull(output.getLimitCtrlBgnDate());
        assertNull(output.getLimitCtrlEndDate());
        assertNull(output.getLimitCtrlBgnTime());
        assertNull(output.getLimitCtrlEndTime());
    }

    // 场景：交易日期2025-12-31早于开始日期2026-01-01（交易时间在范围内），日期条件不成立，检查不通过，五个输出字段全部为空
    @Test
    void testST009T05() throws ParseException {
        Mockito.lenient().when(rbLimitCtrlConfBcc.findByPrimaryKey("351155", "S001"))
                .thenReturn(buildStubConf());

        ST009OutputBO output = st009Pbc.execute(buildInput("20251231", "093000"));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertNull(output.getLimitSceneNo());
        assertNull(output.getLimitCtrlBgnDate());
        assertNull(output.getLimitCtrlEndDate());
        assertNull(output.getLimitCtrlBgnTime());
        assertNull(output.getLimitCtrlEndTime());
    }

    // 场景：交易日期2027-01-01晚于结束日期2026-12-31（交易时间在范围内），日期条件不成立，检查不通过，五个输出字段全部为空
    @Test
    void testST009T06() throws ParseException {
        Mockito.lenient().when(rbLimitCtrlConfBcc.findByPrimaryKey("351155", "S001"))
                .thenReturn(buildStubConf());

        ST009OutputBO output = st009Pbc.execute(buildInput("20270101", "093000"));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertNull(output.getLimitSceneNo());
        assertNull(output.getLimitCtrlBgnDate());
        assertNull(output.getLimitCtrlEndDate());
        assertNull(output.getLimitCtrlBgnTime());
        assertNull(output.getLimitCtrlEndTime());
    }

    // 场景：交易日期在范围内但交易时间082959早于开始时间08:30:00，时间条件不成立，检查不通过，五个输出字段全部为空
    @Test
    void testST009T07() throws ParseException {
        Mockito.lenient().when(rbLimitCtrlConfBcc.findByPrimaryKey("351155", "S001"))
                .thenReturn(buildStubConf());

        ST009OutputBO output = st009Pbc.execute(buildInput("20260615", "082959"));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertNull(output.getLimitSceneNo());
        assertNull(output.getLimitCtrlBgnDate());
        assertNull(output.getLimitCtrlEndDate());
        assertNull(output.getLimitCtrlBgnTime());
        assertNull(output.getLimitCtrlEndTime());
    }

    // 场景：交易日期在范围内但交易时间173001晚于结束时间17:30:00，时间条件不成立，检查不通过，五个输出字段全部为空
    @Test
    void testST009T08() throws ParseException {
        Mockito.lenient().when(rbLimitCtrlConfBcc.findByPrimaryKey("351155", "S001"))
                .thenReturn(buildStubConf());

        ST009OutputBO output = st009Pbc.execute(buildInput("20260615", "173001"));

        assertTrue(output.isSucceed());
        assertNull(output.getErrorCode());
        assertNull(output.getErrorMessage());
        assertNull(output.getLimitSceneNo());
        assertNull(output.getLimitCtrlBgnDate());
        assertNull(output.getLimitCtrlEndDate());
        assertNull(output.getLimitCtrlBgnTime());
        assertNull(output.getLimitCtrlEndTime());
    }
}
