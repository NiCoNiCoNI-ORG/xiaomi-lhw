package com.xiaomi.bms.monitor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SignalInfo {


    private Long id;


    private Long carId;


    private BigDecimal batteryCapacity;


    private BigDecimal Mx;


    private BigDecimal Mi;


    private BigDecimal Ix;


    private BigDecimal Ii;


    private Date uploadTime;
}