package com.hohenheim.java.serviceplatform.account.define;

/**
 * @author Hohenheim
 * @date 2022/7/16
 * @description 登录类型，用于记录用户最近一次的登录方式
 */
public enum LoginType {
    LOGIN_TYPE_ACCOUNT(0, "账号登录"),
    LOGIN_TYPE_WECHAT(1, "微信登录"),
    LOGIN_TYPE_QQ(2, "QQ登录");

    private int type;
    private String desc;

    LoginType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}