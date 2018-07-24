package com.xulihuazj.pms.enums.user;

import com.xulihuazj.pms.enums.BaseEnum;

public enum CertTypeEnum implements BaseEnum {

    ID_CD("ID_CD", "居民身份证"),

    RESIDENCE_PERMIT("RESIDENCE_PERMIT", "居住证"),;

    private String code;

    private String desc;

    CertTypeEnum(String code, String desc) {
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
