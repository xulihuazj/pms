package com.xulihuazj.pms.boot.auth;

import com.xulihuazj.pms.ModuleEnum;

import java.lang.annotation.*;

/**
 *
 * @author xulihua
 * @Description
 * @Date 2018年11月20日下午4:00:42
 */
@Target(value = { ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface PermissionModule {

    ModuleEnum[] belong() default {ModuleEnum.ALL};

}
