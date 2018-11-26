package com.xulihuazj.pms.service.auth;


import com.xulihuazj.pms.ModuleEnum;

/**
 * 策略模式，支持之后的扩展策略
 *
 * @author xulihua
 * @Description
 * @Date 2018年11月20日下午4:00:42
 */
public class AuthStrategyContext {

    private AuthStrategy authStrategyImpl;

    public AuthStrategyContext(AuthStrategy strategy) {
        this.authStrategyImpl = strategy;
    }

    /**
     * 执行策略
     *
     * @param modules
     * @param userId
     * @return
     * @throws Exception
     */
    public boolean execute(ModuleEnum[] modules, Long userId) throws Exception {
        return this.authStrategyImpl.executeAuth(modules, userId);
    }

}
