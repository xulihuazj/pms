package com.xulihuazj.pms.impl.proxy.proxy;

/**
 * ProxyTest.java 1.0.0 2018/09/07  15:26
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 * 1. 2018/09/07  15:26 created by xulihua
 */
public class ProxyTest {

    public static void main(String[] args) {
        Target target = new Target();
        TargetProxy proxy = new TargetProxy(target);
        proxy.test();
    }
}

class Target {

    public void test() {
        System.out.println("Target test");
    }
}

class TargetProxy {

    private Target target;

    public TargetProxy(Target target) {
        this.target = target;
    }

    public void test() {
        System.out.println("***方法执行前***");
        this.target.test();
        System.out.println("***方法执行后***");
    }

}
