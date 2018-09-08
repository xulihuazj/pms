package com.xulihuazj.pms.impl.test;

import java.util.HashMap;
import java.util.Map;

/**
 * Favorites.java 1.0.0 2018/08/09  08:14
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 * 1. 2018/08/09  08:14 created by xulihua
 */
public class Favorites {

    private Map<Class<?>,Object> favorites = new HashMap<>();

    public <T> void putFavorite(Class<T> type,T instance){
        if(null == type){
            throw new NullPointerException("");
        }else{
            this.favorites.put(type,instance);
        }
    }

    public <T> T getFavorite(Class<T> type){
        return type.cast(favorites.get(type));
    }

}
