package com.yupi.yupicturebackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.yupicturebackend.model.entity.User;
import com.yupi.yupicturebackend.service.UserService;
import com.yupi.yupicturebackend.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2025-05-19 17:47:07
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        // 1. 校验参数

        // 2. 检查用户账号是否和数据库中已有的重复

        // 3. 密码一定要加密

        // 4. 插入数据到数据库中

        return 0;
    }
}




