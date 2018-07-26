package com.xulihuazj.pms.boot.config;

import com.bstek.ureport.console.UReportServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.servlet.Servlet;

/**
 * ureport 报表 配置类
 */
@ImportResource("classpath:ureport-console-context.xml")
@Configuration
public class UreportConfig {

    @Bean
    public ServletRegistrationBean<Servlet> registrationBean(){
        return  new ServletRegistrationBean<>(new UReportServlet(),"/ureport/*");
    }
}
