package com.hohenheim.java.serviceplatform.account.core.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Hohenheim
 * @date 2022/7/31
 * @description 获取用户信息
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface GetUserInfo {
    /**
     * 检查用户是否存在
     * true：默认值，如果用户信息不存在，抛出异常
     * false：不检查用户信息是否存在，如果用户信息不存在，返回null
     */
    boolean userExist() default true;
}