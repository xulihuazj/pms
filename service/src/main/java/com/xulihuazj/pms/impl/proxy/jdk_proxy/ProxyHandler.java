package com.xulihuazj.pms.impl.proxy.jdk_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * ProxyHandler.java 1.0.0 2018/09/07  15:41
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 * 1. 2018/09/07  15:41 created by xulihua
 */
public class ProxyHandler implements InvocationHandler {

    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("***JDK代理方法执行前***");
        Object invoke = method.invoke(this.target, args);
        System.out.println("***JDK代理方法执行后***");
        return invoke;
    }

}
