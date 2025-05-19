package com.bench.bms.api.converter;

import com.bench.bms.api.model.req.RuleReq;
import com.bench.bms.api.model.res.RuleVo;
import com.bench.bms.application.model.RuleDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author bench
 * @Date 2024/06/26 10:39
 **/

@Mapper(componentModel = "spring")
public interface RuleVoConverter {

    @Mapping(source = "warnRule", target = "warnRule", qualifiedByName = "stringToSortedMap")
    RuleDto toDto(RuleReq ruleReq);

//    @Mapping(source = "rule", target = "rule", qualifiedByName = "mapToString")
    RuleVo toVo(RuleDto ruleDto);

    @Named("stringToSortedMap")
    default Map<Double, Integer> stringToSortedMap(String value) {
        if (value == null || value.isEmpty()) {
            return Collections.emptyMap();
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(value, new TypeReference<Map<Double, Integer>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert String to Map<String, Double>", e);
        }
    }

}
