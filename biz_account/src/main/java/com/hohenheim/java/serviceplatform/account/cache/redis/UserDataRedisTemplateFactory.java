package com.hohenheim.java.serviceplatform.account.cache.redis;

import com.hohenheim.java.serviceplatform.account.db.entity.UserInfoEntity;
import com.hohenheim.java.serviceplatform.account.model.data.RegisterVerifyCodeCacheModel;
import com.hohenheim.java.serviceplatform.core.cache.redis.BaseRedisTemplateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Hohenheim
 * @date 2022/7/10
 * @description Redis 操作配置
 */
@Configuration
public class UserDataRedisTemplateFactory extends BaseRedisTemplateFactory {
    /**
     * 注册验证码缓存模板
     */
    @Bean(name = "regCodeRedisTemplate")
    public RedisTemplate<String, RegisterVerifyCodeCacheModel> registerVerifyCodeRedis(RedisConnectionFactory connFactory) {
        return createRedisTemplate(connFactory, RegisterVerifyCodeCacheModel.class);
    }

    /**
     * 用户信息缓存模板
     */
    @Bean(name = "userInfoRedisTemplate")
    public RedisTemplate<String, UserInfoEntity> userInfoRedis(RedisConnectionFactory connFactory) {
        return createRedisTemplate(connFactory, UserInfoEntity.class);
    }
}