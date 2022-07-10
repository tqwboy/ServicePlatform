package com.hohenheim.java.serviceplatform.core.cache.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Hohenheim
 * @date 2022/7/10
 * @description Redis 操作配置工厂
 */
@Configuration
public class RedisOpsConfigFactory {
    @Bean("regCodeRedisConfig")
    @ConfigurationProperties("redis.ops.register-verify-code")
    public RedisOpsConfig verifyCodeRedisOpsConfig() {
        return new RedisOpsConfig();
    }
}