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

INSERT INTO realworld.user_follow (id, user_id, followed_user_id, created_at, deleted) VALUES (12, 8, 2, '2026-01-02 14:45:56', 0);
