package com.xulihuazj.pms.impl.proxy.jdk_proxy;

import com.xulihuazj.pms.impl.proxy.LoginService;
import com.xulihuazj.pms.impl.proxy.LoginServiceImpl;
import com.xulihuazj.pms.impl.proxy.UserService;
import com.xulihuazj.pms.impl.proxy.UserServiceImpl;

import java.lang.reflect.Proxy;

/**
 * ProxyTestJDK.java 1.0.0 2018/09/07  15:56
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 * 1. 2018/09/07  15:56 created by xulihua
 */
public class ProxyTestJDK {

    public static void main(String[] args) {
        LoginService loginService = new LoginServiceImpl();
        UserService userService = new UserServiceImpl();

        ProxyHandler proxyHandler = new ProxyHandler();
        proxyHandler.setTarget(loginService);
        LoginService loginService$Proxy = (LoginService) Proxy.newProxyInstance(loginService.getClass().getClassLoader(), loginService.getClass().getInterfaces(), proxyHandler);
        loginService$Proxy.checkUser();

        proxyHandler.setTarget(userService);
        UserService userService$Proxy = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(), userService.getClass().getInterfaces(), proxyHandler);
        userService$Proxy.getUserName();

    }
}
