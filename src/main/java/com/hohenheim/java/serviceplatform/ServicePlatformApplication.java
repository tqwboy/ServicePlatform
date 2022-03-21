package com.hohenheim.java.serviceplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "com.hohenheim.java.serviceplatform")
@Import(cn.hutool.extra.spring.SpringUtil.class)
public class ServicePlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicePlatformApplication.class, args);
    }

}
