package com.hohenheim.java.serviceplatform.account.cache.redis.ops;

import io.lettuce.core.RedisException;
import org.slf4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.function.Consumer;

/**
 * @author Hohenheim
 * @date 2022/7/16
 * @description
 */
public class BaseRedisOps<K, V> {
    protected RedisTemplate<K, V> mRedisOps;

    protected Logger log;

    public BaseRedisOps(RedisTemplate<K, V> redisOps, Logger log) {
        mRedisOps = redisOps;
        this.log = log;
    }

    /**
     * 保存缓存数据
     * @param key 缓存key
     * @param value 缓存数据
     * @param duration 缓存失效时间
     * @param failConsumer 错误回调函数
     */
    public boolean saveCache(K key, V value, Duration duration, Consumer<RedisException> failConsumer) {
        boolean saveResult = true;

        try {
            mRedisOps.opsForValue().set(key, value, duration);
        }
        catch (RedisException e) {
            saveResult = false;
            log.error("[Redis] 缓存保存失败，Key：{} | value：{}", key, value, e);
            if(null != failConsumer) {
                failConsumer.accept(e);
            }
        }

        return saveResult;
    }

    /**
     * 如果缓存不存在，就保存缓存数据
     * @param key 缓存key
     * @param value 缓存数据
     * @param duration 缓存失效时间
     * @param failConsumer 错误回调函数
     */
    public boolean setIfAbsent(K key, V value, Duration duration, Consumer<RedisException> failConsumer) {
        boolean saveResult = true;

        try {
            mRedisOps.opsForValue().setIfAbsent(key, value, duration);
        }
        catch (RedisException e) {
            saveResult = false;
            log.error("[Redis] 缓存保存失败，Key：{} | value：{}", key, value, e);
            if(null != failConsumer) {
                failConsumer.accept(e);
            }
        }

        return saveResult;
    }

    /**
     * 获取缓存数据
     * @param key 缓存key
     * @param failConsumer 错误回调函数
     * @return 缓存数据
     */
    public V getCache(K key, Consumer<RedisException> failConsumer) {
        V value = null;
        try {
            value = mRedisOps.opsForValue().get(key);
        }
        catch (RedisException e) {
            log.error("[Redis] 获取缓存失败，Key：{}", key, e);
            if(null != failConsumer) {
                failConsumer.accept(e);
            }
        }

        return value;
    }

    /**
     * 删除缓存
     * @param key 缓存key
     * @param failConsumer 错误回调函数
     * @return 删除结果
     */
    public boolean delCache(K key, Consumer<RedisException> failConsumer) {
        try {
            return Boolean.TRUE.equals(mRedisOps.delete(key));
        }
        catch (RedisException e) {
            log.error("[Redis] 缓存失败，Key：{}", key, e);
            if(null != failConsumer) {
                failConsumer.accept(e);
            }
        }

        return false;
    }
}