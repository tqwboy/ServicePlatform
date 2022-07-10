package com.hohenheim.java.serviceplatform.core.model;

import lombok.Data;

/**
 * @author Hohenheim
 * @date 2018/4/27
 * @description 响应数据统一封装
 */
@Data
public class BaseRespModel<T> {
    private boolean reqSuccess;

    private String resultCode;

    private String msg;

    private T data;
}