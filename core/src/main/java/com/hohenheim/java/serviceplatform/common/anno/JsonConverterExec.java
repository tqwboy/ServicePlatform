package com.hohenheim.java.serviceplatform.common.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Hohenheim
 * @date 2018/4/27
 * @description 加了该注解的实体类，在接收到数据或者发送数据的时候，将被解析为 JSON
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonConverterExec {
    boolean packing() default true;

    boolean successPacking() default true;
}