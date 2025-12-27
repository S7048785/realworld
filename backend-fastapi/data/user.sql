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

INSERT INTO realworld.user (id, username, email, password, bio, avatar, created_at, updated_at, deleted) VALUES (1, 'admin', '', '{noop}123456', 'This is Admin12', 'https://java-web-lws.oss-cn-beijing.aliyuncs.com/hm.jpg', '2025-12-26 23:48:31', '2025-12-26 23:48:31', 0);
INSERT INTO realworld.user (id, username, email, password, bio, avatar, created_at, updated_at, deleted) VALUES (2, 'SEDGS', '', '{noop}wwf5r', 'rht', 'https://tse1-mm.cn.bing.net/th/id/OIP-C.djNPiQV3sgY6BUvPAujUEQAAAA?pid=ImgDet&w=360&h=360&rs=1', '2025-12-26 23:48:31', '2025-12-26 23:48:31', 0);
INSERT INTO realworld.user (id, username, email, password, bio, avatar, created_at, updated_at, deleted) VALUES (3, 'WFVZA', '', '{noop}123456', 'iii', 'https://tse4-mm.cn.bing.net/th/id/OIP-C.ABtBaqzLKVvIJdknHDkGtwAAAA?pid=ImgDet&w=360&h=360&rs=1', '2025-12-26 23:48:31', '2025-12-26 23:48:31', 0);
INSERT INTO realworld.user (id, username, email, password, bio, avatar, created_at, updated_at, deleted) VALUES (4, 'EYAHB', '', '{noop}123456', 'bbbb', 'https://tse1-mm.cn.bing.net/th/id/OIP-C.NVxW4m4ziwvV9Dxp_99-gAHaHa?rs=1&pid=ImgDetMain', '2025-12-26 23:48:31', '2025-12-26 23:48:31', 0);
