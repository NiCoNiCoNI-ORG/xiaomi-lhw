package com.xiaom.bms.Service.impl;

import com.xiaom.bms.Exception.GlobalException;
import com.xiaom.bms.Mapper.BatteryRuleMapper;
import com.xiaom.bms.Service.BatteryRuleService;
import com.xiaom.bms.common.ResponseResultType;
import com.xiaom.bms.model.BatteryRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BatteryRuleServiceImpl implements BatteryRuleService {
    @Autowired
    private BatteryRuleMapper batteryRuleMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public int createRule(BatteryRule batteryRule) {
        validateBatteryRule(batteryRule);
        int result = batteryRuleMapper.insert(batteryRule);
        if (result > 0) {
            String cacheKey = "Battery_Rule:" + batteryRule.getId() + ":" + batteryRule.getRid();
            Map<String, Object> ruleMap = new HashMap<>();
            ruleMap.put("rule_name", batteryRule.getRuleName());
            ruleMap.put("battery_type", batteryRule.getBatteryType());
            ruleMap.put("min_value", batteryRule.getMinValue());
            ruleMap.put("max_value", batteryRule.getMaxValue());
            ruleMap.put("alert_level", batteryRule.getAlertLevel());
            redisTemplate.opsForHash().putAll(cacheKey, ruleMap);
        }
        return result;
    }

    private void validateBatteryRule(BatteryRule batteryRule) {
        if (batteryRule == null) {
            throw new GlobalException(ResponseResultType.BAD_REQUEST, "电池规则不能为空");
        }

        if (batteryRule.getRid() == null || batteryRule.getRid() <= 0) {
            throw new GlobalException(ResponseResultType.BAD_REQUEST, "规则编号必须大于0");
        }

        if (batteryRule.getRuleName() == null || batteryRule.getRuleName().trim().isEmpty()) {
            throw new GlobalException(ResponseResultType.BAD_REQUEST, "规则名称不能为空");
        }

        if (batteryRule.getBatteryType() == null || "三元电池".equals(batteryRule.getBatteryType())
                || "铁锂电池".equals(batteryRule.getBatteryType())) {
            throw new IllegalArgumentException("电池类型必须是0（三元电池）或1（铁锂电池）");
        }

        if (batteryRule.getMinValue() == null || batteryRule.getMaxValue() == null) {
            throw new IllegalArgumentException("最小值和最大值不能为空");
        }

        if (batteryRule.getMinValue().compareTo(batteryRule.getMaxValue()) > 0) {
            throw new IllegalArgumentException("最小值不能大于最大值");
        }

        if (batteryRule.getAlertLevel() == null || batteryRule.getAlertLevel() < 0 || batteryRule.getAlertLevel() > 4) {
            throw new IllegalArgumentException("报警等级必须在0到4之间");
        }
        BigDecimal minValue = batteryRule.getMinValue();
        BigDecimal maxValue = batteryRule.getMaxValue();

        if (minValue.compareTo(BigDecimal.ZERO) < 0 || maxValue.compareTo(new BigDecimal("1000")) > 0) {
            throw new IllegalArgumentException("最小值不能小于0，最大值不能超过1000");
        }
    }

    public int updateRule(BatteryRule batteryRule) {
        validateBatteryRule(batteryRule);
        int result = batteryRuleMapper.update(batteryRule);
        if (result > 0) {
            String cacheKey = "Battery_Rule:" + batteryRule.getId() + ":" + batteryRule.getRid();
            redisTemplate.delete(cacheKey);
            Map<String, Object> ruleMap = new HashMap<>();
            ruleMap.put("rule_name", batteryRule.getRuleName());
            ruleMap.put("battery_type", batteryRule.getBatteryType());
            ruleMap.put("min_value", batteryRule.getMinValue());
            ruleMap.put("max_value", batteryRule.getMaxValue());
            ruleMap.put("alert_level", batteryRule.getAlertLevel());
            redisTemplate.opsForHash().putAll(cacheKey, ruleMap);
        }
        return result;
    }

    public int deleteRule(Long id) {
        return batteryRuleMapper.deleteById(id);
    }

    public BatteryRule getRuleById(Long id) {
        return batteryRuleMapper.selectById(id);
    }

    @Override
    public List<BatteryRule> getAllRules() {
        return batteryRuleMapper.selectAll();
    }
}