-- 创建库
CREATE DATABASE IF NOT EXISTS yu_picture;
-- 切换库
USE yu_picture;
-- 用户表
CREATE TABLE IF NOT EXISTS user
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

-- 新增用户 yFan666
INSERT INTO yu_picture.user (id, userAccount, userPassword, userName, userAvatar, userProfile, userRole, editTime,
                             createTime, updateTime, isDelete)
VALUES (1925145140651917313, 'yFan666', 'b0dd3697a192885d7c055db46155b26a', '无名', null, null, 'user',
        '2025-05-21 19:02:23', '2025-05-21 19:02:23', '2025-05-21 19:02:23', 0);

-- 图片表
create table if not exists picture
(
    id           bigint auto_increment comment 'id' primary key,
    url          varchar(512)                       not null comment '图片 url',
    name         varchar(128)                       not null comment '图片名称',
    introduction varchar(512)                       null comment '简介',
    category     varchar(64)                        null comment '分类',
    tags         varchar(512)                       null comment '标签（JSON 数组）',
    picSize      bigint                             null comment '图片体积',
    picWidth     int                                null comment '图片宽度',
    picHeight    int                                null comment '图片高度',
    picScale     double                             null comment '图片宽高比例',
    picFormat    varchar(32)                        null comment '图片格式',
    userId       bigint                             not null comment '创建用户 id',
    createTime   datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    editTime     datetime default CURRENT_TIMESTAMP not null comment '编辑时间',
    updateTime   datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint  default 0                 not null comment '是否删除',
    INDEX idx_name (name),                 -- 提升基于图片名称的查询性能
    INDEX idx_introduction (introduction), -- 用于模糊搜索图片简介
    INDEX idx_category (category),         -- 提升基于分类的查询性能
    INDEX idx_tags (tags),                 -- 提升基于标签的查询性能
    INDEX idx_userId (userId)              -- 提升基于用户 ID 的查询性能
    ) comment '图片' collate = utf8mb4_unicode_ci;

