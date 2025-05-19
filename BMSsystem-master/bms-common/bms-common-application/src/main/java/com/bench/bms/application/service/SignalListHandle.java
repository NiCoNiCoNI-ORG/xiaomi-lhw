package com.bench.bms.application.service;

import com.bench.bms.application.model.SignalDto;

import java.util.List;

/**
 * @Author bench
 * @Date 2024/06/19 20:42
 **/
public interface SignalListHandle {
    List<SignalDto> signalHandle(List<SignalDto> signalDtoList);
}
