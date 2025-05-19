package com.bench.bms.application.service;

import com.bench.bms.application.model.SignalDto;
import com.bench.bms.application.model.WarnDto;

import java.util.List;

/**
 * @Author bench
 * @Date 2024/06/17 13:11
 **/
public interface SignalService {

//    通过信号的Dto获取对应的报警信息
    List<WarnDto> getWarnInfo(List<SignalDto> signalDtoList);

}
