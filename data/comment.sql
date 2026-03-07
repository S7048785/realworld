create table comment
(
    id         int unsigned auto_increment
        primary key,
    body       varchar(255)         not null comment '评论内容',
    user_id    int unsigned         not null comment '评论者id',
    article_id int unsigned         not null comment '文章id',
    created_at datetime             not null comment '评论时间',
    deleted    tinyint(1) default 0 not null comment '标记删除状态'
)
    comment '文章评论表';

INSERT INTO realworld.comment (id, body, user_id, article_id, created_at, deleted) VALUES (1, '评论21235425', 1, 1, '2025-04-22 14:01:10', 0);
INSERT INTO realworld.comment (id, body, user_id, article_id, created_at, deleted) VALUES (2, 'rth467568', 2, 1, '2025-04-22 14:01:11', 0);
INSERT INTO realworld.comment (id, body, user_id, article_id, created_at, deleted) VALUES (3, '44444444', 2, 1, '2025-04-22 14:01:12', 0);
INSERT INTO realworld.comment (id, body, user_id, article_id, created_at, deleted) VALUES (4, 'Jane Doe有无名氏的意思在西方国家发生凶杀案无名女尸就被称为Jane Doe而蕾塞死在和电次赴约的小巷里也许在他人眼里只是死了一个Jane Doe而蕾塞最终想告诉电次其实她也没上过学可再也无法传达给电次对于电次而言蕾塞失约了也许电次也知道蕾塞失约的原因但是电次在扭曲的环境生长他面对蕾塞的死也许会伤心但是后面玛奇玛提出的约会让他在二者处理是继续伤心还是迎接玛奇玛的约会电次可以说他没心没肺的选择了后者所以蕾塞成为了真正的Jane Doe一个无名女尸只是活在读者的心中', 4, 1, '2026-01-02 18:57:27', 0);
INSERT INTO realworld.comment (id, body, user_id, article_id, created_at, deleted) VALUES (5, '好多人④都是前没铺垫后没售后（树e特色），姬野前辈快得我都没反应过来还以为是假死，蕾塞也是，我倒也不求电次能为她哭出来，结果后面一个好耶直接把我气笑了[微笑]电锯人的世界真是从头到脚的无力感，普通人说逝就逝，主要角色说逝就逝，秋的④甚至还有预告函，要是有人还惦记着他们离开也算，但是电次说忘就忘啊[笑哭]逝就逝了下一个会更好。电锯人2甚至又玄压抑上了，我甚至都怀疑电次还记不记得蕾塞了[吃瓜]', 3, 1, '2026-01-02 18:59:11', 0);
INSERT INTO realworld.comment (id, body, user_id, article_id, created_at, deleted) VALUES (6, '雷赛可悲但不可怜，死在玛奇玛手下也只能算报应', 5, 1, '2026-01-02 18:59:12', 0);
INSERT INTO realworld.comment (id, body, user_id, article_id, created_at, deleted) VALUES (7, '估计没人和我一样。单论最后片尾曲
1、卢浮宫
2、天之杯3春之歌
3、此曲', 2, 1, '2026-01-02 18:59:13', 0);
INSERT INTO realworld.comment (id, body, user_id, article_id, created_at, deleted) VALUES (8, '蕾塞最后没有拉环，不是她拉不动，而是她看到了咖啡店里捧花等待着的电次，她看到了自己必定被玛奇玛断送的结局，她不想变身闹太大动静让电次看着自己死去，这是她最后的温柔。', 2, 1, '2026-01-02 18:59:14', 0);
INSERT INTO realworld.comment (id, body, user_id, article_id, created_at, deleted) VALUES (9, '日本流行乐最严厉的父亲和母亲', 3, 1, '2026-01-02 18:59:17', 0);
INSERT INTO realworld.comment (id, body, user_id, article_id, created_at, deleted) VALUES (10, '没看过电锯人，听说后期很抽象还是咋的？但因为这首歌对蕾塞产生了兴趣，从头补番然后补这个剧场版能行吗。。。', 4, 1, '2026-01-02 18:59:17', 0);
INSERT INTO realworld.comment (id, body, user_id, article_id, created_at, deleted) VALUES (11, '藤本树看到剧场版这个票房表现不知道会不会在他那灾难性的电锯人第二部里画点蕾塞桥段，只不过目前来看复活蕾塞除了整烂活外也挽回不了稀碎的剧情', 7, 1, '2026-01-02 18:59:18', 0);
INSERT INTO realworld.comment (id, body, user_id, article_id, created_at, deleted) VALUES (12, '看完电影在电影院里听这个歌才叫劲大', 8, 1, '2026-01-02 18:59:19', 0);
INSERT INTO realworld.comment (id, body, user_id, article_id, created_at, deleted) VALUES (13, '我对电锯人永远都是一个评价，用外在的cult血浆片的外表来讲述爱的故事，电次经历的一切让了他从第一话索取爱和拥抱的人最终成长为了一个奉献温暖和怀抱的人[保佑]，它在我心里永远是一个内核温情的故事，藤本树不是疯子他一定是个温柔的人', 1, 1, '2026-01-02 18:59:20', 0);
INSERT INTO realworld.comment (id, body, user_id, article_id, created_at, deleted) VALUES (14, '没看过电锯人跟朋友一起去看的，开场前补了十分钟剧情，看完觉得有点不开心，好看但是不开心🙁怪怪的', 6, 1, '2026-01-02 18:59:21', 0);
INSERT INTO realworld.comment (id, body, user_id, article_id, created_at, deleted) VALUES (15, 'asda', 8, 3, '2026-01-02 20:06:57', 0);
INSERT INTO realworld.comment (id, body, user_id, article_id, created_at, deleted) VALUES (16, 'vvv', 8, 3, '2026-01-02 20:07:02', 0);
