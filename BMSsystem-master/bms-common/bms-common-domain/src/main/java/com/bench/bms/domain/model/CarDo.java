package com.bench.bms.domain.model;

import lombok.Data;

/**
 * @Author bench
 * @Date 2024/06/20 19:06
 **/
@Data
public class CarDo {

    private String vid;
    private Long carId;
    private String batteryType;
    private Long totalMileage;
    private Integer batteryStatus;

}
