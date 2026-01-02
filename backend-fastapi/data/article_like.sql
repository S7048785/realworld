create table article_like
(
    id         int unsigned auto_increment
        primary key,
    user_id    int unsigned         not null,
    article_id int unsigned         not null,
    created_at datetime             not null,
    deleted    tinyint(1) default 0 null
);

create index article_like_article_id_index
    on article_like (article_id);

create index article_like_user_id_index
    on article_like (user_id);

INSERT INTO realworld.article_like (id, user_id, article_id, created_at, deleted) VALUES (1, 1, 1, '2025-04-23 20:12:57', 0);
INSERT INTO realworld.article_like (id, user_id, article_id, created_at, deleted) VALUES (2, 2, 1, '2025-04-25 15:52:25', 0);
INSERT INTO realworld.article_like (id, user_id, article_id, created_at, deleted) VALUES (3, 1, 28, '2025-04-29 14:22:35', 0);
INSERT INTO realworld.article_like (id, user_id, article_id, created_at, deleted) VALUES (4, 1, 8, '2025-04-29 14:22:50', 0);
INSERT INTO realworld.article_like (id, user_id, article_id, created_at, deleted) VALUES (5, 1, 16, '2025-04-29 14:27:02', 0);
INSERT INTO realworld.article_like (id, user_id, article_id, created_at, deleted) VALUES (6, 1, 20, '2025-04-29 14:30:19', 1);
INSERT INTO realworld.article_like (id, user_id, article_id, created_at, deleted) VALUES (7, 1, 24, '2025-04-29 15:23:58', 1);
INSERT INTO realworld.article_like (id, user_id, article_id, created_at, deleted) VALUES (8, 8, 1, '2025-12-31 16:41:40', 0);
INSERT INTO realworld.article_like (id, user_id, article_id, created_at, deleted) VALUES (9, 8, 2, '2025-12-31 16:42:51', 0);
INSERT INTO realworld.article_like (id, user_id, article_id, created_at, deleted) VALUES (10, 8, 3, '2025-12-31 16:44:13', 0);
INSERT INTO realworld.article_like (id, user_id, article_id, created_at, deleted) VALUES (11, 8, 4, '2025-12-31 16:44:41', 0);
INSERT INTO realworld.article_like (id, user_id, article_id, created_at, deleted) VALUES (12, 8, 6, '2025-12-31 16:45:10', 0);
INSERT INTO realworld.article_like (id, user_id, article_id, created_at, deleted) VALUES (13, 8, 8, '2025-12-31 16:45:15', 0);
INSERT INTO realworld.article_like (id, user_id, article_id, created_at, deleted) VALUES (14, 8, 9, '2025-12-31 16:45:26', 0);
