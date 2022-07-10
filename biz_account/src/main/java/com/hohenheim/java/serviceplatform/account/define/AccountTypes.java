package com.hohenheim.java.serviceplatform.account.define;

import cn.hutool.core.lang.Validator;

/**
 * @author Hohenheim
 * @date 2021/1/31
 * @description 账号类型
 */
public enum AccountTypes {
    //普通用户名
    USER_NAME(1, "user_name"),
    //邮箱
    EMAIL(2, "email"),
    //手机号码
    MOBILE(3, "mobile")
    ;

    AccountTypes(int typeCode, String tabColumnName) {
        this.typeCode = typeCode;
        this.tabColumnName = tabColumnName;
    }

    private int typeCode;

    private String tabColumnName;

    public int getTypeCode() {
        return typeCode;
    }

    public String getTabColumnName() {
        return tabColumnName;
    }

    public static AccountTypes getTypeByAccount(String account) {
        if(Validator.isEmail(account)) {
            return EMAIL;
        }
        else if(Validator.isMobile(account)) {
            return MOBILE;
        }
        else {
            return USER_NAME;
        }
    }
}