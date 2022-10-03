package com.hohenheim.java.serviceplatform.core.conf.threadpool;

import lombok.Data;

/**
 * @author Hohenheim
 * @date 2021/12/7
 * @description Spring 异步任务线程池配置参数
 */
@Data
public class AsyncThreadPoolConfig {
    /** 核心线程数，默认值为当前机器内核数量 */
    private int corePoolSize = Runtime.getRuntime ().availableProcessors();
    /** 核心线程数，默认值为当前机器内核数量 * 2 */
    private int maxPoolSize = Runtime.getRuntime ().availableProcessors() * 2;

    private int keepAliveSeconds;

    private int queueCapacity;

    private String threadNamePrefix;
}