package com.hohenheim.java.serviceplatform.core.utils;

import cn.hutool.core.bean.BeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Hohenheim
 * @date 2020/1/9
 * @description 数据模型操作工具类
 */
public class ModelUtils {
    /**
     * 批量复制实体对象的属性
     * @param tagClass 需要新创建的实体
     * @param sourceList 数据来源实体列表
     * @param opt 实体创建回调
     *
     * @return 新数据实体对象列表
     */
    public static <T, K> List<T> createModelListByEntity(Class<T> tagClass, List<K> sourceList, CreateModelOpt<T, K> opt) {
        List<T> modelList = new ArrayList<>();
        for(K sourceObj : sourceList) {
            T model = BeanUtil.copyProperties(sourceObj, tagClass);
            Optional.ofNullable(model).ifPresent(t -> {
                modelList.add(t);
                if(null != opt) {
                    opt.createdModel(t, sourceObj);
                }
            });
        }

        return modelList;
    }

    public interface CreateModelOpt<T, K> {
        /**
         * 返回新创建的实体对象
         * @param model 新创建的实体
         * @param source 数据源
         */
        void createdModel(T model, K source);
    }
}