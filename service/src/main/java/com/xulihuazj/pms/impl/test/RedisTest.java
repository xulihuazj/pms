package com.xulihuazj.pms.impl.test;

import sun.nio.ch.ThreadPool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * RedisTest.java 1.0.0 2018/08/21  10:15
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 * 1. 2018/08/21  10:15 created by xulihua
 */
public class RedisTest {
//
//    public Object getProductListNew(){
//        int cacheTime = 30;
//        String cacheKey = "product_list";
//        String localKey = cacheKey;
//
//        String cacheValue = CacheHelper
//    }
//
//    public Object getProdutctListNew2(){
//        int cacheTime = 30;
//        String cacheKey = "product_list";
//        String cacheSign = cacheKey + "_sign";
//        String sign = CacheHelper.get(cacheSign);
//
//        String cacheValue = CacheHelper.get(cacheKey);
//        if(null != sign){
//            return cacheValue;
//        }else{
//            CacheHelper.add(cacheSign,"1",cacheTime);
//        }
//    }

    public static void main(String[] args) {
        System.out.println("31232091jfdsjfklasjklf;sd".hashCode());

        List<Integer> list = new ArrayList<Integer>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(5);

        System.out.println(hasSame(list));
    }

    private static boolean hasSame(List<? extends Object> list) {
        if (null == list)
            return false;
        return list.size() == new HashSet<Object>(list).size();
    }

}
