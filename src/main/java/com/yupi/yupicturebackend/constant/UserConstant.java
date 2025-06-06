package com.yupi.yupicturebackend.constant;

public interface UserConstant {
    /**
     * 用户登录态
     */
    String USER_LOGIN_STATUS = "user_login_state";

    // region 权限
    /**
     * 默认角色
     */
    String DEFAULT_ROLE = "user";
    /**
     * 管理员角色
     */
    String ADMIN_ROLE = "admin";
    // endregion
    // 加盐
    String SALE = "yupi";
}
