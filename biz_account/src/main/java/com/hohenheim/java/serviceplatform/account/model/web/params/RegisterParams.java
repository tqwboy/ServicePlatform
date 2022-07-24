package com.hohenheim.java.serviceplatform.account.model.web.params;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Hohenheim
 * @date 2022/7/3
 * @description 注册参数
 */
@Data
public class RegisterParams {
    @NotBlank(message = "请输入邮箱地址")
    private String account;

    @NotBlank(message = "请输入密码")
    private String pwd;
}
