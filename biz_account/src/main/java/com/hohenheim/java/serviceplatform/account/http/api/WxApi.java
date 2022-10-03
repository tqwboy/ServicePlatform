package com.hohenheim.java.serviceplatform.account.http.api;

import com.github.lianjiatech.retrofit.spring.boot.core.RetrofitClient;
import com.github.lianjiatech.retrofit.spring.boot.retry.Retry;
import com.hohenheim.java.serviceplatform.account.http.api.model.wx.WxAccessToken;
import com.hohenheim.java.serviceplatform.account.http.api.model.wx.WxAppletSessionRespModel;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.concurrent.CompletableFuture;

/**
 * @author Hohenheim
 * @date 2020/8/10
 * @description 微信平台API
 */
@RetrofitClient(baseUrl = "${platforms.wx.baseUrl}", sourceOkHttpClient = "platformsHttpClient")
public interface WxApi {
    /**
     * 获取微信小程序 session
     * @param appId 微信开放平台ID
     * @param secret 微信开放平台秘钥
     * @param code 用户授权码
     * @return 请求结果数据实体
     */
    @GET("/sns/jscode2session?grantType=authorization_code")
    @Retry(intervalMs = 250)
    CompletableFuture<WxAppletSessionRespModel> getWeChatAppletSession(@Query("appid") String appId,
                                                                       @Query("secret") String secret,
                                                                       @Query("js_code") String code);

    /**
     * 通过code换取网页授权access_token
     * @param appId 小程序唯一凭证，即 AppID
     * @param secret 小程序唯一凭证密钥，即 AppSecret
     * @return 含有AccessToken等一系列请求数据的实体
     */
    @GET("/cgi-bin/token?grant_type= client_credential")
    WxAccessToken wxSubscriptionRefreshToken(@Query("appid") String appId, @Query("secret") String secret);
}