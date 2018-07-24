package com.xulihuazj.pms.boot.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;

/**
 * 初始化Bean处理
 */
public class AppBeanConfig {

    @Bean
    public ObjectMapper objectMapper() {
        // 提供一些功能将转换成Java对象匹配JSON结构
        ObjectMapper objectMapper = new ObjectMapper().
                // 对于空的对象转json的时候不抛出错误
                        disable(SerializationFeature.FAIL_ON_EMPTY_BEANS).
                // 禁用遇到未知属性抛出异常
                        disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).
                // 视空字符传为null
                        enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT).
                // 允许属性名称没有引号
                        configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true).
                // 允许单引号
                        configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true).
                // 取消对非ASCII字符的转码
                        configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, false);
        // 设置 属性名称转换
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        // 属性为 空（""） 或者为 NULL 都不序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return objectMapper;
    }

}
