package com.xiaom.bms.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaom.bms.Controller.BatteryWarnController;
import com.xiaom.bms.common.ResponseResultType;
import com.xiaom.bms.model.BatteryWarn;
import com.xiaom.bms.model.WarnReportRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BatteryWarnServiceTest {

//    private MockMvc mockMvc;
//
//    @Mock
//    private BatteryWarnService batteryWarnService;
//
//    @InjectMocks
//    private BatteryWarnController batteryWarnController;
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(batteryWarnController).build();
//    }
//
//    @Test
//    public void testCreateWarns() throws Exception {
//        BatteryWarn warn1 = new BatteryWarn();
//        warn1.setWid(1L);
//        warn1.setRuleNum(1L);
//        warn1.setRuleName("Overcharge Alert");
//        warn1.setAlertLevel((byte) 2);
//
//        BatteryWarn warn2 = new BatteryWarn();
//        warn2.setWid(2L);
//        warn2.setRuleNum(2L);
//        warn2.setRuleName("Discharge Alert");
//        warn2.setAlertLevel((byte) 3);
//
//        List<BatteryWarn> batteryWarns = Arrays.asList(warn1, warn2);
//
//        when(batteryWarnService.createWarns(any(List.class))).thenReturn(Arrays.asList(1, 2));
//
//        mockMvc.perform(post("/api/warn")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(batteryWarns)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value(ResponseResultType.SUCCESS.getCode()))
//                .andExpect(jsonPath("$.data", hasSize(2)))
//                .andExpect(jsonPath("$.data[0]").value(1))
//                .andExpect(jsonPath("$.data[1]").value(2));
//    }
//
//    @Test
//    public void testCreateWarn() {
//        BatteryWarn batteryWarn = new BatteryWarn();
//        batteryWarn.setWid(1L);
//        batteryWarn.setRuleNum(1L);
//        batteryWarn.setRuleName("Overcharge Alert");
//        batteryWarn.setAlertLevel((byte) 2);
//
//        when(batteryWarnService.createWarn(any(BatteryWarn.class))).thenReturn(1);
//
//        int result = batteryWarnService.createWarn(batteryWarn);
//        assertEquals(1, result);
//        verify(batteryWarnService, times(1)).createWarn(batteryWarn);
//    }
//
//    @Test
//    public void testUpdateWarn() {
//        BatteryWarn batteryWarn = new BatteryWarn();
//        batteryWarn.setId(1L);
//        batteryWarn.setWid(1L);
//        batteryWarn.setRuleNum(1L);
//        batteryWarn.setRuleName("Overcharge Alert Updated");
//        batteryWarn.setAlertLevel((byte) 3);
//
//        when(batteryWarnService.updateWarn(any(BatteryWarn.class))).thenReturn(1);
//
//        int result = batteryWarnService.updateWarn(batteryWarn);
//        assertEquals(1, result);
//        verify(batteryWarnService, times(1)).updateWarn(batteryWarn);
//    }
//
//    @Test
//    public void testDeleteWarn() {
//        Long id = 1L;
//
//        when(batteryWarnService.deleteWarn(id)).thenReturn(1);
//
//        int result = batteryWarnService.deleteWarn(id);
//        assertEquals(1, result);
//        verify(batteryWarnService, times(1)).deleteWarn(id);
//    }
//
//    @Test
//    public void testGetWarnById() {
//        Long id = 1L;
//        BatteryWarn batteryWarn = new BatteryWarn();
//        batteryWarn.setId(id);
//        batteryWarn.setWid(1L);
//        batteryWarn.setRuleNum(1L);
//        batteryWarn.setRuleName("Overcharge Alert");
//        batteryWarn.setAlertLevel((byte) 2);
//
//        when(batteryWarnService.getWarnById(id)).thenReturn(batteryWarn);
//
//        BatteryWarn result = batteryWarnService.getWarnById(id);
//        assertEquals(batteryWarn, result);
//        verify(batteryWarnService, times(1)).getWarnById(id);
//    }
//
//    @Test
//    public void testGetAllWarns() {
//        BatteryWarn warn1 = new BatteryWarn();
//        warn1.setId(1L);
//        warn1.setWid(1L);
//        warn1.setRuleNum(1L);
//        warn1.setRuleName("Overcharge Alert");
//        warn1.setAlertLevel((byte) 2);
//
//        BatteryWarn warn2 = new BatteryWarn();
//        warn2.setId(2L);
//        warn2.setWid(2L);
//        warn2.setRuleNum(2L);
//        warn2.setRuleName("Discharge Alert");
//        warn2.setAlertLevel((byte) 3);
//
//        List<BatteryWarn> warns = Arrays.asList(warn1, warn2);
//
//        when(batteryWarnService.getAllWarns()).thenReturn(warns);
//
//        List<BatteryWarn> result = batteryWarnService.getAllWarns();
//        assertEquals(warns, result);
//        verify(batteryWarnService, times(1)).getAllWarns();
//    }
//
//    @Test
//    public void testReportWarnings() throws Exception {
//        WarnReportRequest request1 = new WarnReportRequest();
//        request1.setCarId(1L);
//        request1.setWarnId(1L);
//        Map<String, Double> signal1 = new HashMap<>();
//        signal1.put("signal1", 1.0);
//        request1.setSignal(signal1);
//
//        WarnReportRequest request2 = new WarnReportRequest();
//        request2.setCarId(2L);
//        request2.setWarnId(2L);
//        Map<String, Double> signal2 = new HashMap<>();
//        signal2.put("signal2", 2.0);
//        request2.setSignal(signal2);
//
//        List<WarnReportRequest> requests = Arrays.asList(request1, request2);
//
//        Map<String, Object> warning1 = new HashMap<>();
//        warning1.put("id", 1L);
//        warning1.put("message", "Overcharge detected");
//
//        Map<String, Object> warning2 = new HashMap<>();
//        warning2.put("id", 2L);
//        warning2.put("message", "Discharge detected");
//
//        when(batteryWarnService.reportWarnings(any(List.class))).thenReturn(Arrays.asList(warning1, warning2));
//
//        mockMvc.perform(post("/api/warn/report")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(requests)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value(ResponseResultType.SUCCESS.getCode()))
//                .andExpect(jsonPath("$.data", hasSize(2)))
//                .andExpect(jsonPath("$.data[0].id").value(1))
//                .andExpect(jsonPath("$.data[0].message").value("Overcharge detected"))
//                .andExpect(jsonPath("$.data[1].id").value(2))
//                .andExpect(jsonPath("$.data[1].message").value("Discharge detected"));
//    }
}



