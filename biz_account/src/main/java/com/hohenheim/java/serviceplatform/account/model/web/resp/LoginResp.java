package com.hohenheim.java.serviceplatform.account.model.web.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Hohenheim
 * @date 2022/7/16
 * @description 登录结果
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginResp extends UserInfoModel {
    private String accessToken;
}