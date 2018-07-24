package com.xulihuazj.pms.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController.java 1.0.0 2018/07/24  16:43
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 * 1. 2018/07/24  16:43 created by xulihua
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/index")
    public String getTest(){
        return  "32111111111";
    }
}
