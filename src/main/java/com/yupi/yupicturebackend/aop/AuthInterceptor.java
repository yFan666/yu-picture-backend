package com.yupi.yupicturebackend.aop;

import com.yupi.yupicturebackend.annotation.AuthCheck;
import com.yupi.yupicturebackend.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import javax.annotation.Resource;

/**
 * 权限拦截器切面类。
 * 该类用于拦截带有@AuthCheck注解的方法，检查用户权限并执行相关逻辑。
 */
@Aspect
public class AuthInterceptor {

    @Resource
    private UserService userService;

    /**
     * 拦截带有@AuthCheck注解的方法，进行权限校验。
     *
     * @param joinPoint 切点，包含被拦截方法的相关信息。
     * @param authCheck 注解对象，包含权限校验的配置信息。
     * @return 被拦截方法的执行结果。
     * @throws Throwable 如果被拦截方法抛出异常，则向上抛出。
     */
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        // 获取必须的角色信息
        String requiredRole = authCheck.mustRole();
        // TODO: 在此处添加具体的权限校验逻辑，例如验证用户是否具有requiredRole角色
        // 继续执行被拦截的方法
        return joinPoint.proceed();
    }
}
