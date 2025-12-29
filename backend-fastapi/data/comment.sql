create table comment
(
    id         int unsigned auto_increment
        primary key,
    body       varchar(255)         not null comment '评论内容',
    user_id    int unsigned         not null comment '评论者id',
    article_id int unsigned         not null comment '文章id',
    created_at datetime             not null comment '评论时间',
    deleted    tinyint(1) default 0 null comment '标记删除状态'
)
    comment '文章评论表';

INSERT INTO realworld.comment (id, body, user_id, article_id, created_at, deleted) VALUES (1, '评论21235425', 1, 0, '2025-04-22 14:01:10', 0);
INSERT INTO realworld.comment (id, body, user_id, article_id, created_at, deleted) VALUES (2, 'rth467568', 2, 0, '2025-04-22 14:01:11', 0);
INSERT INTO realworld.comment (id, body, user_id, article_id, created_at, deleted) VALUES (3, '44444444', 2, 0, '2025-04-22 14:01:12', 0);
