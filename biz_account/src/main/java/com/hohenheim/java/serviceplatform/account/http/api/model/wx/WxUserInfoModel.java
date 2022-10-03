package com.hohenheim.java.serviceplatform.account.http.api.model.wx;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Hohenheim
 * @date 2020/11/5
 * @description 微信用户信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxUserInfoModel extends WxRespBaseModel {
    @JsonProperty("openid")
    private String openId;

    @JsonProperty("nickname")
    private String nickName;

    /** 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知 */
    private String gender;

    private String province;

    private String city;

    /** 国家，如中国为CN */
    private String country;

    /** 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。 */
    @JsonProperty("headimgurl")
    private String headImgUrl;

    /** 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom） */
    private String[] privilege;

    @JsonProperty("unionid")
    private String unionId;

    private String accessToken;

    private String refreshToken;
}