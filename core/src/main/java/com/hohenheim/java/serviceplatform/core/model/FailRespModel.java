package com.hohenheim.java.serviceplatform.core.model;

import com.hohenheim.java.serviceplatform.core.anno.JsonConverterExec;
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