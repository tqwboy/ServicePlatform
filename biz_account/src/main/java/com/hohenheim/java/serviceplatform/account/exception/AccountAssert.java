package com.hohenheim.java.serviceplatform.account.exception;

import cn.hutool.core.lang.Validator;
import com.hohenheim.java.serviceplatform.account.define.AccountResultCodes;
import com.hohenheim.java.serviceplatform.core.exception.BizAssert;

/**
 * @author Hohenheim
 * @date 2022/7/10
 * @description 账号模块断言
 */
public final class AccountAssert {
    private AccountAssert() {

    }

    /**
     * 判断内容是否是Email
     * @param content 要判断的内容
     */
    public static void isEmail(String content) {
        BizAssert.isTrue(Validator.isEmail(content), AccountResultCodes.EMAIL_FORMAT_ERROR, AccountException.class);
    }
}