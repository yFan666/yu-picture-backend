-- 创建库
CREATE DATABASE IF NOT EXISTS yu_picture;
-- 切换库
USE yu_picture;
-- 用户表
CREATE TABLE IF NOT EXISTS USER
(
    id bigint AUTO_INCREMENT comment 'id' PRIMARY KEY,
    userAccount varchar(256) NOT NULL comment '账号',
    userPassword varchar(512) NOT NULL comment '密码',
    userName varchar(256) NULL comment '用户昵称',
    userAvatar varchar(1024) NULL comment '用户头像',
    userProfile varchar(512) NULL comment '用户简介',
    userRole varchar(256) DEFAULT 'user' NOT NULL comment '用户角色：user/admin',
    editTime datetime DEFAULT CURRENT_TIMESTAMP NOT NULL comment '编辑时间',
    createTime datetime DEFAULT CURRENT_TIMESTAMP NOT NULL comment '创建时间',
    updateTime datetime DEFAULT CURRENT_TIMESTAMP NOT NULL ON
    UPDATE
        CURRENT_TIMESTAMP comment '更新时间',
        isDelete TINYINT DEFAULT 0 NOT NULL comment '是否删除',
        UNIQUE KEY uk_userAccount (userAccount),
        INDEX idx_userName (userName)
) comment '用户' COLLATE = utf8mb4_unicode_ci;

SELECT
    *
FROM
    `user` u;