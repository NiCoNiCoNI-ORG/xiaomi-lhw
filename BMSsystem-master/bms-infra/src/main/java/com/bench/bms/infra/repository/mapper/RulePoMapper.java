package com.bench.bms.infra.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bench.bms.domain.model.SignalDo;
import com.bench.bms.infra.repository.entity.RulePo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author bench
 * @Date 2024/06/17 20:36
 **/

@Mapper
public interface RulePoMapper extends BaseMapper<RulePo> {

    List<RulePo> listRules();

    RulePo selectRulesBySignal(SignalDo signalDo);

    RulePo selectRuleById(Long Id);

    Integer updateRuleById(RulePo rulePo);

}
