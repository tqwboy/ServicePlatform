package com.hohenheim.java.serviceplatform.core.conf.factory;

import com.hohenheim.java.serviceplatform.core.conf.AsyncThreadPoolConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @author Hohenheim
 * @date 2021/12/7
 * @description Spring 异步任务线程池配置
 */
@Configuration
@EnableAsync
@Slf4j
public class AsyncThreadPoolConfigFactory {
    @Bean("coreAsyncExecutorConfig")
    @ConfigurationProperties("async-task-thread-pool.core")
    @Primary
    public AsyncThreadPoolConfig getCoreAsyncThreadPoolConfig() {
        return new AsyncThreadPoolConfig();
    }

    @Bean("coreAsyncExecutor")
    @Primary
    public Executor coreArchivesAsyncExecutor(@Qualifier("coreAsyncExecutorConfig") AsyncThreadPoolConfig config) {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setThreadNamePrefix(config.getThreadNamePrefix());
        threadPool.setDaemon(false);
        threadPool.setCorePoolSize(config.getCorePoolSize());
        threadPool.setMaxPoolSize(config.getMaxPoolSize());
        threadPool.setAllowCoreThreadTimeOut(true);
        threadPool.setKeepAliveSeconds(config.getKeepAliveSeconds());
        threadPool.setQueueCapacity(config.getQueueCapacity());
        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                log.error("[ServicePlatform-xi-async] 学习问题异步任务队列已满，任务未能正常运行！");
            }
        });
        threadPool.initialize();
        return threadPool;
    }
}
