package com.bench.bms.application.model;

import lombok.Data;

/**
 * @Author bench
 * @Date 2024/06/20 19:02
 **/

@Data
public class CarDto {

    private String vid;
    private Long carId;
    private String batteryType;
    private Long totalMileage;
    private Integer batteryStatus;

}
