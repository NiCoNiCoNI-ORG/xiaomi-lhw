package com.xiaom.bms.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaom.bms.Service.VehicleBatteryInfoService;
import com.xiaom.bms.common.ResponseResultType;
import com.xiaom.bms.model.VehicleBatteryInfo;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class VehicleBatteryInfoControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Mock
//    private VehicleBatteryInfoService vehicleBatteryInfoService;
//
//    @InjectMocks
//    private VehicleBatteryInfoController vehicleBatteryInfoController;
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(vehicleBatteryInfoController).build();
//    }
//
//    @Test
//    public void testCreateVehicleBatteryInfo() throws Exception {
//        VehicleBatteryInfo info = new VehicleBatteryInfo();
//        info.setCarId(1L);
//        info.setBatteryCapacity(new BigDecimal("50.0"));
//        info.setVoltage(new BigDecimal("12.5"));
//        info.setCurrent(new BigDecimal("2.5"));
//        info.setTemperature(new BigDecimal("25.0"));
//
//        when(vehicleBatteryInfoService.createVehicleBatteryInfo(any(VehicleBatteryInfo.class))).thenReturn(1);
//
//        mockMvc.perform(post("/api/vehicle-battery-info")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(info)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value(ResponseResultType.SUCCESS.getCode()))
//                .andExpect(jsonPath("$.data").value(1));
//    }
//
//    @Test
//    public void testCreateVehicleBatteryInfoWithInvalidInput() throws Exception {
//        VehicleBatteryInfo invalidInfo = new VehicleBatteryInfo();
//        invalidInfo.setCarId(null);
//        invalidInfo.setBatteryCapacity(null);
//
//        mockMvc.perform(post("/api/vehicle-battery-info")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(invalidInfo)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void testUpdateVehicleBatteryInfo() throws Exception {
//        Long id = 1L;
//        VehicleBatteryInfo info = new VehicleBatteryInfo();
//        info.setId(id);
//        info.setCarId(1L);
//        info.setBatteryCapacity(new BigDecimal("50.0"));
//        info.setVoltage(new BigDecimal("12.5"));
//        info.setCurrent(new BigDecimal("2.5"));
//        info.setTemperature(new BigDecimal("25.0"));
//
//        when(vehicleBatteryInfoService.updateVehicleBatteryInfo(any(VehicleBatteryInfo.class))).thenReturn(1);
//
//        mockMvc.perform(put("/api/vehicle-battery-info/{id}", id)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(info)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value(ResponseResultType.SUCCESS.getCode()))
//                .andExpect(jsonPath("$.data").value(1));
//    }
//
//    @Test
//    public void testDeleteVehicleBatteryInfo() throws Exception {
//        Long id = 1L;
//
//        when(vehicleBatteryInfoService.deleteVehicleBatteryInfo(id)).thenReturn(1);
//
//        mockMvc.perform(delete("/api/vehicle-battery-info/{id}", id))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value(ResponseResultType.SUCCESS.getCode()))
//                .andExpect(jsonPath("$.data").value(1));
//    }
//
//    @Test
//    public void testGetVehicleBatteryInfoById() throws Exception {
//        Long id = 1L;
//        VehicleBatteryInfo info = new VehicleBatteryInfo();
//        info.setId(id);
//        info.setCarId(1L);
//        info.setBatteryCapacity(new BigDecimal("50.0"));
//        info.setVoltage(new BigDecimal("12.5"));
//        info.setCurrent(new BigDecimal("2.5"));
//        info.setTemperature(new BigDecimal("25.0"));
//
//        when(vehicleBatteryInfoService.getVehicleBatteryInfoById(id)).thenReturn(info);
//
//        mockMvc.perform(get("/api/vehicle-battery-info/{id}", id))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value(ResponseResultType.SUCCESS.getCode()))
//                .andExpect(jsonPath("$.data.id").value(id))
//                .andExpect(jsonPath("$.data.carId").value(1))
//                .andExpect(jsonPath("$.data.batteryCapacity").value(50.0));
//    }
//
//    @Test
//    public void testGetAllVehicleBatteryInfos() throws Exception {
//        VehicleBatteryInfo info1 = new VehicleBatteryInfo();
//        info1.setId(1L);
//        info1.setCarId(1L);
//        info1.setBatteryCapacity(new BigDecimal("50.0"));
//        info1.setVoltage(new BigDecimal("12.5"));
//        info1.setCurrent(new BigDecimal("2.5"));
//        info1.setTemperature(new BigDecimal("25.0"));
//
//        VehicleBatteryInfo info2 = new VehicleBatteryInfo();
//        info2.setId(2L);
//        info2.setCarId(2L);
//        info2.setBatteryCapacity(new BigDecimal("60.0"));
//        info2.setVoltage(new BigDecimal("13.5"));
//        info2.setCurrent(new BigDecimal("3.5"));
//        info2.setTemperature(new BigDecimal("30.0"));
//
//        List<VehicleBatteryInfo> infos = Arrays.asList(info1, info2);
//
//        when(vehicleBatteryInfoService.getAllVehicleBatteryInfos()).thenReturn(infos);
//
//        mockMvc.perform(get("/api/vehicle-battery-info"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value(ResponseResultType.SUCCESS.getCode()))
//                .andExpect(jsonPath("$.data", hasSize(2)))
//                .andExpect(jsonPath("$.data[0].id").value(1))
//                .andExpect(jsonPath("$.data[1].id").value(2));
//    }
//
//    @Test
//    public void testGetVehicleBatteryInfoByCarId() throws Exception {
//        Long carId = 1L;
//        VehicleBatteryInfo info = new VehicleBatteryInfo();
//        info.setId(1L);
//        info.setCarId(carId);
//        info.setBatteryCapacity(new BigDecimal("50.0"));
//        info.setVoltage(new BigDecimal("12.5"));
//        info.setCurrent(new BigDecimal("2.5"));
//        info.setTemperature(new BigDecimal("25.0"));
//
//        when(vehicleBatteryInfoService.getVehicleBatteryInfoByCarId(carId)).thenReturn(info);
//
//        mockMvc.perform(get("/api/vehicle-battery-info/car/{carId}", carId))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value(ResponseResultType.SUCCESS.getCode()))
//                .andExpect(jsonPath("$.data.carId").value(carId));
//    }
//
//    @Test
//    public void testUpdateVehicleBatteryInfoWithInvalidId() throws Exception {
//        Long invalidId = 999L;
//        VehicleBatteryInfo info = new VehicleBatteryInfo();
//        info.setId(invalidId);
//
//        when(vehicleBatteryInfoService.updateVehicleBatteryInfo(any(VehicleBatteryInfo.class)))
//                .thenThrow(new IllegalArgumentException("Invalid ID"));
//
//        mockMvc.perform(put("/api/vehicle-battery-info/{id}", invalidId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(info)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void testDeleteVehicleBatteryInfoWithInvalidId() throws Exception {
//        Long invalidId = 999L;
//
//        when(vehicleBatteryInfoService.deleteVehicleBatteryInfo(invalidId))
//                .thenThrow(new IllegalArgumentException("Invalid ID"));
//
//        mockMvc.perform(delete("/api/vehicle-battery-info/{id}", invalidId))
//                .andExpect(status().isBadRequest());
//    }
}



