/*
 * CachedBeanCopier.java 1.0.0 2018/3/5  20:41
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/3/5  20:41 created by xulihua
 */
package com.xulihuazj.pms.utils.convert;

import com.xulihuazj.pms.utils.log.LogHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Object 拷贝
 *
 * @author xulihua
 * @description
 * @date 2018/3/5 20:41
 */
public class CachedBeanCopier {

    private static Logger logger = LogManager.getLogger(CachedBeanCopier.class);

    private static final Map<String, BeanCopier> BEAN_COPIERS = new HashMap<>();

    private static final Map<String, Converter> map = new HashMap<>();

    public static <SourceType, TargetType> TargetType copyConvert(SourceType sourceObject, Class targetClass) {
        if (sourceObject == null || targetClass == null) {
            return null;
        }
        String key = genKey(sourceObject.getClass(), targetClass);

        BeanCopier copier;
        Converter converter;
        if (!BEAN_COPIERS.containsKey(key)) {
            // 创建实例
            copier = BeanCopier.create(sourceObject.getClass(), targetClass, true);
            // 添加缓存
            BEAN_COPIERS.put(key, copier);
        } else {
            copier = BEAN_COPIERS.get(key);
        }
        if (null != map.get("bean_convert")) {
            converter = map.get("bean_convert");
        } else {
            converter = new BeanCopierConvertImpl();
            map.put("bean_convert", converter);
        }
        try {
            // 创建返回实例对象
            Object targetObject = targetClass.newInstance();
            // 浅层拷贝
            copier.copy(sourceObject, targetObject, converter);
            return (TargetType) targetObject;
        } catch (InstantiationException | IllegalAccessException e) {
            LogHelper.exception(e, logger, "对象类型转换异常，method={0}", "CachedBeanCopier.copyConvert()");
            throw new RuntimeException(e);
        }
    }

    /**
     * 对象类型转换
     *
     * @param originObjs      原始对象列表
     * @param targetTypeClass 模板对象类型
     * @param <OriginType>    原始类型
     */
    public static <OriginType, TargetType> List<TargetType> copyConvertList(List<OriginType> originObjs, Class targetTypeClass) {
        if (CollectionUtils.isEmpty(originObjs)) {
            return null;
        } else {
            List<TargetType> targetTypes = new ArrayList<>();
            for (OriginType originType : originObjs) {
                targetTypes.add(copyConvert(originType, targetTypeClass));
            }
            return targetTypes;
        }
    }

    /**
     * 获取缓存key
     */
    private static String genKey(Class<?> sourceClazz, Class<?> targetClazz) {
        return sourceClazz.getName() + targetClazz.getName();
    }

    /**
     * 本方法主要解决查询列表是要查询子关系时多次查询数据
     * 造成数据库的负担
     * master 代表主表
     * mearges 代表从表
     * <p>
     * masterIdName代表主表id
     * meargesIdName 代表从表中外键id名一般情况两个id名称相同
     */
    public static <TargetType, T> List<TargetType> meargeList(List<TargetType> masters, List<T> mearges, String masterIdName, String meargeIdName) {
        final String idMasterGetter = "get" + masterIdName.substring(0, 1).toUpperCase() + masterIdName.substring(1, masterIdName.length());
        final String idMeargeGetter = "get" + meargeIdName.substring(0, 1).toUpperCase() + meargeIdName.substring(1, meargeIdName.length());
        try {
            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(masters)) {
                for (TargetType master : masters) {
                    //获取id主键的getter方法
                    Method getID = master.getClass().getMethod(idMasterGetter, new Class[]{});
                    String masterId = getID.invoke(master, new Object[]{}).toString();
                    Class claMearges = null;
                    if (org.apache.commons.collections.CollectionUtils.isNotEmpty(mearges)) {
                        claMearges = mearges.get(0).getClass();
                    }
                    Class claz = master.getClass();
                    //获取主表里从表的属性名
                    String fieldName = null;
                    if (claMearges != null) {
                        Field[] fields = claz.getDeclaredFields();
                        for (Field field : fields) {
                            if (field.getGenericType().getTypeName().equals(claMearges.getTypeName())) {
                                fieldName = field.getName();
                                break;
                            }
                        }
                    }
                    //判断主表是否包含从表
                    if (claMearges != null) {
                        if (fieldName != null) {
                            //获取主表设置从表属性的方法
                            String setterFieldName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
                            Method setterFieldMethod = master.getClass().getMethod(setterFieldName, new Class[]{claMearges});

                            //根据id查找出相应的从表对象病设置主表中
                            for (T mearge : mearges) {
                                Method meargeIdGetter = mearge.getClass().getMethod(idMeargeGetter, new Class[]{});
                                String meargeId = meargeIdGetter.invoke(mearge, new Object[]{}).toString();
                                if (meargeId != null && meargeId.equals(masterId)) {
                                    setterFieldMethod.invoke(master, new Object[]{mearge});
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {

        }
        return masters;
    }
}
