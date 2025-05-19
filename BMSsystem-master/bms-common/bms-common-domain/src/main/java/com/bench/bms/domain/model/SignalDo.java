package com.bench.bms.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * @Author bench
 * @Date 2024/06/17 13:04
 **/

@Data
//@AllArgsConstructor
public class SignalDo {
    private Long carId;
    private Integer warnId;
    private Map<String, Double> signal;
}
