package com.xulihuazj.pms.boot.authority;

import org.springframework.web.servlet.HandlerInterceptor;
//import com.xulihuazj.pms.service.ip.IpWhiteService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * IP白名单检查
 */
public class IPWhiteInterceptor implements HandlerInterceptor {

//    @Resource
//    private IpWhiteService ipWhiteService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        return true;
    }
}
