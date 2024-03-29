package com.hohenheim.java.serviceplatform.core.conf.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author Hohenheim
 * @date 2020/7/12
 * @description
 */
public class NullNumberJsonSerializer extends JsonSerializer<Object> {
    @Override
    public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(0);
    }
}