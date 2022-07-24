package com.hohenheim.java.serviceplatform.account.aop.anno;

import java.lang.annotation.*;

/**
 * @author Hohenheim
 * @date 2022/7/10
 * @description 注册码缓存 KEY 拼接切面注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RegisterVerifyCodeCacheKeyAnno {
    /** 用作缓存 KEY 值的参数的名称 */
    String keyName() default "code";
}
