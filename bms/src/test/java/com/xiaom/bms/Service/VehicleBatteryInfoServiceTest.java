package com.xiaom.bms.Service;

import com.xiaom.bms.common.BATTERY_NAME;
import com.xiaom.bms.model.VehicleBatteryInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class VehicleBatteryInfoServiceTest {

    @Mock
    private VehicleBatteryInfoService vehicleBatteryInfoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateVehicleBatteryInfo() {
        VehicleBatteryInfo vehicleBatteryInfo = new VehicleBatteryInfo();
        vehicleBatteryInfo.setVid("VID1");
        vehicleBatteryInfo.setCid(1L);
        vehicleBatteryInfo.setCarTotalMileage(1000L);
        vehicleBatteryInfo.setBatteryId(1L);
        vehicleBatteryInfo.setBatteryName("三元电池");
        vehicleBatteryInfo.setBatteryType((byte) 1);
        vehicleBatteryInfo.setBatteryTotalMileage(500L);
        vehicleBatteryInfo.setBatteryStatus((byte) 1);
        vehicleBatteryInfo.setMx(new BigDecimal("42.00"));
        vehicleBatteryInfo.setMi(new BigDecimal("38.00"));
        vehicleBatteryInfo.setIx(new BigDecimal("15.00"));
        vehicleBatteryInfo.setIi(new BigDecimal("5.00"));

        when(vehicleBatteryInfoService.createVehicleBatteryInfo(any(VehicleBatteryInfo.class))).thenReturn(1);

        int result = vehicleBatteryInfoService.createVehicleBatteryInfo(vehicleBatteryInfo);
        assertEquals(1, result);
        verify(vehicleBatteryInfoService, times(1)).createVehicleBatteryInfo(vehicleBatteryInfo);
    }

    @Test
    public void testUpdateVehicleBatteryInfo() {
        VehicleBatteryInfo vehicleBatteryInfo = new VehicleBatteryInfo();
        vehicleBatteryInfo.setId(1L);
        vehicleBatteryInfo.setVid("VID1");
        vehicleBatteryInfo.setCid(1L);
        vehicleBatteryInfo.setCarTotalMileage(1000L);
        vehicleBatteryInfo.setBatteryId(1L);
        vehicleBatteryInfo.setBatteryName("三元电池");
        vehicleBatteryInfo.setBatteryType((byte) 2);
        vehicleBatteryInfo.setBatteryTotalMileage(500L);
        vehicleBatteryInfo.setBatteryStatus((byte) 2);
        vehicleBatteryInfo.setMx(new BigDecimal("42.00"));
        vehicleBatteryInfo.setMi(new BigDecimal("38.00"));
        vehicleBatteryInfo.setIx(new BigDecimal("15.00"));
        vehicleBatteryInfo.setIi(new BigDecimal("5.00"));

        when(vehicleBatteryInfoService.updateVehicleBatteryInfo(any(VehicleBatteryInfo.class))).thenReturn(1);

        int result = vehicleBatteryInfoService.updateVehicleBatteryInfo(vehicleBatteryInfo);
        assertEquals(1, result);
        verify(vehicleBatteryInfoService, times(1)).updateVehicleBatteryInfo(vehicleBatteryInfo);
    }

    @Test
    public void testDeleteVehicleBatteryInfo() {
        String vid = "VID1";

        when(vehicleBatteryInfoService.deleteVehicleBatteryInfo(vid)).thenReturn(1);

        int result = vehicleBatteryInfoService.deleteVehicleBatteryInfo(vid);
        assertEquals(1, result);
        verify(vehicleBatteryInfoService, times(1)).deleteVehicleBatteryInfo(vid);
    }

    @Test
    public void testGetVehicleBatteryInfoById() {
        String vid = "VID1";
        VehicleBatteryInfo vehicleBatteryInfo = new VehicleBatteryInfo();
        vehicleBatteryInfo.setVid(vid);
        vehicleBatteryInfo.setCid(1L);
        vehicleBatteryInfo.setCarTotalMileage(1000L);
        vehicleBatteryInfo.setBatteryId(1L);
        vehicleBatteryInfo.setBatteryName("三元电池");
        vehicleBatteryInfo.setBatteryType((byte) 1);
        vehicleBatteryInfo.setBatteryTotalMileage(500L);
        vehicleBatteryInfo.setBatteryStatus((byte) 1);
        vehicleBatteryInfo.setMx(new BigDecimal("42.00"));
        vehicleBatteryInfo.setMi(new BigDecimal("38.00"));
        vehicleBatteryInfo.setIx(new BigDecimal("15.00"));
        vehicleBatteryInfo.setIi(new BigDecimal("5.00"));

        when(vehicleBatteryInfoService.getVehicleBatteryInfoById(vid)).thenReturn(vehicleBatteryInfo);

        VehicleBatteryInfo result = vehicleBatteryInfoService.getVehicleBatteryInfoById(vid);
        assertEquals(vehicleBatteryInfo, result);
        verify(vehicleBatteryInfoService, times(1)).getVehicleBatteryInfoById(vid);
    }

    @Test
    public void testGetAllVehicleBatteryInfos() {
        VehicleBatteryInfo info1 = new VehicleBatteryInfo();
        info1.setVid("VID1");
        info1.setCid(1L);
        info1.setCarTotalMileage(1000L);
        info1.setBatteryId(1L);
        info1.setBatteryName("三元电池");
        info1.setBatteryType((byte) 1);
        info1.setBatteryTotalMileage(500L);
        info1.setBatteryStatus((byte) 1);
        info1.setMx(new BigDecimal("42.00"));
        info1.setMi(new BigDecimal("38.00"));
        info1.setIx(new BigDecimal("15.00"));
        info1.setIi(new BigDecimal("5.00"));

        VehicleBatteryInfo info2 = new VehicleBatteryInfo();
        info2.setVid("VID2");
        info2.setCid(2L);
        info2.setCarTotalMileage(1500L);
        info2.setBatteryId(2L);
        info2.setBatteryName("三元电池");
        info2.setBatteryType((byte) 2);
        info2.setBatteryTotalMileage(750L);
        info2.setBatteryStatus((byte) 2);
        info2.setMx(new BigDecimal("45.00"));
        info2.setMi(new BigDecimal("40.00"));
        info2.setIx(new BigDecimal("17.00"));
        info2.setIi(new BigDecimal("6.00"));

        List<VehicleBatteryInfo> vehicleBatteryInfos = new ArrayList<>();
        vehicleBatteryInfos.add(info1);
        vehicleBatteryInfos.add(info2);

        when(vehicleBatteryInfoService.getAllVehicleBatteryInfos()).thenReturn(vehicleBatteryInfos);

        List<VehicleBatteryInfo> result = vehicleBatteryInfoService.getAllVehicleBatteryInfos();
        assertEquals(vehicleBatteryInfos, result);
        verify(vehicleBatteryInfoService, times(1)).getAllVehicleBatteryInfos();
    }
}



