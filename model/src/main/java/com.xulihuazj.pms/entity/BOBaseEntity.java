package com.xulihuazj.pms.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * BOBaseEntity.java 1.0.0 2018/08/07  11:27
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 * 1. 2018/08/07  11:27 created by xulihua
 */
public class BOBaseEntity extends BaseEntity {

    @Getter
    @Setter
    private String environment;

    @Getter
    @Setter
    private Map<String, String> extInfo;

}
