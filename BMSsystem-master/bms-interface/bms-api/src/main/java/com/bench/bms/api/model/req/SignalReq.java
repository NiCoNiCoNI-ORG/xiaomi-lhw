package com.bench.bms.api.model.req;

import com.bench.bms.common.model.ValidGroup.ReportGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author bench
 * @Date 2024/06/16 17:13
 **/

@Data
public class SignalReq {

    @NotNull(message = "汽车id不能为空", groups = {ReportGroup.class})
    private Long carId;

    private Integer warnId;

    @NotBlank(message = "信号id不能为空", groups = {ReportGroup.class})
    private String signal;

}
