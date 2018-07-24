package com.xulihuazj.pms.api.init;

import com.xulihuazj.pms.api.ApiApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * ServletInitializer.java 1.0.0 2018/07/24  16:37
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 * 1. 2018/07/24  16:37 created by xulihua
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApiApplication.class);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
//        PropertiesInitializer.init(servletContext);
        super.onStartup(servletContext);

    }

}
