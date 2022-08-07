package com.hohenheim.java.serviceplatform.core.conf;

import lombok.Data;

/**
 * @author Hohenheim
 * @date 2021/12/7
 * @description Spring 异步任务线程池配置参数
 */
@Data
public class AsyncThreadPoolConfig {
    private int corePoolSize;

    private int maxPoolSize;

    private int keepAliveSeconds;

    private int queueCapacity;

    private String threadNamePrefix;
}