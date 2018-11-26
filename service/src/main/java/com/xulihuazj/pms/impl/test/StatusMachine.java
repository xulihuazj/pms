package com.xulihuazj.pms.impl.test;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * StatusMachine.java 1.0.0 2018/09/04  09:39
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 * 1. 2018/09/04  09:39 created by xulihua
 */
public class StatusMachine {

    private final static Map<Set<Integer>, String> statusControl = new HashMap<>();

    static {
        statusControl.put(new HashSet<Integer>() {
            {
                add(1);
                add(2);
                add(3);
            }
        }, "未完成");

        statusControl.put(new HashSet<Integer>() {
            {
                add(4);
            }
        }, "成功");

        statusControl.put(new HashSet<Integer>() {
            {
                add(5);
                add(4);
            }
        }, "失败");
        statusControl.put(new HashSet<Integer>() {
            {
                add(5);
//                add(4);
            }
        }, "失败");

    }

    public static String changeStatus(Set<Integer> params) {
        if (CollectionUtils.isEmpty(params)) {
            throw new RuntimeException("参数异常");
        }

        if (statusControl.containsKey(params)) {
            return statusControl.get(params);
        } else {
            throw new RuntimeException("无匹配类型");
        }
    }

    @Test
    public void test(){
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        System.out.println(StatusMachine.changeStatus(set));

        Set<Integer> set2 = new HashSet<>();
        set2.add(4);
        System.out.println(StatusMachine.changeStatus(set2));

        Set<Integer> set3 = new HashSet<>();
//        set3.add(4);
        set3.add(5);
        System.out.println(StatusMachine.changeStatus(set3));
    }
}
