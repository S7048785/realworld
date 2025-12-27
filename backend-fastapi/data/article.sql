create table article
(
    id         int unsigned auto_increment
        primary key,
    title      varchar(255)                 not null comment '文章标题',
    body       varchar(255)                 null comment '文章内容',
    user_id    int unsigned                 not null comment '作者id',
    likes      int unsigned default '0'     not null,
    comments   int unsigned default '0'     not null,
    tags       json                         null,
    created_at datetime     default (now()) not null comment '创建时间',
    updated_at datetime     default (now()) not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted    tinyint(1)   default 0       null comment '标记删除状态'
)
    comment '文章表';

create index article_id_index
    on article (id);

INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (1, '测试文章1', 'asd', 1, 0, 0, null, '2025-04-18 22:46:18', '2025-04-18 22:46:20', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (2, '测试2', 'aGtrhn', 2, 0, 0, null, '2025-04-22 13:57:05', '2025-04-22 13:57:08', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (3, '测试3', 'sabntrj', 1, 0, 0, null, '2025-04-22 13:57:06', '2025-04-22 13:57:10', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (4, 'atgaet5aeth', 'srhgff', 2, 0, 0, null, '2025-04-22 13:57:07', '2025-04-22 13:57:12', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (6, '标题1', '内容1', 1, 0, 0, null, '2025-04-22 23:17:01', '2025-04-22 23:23:59', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (8, '标题2', '内容2', 1, 0, 0, null, '2025-04-22 23:42:12', '2025-04-22 23:42:12', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (9, '测试ass', '测试asss', 1, 0, 0, null, '2025-04-24 22:28:34', '2025-04-24 22:28:34', 1);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (10, 'wegretsr', 'aa', 2, 0, 0, null, '2025-04-26 02:13:38', '2025-04-26 02:13:38', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (11, 'tumuildrt', 'ss', 3, 0, 0, null, '2025-04-26 02:13:39', '2025-04-26 02:13:39', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (12, 'fjtdsetjn', 'ss', 4, 0, 0, null, '2025-04-26 02:13:41', '2025-04-26 02:13:41', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (13, 'wsrsfbfgc', 'dd', 2, 0, 0, null, '2025-04-26 02:13:42', '2025-04-26 02:13:42', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (14, 'sfgdfbsrt', 'dd', 3, 0, 0, null, '2025-04-26 02:13:43', '2025-04-26 02:13:43', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (15, 'wsrgdfb', 'srj', 4, 0, 0, null, '2025-04-26 02:13:50', '2025-04-26 02:13:50', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (16, 'szrgeth', 'sryj', 1, 0, 0, null, '2025-04-26 02:13:51', '2025-04-26 02:13:51', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (17, 'xcbdtjy', 'sryj', 2, 0, 0, null, '2025-04-26 02:13:52', '2025-04-26 02:13:52', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (18, 'agertuhmhjf', 'srj', 3, 0, 0, null, '2025-04-26 02:13:51', '2025-04-26 02:13:51', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (19, 'adsgfaerh', 'sryj', 4, 0, 0, null, '2025-04-26 02:13:54', '2025-04-26 02:13:54', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (20, 'sfbhyjtrdaer', 'sryj', 1, 0, 0, null, '2025-04-26 02:13:55', '2025-04-26 02:13:55', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (21, 'Dfhntyjsrthcf', 'srj', 2, 0, 0, null, '2025-04-26 02:13:55', '2025-04-26 02:13:55', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (22, 'rhyijtdgfbs', 'ssj', 3, 0, 0, null, '2025-04-26 02:13:48', '2025-04-26 02:13:48', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (23, 'Asdgerryhtscb', 'syj', 4, 0, 0, null, '2025-04-26 02:13:57', '2025-04-26 02:13:57', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (24, 'yhkdtyuikdtaf', 'sryj', 1, 0, 0, null, '2025-04-26 02:13:52', '2025-04-26 02:13:52', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (25, 'segyik7ufgbxfv', 'sj', 2, 0, 0, null, '2025-04-26 02:13:51', '2025-04-26 02:13:51', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (26, 'bytdyjstgjnzfg', 'srj', 3, 0, 0, null, '2025-04-26 02:13:54', '2025-04-26 02:13:54', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (27, 'Dtfjysryhzdfbd', 'syj', 4, 0, 0, null, '2025-04-26 02:13:55', '2025-04-26 02:13:55', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (28, 'sRhtszdfbgxfSR', 'trsr', 1, 0, 0, null, '2025-04-26 02:13:55', '2025-04-26 02:13:55', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (29, 'htrztrWegxcbv', 'ytuj', 2, 0, 0, null, '2025-04-26 02:13:48', '2025-04-26 02:13:48', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (30, 'sdhysrbzdf', 'trtr', 3, 0, 0, null, '2025-04-26 02:13:57', '2025-04-26 02:13:57', 0);
INSERT INTO realworld.article (id, title, body, user_id, likes, comments, tags, created_at, updated_at, deleted) VALUES (31, 'muzthatrjy', 'ssd', 4, 0, 0, null, '2025-04-26 02:13:52', '2025-04-26 02:13:52', 0);
