package com.bench.bms.infra.repository;

import com.bench.bms.domain.model.RuleDo;
import com.bench.bms.domain.model.SignalDo;

/**
 * @Author bench
 * @Date 2024/06/18 14:08
 **/
public interface SignalRepository {

    RuleDo getRule(SignalDo signalDo);

}
