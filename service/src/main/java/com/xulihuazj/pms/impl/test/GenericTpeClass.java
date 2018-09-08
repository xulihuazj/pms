package com.xulihuazj.pms.impl.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GenericTpeClass.java 1.0.0 2018/08/08  10:16
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 * 1. 2018/08/08  10:16 created by xulihua
 */
public class GenericTpeClass {

    public static void main(String[] args){
        ParentClass parentClass = new SubClass();
        ((SubClass) parentClass).test1();
        ((SubClass) parentClass).test2();

    }

    private void test1(){


    }

}
