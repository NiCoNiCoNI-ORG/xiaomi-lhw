package com.bench.bms.application.service;

import com.bench.bms.application.model.RuleDto;

import java.util.List;

/**
 * @Author bench
 * @Date 2024/06/26 10:09
 **/
public interface WarnRuleService {

    RuleDto addRule(RuleDto ruleDto);

    void remove(Long Id);

    void modifyRule(RuleDto ruleDto);

    RuleDto getRuleById(Long Id);

    List<RuleDto> listRules();

}
