package com.hohenheim.java.serviceplatform.common.web.converter;

import cn.hutool.core.collection.ListUtil;
import com.hohenheim.java.serviceplatform.common.anno.JsonConverterExec;
import com.hohenheim.java.serviceplatform.common.define.CommonResultCodes;
import com.hohenheim.java.serviceplatform.common.web.ResponsePack;
import com.hohenheim.java.serviceplatform.common.model.BaseRespModel;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

/**
 * @author Hohenheim
 * @date 2020/7/11
 * @description
 */
public class JsonConverter extends MappingJackson2HttpMessageConverter {
    /**
     * 需要添加元数据的包
     */
    private final List<String> mToWrapPackages = ListUtil.of("java.util",
            "java.lang",
            "com.github.pagehelper",
            "com.baomidou.mybatisplus.extension.plugins.pagination",
            "com.hohenheim.java.serviceplatform");

    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        // 判断是否需要再包一层
        boolean toWrap = false;
        for (String mPackage : mToWrapPackages) {
            if (objInPackage(object, mPackage)) {
                toWrap = true;
            }
        }
        if (!toWrap) {
            super.writeInternal(object, type, outputMessage);
            return;
        }


        Object respData = null;
        JsonConverterExec jsonConverter = object.getClass().getAnnotation(JsonConverterExec.class);
        if (null != jsonConverter) {
            if (!jsonConverter.packing()) {
                respData = object;
            }
            else if (jsonConverter.successPacking()) {
                respData = ResponsePack.reqSuccess(object);
            }
            else {
                BaseRespModel<Object> respModel = ResponsePack.reqFail(CommonResultCodes.REQ_OPT_FAILURE);
                respModel.setData(object);
                respData = respModel;
            }
        }
        else if(object instanceof Boolean value) {
            if(value) {
                respData = ResponsePack.reqSuccess();
            }
            else {
                respData = ResponsePack.reqFail(CommonResultCodes.REQ_OPT_FAILURE);
            }
        }
        else {
            respData = ResponsePack.reqSuccess(object);
        }

        super.writeInternal(respData, type, outputMessage);
    }

    /** 判断对象是否来自某个包 */
    private boolean objInPackage(Object object, String packageToCheck) {
        if (object instanceof Collection<?>) {
            if (((Collection<?>) object).isEmpty()) {
                return true;
            }
            for (Object elem : (Collection<?>) object) {
                String currPackage = elem.getClass().getPackage().getName();
                if (currPackage.contains(packageToCheck)) {
                    return true;
                }
            }
        }
        else { // 单个对象
            String currPackage = object.getClass().getPackage().getName();
            return currPackage.contains(packageToCheck);
        }
        return false;
    }
}
