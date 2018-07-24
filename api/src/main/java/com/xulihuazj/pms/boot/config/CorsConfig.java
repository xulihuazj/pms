package com.xulihuazj.pms.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 设置跨域环境
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", this.buildConfig());
        return new CorsFilter(source);
    }

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 1：允许任何域名使用
        corsConfiguration.addAllowedOrigin("*");
        // 2：允许任何头
        corsConfiguration.addAllowedHeader("*");
        // 3：允许任何请求方法
        corsConfiguration.addAllowedMethod("*");
        return corsConfiguration;
    }
}
