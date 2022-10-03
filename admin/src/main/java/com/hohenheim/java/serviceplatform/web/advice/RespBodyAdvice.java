package com.hohenheim.java.serviceplatform.web.advice;

import com.hohenheim.java.serviceplatform.core.anno.JsonConverterExec;
import com.hohenheim.java.serviceplatform.core.define.CoreResultCodes;
import com.hohenheim.java.serviceplatform.core.model.BaseRespModel;
import com.hohenheim.java.serviceplatform.core.web.ResponsePack;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author Hohenheim
 * @date 2022/9/24
 * @description 响应数据包装器
 */
@ControllerAdvice(basePackages = "com.hohenheim.java.serviceplatform.account.web.controller")
public class RespBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        if(methodParameter.getParameterType().isAssignableFrom(BaseRespModel.class)) {
            return false;
        }

        JsonConverterExec jsonConverterExec = methodParameter.getMethodAnnotation(JsonConverterExec.class);
        return null == jsonConverterExec || jsonConverterExec.packing();
    }

    @Override
    public Object beforeBodyWrite(Object body, @NotNull MethodParameter returnType, MediaType mediaType,
                                  @NotNull Class<? extends HttpMessageConverter<?>> cnverterType,
                                  @NotNull ServerHttpRequest request, @NotNull ServerHttpResponse response) {

        Object respData = body;

        if(mediaType.equals(MediaType.APPLICATION_JSON)) {
            JsonConverterExec jsonConverterExec = returnType.getMethodAnnotation(JsonConverterExec.class);
            if(null != jsonConverterExec) {
                if(jsonConverterExec.successPacking()) {
                    respData = ResponsePack.reqSuccess(body);
                }
                else  {
                    respData = ResponsePack.reqFail(CoreResultCodes.CORE_SYSTEM_ERROR, body);
                }
            }
            else {
                respData = ResponsePack.reqSuccess(body);
            }
        }

        return respData;
    }
}
