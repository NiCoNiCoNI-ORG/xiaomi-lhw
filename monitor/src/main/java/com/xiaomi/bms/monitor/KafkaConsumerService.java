package com.xiaomi.bms.monitor;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @Resource
    private final ObjectMapper objectMapper;

    public KafkaConsumerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "bms.battery.warning", groupId = "warning-consumer-group")
    public void receiveWarnMessage(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        try {
            // ✅ 先直接打印原始消息内容
            logger.info("Received raw message: {}", record.value());

            // 🔁 调试时可注释掉下面这行，暂时不要做反序列化
            // SignalInfo signalInfo = objectMapper.readValue(record.value(), SignalInfo.class);

            // processSignal(signalInfo); // 暂时不处理业务逻辑

            // ✅ 手动提交 offset（确保消息只被消费一次）
            acknowledgment.acknowledge();

        } catch (Exception e) {
            logger.error("Error consuming Kafka message", e);
        }
    }
}