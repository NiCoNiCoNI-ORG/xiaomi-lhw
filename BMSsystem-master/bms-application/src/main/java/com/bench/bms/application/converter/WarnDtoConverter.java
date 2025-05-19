package com.bench.bms.application.converter;

import com.bench.bms.application.model.WarnDto;
import com.bench.bms.domain.model.WarnDo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @Author bench
 * @Date 2024/06/18 13:39
 **/

@Mapper(componentModel = "spring")
public interface WarnDtoConverter {

    WarnDo toDo(WarnDto warnDto);
//    @Mapping(source = "carId", target = "carId")
    WarnDto toDto(WarnDo warnDo);

}
