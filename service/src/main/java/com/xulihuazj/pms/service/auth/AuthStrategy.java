package com.xulihuazj.pms.service.auth;

import com.xulihuazj.pms.ModuleEnum;

/**
 * 授权接口
 */
public interface AuthStrategy {

    boolean executeAuth(ModuleEnum[] modules, Long userId);

}
