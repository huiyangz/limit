package com.dcits.limit.step;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.lenient;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dcits.limit.facade.bo.ST007InputBO;
import com.dcits.limit.facade.bo.ST007OutputBO;
import com.dcits.limit.facade.components.IRbLimitRuleRelationBcc;
import com.dcits.limit.facade.components.IRbLimitSceneDefBcc;
import com.dcits.limit.facade.eo.RbLimitRuleRelationEO;
import com.dcits.limit.facade.eo.RbLimitSceneDefEO;

/**
 * ST007 匹配限额场景 单元测试
 */
@ExtendWith(MockitoExtension.class)
class ST007PbcTest {

    @Mock
    private IRbLimitRuleRelationBcc ruleRelationBcc;

    @Mock
    private IRbLimitSceneDefBcc sceneDefBcc;

    @InjectMocks
    private ST007Pbc st007Pbc;

    // 场景：因子命中已配置规则关系，表达式对应单个限额场景编码且该场景启用标志为Y，首个场景即匹配；预期返回已匹配到限额场景
    @Test
    void testST007T01() {
        RbLimitRuleRelationEO relation = new RbLimitRuleRelationEO();
        relation.setRuleId("RULE_BLACK_001");
        relation.setRuleRelationExpr("A&&(B||C)");
        relation.setLimitSceneNo("SCN_DEPOSIT_01");
        lenient().when(ruleRelationBcc.findByPrimaryKey("RULE_BLACK_001")).thenReturn(relation);

        RbLimitRuleRelationEO sceneRelation = new RbLimitRuleRelationEO();
        sceneRelation.setRuleRelationExpr("A&&(B||C)");
        sceneRelation.setLimitSceneNo("SCN_DEPOSIT_01");
        lenient().when(ruleRelationBcc.findByEo(
                        argThat(eo -> eo != null && "A&&(B||C)".equals(eo.getRuleRelationExpr()))))
                .thenReturn(List.of(sceneRelation));

        RbLimitSceneDefEO sceneDef = new RbLimitSceneDefEO();
        sceneDef.setLimitSceneNo("SCN_DEPOSIT_01");
        sceneDef.setValidFlag("Y");
        lenient().when(sceneDefBcc.findByEo(
                        argThat(eo -> eo != null && "SCN_DEPOSIT_01".equals(eo.getLimitSceneNo()) && "Y".equals(eo.getValidFlag()))))
                .thenReturn(List.of(sceneDef));

        ST007InputBO input = new ST007InputBO();
        input.setFactorName("RULE_BLACK_001");

        ST007OutputBO result = st007Pbc.execute(input);

        assertTrue(result.isSucceed());
        assertNull(result.getErrorCode());
        assertNull(result.getErrorMessage());
        assertEquals("已匹配到限额场景", result.getCheckResult());
        assertEquals("SCN_DEPOSIT_01", result.getLimitSceneNo());
        assertEquals("A&&(B||C)", result.getRuleRelationExpr());
        assertEquals("Y", result.getValidFlag());
    }

    // 场景：表达式对应两个限额场景编码，第1个未配置启用数据、第2个启用，遍历继续后命中第2项；预期返回已匹配到限额场景且编码来自遍历第2项
    @Test
    void testST007T02() {
        RbLimitRuleRelationEO relation = new RbLimitRuleRelationEO();
        relation.setRuleId("RULE_BLACK_002");
        relation.setRuleRelationExpr("D&&E");
        relation.setLimitSceneNo("SCN_CLOSED_01");
        lenient().when(ruleRelationBcc.findByPrimaryKey("RULE_BLACK_002")).thenReturn(relation);

        RbLimitRuleRelationEO sceneRelation1 = new RbLimitRuleRelationEO();
        sceneRelation1.setRuleRelationExpr("D&&E");
        sceneRelation1.setLimitSceneNo("SCN_CLOSED_01");
        RbLimitRuleRelationEO sceneRelation2 = new RbLimitRuleRelationEO();
        sceneRelation2.setRuleRelationExpr("D&&E");
        sceneRelation2.setLimitSceneNo("SCN_DEPOSIT_02");
        lenient().when(ruleRelationBcc.findByEo(
                        argThat(eo -> eo != null && "D&&E".equals(eo.getRuleRelationExpr()))))
                .thenReturn(List.of(sceneRelation1, sceneRelation2));

        lenient().when(sceneDefBcc.findByEo(
                        argThat(eo -> eo != null && "SCN_CLOSED_01".equals(eo.getLimitSceneNo()) && "Y".equals(eo.getValidFlag()))))
                .thenReturn(Collections.emptyList());

        RbLimitSceneDefEO sceneDef = new RbLimitSceneDefEO();
        sceneDef.setLimitSceneNo("SCN_DEPOSIT_02");
        sceneDef.setValidFlag("Y");
        lenient().when(sceneDefBcc.findByEo(
                        argThat(eo -> eo != null && "SCN_DEPOSIT_02".equals(eo.getLimitSceneNo()) && "Y".equals(eo.getValidFlag()))))
                .thenReturn(List.of(sceneDef));

        ST007InputBO input = new ST007InputBO();
        input.setFactorName("RULE_BLACK_002");

        ST007OutputBO result = st007Pbc.execute(input);

        assertTrue(result.isSucceed());
        assertNull(result.getErrorCode());
        assertNull(result.getErrorMessage());
        assertEquals("已匹配到限额场景", result.getCheckResult());
        assertEquals("SCN_DEPOSIT_02", result.getLimitSceneNo());
        assertEquals("D&&E", result.getRuleRelationExpr());
        assertEquals("Y", result.getValidFlag());
    }

