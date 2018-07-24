/*
 * LongIntegerConvert.java 1.0.0 2018/03/12  11:55 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/03/12  11:55 created by xulihua
 */
package com.xulihuazj.pms.utils.convert;


import com.xulihuazj.pms.utils.json.JSONHelper;
import org.springframework.cglib.core.Converter;

import java.util.Map;

/**
 * 扩展BeanCopier
 *
 * @author 徐礼华
 * @date 2018/03/12 11:56
 */
public class BeanCopierConvertImpl implements Converter {

    @Override
    public Object convert(Object sourceObject, Class targetClass, Object context) {

        // 添加：Long与Integer 的互转
        if (sourceObject instanceof Integer && targetClass.getTypeName().equals("java.lang.Long")) {
            return ((Integer) sourceObject).longValue();
        } else if (sourceObject instanceof Long && targetClass.getTypeName().equals("java.lang.Integer")) {
            return ((Long) sourceObject).intValue();
        }

        // 添加：Long与String 的互转
        if (sourceObject instanceof Long && targetClass.getTypeName().equals("java.lang.String")) {
            return String.valueOf(sourceObject);
        } else if (sourceObject instanceof String && targetClass.getTypeName().equals("java.lang.Long")) {
            return Long.parseLong((String) sourceObject);
        }

        // 添加：Integer与String 的互转
        if (sourceObject instanceof Integer && targetClass.getTypeName().equals("java.lang.String")) {
            return String.valueOf(sourceObject);
        } else if (sourceObject instanceof String && targetClass.getTypeName().equals("java.lang.Integer")) {
            return Integer.parseInt((String) sourceObject);
        }

        // 添加：枚举与String 的互转
        if (sourceObject != null && sourceObject.getClass().isEnum() && targetClass.getTypeName().equals("java.lang.String")) {
            return ((Enum) sourceObject).name();
        } else if (sourceObject instanceof String && targetClass.isEnum()) {
            return Enum.valueOf(targetClass, sourceObject.toString());
        }

        // 添加：枚举名称相同，却不是同一个对象
        if (sourceObject != null && sourceObject.getClass().isEnum() && targetClass.isEnum()) {
            return Enum.valueOf(targetClass, ((Enum) sourceObject).name());
        }

        // 添加：Map 与 String 的转换
        if (sourceObject instanceof Map && targetClass.getTypeName().equals("java.lang.String")) {
            return JSONHelper.toJson(sourceObject);
        } else if (sourceObject instanceof String && targetClass.getTypeName().equals("java.util.Map")) {
            return JSONHelper.toObject((String) sourceObject, Map.class);
        }

        return sourceObject;
    }
}
