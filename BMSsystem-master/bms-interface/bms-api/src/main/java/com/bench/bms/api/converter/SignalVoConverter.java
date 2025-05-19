package com.bench.bms.api.converter;

import com.bench.bms.api.model.req.SignalReq;
import com.bench.bms.application.model.SignalDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.io.IOException;
import java.util.Map;

/**
 * @Author bench
 * @Date 2024/06/16 21:48
 **/

@Mapper(componentModel = "spring")
public interface SignalVoConverter {

    // 用于将接口层接收到的数据signalReq转换为，应用层处理的对象Dto。
    @Mapping(source = "signal", target = "signal", qualifiedByName = "stringToMap")
    SignalDto toDto(SignalReq signalReq);

    @Named("stringToMap")
    default Map<String, Double> map(String value) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(value, new TypeReference<Map<String, Double>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert String to Map<String, Double>", e);
        }
    }
}
