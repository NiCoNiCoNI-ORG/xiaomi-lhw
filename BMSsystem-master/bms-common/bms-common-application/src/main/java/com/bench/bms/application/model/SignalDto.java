package com.bench.bms.application.model;

import lombok.Data;

import java.util.Map;

/**
 * @Author bench
 * @Date 2024/06/16 21:42
 **/

@Data
public class SignalDto {
    private Long carId;
    private Integer warnId;
    private Map<String, Double> signal;

    public SignalDto(Long carId, Integer warnId, Map<String, Double> signal){
        this.carId = carId;
        this.warnId = warnId;
        this.signal = signal;
    }
}
