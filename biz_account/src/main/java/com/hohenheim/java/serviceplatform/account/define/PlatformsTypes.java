package com.hohenheim.java.serviceplatform.account.define;

/**
 * @author Hohenheim
 * @date 2022/10/2
 * @description 第三方平台类型
 */
public enum PlatformsTypes {
    /** 微信 */
    WX(1, "微信小程序"),
    /** QQ */
    QQ(2, "QQ"),
    /** 新浪微博 */
    WEIBO(3, "新浪微博"),
    ;

    private int type;
    private String desc;
    PlatformsTypes(int type, String desc) {
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
