package com.bench.bms.api.converter;

import com.bench.bms.api.model.req.CarReq;
import com.bench.bms.api.model.res.CarVo;
import com.bench.bms.application.model.CarDto;
import org.mapstruct.Mapper;

/**
 * @Author bench
 * @Date 2024/06/20 19:09
 **/

@Mapper(componentModel = "spring")
public interface CarVoConverter {

    CarDto toDto(CarReq carReq);

    CarVo toVo(CarDto carDto);


}
