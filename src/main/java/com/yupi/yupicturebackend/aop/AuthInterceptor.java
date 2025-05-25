package com.yupi.yupicturebackend.aop;

import com.yupi.yupicturebackend.annotation.AuthCheck;
import com.yupi.yupicturebackend.exception.BusinessException;
import com.yupi.yupicturebackend.exception.ErrorCode;
import com.yupi.yupicturebackend.model.entity.User;
import com.yupi.yupicturebackend.model.enums.UserRoleEnum;
import com.yupi.yupicturebackend.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 权限拦截器切面类。
 * 该类用于拦截带有@AuthCheck注解的方法，检查用户权限并执行相关逻辑。
 */
@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private UserService userService;

    /**
     * 拦截带有@AuthCheck注解的方法，进行权限校验。
     *
     * @param joinPoint 切点，包含被拦截方法的相关信息。
     * @param authCheck 权限校验注解
     * @return 被拦截方法的执行结果。
     * @throws Throwable 如果被拦截方法抛出异常，则向上抛出。
     */
    @Around("@annotation(authCheck)")  // 环绕切面 - annotation注解对authCheck进行了拦截，然后通过authCheck参数就能获得
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        // 获取必须的角色信息
        String mustRole = authCheck.mustRole();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes(); // 全局请求上下文拿到当前请求的所有属性

        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest(); // 子对象转换，拿到需要的函数

        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
        // 如果不需要权限，放行
        if (mustRoleEnum == null) {
            joinPoint.proceed();
        }
        // 以下代码，必须有权限，才会通过
        // 获取当前登录用户
        User loginUser = userService.getLoginUser(request);
        /*
          1. 拿到用户的权限信息
          2. 通过转换成枚举类，便于使用枚举类的方法
         */
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(loginUser.getUserRole());
        if (userRoleEnum == null) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        // 要求必须有管理员权限，但用户没有管理员权限，拒绝 （检验用户的权限 && 用户的权限不是管理员权限）
        if (UserRoleEnum.ADMIN.equals(mustRoleEnum) && !UserRoleEnum.ADMIN.equals(userRoleEnum)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }

        // 继续执行被拦截的方法
        return joinPoint.proceed();
    }
}
