package com.hohenheim.java.serviceplatform.account.define;

import com.hohenheim.java.serviceplatform.core.define.CoreResultCodes;
import com.hohenheim.java.serviceplatform.core.define.IResultCode;

/**
 * @author Hohenheim
 * @date 2022/7/16
 * @description
 */
public enum AccountStatus {
    // 正常
    NOT_VERIFY(0, "未验证"),
    // 已删除
    ACTIVE(1, "正常"),
    // 已锁定
    LOCKED(2, "锁定"),

    ;

    private int code;
    private String statusName;

    AccountStatus(int code, String statusName) {
        this.code = code;
        this.statusName = statusName;
    }

    public int getCode() {
        return code;
    }

    public String getStatusName() {
        return statusName;
    }

    public static AccountStatus getStatusByCode(int statusCode) {
        for (AccountStatus status : AccountStatus.values()) {
            if (status.getCode() == statusCode) {
                return status;
            }
        }
        return null;
    }

    /**
     * 根据状态，获取错误码
     * @param statusCode 状态码
     */
    public static IResultCode getErrorCodeByStatus(int statusCode) {
        if (statusCode == NOT_VERIFY.getCode()) {
            return AccountResultCodes.ACCOUNT_NOT_VERIFY;
        }
        else if (statusCode == LOCKED.getCode()) {
            return AccountResultCodes.ACCOUNT_LOCKED;
        }
        else {
            return CoreResultCodes.CORE_INVALID_PARAMS;
        }
    }
}