package com.bench.bms.infra.repository.converter;

import com.bench.bms.domain.model.CarDo;
import com.bench.bms.infra.repository.entity.CarPo;
import org.mapstruct.Mapper;

/**
 * @Author bench
 * @Date 2024/06/20 19:41
 **/

@Mapper(componentModel = "spring")
public interface CarPoConverter {

//    @Mapping(source = "vId", target = "vId")
    CarDo toDo(CarPo carPo);

//    @Mapping(source = "vId", target = "vId")
    CarPo toPo(CarDo carDo);

}
