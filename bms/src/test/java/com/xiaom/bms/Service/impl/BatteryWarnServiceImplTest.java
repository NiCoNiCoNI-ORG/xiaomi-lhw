package com.xiaom.bms.Service.impl;

import com.xiaom.bms.Exception.GlobalException;
import com.xiaom.bms.Mapper.BatteryRuleMapper;
import com.xiaom.bms.model.BatteryRule;
import com.xiaom.bms.model.WarnReportRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BatteryWarnServiceImplTest {

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private BatteryRuleMapper batteryRuleMapper;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private BatteryWarnServiceImpl batteryWarnService;

    private WarnReportRequest validRequest;

    @BeforeEach
    void setUp() {
        validRequest = new WarnReportRequest();
        validRequest.setWarnId(1L);
        Map<String, Double> signals = new HashMap<>();
        signals.put("Mx", 500.0);
        signals.put("Mi", 400.0);
        signals.put("Ix", 50.0);
        signals.put("Ii", 40.0);
        validRequest.setSignal(signals);
    }

    @Test
    void reportWarnings_ShouldThrowException_WhenSignalIsNull() {
        validRequest.setSignal(null);

        Exception exception = assertThrows(GlobalException.class, () -> {
            batteryWarnService.reportWarnings(Arrays.asList(validRequest));
        });
        assertEquals("告警信息不能为空", exception.getMessage());
    }

    @Test
    void reportWarnings_ShouldSendKafkaMessage_WhenDifferenceExceedsThreshold() {
        BatteryRule rule = new BatteryRule();
        rule.setMinValue(new BigDecimal("0"));
        rule.setMaxValue(new BigDecimal("50"));
        rule.setAlertLevel((byte) 1);
        rule.setRuleName("Test Rule");

        when(batteryRuleMapper.findAllRules()).thenReturn(Arrays.asList(rule));

        batteryWarnService.reportWarnings(Arrays.asList(validRequest));

        verify(kafkaTemplate).send(any(), any());
    }
}