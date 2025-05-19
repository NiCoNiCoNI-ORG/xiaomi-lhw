package com.bench.bms.api.model.res;

import lombok.Data;

/**
 * @Author bench
 * @Date 2024/06/20 19:04
 **/

@Data
public class CarVo {

    private String vid;
    private Long carId;
    private String batteryType;
    private Long totalMileage;
    private Integer batteryStatus;

}
