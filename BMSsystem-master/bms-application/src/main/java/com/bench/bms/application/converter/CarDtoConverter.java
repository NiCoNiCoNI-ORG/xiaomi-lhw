package com.bench.bms.application.converter;

import com.bench.bms.application.model.CarDto;
import com.bench.bms.domain.model.CarDo;
import com.bench.bms.infra.repository.entity.CarPo;
import org.mapstruct.Mapper;

/**
 * @Author bench
 * @Date 2024/06/20 19:07
 **/

@Mapper(componentModel = "spring")
public interface CarDtoConverter {

    CarDo toDo(CarDto carDto);

    CarDto toDto(CarDo carDo);

}
