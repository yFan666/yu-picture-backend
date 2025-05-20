package com.yupi.yupicturebackend.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求
 */

@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 8735650154179439661L;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 确认密码
     */
    private String checkPassword;
}
