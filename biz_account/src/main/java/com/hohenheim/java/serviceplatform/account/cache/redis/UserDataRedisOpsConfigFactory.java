package com.hohenheim.java.serviceplatform.account.cache.redis;

import com.hohenheim.java.serviceplatform.core.cache.redis.RedisOpsConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Hohenheim
 * @date 2022/7/10
 * @description Redis 操作配置工厂
 */
@Configuration
public class UserDataRedisOpsConfigFactory {
    @Bean("regCodeRedisConfig")
    @ConfigurationProperties("redis.ops.register-verify-code")
    public RedisOpsConfig verifyCodeRedisOpsConfig() {
        return new RedisOpsConfig();
    }

    @Bean("userInfoRedisConfig")
    @ConfigurationProperties("redis.ops.user-info")
    public RedisOpsConfig userInfoRedisOpsConfig() {
        return new RedisOpsConfig();
    }
}