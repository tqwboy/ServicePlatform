package com.hohenheim.java.serviceplatform.account.http.api.model.wx;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Hohenheim
 * @date 2019/4/7
 * @description 微信接口响应基础数据
 */
@Data
public class WxRespBaseModel {
    /**
     * errcode : 40029
     * errmsg : invalid code
     */
    @JsonProperty("errcode")
    private int errCode;

    @JsonProperty("errmsg")
    private String errMsg;
}