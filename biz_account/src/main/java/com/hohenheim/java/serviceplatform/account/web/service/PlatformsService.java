package com.hohenheim.java.serviceplatform.account.web.service;

import com.hohenheim.java.serviceplatform.account.define.AccountResultCodes;
import com.hohenheim.java.serviceplatform.account.exception.AccountException;
import com.hohenheim.java.serviceplatform.account.model.web.params.PlatformsLoginParams;
import com.hohenheim.java.serviceplatform.account.model.web.resp.LoginResp;
import com.hohenheim.java.serviceplatform.account.web.service.login.IPlatformsLogin;
import com.hohenheim.java.serviceplatform.core.exception.BizAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Hohenheim
 * @date 2022/10/3
 * @description 第三方平台服务
 */
@Service
public class PlatformsService {
    @Autowired
    private Map<String, IPlatformsLogin> mPlatformsLoginMap;

    public LoginResp login(PlatformsLoginParams params) {
        IPlatformsLogin platformsLogin = mPlatformsLoginMap.get(params.getPlatformName() + "Login");
        BizAssert.notNull(platformsLogin, AccountResultCodes.NO_THIRD_PLATFORM, AccountException.class);

        return platformsLogin.login(params);
    }
}