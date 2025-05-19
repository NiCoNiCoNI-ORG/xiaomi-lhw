package com.xiaom.bms.mq;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BatterySignalConsumer {

    @KafkaListener(topics = "battery-signal-topic")
    public void consumeBatterySignals(String message) {
        // 处理MQ消息并生成预警信息
    }
}