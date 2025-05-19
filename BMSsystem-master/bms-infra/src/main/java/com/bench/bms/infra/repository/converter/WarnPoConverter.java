package com.bench.bms.infra.repository.converter;

import com.bench.bms.domain.model.WarnDo;
import com.bench.bms.infra.repository.entity.WarnPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

/**
 * @Author bench
 * @Date 2024/06/17 15:22
 **/

@Component
@Mapper(componentModel = "spring")
public interface WarnPoConverter {


    @Mapping(source = "carId", target = "carId")
    @Mapping(source = "batteryType", target = "batteryType")
    @Mapping(source = "warnName", target = "warnName")
    @Mapping(source = "warnLevel", target = "warnLevel")
    WarnPo toPo(WarnDo warnDo);

    @Mapping(source = "carId", target = "carId")
    @Mapping(source = "batteryType", target = "batteryType")
    @Mapping(source = "warnName", target = "warnName")
    @Mapping(source = "warnLevel", target = "warnLevel")
    WarnDo toDo(WarnPo warnPo);
}