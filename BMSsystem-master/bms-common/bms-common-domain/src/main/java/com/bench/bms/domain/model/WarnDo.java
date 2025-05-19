package com.bench.bms.domain.model;

import lombok.Data;

/**
 * @Author bench
 * @Date 2024/06/17 20:00
 **/

@Data
public class WarnDo {
    private Long carId;
    private String batteryType;
    private String warnName;
    private Integer warnLevel;
}
