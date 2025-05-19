package com.bench.bms.infra.repository.impl;

import com.bench.bms.domain.model.RuleDo;
import com.bench.bms.domain.model.SignalDo;
import com.bench.bms.infra.redisservice.RedisService;
import com.bench.bms.infra.repository.SignalRepository;
import com.bench.bms.infra.repository.converter.RulePoConverter;
import com.bench.bms.infra.repository.entity.RulePo;
import com.bench.bms.infra.repository.mapper.RulePoMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Author bench
 * @Date 2024/06/18 14:34
 **/
// 负责查询Rule
@Service
public class SignalRepositoryImpl implements SignalRepository {

    @Resource
    private RulePoConverter rulePoConverter;

    @Resource
    private RulePoMapper rulePoMapper;

    @Override
    public RuleDo getRule(SignalDo signalDo) {
        RulePo rulePo = rulePoMapper.selectRulesBySignal(signalDo);
        return rulePoConverter.toDo(rulePo);
    }
}
