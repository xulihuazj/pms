package com.xulihuazj.pms.service.readis;

import com.alibaba.fastjson.TypeReference;

public interface RedisService {

    /**
     * @Function: RedisRepository.java
     * @Description: 数据写入
     * @param:key：键,value:值，expireTime:时间（秒）
     * @return：void
     * @author: xlh
     * @date: 2018年3月8日 下午8:04:12
     */
    void set(String key, Object value, int expireTime);

    /**
     * @Function: RedisRepository.java
     * @Description: 该函数的功能描述
     * @param:TODO
     * @return：T
     * @author: xlh
     * @date: 2018年3月8日 下午8:08:45
     */
    <T> T get(Class<T> t, String key);

    /**
     * 获取一个缓存项 （集合）
     *
     * @param t
     * @param key
     * @param <T>
     * @return
     */
    <T> T get(TypeReference<T> t, String key);

    /**
     * 删除一个缓存项
     *
     * @param key
     */
    void remove(String key);

    /**
     * 设置一个缓存,无过期时间
     *
     * @param key   缓存key
     * @param value 缓存值
     */
    void set(String key, Object value);


}
