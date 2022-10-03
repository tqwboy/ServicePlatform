package com.hohenheim.java.serviceplatform.account.model.web.params;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Hohenheim
 * @date 2022/9/25
 * @description 第三方平台授权登录参数
 */
@Data
public class PlatformsLoginParams {
    /** 平台名称 */
    @NotBlank(message = "平台名称不能为空")
    private String platformName;

    /** 平台授权码 */
    @NotBlank(message = "授权码错误")
    private String authorCode;
}