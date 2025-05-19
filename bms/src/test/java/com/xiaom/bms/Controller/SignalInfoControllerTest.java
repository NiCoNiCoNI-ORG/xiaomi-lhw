package com.xiaom.bms.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaom.bms.Service.SignalInfoService;
import com.xiaom.bms.common.ResponseResult;
import com.xiaom.bms.common.ResponseResultType;
import com.xiaom.bms.model.SignalInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SignalInfoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SignalInfoService signalInfoService;

    @InjectMocks
    private SignalInfoController signalInfoController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(signalInfoController).build();
    }

    @Test
    public void testCreateSignalInfo() throws Exception {
        SignalInfo signalInfo = new SignalInfo();
        signalInfo.setCarId(1L);
        signalInfo.setBatteryCapacity(new BigDecimal("12.5"));
        signalInfo.setMx(new BigDecimal("13.0"));
        signalInfo.setMi(new BigDecimal("12.0"));
        signalInfo.setIx(new BigDecimal("5.0"));
        signalInfo.setIi(new BigDecimal("4.5"));
        signalInfo.setUploadTime(new Date());

        when(signalInfoService.createSignalInfo(any(SignalInfo.class))).thenReturn(1);

        mockMvc.perform(post("/api/signal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signalInfo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResponseResultType.SUCCESS.getCode()))
                .andExpect(jsonPath("$.data").value(1));
    }

    @Test
    public void testUpdateSignalInfo() throws Exception {
        Long id = 1L;
        SignalInfo signalInfo = new SignalInfo();
        signalInfo.setId(id);
        signalInfo.setCarId(1L);
        signalInfo.setBatteryCapacity(new BigDecimal("12.5"));
        signalInfo.setMx(new BigDecimal("13.0"));
        signalInfo.setMi(new BigDecimal("12.0"));
        signalInfo.setIx(new BigDecimal("5.0"));
        signalInfo.setIi(new BigDecimal("4.5"));
        signalInfo.setUploadTime(new Date());

        when(signalInfoService.updateSignalInfo(any(SignalInfo.class))).thenReturn(1);

        mockMvc.perform(put("/api/signal/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signalInfo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResponseResultType.SUCCESS.getCode()))
                .andExpect(jsonPath("$.data").value(1));
    }

    @Test
    public void testDeleteSignalInfo() throws Exception {
        Long id = 1L;

        when(signalInfoService.deleteSignalInfo(id)).thenReturn(1);

        mockMvc.perform(delete("/api/signal/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResponseResultType.SUCCESS.getCode()))
                .andExpect(jsonPath("$.data").value(1));
    }

//    @Test
//    public void testGetSignalInfoById() throws Exception {
//        Long id = 1L;
//        SignalInfo signalInfo = new SignalInfo();
//        signalInfo.setId(id);
//        signalInfo.setCarId(1L);
//        signalInfo.setBatteryCapacity(new BigDecimal("12.5"));
//        signalInfo.setMx(new BigDecimal("13.0"));
//        signalInfo.setMi(new BigDecimal("12.0"));
//        signalInfo.setIx(new BigDecimal("5.0"));
//        signalInfo.setIi(new BigDecimal("4.5"));
//        signalInfo.setUploadTime(new Date());
//
//        when(signalInfoService.getSignalInfoById(id)).thenReturn(signalInfo);
//
//        MvcResult result = mockMvc.perform(get("/api/signal/{id}", id))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String content = result.getResponse().getContentAsString();
//        System.out.println(content); // 打印响应内容
//
//        mockMvc.perform(get("/api/signal/{id}", id))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value(ResponseResultType.SUCCESS.getCode()))
//                .andExpect(jsonPath("$.message").value("Success"))
//                .andExpect(jsonPath("$.data.id").value(id.intValue())) // 注意：Long to Integer conversion
//                .andExpect(jsonPath("$.data.carId").value(1))
//                .andExpect(jsonPath("$.data.batteryCapacity").value(12.5))
//                .andExpect(jsonPath("$.data.Mx").value(13.0)) // 使用大写字母
//                .andExpect(jsonPath("$.data.Mi").value(12.0)) // 使用大写字母
//                .andExpect(jsonPath("$.data.Ix").value(5.0)) // 使用大写字母
//                .andExpect(jsonPath("$.data.Ii").value(4.5)); // 使用大写字母
//    }

    @Test
    public void testGetAllSignalInfos() throws Exception {
        SignalInfo info1 = new SignalInfo();
        info1.setId(1L);
        info1.setCarId(1L);
        info1.setBatteryCapacity(new BigDecimal("12.5"));
        info1.setMx(new BigDecimal("13.0"));
        info1.setMi(new BigDecimal("12.0"));
        info1.setIx(new BigDecimal("5.0"));
        info1.setIi(new BigDecimal("4.5"));
        info1.setUploadTime(new Date());

        SignalInfo info2 = new SignalInfo();
        info2.setId(2L);
        info2.setCarId(2L);
        info2.setBatteryCapacity(new BigDecimal("13.5"));
        info2.setMx(new BigDecimal("14.0"));
        info2.setMi(new BigDecimal("13.0"));
        info2.setIx(new BigDecimal("6.0"));
        info2.setIi(new BigDecimal("5.5"));
        info2.setUploadTime(new Date());

        List<SignalInfo> infos = Arrays.asList(info1, info2);

        when(signalInfoService.getAllSignalInfos()).thenReturn(infos);

        mockMvc.perform(get("/api/signal"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResponseResultType.SUCCESS.getCode()))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].carId").value(1))
                .andExpect(jsonPath("$.data[1].id").value(2))
                .andExpect(jsonPath("$.data[1].carId").value(2));
    }
}



