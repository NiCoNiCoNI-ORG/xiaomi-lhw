package com.xiaom.bms.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaom.bms.Service.BatteryWarnService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BatteryWarnControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BatteryWarnService batteryWarnService;

    @InjectMocks
    private BatteryWarnController batteryWarnController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(batteryWarnController).build();
    }

    @Test
    public void testReportWarningsWithInvalidInput() throws Exception {
        WarnReportRequest invalidRequest = new WarnReportRequest();
        invalidRequest.setCarId(null);
        invalidRequest.setWarnId(null);

        mockMvc.perform(post("/api/warn")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Arrays.asList(invalidRequest))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(ResponseResultType.BAD_REQUEST.getCode()));
    }
}
