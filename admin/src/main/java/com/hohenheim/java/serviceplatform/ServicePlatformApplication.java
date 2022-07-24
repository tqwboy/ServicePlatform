package com.hohenheim.java.serviceplatform;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Hohenheim
 */
@SpringBootApplication(scanBasePackages = "com.hohenheim.java.serviceplatform")
@Import(cn.hutool.extra.spring.SpringUtil.class)
@MapperScan("com.hohenheim.java.serviceplatform.*.db.mapper")
@EnableTransactionManagement
@EnableMethodCache(basePackages = "com.hohenheim.java.serviceplatform.*.service")
@EnableCreateCacheAnnotation
public class ServicePlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicePlatformApplication.class, args);
    }
}