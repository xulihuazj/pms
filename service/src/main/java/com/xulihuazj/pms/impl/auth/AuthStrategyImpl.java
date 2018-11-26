package com.xulihuazj.pms.impl.auth;

import com.xulihuazj.pms.ModuleEnum;
import com.xulihuazj.pms.service.auth.AuthStrategy;
import org.springframework.stereotype.Service;

@Service
public class AuthStrategyImpl implements AuthStrategy {

    @Override
    public boolean executeAuth(ModuleEnum[] modules, Long userId) {
        return false;
    }

}
