package com.hohenheim.java.serviceplatform.core.exception;

import com.hohenheim.java.serviceplatform.core.define.IResultCode;

/**
 * @author Hohenheim
 * @date 2022/7/10
 * @description 平台异常
 */
public class ServicePlatformException extends RuntimeException {
    private String errorCode;

    /**
     * @param resultCode 错误信息
     */
    public ServicePlatformException(IResultCode resultCode) {
        this(resultCode.getCode(), resultCode.getMsg());
    }

    /**
     * @param errCode 错误code
     * @param msg     错误消息
     */
    public ServicePlatformException(String errCode, String msg) {
        super(msg);
        this.errorCode = errCode;
    }

    /**
     * 获取错误码
     * @return error code
     */
    public String getErrorCode() {
        return errorCode;
    }
}