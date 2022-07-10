package com.hohenheim.java.serviceplatform.account.exception;

import com.hohenheim.java.serviceplatform.core.define.IResultCode;
import com.hohenheim.java.serviceplatform.core.exception.ServicePlatformException;

/**
 * @author Hohenheim
 * @date 2022/7/10
 * @description 账号业务错误类
 */
public class AccountException extends ServicePlatformException {
    public AccountException(IResultCode resultCode) {
        super(resultCode);
    }

    public AccountException(String errCode, String msg) {
        super(errCode, msg);
    }
}
