package com.hohenheim.java.serviceplatform.account.core;

import com.hohenheim.java.serviceplatform.account.exception.AccountException;
import com.hohenheim.java.serviceplatform.core.model.BaseRespModel;
import com.hohenheim.java.serviceplatform.core.web.ResponsePack;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Hohenheim
 * @date 2022/7/24
 * @description 用户模块统一错误处理中心
 */
@ControllerAdvice(basePackages = "com.hohenheim.java.serviceplatform.account.*")
@Slf4j
public class AccountAdvice {
    @ExceptionHandler(value = AccountException.class)
    @ResponseBody
    public BaseRespModel<Object> apiReqException(AccountException exception) {
        log.error("[Account] 用户模块API错误", exception);
        return ResponsePack.reqFail(exception.getErrorCode(), exception.getMessage());
    }
}
