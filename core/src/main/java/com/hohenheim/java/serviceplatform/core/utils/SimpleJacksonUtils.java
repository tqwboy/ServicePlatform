package com.hohenheim.java.serviceplatform.core.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hohenheim.java.serviceplatform.core.conf.JsonSerializerModifier;

import java.text.SimpleDateFormat;

/**
 * @author Hohenheim
 * @date 2021/1/17
 * @description 简单的Jackson工具类
 */
public class SimpleJacksonUtils {
    private static ObjectMapper jsonMapper;

    private SimpleJacksonUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static ObjectMapper getJsonMapper() {
        if(null == jsonMapper) {
            jsonMapper = new ObjectMapper();
            jsonMapper.setSerializerFactory(jsonMapper
                    .getSerializerFactory()
                    .withSerializerModifier(new JsonSerializerModifier()));
            SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            jsonMapper.setDateFormat(smt);
            jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            jsonMapper.registerModule(new JavaTimeModule());
            jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            jsonMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }

        return jsonMapper;
    }

    public static String objectToJsonStr(Object obj) {
        String jsonStr = "";
        ObjectMapper mapper = getJsonMapper();

        try {
            jsonStr = mapper.writeValueAsString(obj);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonStr;
    }
}