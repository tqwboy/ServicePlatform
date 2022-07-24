package com.hohenheim.java.serviceplatform.account.core.controller;

import com.hohenheim.java.serviceplatform.account.core.anno.GetLoginUserId;
import com.hohenheim.java.serviceplatform.account.core.service.UserManagerService;
import com.hohenheim.java.serviceplatform.account.db.entity.association.UserWithRoleEntity;
import com.hohenheim.java.serviceplatform.account.define.AccountResultCodes;
import com.hohenheim.java.serviceplatform.account.exception.AccountAssert;
import com.hohenheim.java.serviceplatform.account.exception.AccountException;
import com.hohenheim.java.serviceplatform.account.model.web.params.LoginParams;
import com.hohenheim.java.serviceplatform.account.model.web.params.RegisterParams;
import com.hohenheim.java.serviceplatform.account.core.service.AccountService;
import com.hohenheim.java.serviceplatform.account.model.web.resp.LoginResp;
import com.hohenheim.java.serviceplatform.core.exception.BizAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hohenheim
 * @date 2022/5/29
 * @description
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService mAccountService;
    @Autowired
    private UserManagerService mUserManagerService;

    /**
     * 用户名、邮箱注册接口
     * @param params 注册参数
     * @return 注册结果
     */
    @PostMapping("/register")
    public boolean register(@RequestBody RegisterParams params) {
        AccountAssert.isEmail(params.getAccount());  //当前只针对邮箱注册，后续可以扩展为用户名注册
        return null != mAccountService.register(params.getAccount(), params.getPwd());
    }

    /**
     * 用户账户激活
     * @param verifyCode 验证码
     * @param userId 用户ID
     */
    @GetMapping("/active")
    public Long active(@RequestParam String verifyCode, @GetLoginUserId Long userId) {
        UserWithRoleEntity userWithRole = mUserManagerService.getUserRoleInfo(userId);
        BizAssert.notNull(userWithRole, AccountResultCodes.USER_NOT_EXIST, AccountException.class);
        return mAccountService.verifyAccount(verifyCode, userWithRole);
    }

    /**
     * 用户登录接口
     */
    @PostMapping("/login")
    public LoginResp login(LoginParams params) {
        return mAccountService.login(params.getAccount(), params.getPassword());
    }
}