    // 场景：因子在限额规则关系表无记录，子步骤1直接返回；预期返回未匹配到限额场景，其余输出均为null
    @Test
    void testST007T03() {
        lenient().when(ruleRelationBcc.findByPrimaryKey("RULE_NOT_CONFIGURED")).thenReturn(null);

        ST007InputBO input = new ST007InputBO();
        input.setFactorName("RULE_NOT_CONFIGURED");

        ST007OutputBO result = st007Pbc.execute(input);

        assertTrue(result.isSucceed());
        assertNull(result.getErrorCode());
        assertNull(result.getErrorMessage());
        assertEquals("未匹配到限额场景", result.getCheckResult());
        assertNull(result.getLimitSceneNo());
        assertNull(result.getRuleRelationExpr());
        assertNull(result.getValidFlag());
    }

    // 场景：规则关系记录存在，但表达式对应的限额场景编码列表为空，遍历零次；预期返回未匹配到限额场景，仅输出关系规则表达式
    @Test
    void testST007T04() {
        RbLimitRuleRelationEO relation = new RbLimitRuleRelationEO();
        relation.setRuleId("RULE_BLACK_003");
        relation.setRuleRelationExpr("F");
        relation.setLimitSceneNo("SCN_OFFLINE_01");
        lenient().when(ruleRelationBcc.findByPrimaryKey("RULE_BLACK_003")).thenReturn(relation);

        lenient().when(ruleRelationBcc.findByEo(
                        argThat(eo -> eo != null && "F".equals(eo.getRuleRelationExpr()))))
                .thenReturn(Collections.emptyList());

        ST007InputBO input = new ST007InputBO();
        input.setFactorName("RULE_BLACK_003");

        ST007OutputBO result = st007Pbc.execute(input);

        assertTrue(result.isSucceed());
        assertNull(result.getErrorCode());
        assertNull(result.getErrorMessage());
        assertEquals("未匹配到限额场景", result.getCheckResult());
        assertNull(result.getLimitSceneNo());
        assertEquals("F", result.getRuleRelationExpr());
        assertNull(result.getValidFlag());
    }

    // 场景：表达式对应两个限额场景编码，均未查询到启用标志为Y的配置数据，遍历结束；预期返回未匹配到限额场景，仅输出关系规则表达式
    @Test
    void testST007T05() {
        RbLimitRuleRelationEO relation = new RbLimitRuleRelationEO();
        relation.setRuleId("RULE_BLACK_004");
        relation.setRuleRelationExpr("G||H");
        relation.setLimitSceneNo("SCN_CLOSED_02");
        lenient().when(ruleRelationBcc.findByPrimaryKey("RULE_BLACK_004")).thenReturn(relation);

        RbLimitRuleRelationEO sceneRelation1 = new RbLimitRuleRelationEO();
        sceneRelation1.setRuleRelationExpr("G||H");
        sceneRelation1.setLimitSceneNo("SCN_CLOSED_02");
        RbLimitRuleRelationEO sceneRelation2 = new RbLimitRuleRelationEO();
        sceneRelation2.setRuleRelationExpr("G||H");
        sceneRelation2.setLimitSceneNo("SCN_CLOSED_03");
        lenient().when(ruleRelationBcc.findByEo(
                        argThat(eo -> eo != null && "G||H".equals(eo.getRuleRelationExpr()))))
                .thenReturn(List.of(sceneRelation1, sceneRelation2));

        lenient().when(sceneDefBcc.findByEo(
                        argThat(eo -> eo != null && "SCN_CLOSED_02".equals(eo.getLimitSceneNo()) && "Y".equals(eo.getValidFlag()))))
                .thenReturn(Collections.emptyList());
        lenient().when(sceneDefBcc.findByEo(
                        argThat(eo -> eo != null && "SCN_CLOSED_03".equals(eo.getLimitSceneNo()) && "Y".equals(eo.getValidFlag()))))
                .thenReturn(Collections.emptyList());

        ST007InputBO input = new ST007InputBO();
        input.setFactorName("RULE_BLACK_004");

        ST007OutputBO result = st007Pbc.execute(input);

        assertTrue(result.isSucceed());
        assertNull(result.getErrorCode());
        assertNull(result.getErrorMessage());
        assertEquals("未匹配到限额场景", result.getCheckResult());
        assertNull(result.getLimitSceneNo());
        assertEquals("G||H", result.getRuleRelationExpr());
        assertNull(result.getValidFlag());
    }
}
