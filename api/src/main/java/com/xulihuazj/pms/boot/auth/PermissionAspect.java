package com.xulihuazj.pms.boot.auth;

import com.xulihuazj.pms.ModuleEnum;
import com.xulihuazj.pms.context.LocalContextHolder;
import com.xulihuazj.pms.entity.error.BizErrorCode;
import com.xulihuazj.pms.entity.exception.BusinessException;
import com.xulihuazj.pms.service.auth.AuthStrategy;
import com.xulihuazj.pms.service.auth.AuthStrategyContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * 用户权限AOP
 *
 * @author xulihua
 * @Description
 * @Date 2018年11月20日下午4:00:42
 */
@Aspect
@Component
public class PermissionAspect {

    @Resource
    private AuthStrategy authStrategyImpl;

    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping) &&  @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public Object executeAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 用户Id
        Long userId = LocalContextHolder.getContext().getUserId();
        if (null != userId) {
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            Method targetMethod = methodSignature.getMethod();
            Method realMethod = joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), targetMethod.getParameterTypes());
            Object obj;
            if (this.isHasPermission(realMethod, userId)) {
                //用户拥有该方法权限时执行方法里面的内容
                obj = joinPoint.proceed();
            } else {//用户没有权限，则直接返回没有权限的通知
                throw new BusinessException(BizErrorCode.REQUEST_PARAM_EMPTY_ERROR, "当前用户无此接口的执行权限");
            }
            return obj;
        } else {
            throw new BusinessException(BizErrorCode.REQUEST_PARAM_EMPTY_ERROR, "未登录，请先登录");
        }
    }

    /**
     * 判断用户是否拥有权限
     *
     * @param realMethod
     * @param userId
     * @return
     */
    private boolean isHasPermission(Method realMethod, Long userId) {
        try {
            if (realMethod.isAnnotationPresent(PermissionModule.class)) {
                PermissionModule permissionModule = realMethod.getAnnotation(PermissionModule.class);
                ModuleEnum[] modules = permissionModule.belong();
                //执行权限策略，判断用户权限
                return new AuthStrategyContext(authStrategyImpl).execute(modules, userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }


}
