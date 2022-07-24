package com.hohenheim.java.serviceplatform.core.conf;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.hohenheim.java.serviceplatform.core.conf.serializer.NullArrayJsonSerializer;
import com.hohenheim.java.serviceplatform.core.conf.serializer.NullBooleanJsonSerializer;
import com.hohenheim.java.serviceplatform.core.conf.serializer.NullNumberJsonSerializer;
import com.hohenheim.java.serviceplatform.core.conf.serializer.NullStringJsonSerializer;

import java.util.Collection;
import java.util.List;

/**
 * @author Hohenheim
 * @date 2020/7/12
 * @description 空值修饰器
 */
public class JsonSerializerModifier extends BeanSerializerModifier {
    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        //循环所有的beanPropertyWriter
        for (BeanPropertyWriter beanProperty : beanProperties) {
            //判断字段的类型，如果是array，list，set则注册nullSerializer
            if (isArrayType(beanProperty)) {
                //给writer注册一个自己的nullSerializer
                beanProperty.assignNullSerializer(new NullArrayJsonSerializer());
            }
            else if (isNumberType(beanProperty)) {
                beanProperty.assignNullSerializer(new NullNumberJsonSerializer());
            }
            else if (isBooleanType(beanProperty)) {
                beanProperty.assignNullSerializer(new NullBooleanJsonSerializer());
            }
            else if (isStringType(beanProperty)) {
                beanProperty.assignNullSerializer(new NullStringJsonSerializer());
            }
        }

        return beanProperties;
    }

    /**
     * 是否是数组
     */
    private boolean isArrayType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return clazz.isArray() || Collection.class.isAssignableFrom(clazz);
    }

    /**
     * 是否是string
     */
    private boolean isStringType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return CharSequence.class.isAssignableFrom(clazz) || Character.class.isAssignableFrom(clazz);
    }


    /**
     * 是否是数字
     */
    private boolean isNumberType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return Number.class.isAssignableFrom(clazz);
    }

    /**
     * 是否是boolean
     */
    private boolean isBooleanType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return clazz.equals(Boolean.class);
    }
}
