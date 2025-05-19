package com.bench.bms.application.model;

import lombok.Data;

import java.util.Map;

/**
 * @Author bench
 * @Date 2024/06/18 13:11
 **/

@Data
public class RuleDto {
    private Long id;
    private Integer warnId;
    private String warnName;
    private String batteryType;
    private Map<Double, Integer> warnRule;

}
