package com.xulihuazj.pms.enums.user;

import com.xulihuazj.pms.enums.BaseEnum;

/**
 * 请求来源渠道
 */
public enum Source implements BaseEnum {

    WEB("WEB", "网站"),

    ANDROID("ANDROID", "安卓APP"),

    IOS("IOS", "IOSAPP"),

    H5("H5", "H5"),;

    String code;

    String desc;

    Source(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
