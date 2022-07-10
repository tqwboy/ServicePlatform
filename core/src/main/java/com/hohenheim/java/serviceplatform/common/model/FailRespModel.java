package com.hohenheim.java.serviceplatform.common.model;

import com.hohenheim.java.serviceplatform.common.anno.JsonConverterExec;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Hohenheim
 * @date 2020/2/6
 * @description
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonConverterExec(packing = false, successPacking = false)
public class FailRespModel<T> extends BaseRespModel<T> {
}