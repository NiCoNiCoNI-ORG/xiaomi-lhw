package com.bench.bms.application.service.impl;

import com.bench.bms.application.converter.RuleDtoConverter;
import com.bench.bms.application.model.RuleDto;
import com.bench.bms.application.service.WarnRuleService;
import com.bench.bms.domain.model.RuleDo;
import com.bench.bms.domain.service.RuleDomainService;
import com.bench.bms.infra.repository.RuleRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author bench
 * @Date 2024/06/26 10:59
 **/

@Service
public class WarnRuleServiceImpl implements WarnRuleService {

    @Resource
    private RuleDomainService ruleDomainService;

    @Resource
    private RuleDtoConverter ruleDtoConverter;

    @Resource
    private RuleRepository ruleRepository;


    @Override
    public RuleDto addRule(RuleDto ruleDto) {
        RuleDo ruleDo = ruleDtoConverter.toDo(ruleDto);
        ruleDo = ruleDomainService.saveRule(ruleDo);
        return ruleDtoConverter.toDto(ruleDo);
    }

    @Override
    public void remove(Long Id) {
        ruleDomainService.removeRule(Id);
    }

    @Override
    public void modifyRule(RuleDto ruleDto) {
        RuleDo ruleDo = ruleDtoConverter.toDo(ruleDto);
        ruleDomainService.modifyRule(ruleDo);
    }

    @Override
    public RuleDto getRuleById(Long Id) {
        RuleDo ruleDo = ruleRepository.getRule(Id);
        return ruleDtoConverter.toDto(ruleDo);
    }

    @Override
    public List<RuleDto> listRules() {
        List<RuleDo> ruleDoList = ruleRepository.list();
        List<RuleDto> ruleDtoList = ruleDoList.stream()
                .map(ruleDtoConverter::toDto)
                .collect(Collectors.toList());
        return ruleDtoList;
    }
}
