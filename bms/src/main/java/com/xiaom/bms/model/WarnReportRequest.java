package com.xiaom.bms.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.xiaom.bms.common.StringToMapDeserializer;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Map;

@Data
public class WarnReportRequest extends BaseModel{

    @NotNull(message = "车架编号(carId)不能为空")
    @Positive(message = "车架编号(carId)必须大于0")
    private Long carId;

    @Positive(message = "规则编号(warnId)必须大于0")
    private Long warnId;

    @NotNull(message = "信号(signal)不能为空")
    @JsonDeserialize(using = StringToMapDeserializer.class)
        private Map<String, Double> signal;
}
