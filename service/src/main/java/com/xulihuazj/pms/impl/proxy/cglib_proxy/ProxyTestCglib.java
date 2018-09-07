package com.xulihuazj.pms.impl.proxy.cglib_proxy;

import com.xulihuazj.pms.impl.proxy.LoginService;
import com.xulihuazj.pms.impl.proxy.LoginServiceImpl;
import com.xulihuazj.pms.impl.proxy.UserService;
import com.xulihuazj.pms.impl.proxy.UserServiceImpl;

/**
 * ProxyTestCglib.java 1.0.0 2018/09/07  16:16
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 * 1. 2018/09/07  16:16 created by xulihua
 */
public class ProxyTestCglib {

    public static void main(String[] args) {
        LoginService loginService = new LoginServiceImpl();
        UserService userService = new UserServiceImpl();
        CglibProxy cglibProxy = new CglibProxy();

        LoginService loginService$Proxy = (LoginService) cglibProxy.newProxy(loginService);

        UserService userService$Proxy = (UserService) cglibProxy.newProxy(userService);

        loginService$Proxy.checkUser();
        userService$Proxy.getUserName();
    }
}
