package com.hohenheim.java.serviceplatform.common.model;

import lombok.Data;

/**
 * @author Hohenheim
 * @date 2018/4/27
 * @description 响应数据统一封装
 */
@Data
public class BaseRespModel<T> {
    private boolean reqSuccess;

    private int resultCode;

    private String msg;

    private T data;
}