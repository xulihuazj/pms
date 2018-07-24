package com.xulihuazj.pms.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * DO 基类
 */
public class BaseEntity extends ToString {

    /**
     * 创建时间
     */
    @Getter
    @Setter
    private Date createTime;

    /**
     * 最后修改时间
     */
    @Getter
    @Setter
    private Date lastModifyTime;

    /**
     * 环境
     */
    @Getter
    @Setter
    private String environment;


}
