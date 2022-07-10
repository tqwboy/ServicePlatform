package com.hohenheim.java.serviceplatform.core.utils;

import cn.hutool.core.text.CharSequenceUtil;
import org.aspectj.lang.reflect.CodeSignature;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Hohenheim
 * @date 2021/6/25
 * @description 切面工具类
 */
public class AopUtils {
    private AopUtils() { }

    /**
     * 根据参数名称，获取参数的值
     * @param args 方法参数
     * @param signature 被增强的方法相关信息
     * @param paramPath 参数路径，格式：参数名.属性名1.属性名2 ……
     * @return 参数值
     */
    public static Object getValueByName(Object[] args, CodeSignature signature, String paramPath) {
        if (CharSequenceUtil.isBlank(paramPath)) {
            return null;
        }

        String[] keys = paramPath.split("\\.");

        // 获取Controller方法上的参数名称
        String[] paramNames = signature.getParameterNames();
        Object paramArg = null;
        for (int i=0; i<paramNames.length; ++i) {
            String name = paramNames[i];
            if (name.equals(keys[0])) {
                paramArg = args[i];
                break;
            }
        }

        Object paramValue = null;
        if (keys.length == 1) {
            paramValue = paramArg;
        }
        else if(null != paramArg) {
            paramValue = getParamValue(paramArg, keys, 1);
        }

        return paramValue;
    }

    /**
     * 递归方式，获取
     */
    private static Object getParamValue(Object param, String[] keys, int keyIndex) {
        Optional<Field> fieldOptional = getAllFields(new ArrayList<>(), param.getClass())
                .stream()
                .filter(field -> field.getName().equals(keys[keyIndex]))
                .findFirst();

        if(fieldOptional.isEmpty()) {
            return null;
        }


        Field field = fieldOptional.get();
//        field.setAccessible(true);
        Object fieldValue = null;
        try {
            fieldValue = field.get(param);
        }
        catch (IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        if(null == fieldValue || keyIndex + 1 == keys.length) {
            return fieldValue;
        }
        else {
            return getParamValue(fieldValue, keys, keyIndex + 1);
        }
    }

    /**
     * 获取所有Field
     */
    public static List<Field> getAllFields(List<Field> fieldList, Class<?> type) {
        // 获取当前类的所有Field
        fieldList.addAll(Arrays.asList(type.getDeclaredFields()));

        // 获取父类的所有Field
        Class<?> superClass = type.getSuperclass();
        if(superClass != null && superClass != Object.class) {
            getAllFields(fieldList, superClass);
        }

        return fieldList;
    }
}