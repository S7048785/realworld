create table user
(
    id         int unsigned auto_increment
        primary key,
    username   varchar(255)                                                                                         not null comment '用户名',
    email      varchar(50)                                                                                          not null,
    password   varchar(255)                                                                                         not null comment '密码',
    bio        varchar(255)                                                                                         null comment '个人简介',
    avatar     varchar(255) default 'https://i0.hdslb.com/bfs/article/b6b843d84b84a3ba5526b09ebf538cd4b4c8c3f3.jpg' null comment '头像',
    created_at datetime     default CURRENT_TIMESTAMP                                                               not null,
    updated_at datetime     default CURRENT_TIMESTAMP                                                               not null,
    deleted    tinyint(1)   default 0                                                                               null,
    constraint user_pk_2
        unique (username)
)
    comment '用户表';

create index user_email_index
    on user (email);

INSERT INTO realworld.user (id, username, email, password, bio, avatar, created_at, updated_at, deleted) VALUES (1, 'admin', '2561as54@qq.com', '123456', 'This is Admin12', 'https://java-web-lws.oss-cn-beijing.aliyuncs.com/hm.jpg', '2025-12-26 23:48:31', '2025-12-26 23:48:31', 0);
INSERT INTO realworld.user (id, username, email, password, bio, avatar, created_at, updated_at, deleted) VALUES (2, '答案answer', '2165as1d@163.com', '789456', 'rht', '/images/1839039375.jpg', '2025-12-26 23:48:31', '2025-12-26 23:48:31', 0);
INSERT INTO realworld.user (id, username, email, password, bio, avatar, created_at, updated_at, deleted) VALUES (3, '天天摸鱼的Java工程师', 'asv5df1w.@126.com', '123456', 'iii', '/images/9419024696.jpg', '2025-12-26 23:48:31', '2025-12-26 23:48:31', 0);
INSERT INTO realworld.user (id, username, email, password, bio, avatar, created_at, updated_at, deleted) VALUES (4, 'ServBaoy1', 'f484fr1f.@126.com', '123456', 'bbbb', '/images/2230369404.jpg', '2025-12-26 23:48:31', '2025-12-26 23:48:31', 0);
INSERT INTO realworld.user (id, username, email, password, bio, avatar, created_at, updated_at, deleted) VALUES (5, '追逐时光者', 's65484gy41@gemail.com', '123545', '公众号:「追逐时光者」，微软MVP、华为云开发者专家计划、华为云云享专家、阿里云专家博主、51CTO专家博主、CSDN博客专家、腾讯云创作之星、掘金优秀创作者，擅长C#、.NET、Golang、Vue等相关技术开发。', '/images/3757021294.jpg', '2025-12-29 16:41:26', '2025-12-29 16:41:26', 0);
INSERT INTO realworld.user (id, username, email, password, bio, avatar, created_at, updated_at, deleted) VALUES (6, '捡田螺的小男孩', '68341frb@gemail.com', '123456', '公众号:捡田螺的小男孩', '/images/6082720531.jpg', '2025-12-29 16:55:13', '2025-12-29 16:55:13', 0);
INSERT INTO realworld.user (id, username, email, password, bio, avatar, created_at, updated_at, deleted) VALUES (7, '恋猫de小郭', '5f51f1b0@qq.com', '123456', '《Flutter 开发实战详解》作者，Github GSY 系列项目负责人，一个写代码的老二次猿', 'https://p3-passport.byteacctimg.com/img/user-avatar/f4117a39915ab646a21a190e1fc5795d~130x130.awebp', '2025-12-29 16:57:02', '2025-12-29 16:57:02', 0);
INSERT INTO realworld.user (id, username, email, password, bio, avatar, created_at, updated_at, deleted) VALUES (8, '陈九', '195726662@qq.com', '123456', '', '\\images\\9419024696.jpg', '2025-12-30 15:19:28', '2025-12-30 19:40:47', 0);
