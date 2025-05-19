package com.bench.bms.api.model.res;

import lombok.Data;

import java.util.Map;

/**
 * @Author bench
 * @Date 2024/06/26 10:35
 **/
@Data
public class RuleVo {
    private Long id;
    private Integer warnId;
    private String warnName;
    private String batteryType;
    private Map<Double, Integer> warnRule;
}
