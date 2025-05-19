package com.bench.bms.domain.service;

import com.bench.bms.domain.model.RuleDo;
import com.bench.bms.domain.model.SignalDo;

/**
 * @Author bench
 * @Date 2024/06/17 15:39
 **/
public interface SignalDomainService {


//    根据信号得到规则
    RuleDo getRuleBySignal(SignalDo signalDo);

}
