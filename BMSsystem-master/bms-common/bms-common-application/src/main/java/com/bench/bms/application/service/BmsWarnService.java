package com.bench.bms.application.service;

import com.bench.bms.application.model.RuleDto;
import com.bench.bms.application.model.SignalDto;
import com.bench.bms.application.model.WarnDto;

import java.util.List;

/**
 * @Author bench
 * @Date 2024/06/18 13:17
 **/
public interface BmsWarnService {

    WarnDto getWarn(SignalDto signalDto);

}
