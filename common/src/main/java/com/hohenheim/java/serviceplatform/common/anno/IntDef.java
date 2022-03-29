package com.hohenheim.java.serviceplatform.common.anno;

/**
 * @author Hohenheim
 * @date 2018/4/27
 * @description
 */
public @interface IntDef {
    /** Defines the allowed constants for this element */
    long[] value() default {};

    /** Defines whether the constants can be used as a flag, or just as an enum (the default) */
    boolean flag() default false;
}