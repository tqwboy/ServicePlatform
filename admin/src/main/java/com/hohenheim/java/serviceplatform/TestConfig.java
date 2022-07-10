package com.hohenheim.java.serviceplatform;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Hohenheim
 * @date 2022/7/3
 * @description
 */
@Data
@Component
@ConfigurationProperties("custom")
public class TestConfig {
    private String content;
}
