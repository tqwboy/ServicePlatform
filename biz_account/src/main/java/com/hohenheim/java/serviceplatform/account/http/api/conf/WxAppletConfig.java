package com.hohenheim.java.serviceplatform.account.http.api.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Hohenheim
 * @date 2022/9/25
 * @description 微信小程序配置
 */
@Component
@ConfigurationProperties("platforms.wx.applet")
@Data
public class WxAppletConfig {
    private String appId;

    private String secret;

    private String oauthUrl;

    private String callbackUrl;
}
