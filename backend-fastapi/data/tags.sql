create table tags
(
    id      int unsigned auto_increment
        primary key,
    name    varchar(255)         not null comment '标签名称',
    deleted tinyint(1) default 0 null comment '标记删除状态',
    constraint tags_pk
        unique (name)
);

INSERT INTO realworld.tags (id, name, deleted) VALUES (1, 'Java', 0);
INSERT INTO realworld.tags (id, name, deleted) VALUES (2, '后端', 0);
INSERT INTO realworld.tags (id, name, deleted) VALUES (3, '前端', 0);
INSERT INTO realworld.tags (id, name, deleted) VALUES (6, '程序员', 0);
INSERT INTO realworld.tags (id, name, deleted) VALUES (7, 'GitHub', 0);
INSERT INTO realworld.tags (id, name, deleted) VALUES (8, 'Three.js', 0);
INSERT INTO realworld.tags (id, name, deleted) VALUES (9, 'CSS', 0);
INSERT INTO realworld.tags (id, name, deleted) VALUES (10, '面试', 0);
INSERT INTO realworld.tags (id, name, deleted) VALUES (11, 'Android', 0);
INSERT INTO realworld.tags (id, name, deleted) VALUES (12, 'AI编程', 0);
