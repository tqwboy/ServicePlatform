package com.hohenheim.java.serviceplatform.core.define;

/**
 * @author Hohenheim
 * @date 2022/3/27
 * @description 通用结果码
 */
public enum CoreResultCodes implements IResultCode {
    /* 通用错误 */
    RETURN_SUCCESS("core_0", "执行成功"),
    CORE_SYSTEM_ERROR("core_1", "服务错误"),
    CORE_INVALID_PARAMS("core_2", "参数错误"),
    REQ_OPT_FAILURE("core_3", "系统繁忙"),
    ILLEGAL_OPS("core_4", "非法操作"),
    ;

    private String code;
    private String msg;

    CoreResultCodes(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getCode() {
        return this.code;
    }
}