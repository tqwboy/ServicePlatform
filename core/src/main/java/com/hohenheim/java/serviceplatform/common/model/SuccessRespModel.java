package com.hohenheim.java.serviceplatform.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Hohenheim
 * @date 2020/2/6
 * @description
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SuccessRespModel<T> extends BaseRespModel<T> {
}