package com.hohenheim.java.serviceplatform.account.web.advice;

import cn.dev33.satoken.exception.NotLoginException;
import com.hohenheim.java.serviceplatform.account.define.AccountResultCodes;
import com.hohenheim.java.serviceplatform.account.exception.AccountException;
import com.hohenheim.java.serviceplatform.core.model.BaseRespModel;
import com.hohenheim.java.serviceplatform.core.web.ResponsePack;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hohenheim
 * @date 2022/7/24
 * @description 用户模块统一错误处理中心
 */
@RestControllerAdvice(basePackages = "com.hohenheim.java.serviceplatform.account")
@Order(value = 1)
@Slf4j
public class AccountAdvice {
    @ExceptionHandler(value = AccountException.class)
    public BaseRespModel<Object> apiReqException(AccountException exception) {
        if (exception.isLogging()) {
            log.error("[Account] 用户模块API错误", exception);
        }
        return ResponsePack.reqFail(exception.getErrorCode(), exception.getMessage());
    }

    /**
     * 未登录错误
     */
    @ExceptionHandler(value = NotLoginException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public BaseRespModel<Object> unauthenticationException(NotLoginException ex) {
        log.error("[Account] 登录异常：" + ex.getMessage(), ex);

        if(NotLoginException.TOKEN_TIMEOUT.equals(ex.getType())) {
            return ResponsePack.reqFail(AccountResultCodes.TOKEN_EXPIRED);
        }
        else if(NotLoginException.INVALID_TOKEN_MESSAGE.equals(ex.getType())) {
            return ResponsePack.reqFail(AccountResultCodes.TOKEN_SIGN_EXCEPTION);
        }
        else if(NotLoginException.BE_REPLACED.equals(ex.getType())) {
            return ResponsePack.reqFail(AccountResultCodes.TOKEN_REPLACE);
        }
        else if(NotLoginException.KICK_OUT.equals(ex.getType())) {
            return ResponsePack.reqFail(AccountResultCodes.TOKEN_KICK_OUT);
        }
        else {
            return ResponsePack.reqFail(AccountResultCodes.NOT_LOGIN);
        }
    }
}
