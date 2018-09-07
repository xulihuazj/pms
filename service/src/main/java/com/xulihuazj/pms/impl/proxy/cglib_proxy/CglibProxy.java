package com.xulihuazj.pms.impl.proxy.cglib_proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CglibProxy.java 1.0.0 2018/09/07  16:12
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 * 1. 2018/09/07  16:12 created by xulihua
 */
public class CglibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object proxy, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("***代理方法执行前***");
        Object resultObj = methodProxy.invokeSuper(proxy, objects);
        System.out.println("***代理方法执行后***");
        return resultObj;
    }

    public Object newProxy(Object target){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        enhancer.setClassLoader(target.getClass().getClassLoader());
        return enhancer.create();
    }

}
