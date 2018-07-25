package com.xulihuazj.pms.impl.redis;

import com.alibaba.fastjson.TypeReference;
import com.xulihuazj.pms.service.readis.RedisService;

public class RedisServiceImpl implements RedisService {

    @Override
    public void set(String key, Object value, int expireTime) {

    }

    @Override
    public <T> T get(Class<T> t, String key) {
        return null;
    }

    @Override
    public <T> T get(TypeReference<T> t, String key) {
        return null;
    }

    @Override
    public void remove(String key) {

    }

    @Override
    public void set(String key, Object value) {

    }
}
