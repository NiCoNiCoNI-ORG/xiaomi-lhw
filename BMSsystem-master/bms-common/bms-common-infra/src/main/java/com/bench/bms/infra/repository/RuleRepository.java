package com.bench.bms.infra.repository;

import com.bench.bms.domain.model.RuleDo;
import com.bench.bms.domain.model.SignalDo;
import com.bench.bms.domain.model.WarnDo;

import java.util.List;

/**
 * @Author bench
 * @Date 2024/06/18 14:09
 **/
public interface RuleRepository {

//    根据信号和规则得到对应的警告信息
    WarnDo getWarn(SignalDo signalDo, RuleDo ruleDo);

    RuleDo save(RuleDo ruleDo);

    Integer remove(Long Id);

    Integer update(RuleDo ruleDo);

    List<RuleDo> list();

    RuleDo getRule(Long Id);

}
