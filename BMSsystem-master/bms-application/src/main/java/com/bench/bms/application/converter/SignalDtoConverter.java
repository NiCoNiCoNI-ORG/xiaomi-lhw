package com.bench.bms.application.converter;

import com.bench.bms.application.model.SignalDto;
import com.bench.bms.domain.model.SignalDo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @Author bench
 * @Date 2024/06/17 13:03
 **/

@Mapper(componentModel = "spring")
public interface SignalDtoConverter {

//    @Mapping(source = "signal", target = "signal")
    SignalDo toDo(SignalDto signalDto);

//    @Mapping(source = "signal", target = "signal")
    SignalDto toDto(SignalDo signalDo);

}
