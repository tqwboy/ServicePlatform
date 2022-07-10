package com.hohenheim.java.serviceplatform.core.cache.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author Hohenheim
 * @date 2020/2/15
 * @description Redis Template 工厂
 */
public abstract class BaseRedisTemplateFactory {
    protected StringRedisSerializer mStringRedisSerializer;

    public BaseRedisTemplateFactory() {
        mStringRedisSerializer = new StringRedisSerializer();
    }

    protected  <K, T> RedisTemplate<K, T> createRedisTemplate(RedisConnectionFactory connFactory, Class<T> clazz) {
        RedisTemplate<K, T> template = new RedisTemplate<>();
        template.setConnectionFactory(connFactory);
        Jackson2JsonRedisSerializer<T> serializer = new Jackson2JsonRedisSerializer<>(clazz);

        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 解决jackson2无法反序列化LocalDateTime的问题
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        om.registerModule(new JavaTimeModule());
        serializer.setObjectMapper(om);
        //键序列化
        template.setKeySerializer(mStringRedisSerializer);
        template.setHashKeySerializer(mStringRedisSerializer);
        //值序列化
        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);

        return template;
    }
}