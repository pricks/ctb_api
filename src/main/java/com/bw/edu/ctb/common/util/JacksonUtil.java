package com.bw.edu.ctb.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtil {
    private JacksonUtil() {}

    public final static ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static String serialize(Object obj) throws Exception {
        return MAPPER.writeValueAsString(obj);
    }

    public static Object deserialize(String jsonText, TypeReference type) throws Exception {
        return MAPPER.readValue(jsonText, type);
    }

    public static <T> T deserialize(String jsonText, Class<T> beanClass) throws Exception {
        return MAPPER.readValue(jsonText, beanClass);
    }

    public static JsonNode deserialize(String jsonText) throws Exception {
        return MAPPER.readTree(jsonText);
    }
}
