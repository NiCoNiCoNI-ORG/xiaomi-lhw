package com.xiaom.bms.Service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaom.bms.Exception.GlobalException;
import com.xiaom.bms.Mapper.BatteryRuleMapper;
import com.xiaom.bms.Service.BatteryWarnService;
import com.xiaom.bms.common.ResponseResultType;
import com.xiaom.bms.model.BatteryRule;
import com.xiaom.bms.model.WarnReportRequest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class BatteryWarnServiceImpl implements BatteryWarnService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private BatteryRuleMapper batteryRuleMapper;

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    // 从缓存中获取规则（根据 rid = 1 或 2）
    private List<BatteryRule> getRulesFromCache(WarnReportRequest warnReportRequest) {
        Long warnIdLong = warnReportRequest.getWarnId();
        if (warnIdLong == null || warnIdLong <= 0) {
            throw new GlobalException(ResponseResultType.BAD_REQUEST, "规则编号必须大于0");
        }

        int warnId = warnIdLong.intValue();
        String pattern = "battery_rule:" + warnId + ":*";
        List<BatteryRule> rules = new ArrayList<>();

        redisTemplate.execute((RedisCallback<Void>) connection -> {
            ScanOptions options = ScanOptions.scanOptions().match(pattern).count(100).build();
            Cursor<byte[]> cursor = connection.scan(options);

            while (cursor.hasNext()) {
                byte[] rawKey = cursor.next();
                String key = new String(rawKey, StandardCharsets.UTF_8);

                // 获取 Hash 数据
                Map<byte[], byte[]> entries = connection.hGetAll(rawKey);
                if (entries == null || entries.isEmpty()) {
                    continue;
                }
                Map<String, String> stringMap = new HashMap<>();
                for (Map.Entry<byte[], byte[]> entry : entries.entrySet()) {
                    stringMap.put(
                            new String(entry.getKey(), StandardCharsets.UTF_8),
                            new String(entry.getValue(), StandardCharsets.UTF_8));
                }

                // 反序列化为 BatteryRule
                try {
                    BatteryRule rule = objectMapper.convertValue(stringMap, BatteryRule.class);
                    rules.add(rule);
                } catch (Exception e) {

                    System.err.println("Error converting hash to BatteryRule: " + e.getMessage());
                }
            }
            return null; // 返回类型为 Void，所以返回 null
        });
        // 如果没有找到任何规则，则从数据库中加载所有规则
        if (rules.isEmpty()) {
            return batteryRuleMapper.findAllRules();
        }

        return rules;
    }

    // 检查差值并发送 Kafka 告警消息
    private void checkAndWarn(Map<String, Double> signals,
            String keyX,
            String keyI,
            List<BatteryRule> rules) {

        Double xVal = signals.get(keyX);
        Double iVal = signals.get(keyI);

        if (xVal == null || iVal == null) {
            return;
        }

        double diff = Math.abs(xVal - iVal);
        BigDecimal diffBD = BigDecimal.valueOf(diff);

        for (BatteryRule rule : rules) {

            if (diffBD.compareTo(rule.getMinValue()) < 0 || diffBD.compareTo(rule.getMaxValue()) > 0) {
                // 构建告警信息
                Map<String, Object> warning = new HashMap<>();
                warning.put("signalPair", keyX + "-" + keyI);
                warning.put("difference", diffBD);
                warning.put("thresholdMin", rule.getMinValue());
                warning.put("thresholdMax", rule.getMaxValue());
                warning.put("warnLevel", rule.getAlertLevel());
                warning.put("ruleName", rule.getRuleName());
                warning.put("timestamp", System.currentTimeMillis());

                // 发送到 Kafka
                String topic = "bms.battery.warning";
                try {
                    String message = objectMapper.writeValueAsString(warning);
                    kafkaTemplate.send(topic, message);
                } catch (Exception e) {
                    e.printStackTrace(); // 日志记录更好
                }
            }
        }
    }

    @Override
    public void reportWarnings(List<WarnReportRequest> requests) {
        // 处理Signal_info数据
        String pattern = "Signal_info:*";
        Set<String> keys = redisTemplate.keys(pattern);
        if (keys != null) {
            for (String key : keys) {
                Object value = redisTemplate.opsForValue().get(key);
                System.out.println("Key: " + key + ", Value: " + value);
            }
        }

        // 处理告警请求
        for (WarnReportRequest request : requests) {
            List<BatteryRule> rules = getRulesFromCache(request);
            Map<String, Double> signals = request.getSignal();
            if (signals == null || signals.isEmpty()) {
                throw new GlobalException(ResponseResultType.BAD_REQUEST, "告警信息不能为空");
            }

            // 检查 Mx 和 Mi
            checkAndWarn(signals, "Mx", "Mi", rules);

            // 检查 Ix 和 Ii
            checkAndWarn(signals, "Ix", "Ii", rules);
        }
    }
}