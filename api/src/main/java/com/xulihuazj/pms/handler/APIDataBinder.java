package com.xulihuazj.pms.handler;

import com.xulihuazj.pms.enums.BaseEnum;
import com.xulihuazj.pms.utils.log.LogHelper;
import com.xulihuazj.pms.utils.string.StrUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestParameterPropertyValues;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 自定义数据绑定
 */
public class APIDataBinder extends ServletRequestDataBinder {

    private Logger logger = LogManager.getLogger(APIDataBinder.class);

    public APIDataBinder(Object target) {
        super(target);
    }

    @Override
    public void bind(ServletRequest request) {
        MutablePropertyValues originValues = new ServletRequestParameterPropertyValues(request);
        MutablePropertyValues camelPropertyValues = new ServletRequestParameterPropertyValues(request);
        // 将 原始 url 参数转换为 驼峰命名属性
        for (PropertyValue value : originValues.getPropertyValueList()) {
            // 值的名称
            String name = value.getName();
            String camelName = StrUtil.camelCaseName(name);
            //
            if (!camelName.equals(name)) {
                PropertyValue propValue = originValues.getPropertyValue(name);
                camelPropertyValues.removePropertyValue(name);
                camelPropertyValues.addPropertyValue(camelName, propValue.getValue());
            }
        }
        MultipartRequest multipartRequest = WebUtils.getNativeRequest(request, MultipartRequest.class);
        if (null != multipartRequest) {
            super.bindMultipart(multipartRequest.getMultiFileMap(), camelPropertyValues);
        }
        Object target = super.getTarget();
        Field[] fields = target.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            try {


                // 获取变量名
                String name = fields[i].getName();
                // 转换成 驼峰式
                String cameCaseName = StrUtil.camelCaseName(name);
                if (!camelPropertyValues.contains(cameCaseName)) {
                    continue;
                }
                // 获取参数值
                String parameterValue = (String) camelPropertyValues.get(cameCaseName);
                // 将属性的首字符大写，方便构造 get,set方法
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                // 获取属性的类型
                String type = fields[i].getGenericType().toString();
                // 如果type是类类型，则前面包含"class"，后面跟着类名
                if ("class java.lang.String".equals(type)) {
                    Method method = target.getClass().getMethod("get" + name);
                    String value = (String) method.invoke(target);
                    if (StringUtils.isBlank(value)) {
                        method = target.getClass().getMethod("set" + name, String.class);
                        method.invoke(target, parameterValue);
                    }
                } else if (fields[i].getType().getEnumConstants() != null) {
                    Method m = target.getClass().getMethod("get" + name);
                    if (m.invoke(target) == null) {
                        for (Object enumProperty : fields[i].getType().getEnumConstants()) {
                            if (enumProperty instanceof BaseEnum) {
                                BaseEnum baseEnum = (BaseEnum) enumProperty;
                                if (baseEnum.getCode().equals(parameterValue)) {
                                    m = target.getClass().getMethod("set" + name, enumProperty.getClass());
                                    m.invoke(target, enumProperty);
                                    break;
                                }
                            }
                        }
                    }
                } else if (type.equalsIgnoreCase("int")) {
                    Method m = target.getClass().getMethod("get" + name);
                    int value = (int) m.invoke(target);
                    if (value == 0) {
                        m = target.getClass().getMethod("set" + name, int.class);
                        if (StringUtils.isNotEmpty(parameterValue)) {
                            m.invoke(target, Integer.valueOf(parameterValue));
                        } else {
                            m.invoke(target, 0);
                        }
                    }
                } else if (type.equalsIgnoreCase("class java.lang.Integer")) {
                    Method m = target.getClass().getMethod("get" + name);
                    Integer value = (Integer) m.invoke(target);
                    if (StringUtils.isNotEmpty(parameterValue)) {
                        Integer integerValue = Integer.valueOf(parameterValue);
                        if (value == null) {
                            m = target.getClass().getMethod("set" + name, Integer.class);
                            m.invoke(target, integerValue);
                        }
                    }
                } else if (type.equalsIgnoreCase("long")) {
                    Method m = target.getClass().getMethod("get" + name);
                    int value = (int) m.invoke(target);
                    if (value == 0) {
                        m = target.getClass().getMethod("set" + name, long.class);
                        if (StringUtils.isNotEmpty(parameterValue)) {
                            m.invoke(target, Long.valueOf(parameterValue));
                        } else {
                            m.invoke(target, 0);
                        }
                    }
                } else if (type.equalsIgnoreCase("class java.lang.Long")) {
                    Method m = target.getClass().getMethod("get" + name);
                    Long value = (Long) m.invoke(target);
                    if (StringUtils.isNotEmpty(parameterValue)) {
                        Long integerValue = Long.valueOf(parameterValue);
                        if (value == null) {
                            m = target.getClass().getMethod("set" + name, Long.class);
                            m.invoke(target, integerValue);
                        }
                    }
                } else if (type.equalsIgnoreCase("double")) {
                    Method m = target.getClass().getMethod("get" + name);
                    double value = (double) m.invoke(target);
                    if (value == 0) {
                        m = target.getClass().getMethod("set" + name, double.class);
                        if (StringUtils.isNotEmpty(parameterValue)) {
                            m.invoke(target, Double.valueOf(parameterValue));
                        } else {
                            m.invoke(target, 0);
                        }
                    }
                } else if (type.equalsIgnoreCase("class java.lang.Double")) {
                    Method m = target.getClass().getMethod("get" + name);
                    Double value = (Double) m.invoke(target);
                    if (StringUtils.isNotEmpty(parameterValue)) {
                        Double integerValue = Double.valueOf(parameterValue);
                        if (value == null) {
                            m = target.getClass().getMethod("set" + name, Double.class);
                            m.invoke(target, integerValue);
                        }
                    }
                }
            } catch (Exception ex) {
                LogHelper.exception(ex, logger, "【APIDataBinder】自定义绑定数据异常");
            }
        }
    }
}
