package com.xiaom.bms.Service;

import com.xiaom.bms.model.BatteryRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BatteryRuleServiceTest {

    private MockMvc mockMvc;

    @Mock
    private BatteryRuleService batteryRuleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(batteryRuleService).build();
    }

    @Test
    public void testCreateRule() {
        BatteryRule batteryRule = new BatteryRule();
        batteryRule.setId(1L);
        batteryRule.setRuleName("Overcharge Rule");

        when(batteryRuleService.createRule(any(BatteryRule.class))).thenReturn(1);

        int result = batteryRuleService.createRule(batteryRule);
        assertEquals(1, result);
        verify(batteryRuleService, times(1)).createRule(batteryRule);
    }

    @Test
    public void testUpdateRule() {
        BatteryRule batteryRule = new BatteryRule();
        batteryRule.setId(1L);
        batteryRule.setRuleName("Updated Overcharge Rule");

        when(batteryRuleService.updateRule(any(BatteryRule.class))).thenReturn(1);

        int result = batteryRuleService.updateRule(batteryRule);
        assertEquals(1, result);
        verify(batteryRuleService, times(1)).updateRule(batteryRule);
    }

    @Test
    public void testDeleteRule() {
        Long id = 1L;

        when(batteryRuleService.deleteRule(id)).thenReturn(1);

        int result = batteryRuleService.deleteRule(id);
        assertEquals(1, result);
        verify(batteryRuleService, times(1)).deleteRule(id);
    }

    @Test
    public void testGetRuleById() {
        Long id = 1L;
        BatteryRule batteryRule = new BatteryRule();
        batteryRule.setId(id);
        batteryRule.setRuleName("Overcharge Rule");

        when(batteryRuleService.getRuleById(id)).thenReturn(batteryRule);

        BatteryRule result = batteryRuleService.getRuleById(id);
        assertEquals(batteryRule, result);
        verify(batteryRuleService, times(1)).getRuleById(id);
    }

    @Test
    public void testGetAllRules() {
        BatteryRule rule1 = new BatteryRule();
        rule1.setId(1L);
        rule1.setRuleName("Overcharge Rule");

        BatteryRule rule2 = new BatteryRule();
        rule2.setId(2L);
        rule2.setRuleName("Discharge Rule");

        List<BatteryRule> rules = new ArrayList<>();
        rules.add(rule1);
        rules.add(rule2);

        when(batteryRuleService.getAllRules()).thenReturn(rules);

        List<BatteryRule> result = batteryRuleService.getAllRules();
        assertEquals(rules, result);
        verify(batteryRuleService, times(1)).getAllRules();
    }
}



