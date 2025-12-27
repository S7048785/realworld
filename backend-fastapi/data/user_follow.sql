create table user_follow
(
    id               int unsigned auto_increment
        primary key,
    user_id          int unsigned         not null comment '关注ID',
    followed_user_id int unsigned         not null comment '被关注ID',
    created_at       datetime             not null comment '关注时间',
    deleted          tinyint(1) default 0 null comment '标记删除状态'
)
    comment '用户关注关系表';

create index user_follow_followed_user_id_index
    on user_follow (followed_user_id);

create index user_follow_user_id_index
    on user_follow (user_id);

INSERT INTO realworld.user_follow (id, user_id, followed_user_id, created_at, deleted) VALUES (1, 1, 2, '2025-04-22 20:35:54', 0);
INSERT INTO realworld.user_follow (id, user_id, followed_user_id, created_at, deleted) VALUES (2, 2, 4, '2025-04-23 23:32:20', 0);
INSERT INTO realworld.user_follow (id, user_id, followed_user_id, created_at, deleted) VALUES (3, 1, 3, '2025-04-24 18:06:24', 0);
