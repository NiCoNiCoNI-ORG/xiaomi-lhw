package com.xiaom.bms.Service;


import com.xiaom.bms.model.BatteryRule;

import java.util.List;

public interface BatteryRuleService {
    int createRule(BatteryRule batteryRule);
    int updateRule(BatteryRule batteryRule);
    int deleteRule(Long id);
    BatteryRule getRuleById(Long id);
    List<BatteryRule> getAllRules();
}