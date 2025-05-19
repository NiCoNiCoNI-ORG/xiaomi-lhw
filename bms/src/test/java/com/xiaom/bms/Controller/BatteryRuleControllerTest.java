package com.xiaom.bms.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaom.bms.Exception.GlobalException;
import com.xiaom.bms.Service.BatteryRuleService;
import com.xiaom.bms.common.ResponseResult;
import com.xiaom.bms.common.ResponseResultType;
import com.xiaom.bms.model.BatteryRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BatteryRuleControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Mock
//    private BatteryRuleService batteryRuleService;
//
//    @InjectMocks
//    private BatteryRuleController batteryRuleController;
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(batteryRuleController).build();
//    }
//
//    @Test
//    public void testCreateRule() throws Exception {
//        // 正常创建测试
//        BatteryRule batteryRule = new BatteryRule();
//        batteryRule.setRid(1L);
//        batteryRule.setRuleName("Overcharge Alert");
//        batteryRule.setBatteryType("Li-ion");
//        batteryRule.setMinValue(new BigDecimal("-50.00"));
//        batteryRule.setMaxValue(new BigDecimal("50.00"));
//        batteryRule.setAlertLevel((byte) 2);
//
//        when(batteryRuleService.createRule(any(BatteryRule.class))).thenReturn(1);
//
//        mockMvc.perform(post("/api/rule")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(batteryRule)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value(ResponseResultType.SUCCESS.getCode()))
//                .andExpect(jsonPath("$.data").value(1));
//    }
//
//    @Test
//    public void testCreateRuleWithInvalidMinMax() throws Exception {
//        // 最小最大值无效测试
//        BatteryRule batteryRule = new BatteryRule();
//        batteryRule.setRid(1L);
//        batteryRule.setRuleName("Invalid Rule");
//        batteryRule.setBatteryType("Li-ion");
//        batteryRule.setMinValue(new BigDecimal("100.00")); // min > max
//        batteryRule.setMaxValue(new BigDecimal("50.00"));
//        batteryRule.setAlertLevel((byte) 2);
//
//        mockMvc.perform(post("/api/rule")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(batteryRule)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void testCreateRuleWithEmptyName() throws Exception {
//        // 空规则名称测试
//        BatteryRule batteryRule = new BatteryRule();
//        batteryRule.setRid(1L);
//        batteryRule.setRuleName("");
//        batteryRule.setBatteryType("Li-ion");
//        batteryRule.setMinValue(new BigDecimal("-50.00"));
//        batteryRule.setMaxValue(new BigDecimal("50.00"));
//        batteryRule.setAlertLevel((byte) 2);
//
//        mockMvc.perform(post("/api/rule")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(batteryRule)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void testCreateRuleWithNullBatteryType() throws Exception {
//        // 电池类型为空测试
//        BatteryRule batteryRule = new BatteryRule();
//        batteryRule.setRid(1L);
//        batteryRule.setRuleName("Null Battery Type");
//        batteryRule.setBatteryType(null);
//        batteryRule.setMinValue(new BigDecimal("-50.00"));
//        batteryRule.setMaxValue(new BigDecimal("50.00"));
//        batteryRule.setAlertLevel((byte) 2);
//
//        mockMvc.perform(post("/api/rule")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(batteryRule)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void testCreateRuleWithInvalidAlertLevel() throws Exception {
//        // 无效告警级别测试
//        BatteryRule batteryRule = new BatteryRule();
//        batteryRule.setRid(1L);
//        batteryRule.setRuleName("Invalid Alert Level");
//        batteryRule.setBatteryType("Li-ion");
//        batteryRule.setMinValue(new BigDecimal("-50.00"));
//        batteryRule.setMaxValue(new BigDecimal("50.00"));
//        batteryRule.setAlertLevel((byte) 10); // 超出范围
//
//        mockMvc.perform(post("/api/rule")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(batteryRule)))
//                .andExpect(status().isBadRequest());
//    }
//        BatteryRule batteryRule = new BatteryRule();
//
//        batteryRule.setRid(1L);
//        batteryRule.setRuleName("Overcharge Alert");
//        batteryRule.setBatteryType("Li-ion");
//        batteryRule.setMinValue(new BigDecimal("-50.00"));
//        batteryRule.setMaxValue(new BigDecimal("50.00"));
//        batteryRule.setAlertLevel((byte) 2);
//
//        when(batteryRuleService.createRule(any(BatteryRule.class))).thenReturn(1);
//
//        mockMvc.perform(post("/api/rule")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(batteryRule)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value(ResponseResultType.SUCCESS.getCode()))
//                .andExpect(jsonPath("$.data").value(1));
//    }
//
//    @Test
//    void createRule_ValidationError() {
//        BatteryRule rule = new BatteryRule();
//
//        when(batteryRuleService.createRule(any(BatteryRule.class)))
//                .thenThrow(new IllegalArgumentException("Invalid rule"));
//
//        ResponseResult<Void> result = batteryRuleController.createRule(rule);
//
//        assertNotNull(result);
//        assertEquals(ResponseResultType.BAD_REQUEST, result.getType());
//        assertEquals("Invalid rule", result.getMessage());
//    }
//
//    @Test
//    void createRule_GlobalException() {
//        BatteryRule rule = new BatteryRule();
//        rule.setRid(1L);
//
//        when(batteryRuleService.createRule(any(BatteryRule.class)))
//                .thenThrow(new GlobalException(ResponseResultType.BAD_REQUEST, "Rule validation failed"));
//
//        ResponseResult<Void> result = batteryRuleController.createRule(rule);
//
//        assertNotNull(result);
//        assertEquals(ResponseResultType.BAD_REQUEST, result.getType());
//        assertEquals("Rule validation failed", result.getMessage());
//    }
//
//    @Test
//    void createRule_InternalError() {
//        BatteryRule rule = new BatteryRule();
//        rule.setRid(1L);
//
//        when(batteryRuleService.createRule(any(BatteryRule.class)))
//                .thenThrow(new RuntimeException("Unexpected error"));
//
//        ResponseResult<Void> result = batteryRuleController.createRule(rule);
//
//        assertNotNull(result);
//        assertEquals(ResponseResultType.INTERNAL_SERVER_ERROR, result.getType());
//        assertEquals("Unexpected error", result.getMessage());
//    }
//
//    @Test
//    void updateRule_Success() {
//        BatteryRule rule = new BatteryRule();
//        rule.setId(1L);
//        rule.setRid(1L);
//        rule.setRuleName("Updated Rule");
//        rule.setBatteryType("铁锂电池");
//        rule.setMinValue(new BigDecimal("20"));
//        rule.setMaxValue(new BigDecimal("200"));
//        rule.setAlertLevel(2);
//
//        when(batteryRuleService.updateRule(any(BatteryRule.class))).thenReturn(1);
//
//        ResponseResult<Void> result = batteryRuleController.updateRule(1L, rule);
//
//        assertNotNull(result);
//        assertEquals(ResponseResultType.SUCCESS, result.getType());
//        verify(batteryRuleService, times(1)).updateRule(any(BatteryRule.class));
//    }
//
//    @Test
//    void updateRule_GlobalException() {
//        BatteryRule rule = new BatteryRule();
//        rule.setId(1L);
//
//        when(batteryRuleService.updateRule(any(BatteryRule.class)))
//                .thenThrow(new GlobalException(ResponseResultType.BAD_REQUEST, "Invalid rule data"));
//
//        ResponseResult<Void> result = batteryRuleController.updateRule(1L, rule);
//
//        assertNotNull(result);
//        assertEquals(ResponseResultType.BAD_REQUEST, result.getType());
//        assertEquals("Invalid rule data", result.getMessage());
//    }
//
//    @Test
//    void updateRule_InternalError() {
//        BatteryRule rule = new BatteryRule();
//        rule.setId(1L);
//
//        when(batteryRuleService.updateRule(any(BatteryRule.class)))
//                .thenThrow(new RuntimeException("Database error"));
//
//        ResponseResult<Void> result = batteryRuleController.updateRule(1L, rule);
//
//        assertNotNull(result);
//        assertEquals(ResponseResultType.INTERNAL_SERVER_ERROR, result.getType());
//        assertEquals("Database error", result.getMessage());
//    }
//
//    @Test
//    public void testDeleteRule() throws Exception {
//        Long id = 1L;
//
//        when(batteryRuleService.deleteRule(id)).thenReturn(1);
//
//        mockMvc.perform(delete("/api/rule/{id}", id))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value(ResponseResultType.SUCCESS.getCode()))
//                .andExpect(jsonPath("$.data").value(1));
//    }
//
//    @Test
//    public void testGetRuleById() throws Exception {
//        Long id = 1L;
//        BatteryRule batteryRule = new BatteryRule();
//        batteryRule.setId(id);
//        batteryRule.setRid(1L);
//        batteryRule.setRuleName("Overcharge Alert");
//        batteryRule.setBatteryType("Li-ion");
//        batteryRule.setMinValue(new BigDecimal("-50.00"));
//        batteryRule.setMaxValue(new BigDecimal("50.00"));
//        batteryRule.setAlertLevel((byte) 2);
//
//        when(batteryRuleService.getRuleById(id)).thenReturn(batteryRule);
//
//        mockMvc.perform(get("/api/rule/{id}", id))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value(ResponseResultType.SUCCESS.getCode()))
//                .andExpect(jsonPath("$.data.id").value(id))
//                .andExpect(jsonPath("$.data.ruleName").value("Overcharge Alert"))
//                .andExpect(jsonPath("$.data.batteryType").value("Li-ion"))
//                .andExpect(jsonPath("$.data.minValue").value(-50.00))
//                .andExpect(jsonPath("$.data.maxValue").value(50.00))
//                .andExpect(jsonPath("$.data.alertLevel").value(2));
//    }
//
//    @Test
//    public void testGetAllRules() throws Exception {
//        BatteryRule rule1 = new BatteryRule();
//        rule1.setId(1L);
//        rule1.setRid(1L);
//        rule1.setRuleName("Overcharge Alert");
//        rule1.setBatteryType("Li-ion");
//        rule1.setMinValue(new BigDecimal("-50.00"));
//        rule1.setMaxValue(new BigDecimal("50.00"));
//        rule1.setAlertLevel((byte) 2);
//
//        BatteryRule rule2 = new BatteryRule();
//        rule2.setId(2L);
//        rule2.setRid(2L);
//        rule2.setRuleName("Discharge Alert");
//        rule2.setBatteryType("NiMH");
//        rule2.setMinValue(new BigDecimal("-60.00"));
//        rule2.setMaxValue(new BigDecimal("60.00"));
//        rule2.setAlertLevel((byte) 3);
//
//        List<BatteryRule> rules = Arrays.asList(rule1, rule2);
//
//        when(batteryRuleService.getAllRules()).thenReturn(rules);
//
//        mockMvc.perform(get("/api/rule"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value(ResponseResultType.SUCCESS.getCode()))
//                .andExpect(jsonPath("$.data", hasSize(2)))
//                .andExpect(jsonPath("$.data[0].id").value(1))
//                .andExpect(jsonPath("$.data[0].ruleName").value("Overcharge Alert"))
//                .andExpect(jsonPath("$.data[1].id").value(2))
//                .andExpect(jsonPath("$.data[1].ruleName").value("Discharge Alert"));
//    }
}



