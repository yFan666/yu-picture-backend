package com.yupi.yupicturebackend.exception;

/**
 * 异常处理工具类
 */
public class ThrowUtils {

    /**
     * 根据条件判断是否抛出指定的运行时异常。
     *
     * @param condition 条件，当为 true 时抛出异常
     * @param runtimeException 要抛出的运行时异常对象
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }


    /**
     * 条件成立则抛出指定错误码的业务异常。
     *
     * @param condition 条件，当为 true 时抛出异常
     * @param errorCode 错误码，用于定义异常的错误类型和状态
     */
    public static void throwIf(boolean condition, ErrorCode errorCode) {
        throwIf(condition, new BusinessException(errorCode));
    }


    /**
     * 根据条件判断是否抛出带有指定错误码和自定义消息的业务异常。
     *
     * @param condition 条件，当为 true 时抛出异常
     * @param errorCode 错误码，用于定义异常的错误类型和状态
     * @param message 自定义异常消息，用于描述具体的错误信息
     */
    public static void throwIf(boolean condition, ErrorCode errorCode, String message) {
        throwIf(condition, new BusinessException(errorCode, message));
    }

}
