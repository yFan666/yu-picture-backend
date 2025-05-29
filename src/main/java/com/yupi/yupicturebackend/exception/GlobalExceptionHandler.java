package com.yupi.yupicturebackend.exception;

import com.yupi.yupicturebackend.common.BaseResponse;
import com.yupi.yupicturebackend.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice // 注解实现全局异常捕获
@Slf4j
public class GlobalExceptionHandler {

    // 捕获处理业务异常
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler (BusinessException e) {
        log.error("BusinessException", e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    // 捕获处理系统异常
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> businessExceptionHandler (RuntimeException e) {
        log.error("BusinessException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统错误");
    }
}
