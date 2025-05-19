package com.xiaom.bms.Mapper;


import com.xiaom.bms.model.BatteryRule;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BatteryRuleMapper {
    @Insert("INSERT INTO Battery_Rule(rule_name, rule_description, rule_type, create_time, update_time) VALUES(#{ruleName}, #{ruleDescription}, #{ruleType}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(BatteryRule batteryRule);

    @Update("UPDATE Battery_Rule SET rule_name=#{ruleName}, rule_description=#{ruleDescription}, rule_type=#{ruleType}, update_time=NOW() WHERE id=#{id}")
    int update(BatteryRule batteryRule);

    @Delete("DELETE FROM Battery_Rule WHERE id=#{id}")
    int deleteById(Long id);

    @Select("SELECT * FROM Battery_Rule WHERE id=#{id}")
    BatteryRule selectById(Long id);

    @Select("SELECT * FROM Battery_Rule")
    List<BatteryRule> selectAll();

    @Select("SELECT * FROM Battery_Rule WHERE rid = #{rid}")
    List<BatteryRule> findByRid(Long rid);

    @Select("SELECT * FROM Battery_Rule")
    List<BatteryRule> findAllRules();
}