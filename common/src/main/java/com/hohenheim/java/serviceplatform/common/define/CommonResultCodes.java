package com.hohenheim.java.serviceplatform.common.define;

/**
 * @author Hohenheim
 * @date 2022/3/27
 * @description 通用结果码
 */
public enum CommonResultCodes implements IResultCode {
    /* 通用错误 */
    RETURN_SUCCESS(0, "执行成功"),
    COMMON_SYSTEM_ERROR(1, "服务错误"),
    COMMON_INVALID_PARAMS(2, "参数错误"),
    REQ_OPT_FAILURE(3, "请求处理失败"),
    NOT_LOGGED_ON(4, "没有登录"),
    UNAUTHORIZED(5, "没有授权"),
    ILLEGAL_OPS(6, "非法操作"),
    ;

    private int code;
    private String msg;

    CommonResultCodes(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public Integer getCode() {
        return this.code;
    }
}