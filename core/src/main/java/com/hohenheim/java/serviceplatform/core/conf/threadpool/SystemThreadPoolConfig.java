package com.hohenheim.java.serviceplatform.core.conf.threadpool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Hohenheim
 * @date 2022/9/25
 * @description SpringBoot默认线程池配置
 */
@EnableAsync
@Configuration
@Slf4j
public class SystemThreadPoolConfig implements AsyncConfigurer {
    @Value("${spring.application.name}")
    private String mAppName;

    private String mThreadNamePrefix;

    @PostConstruct
    public void init() {
        mThreadNamePrefix = mAppName + "-thread-";
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setThreadNamePrefix(mThreadNamePrefix);
        threadPool.setDaemon(false);
        threadPool.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        threadPool.setMaxPoolSize(Runtime.getRuntime().availableProcessors() * 2);
        threadPool.setAllowCoreThreadTimeOut(true);
        threadPool.setKeepAliveSeconds(60);
        threadPool.setQueueCapacity(1024);
        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                log.error("[" + mAppName + "-Async] 异步任务队列已满，任务未能正常运行！");
            }
        });
        threadPool.initialize();
        return threadPool;
    }
}
