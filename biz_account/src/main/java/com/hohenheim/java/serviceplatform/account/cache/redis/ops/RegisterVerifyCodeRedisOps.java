package com.hohenheim.java.serviceplatform.account.cache.redis.ops;

import com.hohenheim.java.serviceplatform.account.aop.anno.RegisterVerifyCodeCacheKeyAnno;
import com.hohenheim.java.serviceplatform.account.model.data.RegisterVerifyCodeCacheModel;
import com.hohenheim.java.serviceplatform.core.cache.redis.RedisOpsConfig;
import io.lettuce.core.RedisException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author Hohenheim
 * @date 2022/7/10
 * @description 注册验证码
 */
@Component
@Slf4j
public class RegisterVerifyCodeRedisOps {
    private RedisTemplate<String, RegisterVerifyCodeCacheModel> mRedisOps;
    private RedisOpsConfig mRedisOpsConfig;

    public RegisterVerifyCodeRedisOps(@Qualifier("regCodeRedisTemplate") RedisTemplate<String, RegisterVerifyCodeCacheModel> redisOps,
                                      @Qualifier("regCodeRedisConfig") RedisOpsConfig redisOpsConfig) {
        mRedisOps = redisOps;
        mRedisOpsConfig = redisOpsConfig;

    }

    /**
     * 保存用户注册验证码
     * @param account 用户账号
     * @param code 验证码
     * @return 缓存失效日期
     */
    @RegisterVerifyCodeCacheKeyAnno
    public LocalDateTime saveCache(String account, String code) {
        //计算失效日期
        LocalDateTime expireAt = LocalDateTime.now().plusSeconds(mRedisOpsConfig.getExpireSeconds());
        //设置缓存数据
        RegisterVerifyCodeCacheModel cacheModel = RegisterVerifyCodeCacheModel.newBuilder()
                .code(code)
                .expireTime(expireAt)
                .build();

        try {
            mRedisOps.opsForValue().set(account, cacheModel, Duration.ofSeconds(mRedisOpsConfig.getExpireSeconds()));
        }
        catch (RedisException e) {
            log.error("[Account Redis] 保存用户" + account.replace(mRedisOpsConfig.getKeyPrefix(), "")
                    + "注册验证码数据发生异常", e);
            expireAt = null;
        }

        return expireAt;
    }

    /**
     * 获取用户注册码缓存数据
     * @param account 用户账号
     * @return 用户注册验证码数据实体
     */
    @RegisterVerifyCodeCacheKeyAnno
    public RegisterVerifyCodeCacheModel getCache(String account) {
        RegisterVerifyCodeCacheModel cacheModel = null;
        try {
            cacheModel = mRedisOps.opsForValue().get(account);
        }
        catch (RedisException e) {
            log.error("[Account Redis] 获取用户" + account.replace(mRedisOpsConfig.getKeyPrefix(), "")
                    + "注册验证码数据发生异常", e);
        }

        return cacheModel;
    }
}