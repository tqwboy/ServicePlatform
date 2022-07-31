package com.hohenheim.java.serviceplatform.account.model.web.params;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Hohenheim
 * @date 2022/7/24
 * @description 用户账号密码登录请求参数
 */
@Data
public class LoginParams {
    @NotBlank(message = "账号不能为空")
    private String account;

    @NotBlank(message = "请输入密码")
    private String pwd;
}