package com.bench.bms.domain.service;

import com.bench.bms.domain.model.RuleDo;
import com.bench.bms.domain.model.SignalDo;
import com.bench.bms.domain.model.WarnDo;

import java.util.List;

/**
 * @Author bench
 * @Date 2024/06/18 13:19
 **/
public interface RuleDomainService {

//    根据信号和队则得到对应的报警信息
    WarnDo getWarn(SignalDo signalDo, RuleDo ruleDo);

    RuleDo saveRule(RuleDo ruleDo);

    void modifyRule(RuleDo ruleDo);

    void removeRule(Long warnId);

}
