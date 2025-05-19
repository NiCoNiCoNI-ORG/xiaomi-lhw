package com.xiaom.bms.Service;

import com.xiaom.bms.model.SignalInfo;
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

public class SignalInfoServiceTest {

    @Mock
    private SignalInfoService signalInfoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateSignalInfo() {
        SignalInfo signalInfo = new SignalInfo();
        signalInfo.setCarId(1L);
        signalInfo.setBatteryCapacity(new BigDecimal("50.00"));
        signalInfo.setMx(new BigDecimal("42.00"));
        signalInfo.setMi(new BigDecimal("38.00"));
        signalInfo.setIx(new BigDecimal("15.00"));
        signalInfo.setIi(new BigDecimal("5.00"));
        signalInfo.setUploadTime(new Date());

        when(signalInfoService.createSignalInfo(any(SignalInfo.class))).thenReturn(1);

        int result = signalInfoService.createSignalInfo(signalInfo);
        assertEquals(1, result);
        verify(signalInfoService, times(1)).createSignalInfo(signalInfo);
    }

    @Test
    public void testUpdateSignalInfo() {
        SignalInfo signalInfo = new SignalInfo();
        signalInfo.setId(1L);
        signalInfo.setCarId(1L);
        signalInfo.setBatteryCapacity(new BigDecimal("50.00"));
        signalInfo.setMx(new BigDecimal("42.00"));
        signalInfo.setMi(new BigDecimal("38.00"));
        signalInfo.setIx(new BigDecimal("15.00"));
        signalInfo.setIi(new BigDecimal("5.00"));
        signalInfo.setUploadTime(new Date());

        when(signalInfoService.updateSignalInfo(any(SignalInfo.class))).thenReturn(1);

        int result = signalInfoService.updateSignalInfo(signalInfo);
        assertEquals(1, result);
        verify(signalInfoService, times(1)).updateSignalInfo(signalInfo);
    }

    @Test
    public void testDeleteSignalInfo() {
        Long id = 1L;

        when(signalInfoService.deleteSignalInfo(id)).thenReturn(1);

        int result = signalInfoService.deleteSignalInfo(id);
        assertEquals(1, result);
        verify(signalInfoService, times(1)).deleteSignalInfo(id);
    }

    @Test
    public void testGetSignalInfoById() {
        Long id = 1L;
        SignalInfo signalInfo = new SignalInfo();
        signalInfo.setId(id);
        signalInfo.setCarId(1L);
        signalInfo.setBatteryCapacity(new BigDecimal("50.00"));
        signalInfo.setMx(new BigDecimal("42.00"));
        signalInfo.setMi(new BigDecimal("38.00"));
        signalInfo.setIx(new BigDecimal("15.00"));
        signalInfo.setIi(new BigDecimal("5.00"));
        signalInfo.setUploadTime(new Date());

        when(signalInfoService.getSignalInfoById(id)).thenReturn(signalInfo);

        SignalInfo result = signalInfoService.getSignalInfoById(id);
        assertEquals(signalInfo, result);
        verify(signalInfoService, times(1)).getSignalInfoById(id);
    }

    @Test
    public void testGetAllSignalInfos() {
        SignalInfo info1 = new SignalInfo();
        info1.setId(1L);
        info1.setCarId(1L);
        info1.setBatteryCapacity(new BigDecimal("50.00"));
        info1.setMx(new BigDecimal("42.00"));
        info1.setMi(new BigDecimal("38.00"));
        info1.setIx(new BigDecimal("15.00"));
        info1.setIi(new BigDecimal("5.00"));
        info1.setUploadTime(new Date());

        SignalInfo info2 = new SignalInfo();
        info2.setId(2L);
        info2.setCarId(2L);
        info2.setBatteryCapacity(new BigDecimal("60.00"));
        info2.setMx(new BigDecimal("45.00"));
        info2.setMi(new BigDecimal("40.00"));
        info2.setIx(new BigDecimal("17.00"));
        info2.setIi(new BigDecimal("6.00"));
        info2.setUploadTime(new Date());

        List<SignalInfo> signalInfos = new ArrayList<>();
        signalInfos.add(info1);
        signalInfos.add(info2);

        when(signalInfoService.getAllSignalInfos()).thenReturn(signalInfos);

        List<SignalInfo> result = signalInfoService.getAllSignalInfos();
        assertEquals(signalInfos, result);
        verify(signalInfoService, times(1)).getAllSignalInfos();
    }
}



