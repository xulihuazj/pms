package com.xulihuazj.pms.utils.help;


import com.xulihuazj.pms.enums.BaseEnum;
import com.xulihuazj.pms.utils.log.LogHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 此帮助类严格限定为有code和desc的枚举类，如定义枚举类型为 ADMIN(code,desc)这种
 */
public class EnumHelperUtil {

    private static Logger logger = LogManager.getLogger(EnumHelperUtil.class);

    /**
     * indexOf,传入的参数ordinal指的是需要的枚举值在设定的枚举类中的顺序，以0开始
     * T
     */
    public static <T extends Enum<T>> T indexOf(Class<T> clazz, int ordinal) {
        return (T) clazz.getEnumConstants()[ordinal];
    }

    /**
     * nameOf,传入的参数name指的是枚举值的名称，一般是大写
     * T
     */
    public static <T extends Enum<T>> T nameOf(Class<T> clazz, String name) {
        return (T) Enum.valueOf(clazz, name);
    }

    /**
     * 使用枚举类型对应的desc，获取枚举类型
     * T
     *
     * @param clazz             枚举类的class
     * @param getDescMethodName 传入的enum的get方法
     * @param desc              传入的desc值
     */
    public static <T extends Enum<T>> T getEnumByDesc(Class<T> clazz, String getDescMethodName, String desc) {
        if (StringUtils.isBlank(desc) || StringUtils.isBlank(getDescMethodName)) return null;
        T result = null;
        try {
            T[] arr = clazz.getEnumConstants();
            Method targetMethod = clazz.getDeclaredMethod(getDescMethodName);
            Integer typeCodeVal;
            for (T entity : arr) {
                typeCodeVal = Integer.valueOf(targetMethod.invoke(entity).toString());
                if (typeCodeVal.equals(desc)) {
                    result = entity;
                    break;
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            LogHelper.exception(e, logger, "枚举类型转换发生异常，{0}", "EnumHelperUtil.getEnumByDesc");
        }
        return result;
    }

    /**
     * 使用枚举类型对应的code获取枚举类型
     * T
     *
     * @param clazz             枚举类的class
     * @param getCodeMethodName 传入的enum的get方法
     * @param code              传入的code值，这个方法为String类型
     */
    public static <T extends Enum<T>> T getEnumByCode(Class<T> clazz, String getCodeMethodName, String code) {
        if (StringUtils.isBlank(code) || StringUtils.isBlank(getCodeMethodName)) return null;
        T result = null;
        try {
            T[] arr = clazz.getEnumConstants();
            Method targetMethod = clazz.getDeclaredMethod(getCodeMethodName);
            String typeCodeVal;
            for (T entity : arr) {
                typeCodeVal = targetMethod.invoke(entity).toString();
                if (typeCodeVal.contentEquals(code)) {
                    result = entity;
                    break;
                }
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            LogHelper.exception(e, logger, "枚举类型转换发生异常，{0}", "EnumHelperUtil.getEnumByCode");
        }
        return result;
    }

    /**
     * 此方法，枚举必须继承BaseEnum
     */
    public static <T extends BaseEnum> T getEnumByCode(Class<T> clazz, String code) {
        if (StringUtils.isBlank(code)) return null;
        T result = null;
        try {
            T[] arr = clazz.getEnumConstants();
            String typeCodeVal;
            for (T entity : arr) {
                typeCodeVal = entity.getCode();
                if (typeCodeVal.contentEquals(code)) {
                    result = entity;
                    break;
                }
            }
        } catch (SecurityException e) {
            LogHelper.exception(e, logger, "枚举类型转换发生异常，{0}", "EnumHelperUtil.getEnumByCode");

        }
        return result;
    }
}