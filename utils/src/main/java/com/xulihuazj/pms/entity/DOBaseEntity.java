package com.xulihuazj.pms.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * DO基类
 */
public class DOBaseEntity extends BaseEntity {

    /**
     * 扩展信息
     */
    @Getter
    @Setter
    private String extInfo;

}
