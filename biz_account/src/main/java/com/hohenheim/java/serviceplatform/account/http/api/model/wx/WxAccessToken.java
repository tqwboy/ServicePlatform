package com.hohenheim.java.serviceplatform.account.http.api.model.wx;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Hohenheim
 * @date 2022/10/3
 * @description 小程序接口调用凭证响应数据
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxAccessToken extends WxRespBaseModel {
    /** 接口访问凭证 */
    @JsonProperty("access_token")
    private String accessToken;

    /** 凭证有效时间，单位：秒。 */
    @JsonProperty("expires_in")
    private Long expiresIn;
}