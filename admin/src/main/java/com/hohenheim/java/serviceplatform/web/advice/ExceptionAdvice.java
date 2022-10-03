package com.hohenheim.java.serviceplatform.web.advice;

import com.github.lianjiatech.retrofit.spring.boot.degrade.RetrofitBlockException;
import com.hohenheim.java.serviceplatform.core.define.CoreResultCodes;
import com.hohenheim.java.serviceplatform.core.exception.ServicePlatformException;
import com.hohenheim.java.serviceplatform.core.model.BaseRespModel;
import com.hohenheim.java.serviceplatform.core.web.ResponsePack;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.BeansException;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Hohenheim
 * @date 2022/7/24
 * @description 统一错误处理中心
 */
@RestControllerAdvice(basePackages = "com.hohenheim.java.serviceplatform")
@Order
@Slf4j
public class ExceptionAdvice {
    @ExceptionHandler(value = ServicePlatformException.class)
    public BaseRespModel<Object> apiReqException(ServicePlatformException exception) {
        log.error("[ServicePlatform] API错误", exception);
        return ResponsePack.reqFail(exception.getErrorCode(), exception.getMessage());
    }

    @ExceptionHandler(value = RetrofitBlockException.class)
    public BaseRespModel<Object> retrofitBlockException(RetrofitBlockException ex) {
        log.error("[ServicePlatform] HTTP Client异常：" + ex.getMessage(), ex);
        return ResponsePack.reqFail(CoreResultCodes.CORE_SYSTEM_ERROR);
    }

    @ExceptionHandler(value = {PersistenceException.class, SQLException.class})
    public BaseRespModel<Object> sqlException(Exception exception) {
        log.error("[ServicePlatform] 数据库错误：", exception);
        return ResponsePack.reqFail(CoreResultCodes.CORE_SYSTEM_ERROR);
    }

    @ExceptionHandler(value = {IOException.class})
    public BaseRespModel<Object> apiReqException(Exception exception) {
        log.error("[ServicePlatform] IO错误：", exception);
        return ResponsePack.reqFail(CoreResultCodes.CORE_SYSTEM_ERROR);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
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
    public BaseRespModel<Object> nullPointerException(NullPointerException ex) {
        log.error("[ServicePlatform] 空指针异常：" + ex.getMessage(), ex);
        return ResponsePack.reqFail(CoreResultCodes.CORE_SYSTEM_ERROR);
    }

    @ExceptionHandler(value = BeansException.class)
    public BaseRespModel<Object> beansException(BeansException ex) {
        log.error("[ServicePlatform] 数据复制异常：" + ex.getMessage(), ex);
        return ResponsePack.reqFail(CoreResultCodes.CORE_SYSTEM_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public BaseRespModel<Object> exception(Exception ex) {
        log.error("[ServicePlatform] 系统异常：" + ex.getMessage(), ex);
        return ResponsePack.reqFail(CoreResultCodes.CORE_SYSTEM_ERROR);
    }
}

