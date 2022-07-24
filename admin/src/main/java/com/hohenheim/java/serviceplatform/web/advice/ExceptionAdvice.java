package com.hohenheim.java.serviceplatform.web.advice;

import com.hohenheim.java.serviceplatform.core.define.CoreResultCodes;
import com.hohenheim.java.serviceplatform.core.exception.ServicePlatformException;
import com.hohenheim.java.serviceplatform.core.model.BaseRespModel;
import com.hohenheim.java.serviceplatform.core.web.ResponsePack;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.BeansException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Hohenheim
 * @date 2022/7/24
 * @description 统一错误处理中心
 */
@ControllerAdvice(basePackages = "com.hohenheim.java.serviceplatform.*")
@Slf4j
public class ExceptionAdvice {
    @ExceptionHandler(value = ServicePlatformException.class)
    @ResponseBody
    public BaseRespModel<Object> apiReqException(ServicePlatformException exception) {
        log.error("[ServicePlatform] API错误", exception);
        return ResponsePack.reqFail(exception.getErrorCode(), exception.getMessage());
    }

    @ExceptionHandler(value = {PersistenceException.class, SQLException.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public BaseRespModel<Object> sqlException(Exception exception) {
        log.error("[ServicePlatform] 数据库错误：", exception);
        return ResponsePack.reqFail(CoreResultCodes.CORE_SYSTEM_ERROR);
    }

    @ExceptionHandler(value = {IOException.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public BaseRespModel<Object> apiReqException(Exception exception) {
        log.error("[ServicePlatform] IO错误：", exception);
        return ResponsePack.reqFail(CoreResultCodes.CORE_SYSTEM_ERROR);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(code= HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseRespModel<Object> validException(Exception e) {
        BindingResult result;
        if(e instanceof MethodArgumentNotValidException exception) {
            result = exception.getBindingResult();
        }
        else {
            BindException exception = (BindException) e;
            result = exception.getBindingResult();
        }

        List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder builder = new StringBuilder();
        for (FieldError error : fieldErrors) {
            builder.append(error.getDefaultMessage()).append("|");
        }
        builder.deleteCharAt(builder.length() - 1);
        String errMsg = builder.toString();

        return ResponsePack.reqFail(CoreResultCodes.CORE_INVALID_PARAMS.getCode(), errMsg);
    }

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public BaseRespModel<Object> nullPointerException(NullPointerException ex) {
        log.error("[ServicePlatform] 空指针异常：" + ex.getMessage(), ex);
        return ResponsePack.reqFail(CoreResultCodes.CORE_SYSTEM_ERROR);
    }

    @ExceptionHandler(value = BeansException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public BaseRespModel<Object> beansException(BeansException ex) {
        log.error("[ServicePlatform] 数据复制异常：" + ex.getMessage(), ex);
        return ResponsePack.reqFail(CoreResultCodes.CORE_SYSTEM_ERROR);
    }
}
