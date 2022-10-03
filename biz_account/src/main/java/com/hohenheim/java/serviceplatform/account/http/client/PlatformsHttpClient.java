package com.hohenheim.java.serviceplatform.account.http.client;

import com.github.lianjiatech.retrofit.spring.boot.core.SourceOkHttpClientRegistrar;
import com.github.lianjiatech.retrofit.spring.boot.core.SourceOkHttpClientRegistry;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author Hohenheim
 * @date 2022/9/25
 * @description 第三方平台请求
 */
@Slf4j
@Component
public class PlatformsHttpClient implements SourceOkHttpClientRegistrar {
    private OkHttpClient mHttpClient;

    @PostConstruct
    public void init() {
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        httpBuilder.connectionPool(new ConnectionPool(5, 3, TimeUnit.MINUTES))
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(12, TimeUnit.SECONDS)
                    .writeTimeout(12, TimeUnit.SECONDS);

        mHttpClient = httpBuilder.build();
    }

    @Override
    public void register(SourceOkHttpClientRegistry registry) {
        registry.register("platformsHttpClient", mHttpClient);
    }
}
