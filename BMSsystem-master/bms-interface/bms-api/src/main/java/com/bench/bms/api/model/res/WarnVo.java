package com.bench.bms.api.model.res;

import lombok.Data;

/**
 * @Author bench
 * @Date 2024/06/16 18:52
 **/
@Data
public class WarnVo {

    private Long carId;

    private String batteryType;

    private String warnName;

    private String warnLevel;

}
