package com.bench.bms.infra.repository.converter;

import com.bench.bms.domain.model.RuleDo;
import com.bench.bms.infra.repository.entity.RulePo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author bench
 * @Date 2024/06/18 14:18
 **/
@Component
@Mapper(componentModel = "spring")
public interface RulePoConverter {

    @Mapping(source = "warnRule", target = "warnRule", qualifiedByName = "stringToSortedMap")
    RuleDo toDo(RulePo rulePo);

    @Mapping(source = "warnRule", target = "warnRule", qualifiedByName = "sortedMapToString")
    RulePo toPo(RuleDo ruleDo);

    @Named("stringToSortedMap")
    default Map<Double, Integer> stringToSortedMap(String warnRule) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(warnRule, new TypeReference<Map<Double, Integer>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert String to Map<String, Double>", e);
        }
    }

    @Named("sortedMapToString")
    default String sortedMapToString(Map<Double, Integer> ruleMap) {
        if (ruleMap == null || ruleMap.isEmpty()) {
            return "";
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(ruleMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert Map<Double, Integer> to JSON string", e);
        }
    }

}
