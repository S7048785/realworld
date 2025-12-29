create table tags
(
    id      int unsigned auto_increment
        primary key,
    name    varchar(255)         not null comment '标签名称',
    deleted tinyint(1) default 0 null comment '标记删除状态',
    constraint tags_pk
        unique (name)
);

INSERT INTO realworld.tags (id, name, deleted) VALUES (1, 'Mavem', 0);
INSERT INTO realworld.tags (id, name, deleted) VALUES (2, 'Java', 0);
INSERT INTO realworld.tags (id, name, deleted) VALUES (3, 'C#', 0);
INSERT INTO realworld.tags (id, name, deleted) VALUES (6, 'Kotlin', 0);
INSERT INTO realworld.tags (id, name, deleted) VALUES (7, 'Spring', 0);
INSERT INTO realworld.tags (id, name, deleted) VALUES (8, 'abc', 0);
INSERT INTO realworld.tags (id, name, deleted) VALUES (9, 'def', 0);
