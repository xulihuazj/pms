package com.xulihuazj.pms.utils.uuid;

        import java.util.UUID;

/**
 * UUID工具类
 */
public class UUIDUtil {

    public static String getRandomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
