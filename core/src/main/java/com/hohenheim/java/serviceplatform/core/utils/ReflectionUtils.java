package com.hohenheim.java.serviceplatform.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Hohenheim
 * @date 2020/2/8
 * @description
 */
public class ReflectionUtils {
    /**
     * 	根据字段名称获取对象的属性值
     * @param fieldName 字段名称
     * @param type 字段类型
     * @param target  对象
     * @return
     */
    public static Object getFieldValueByName(String fieldName, Type type, Object target) {
        String firstLetter = fieldName.substring(0, 1).toUpperCase();
        String getter;
        if(Boolean.class.getTypeName().equals(type.getTypeName())) {
            getter = "is" + firstLetter + fieldName.substring(1);
        }
        else {
            getter = "get" + firstLetter + fieldName.substring(1);
        }

        Object value = null;
        try {
            Method method = target.getClass().getMethod(getter);
            value = method.invoke(target);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return value;
    }

    /**
     * 	获取所有字段名字
     * @param target 对象
     * @return
     */
    public static String[] getFiledName(Object target) {
        Field[] fields = target.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; ++i) {
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    /**
     * 获取对象所有值和属性名称
     */
    public static Map<String, Object> getFieldNameAndValue(Object target) {
        Field[] fields = target.getClass().getDeclaredFields();
        Field[] supperFields = target.getClass().getSuperclass().getDeclaredFields();
        int fieldsLength = fields.length;
        int supperFieldsLength = supperFields.length;
        fields = Arrays.copyOf(fields, fieldsLength + supperFieldsLength);
        System.arraycopy(supperFields, 0, fields, fieldsLength, supperFieldsLength);

        Map<String, Object> resultMap = new HashMap<>(fields.length);

        for (Field field : fields) {
            String fieldName = field.getName();
            Type type = field.getType();
            Object value = getFieldValueByName(fieldName, type, target);

            resultMap.put(fieldName, value);
        }

        return resultMap;
    }
}