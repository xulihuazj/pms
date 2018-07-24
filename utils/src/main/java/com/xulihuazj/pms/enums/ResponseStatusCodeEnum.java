package com.xulihuazj.pms.enums;

/**
 *API 返回码 定义
 */
public enum ResponseStatusCodeEnum implements BaseEnum {

    SUCCESS("SUCCESS", "成功"),;

    String code;

    String desc;

    ResponseStatusCodeEnum(String code, String desc) {
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
