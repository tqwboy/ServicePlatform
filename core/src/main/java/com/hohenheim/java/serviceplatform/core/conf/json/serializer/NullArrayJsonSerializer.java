package com.hohenheim.java.serviceplatform.core.conf.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author Hohenheim
 * @date 2020/7/11
 * @description JSON 空数组解析
 */
public class NullArrayJsonSerializer extends JsonSerializer<Object> {
    @Override
    public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (null == value) {
            jsonGenerator.writeStartArray();
            jsonGenerator.writeEndArray();
        }
    }
}