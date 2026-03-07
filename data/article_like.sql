create table article_like
(
    id         int unsigned auto_increment
        primary key,
    user_id    int unsigned         not null,
    article_id int unsigned         not null,
    created_at datetime             not null,
    deleted    tinyint(1) default 0 not null
);

create index article_like_article_id_index
    on article_like (article_id);

create index article_like_user_id_index
    on article_like (user_id);

INSERT INTO realworld.article_like (id, user_id, article_id, created_at, deleted) VALUES (15, 1, 3, '2026-03-04 21:25:05', 0);
INSERT INTO realworld.article_like (id, user_id, article_id, created_at, deleted) VALUES (16, 1, 1, '2026-03-04 21:26:25', 0);
INSERT INTO realworld.article_like (id, user_id, article_id, created_at, deleted) VALUES (17, 1, 2, '2026-03-06 15:53:55', 0);
INSERT INTO realworld.article_like (id, user_id, article_id, created_at, deleted) VALUES (18, 1, 4, '2026-03-06 15:56:37', 0);
INSERT INTO realworld.article_like (id, user_id, article_id, created_at, deleted) VALUES (19, 1, 6, '2026-03-06 15:59:00', 0);
