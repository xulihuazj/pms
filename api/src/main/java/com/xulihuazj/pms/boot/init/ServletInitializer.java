package com.xulihuazj.pms.boot.init;

import com.xulihuazj.pms.ApiApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApiApplication.class);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
//        PropertiesInitializer.test(servletContext);
        super.onStartup(servletContext);

    }

}
