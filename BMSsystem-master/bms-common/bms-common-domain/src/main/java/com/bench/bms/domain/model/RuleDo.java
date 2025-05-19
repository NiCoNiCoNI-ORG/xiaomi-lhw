package com.bench.bms.domain.model;

import lombok.Data;

import java.util.Map;

/**
 * @Author bench
 * @Date 2024/06/18 13:13
 **/

@Data
public class RuleDo {
    private Long id;
    private Integer warnId;
    private String warnName;
    private String batteryType;
    private Map<Double, Integer> warnRule;
}
