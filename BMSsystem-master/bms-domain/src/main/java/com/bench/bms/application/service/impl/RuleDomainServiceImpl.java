package com.bench.bms.application.service.impl;

import com.bench.bms.common.exception.DomainException;
import com.bench.bms.common.exception.exceptionsenum.DomainExceptionEnum;
import com.bench.bms.domain.model.RuleDo;
import com.bench.bms.domain.model.SignalDo;
import com.bench.bms.domain.model.WarnDo;
import com.bench.bms.domain.service.RuleDomainService;
import com.bench.bms.infra.repository.RuleRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author bench
 * @Date 2024/06/19 13:31
 **/

@Service
public class RuleDomainServiceImpl implements RuleDomainService {

    @Resource
    private RuleRepository ruleRepository;

    @Override
    public WarnDo getWarn(SignalDo signalDo, RuleDo ruleDo) {

        return ruleRepository.getWarn(signalDo, ruleDo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RuleDo saveRule(RuleDo ruleDo) {
        ruleDo = ruleRepository.save(ruleDo);
        return ruleDo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void modifyRule(RuleDo ruleDo) {
        if (ruleRepository.update(ruleDo) == 0){
            throw new DomainException(DomainExceptionEnum.RULE_NOT_FOUND);
        }
    }

    @Override
    public void removeRule(Long Id) {
        if (ruleRepository.remove(Id) == 0){
            throw new DomainException(DomainExceptionEnum.RULE_NOT_FOUND);
        }
    }
}
