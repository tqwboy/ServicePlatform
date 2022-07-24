package com.hohenheim.java.serviceplatform.account.cache.redis.ops;

import com.hohenheim.java.serviceplatform.account.aop.anno.RegisterVerifyCodeCacheKeyAnno;
import com.hohenheim.java.serviceplatform.account.model.data.RegisterVerifyCodeCacheModel;
import com.hohenheim.java.serviceplatform.core.cache.redis.RedisOpsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author Hohenheim
 * @date 2022/7/10
 * @description 注册验证码
 */
@Component
@Slf4j
public class RegisterVerifyCodeRedisOps extends BaseRedisOps<String, RegisterVerifyCodeCacheModel> {
    private RedisOpsConfig mRedisOpsConfig;

    public RegisterVerifyCodeRedisOps(@Qualifier("regCodeRedisTemplate") RedisTemplate<String, RegisterVerifyCodeCacheModel> redisOps,
                                      @Qualifier("regCodeRedisConfig") RedisOpsConfig redisOpsConfig) {
        super(redisOps, log);
        mRedisOpsConfig = redisOpsConfig;

    }

    /**
     * 保存用户注册验证码
     * @param code 验证码，缓存KEY
     * @param account 用户账号
     * @return 缓存失效日期
     */
    @RegisterVerifyCodeCacheKeyAnno
    public LocalDateTime setIfAbsent(String code, String account) {
        //计算失效日期
        LocalDateTime expireAt = LocalDateTime.now().plusSeconds(mRedisOpsConfig.getExpireSeconds());
        //设置缓存数据
        RegisterVerifyCodeCacheModel cacheModel = RegisterVerifyCodeCacheModel.newBuilder()
                .account(account)
                .expireTime(expireAt)
                .build();

        boolean setResult = super.setIfAbsent(code, cacheModel, Duration.ofSeconds(mRedisOpsConfig.getExpireSeconds()), null);
        if(!setResult) {
            expireAt = null;
        }
        return expireAt;
    }

    /**
     * 获取用户注册码缓存数据
     * @param code 验证码
     * @return 用户注册验证码数据实体
     */
    @RegisterVerifyCodeCacheKeyAnno
    public RegisterVerifyCodeCacheModel getCache(String code) {
        return super.getCache(code, null);
    }

    /**
     * 删除缓存
     * @param code 验证码
     */
    @RegisterVerifyCodeCacheKeyAnno
    public boolean delCache(String code) {
        return super.delCache(code, null);
    }
}