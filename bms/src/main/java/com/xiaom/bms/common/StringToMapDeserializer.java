package com.xiaom.bms.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class StringToMapDeserializer extends JsonDeserializer<Map<String, Double>> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Map<String, Double> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        if (value == null || value.isEmpty()) {
            return null;
        }
        return mapper.readValue(value, Map.class);
    }
}