package com.hohenheim.java.serviceplatform.core.cache.redis;

import lombok.Data;

/**
 * @author Hohenheim
 * @date 2022/7/10
 * @description Redis操作配置类
 */
@Data
public class RedisOpsConfig {
    /** KEY前缀 */
    private String keyPrefix;

    /** 过期时间，单位秒 */
    private int expireSeconds;
}