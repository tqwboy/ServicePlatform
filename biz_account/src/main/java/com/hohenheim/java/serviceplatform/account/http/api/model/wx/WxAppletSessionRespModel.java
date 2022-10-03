package com.hohenheim.java.serviceplatform.account.http.api.model.wx;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Hohenheim
 * @date 2020/8/11
 * @description 微信小程序获取 session
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxAppletSessionRespModel extends WxRespBaseModel {
    /** 用户唯一标识 */
    @JsonProperty("openid")
    private String openId;

    /** 会话密钥 */
    @JsonProperty("session_key")
    private String sessionKey;

    /** 用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。 */
    @JsonProperty("unionid")
    private String unionId;
}