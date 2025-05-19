package com.bench.bms.infra.repository.impl;

import com.bench.bms.common.exception.InfraException;
import com.bench.bms.common.exception.exceptionsenum.InfraExceptionEnum;
import com.bench.bms.domain.model.RuleDo;
import com.bench.bms.domain.model.SignalDo;
import com.bench.bms.domain.model.WarnDo;
import com.bench.bms.infra.repository.RuleRepository;
import com.bench.bms.infra.repository.converter.RulePoConverter;
import com.bench.bms.infra.repository.entity.RulePo;
import com.bench.bms.infra.repository.entity.WarnId;
import com.bench.bms.infra.repository.mapper.RulePoMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author bench
 * @Date 2024/06/18 14:32
 **/

@Service
public class RuleRepositoryImpl implements RuleRepository {

    @Resource
    private RulePoConverter rulePoConverter;

    @Resource
    private RulePoMapper rulePoMapper;

    @Override
    public WarnDo getWarn(SignalDo signalDo, RuleDo ruleDo) {
        WarnDo warnDo = new WarnDo();
        warnDo.setCarId(signalDo.getCarId());
        warnDo.setWarnName(ruleDo.getWarnName());
        warnDo.setBatteryType(ruleDo.getBatteryType());

        double diff = 0;

        if (WarnId.MX_MI.getValue() == signalDo.getWarnId()) {
            diff = signalDo.getSignal().get("Mx") - signalDo.getSignal().get("Mi");
        } else if (WarnId.IX_II.getValue() == signalDo.getWarnId()) {
            diff = signalDo.getSignal().get("Ix") - signalDo.getSignal().get("Ii");
        } else {
            throw new InfraException(InfraExceptionEnum.WAIN_ID_NOT_EXISTS);
        }

        int warnLevel = findWarnLevel(diff, ruleDo.getWarnRule());
        warnDo.setWarnLevel(warnLevel);
        return warnDo;
    }

    @Override
    public RuleDo save(RuleDo ruleDo) {
        RulePo rulePo = rulePoConverter.toPo(ruleDo);
        rulePoMapper.insert(rulePo);
        return rulePoConverter.toDo(rulePo);
    }

    @Override
    public Integer remove(Long Id) {
        return rulePoMapper.deleteById(Id);
    }

    @Override
    public Integer update(RuleDo ruleDo) {
        RulePo rulePo = rulePoConverter.toPo(ruleDo);
        return rulePoMapper.updateRuleById(rulePo);
    }

    @Override
    public List<RuleDo> list() {
        List<RulePo> rulePoList = rulePoMapper.listRules();
        List<RuleDo> ruleDoList = rulePoList.stream()
                .map(rulePoConverter::toDo)
                .collect(Collectors.toList());
        return ruleDoList;
    }

    @Override
    public RuleDo getRule(Long Id) {
        RulePo rulePo = rulePoMapper.selectRuleById(Id);
        return rulePoConverter.toDo(rulePo);
    }


    /**
     * 根据Mx和Mi的差值以及规则Map查找对应的报警等级
     * @param diff Mx和Mi的差值
     * @param ruleMap 规则Map，键为阈值，值为报警等级
     * @return 报警等级，如果找不到对应的阈值范围则返回-1表示不报警
     */
    private int findWarnLevel(double diff, Map<Double, Integer> ruleMap) {
        for (Map.Entry<Double, Integer> entry : ruleMap.entrySet()) {
            double threshold = entry.getKey();
            if (diff >= threshold) {
                return entry.getValue();
            }
        }
        return -1;
    }
}
