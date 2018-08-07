package com.xulihuazj.pms.enums.user;

import lombok.Getter;

import java.util.EnumSet;

public enum UserStatusEnum {

    EFFECTIVE("EFFECTIVE", "有效"),

    LOCK("LOCK", "锁定"),

    INVALID("INVALID", "禁用"),;

    @Getter
    private String code;

    @Getter
    private String describe;

    UserStatusEnum(String code, String describe) {
        this.code = code;
        this.describe = describe;
    }

    public static UserStatusEnum getEnumFindCode(String code) {
        UserStatusEnum[] result = UserStatusEnum.values();
        for (UserStatusEnum e : result) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

    public static EnumSet<UserStatusEnum> allList() {
        EnumSet<UserStatusEnum> resultList = EnumSet.allOf(UserStatusEnum.class);
        return resultList;
    }
}
