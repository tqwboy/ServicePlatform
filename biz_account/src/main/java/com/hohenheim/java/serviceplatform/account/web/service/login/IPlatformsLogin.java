package com.hohenheim.java.serviceplatform.account.web.service.login;

import com.hohenheim.java.serviceplatform.account.model.web.params.PlatformsLoginParams;
import com.hohenheim.java.serviceplatform.account.model.web.resp.LoginResp;

/**
 * @author Hohenheim
 * @date 2022/9/25
 * @description 第三方平台登录服务接口
 */
public interface IPlatformsLogin {
    /**
     * 登录
     * @param params 第三方平台登录所需参数
     * @return 登录结果
     */
    LoginResp login(PlatformsLoginParams params);
}
