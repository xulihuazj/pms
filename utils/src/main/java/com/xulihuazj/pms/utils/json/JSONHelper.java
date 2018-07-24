package com.xulihuazj.pms.utils.json;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;

public class JSONHelper {

    /**
     * 系列化json
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        if (object == null) {
            return null;
        }
        return JSON.toJSONString(object);
    }

    /**
     * 反序列化object
     * @param json
     * @return
     */
    public static <T> T toObject(String json, Class<T> tClass) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return JSON.parseObject(json, tClass);
    }

}
