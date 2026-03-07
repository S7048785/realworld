create table article
(
    id         int unsigned auto_increment
        primary key,
    title      varchar(255)                 not null comment '文章标题',
    `desc`     varchar(255)                 not null comment '描述',
    body       text                         not null comment '文章内容',
    user_id    int unsigned                 not null comment '作者id',
    likes      int unsigned default '0'     not null,
    views      int unsigned default '0'     not null,
    comments   int unsigned default '0'     not null,
    tags       json                         null,
    created_at datetime     default (now()) not null comment '创建时间',
    updated_at datetime     default (now()) not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted    tinyint(1)   default 0       not null comment '标记删除状态'
)
    comment '文章表';

create index article_id_index
    on article (id);

create index article_user_id_index
    on article (user_id);

INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (1, '🏆2025 AI/Vibe Coding 对我的影响 | 年终技术征文', '当岁末的钟声临近，我们又站在了一年的重点回望。2025年，对你而言，是怎样的轮廓呢？它或许是由一行行被AI重构的代码勾勒，是某个深夜与新技术“顿悟时刻”的灵光一现，也可能是生活中因为智能体工具而悄然改变的工作状态。从智能体（Agent）的横空出世到多模态技术的经验突破，技术愈加深入地流淌尽我们的工作和生活日常，塑造着属于每个人的独特“Vibe”。', '当岁末的钟声临近，我们又站在了一年的重点回望。2025年，对你而言，是怎样的轮廓呢？它或许是由一行行被AI重构的代码勾勒，是某个深夜与新技术“顿悟时刻”的灵光一现，也可能是生活中因为智能体工具而悄然改变的工作状态。从智能体（Agent）的横空出世到多模态技术的经验突破，技术愈加深入地流淌尽我们的工作和生活日常，塑造着属于每个人的独特“Vibe”。', 2, 13, 183, 0, '["前端", "Three.js"]', '2025-04-18 22:46:18', '2026-03-06 15:41:51', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (2, 'Go 语言未来会取代 Java 吗？', 'Go 语言作为一种新兴的编程语言，自2009年发布以来，已经经历了多个版本的迭代。它的设计目标是简单、高效、并发安全。与 Java 相比，Go 语言在语法上更加简洁，同时提供了更好的并发支持。', 'Go 语言作为一种新兴的编程语言，自2009年发布以来，已经经历了多个版本的迭代。它的设计目标是简单、高效、并发安全。与 Java 相比，Go 语言在语法上更加简洁，同时提供了更好的并发支持。', 3, 26, 76, 0, '["后端", "Java"]', '2025-04-22 13:57:05', '2026-03-06 17:07:56', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (3, '从一个程序员的角度告诉你：“12306”有多牛逼？', '每到节假日期间，一二线城市返乡、外出游玩的人们几乎都面临着一个问题：抢火车票！
', '每到节假日期间，一二线城市返乡、外出游玩的人们几乎都面临着一个问题：抢火车票！

12306 抢票，极限并发带来的思考

虽然现在大多数情况下都能订到票，但是放票瞬间即无票的场景，相信大家都深有体会。

尤其是春节期间，大家不仅使用 12306，还会考虑“智行”和其他的抢票软件，全国上下几亿人在这段时间都在抢票。

“12306 服务”承受着这个世界上任何秒杀系统都无法超越的 QPS，上百万的并发再正常不过了！

笔者专门研究了一下“12306”的服务端架构，学习到了其系统设计上很多亮点，在这里和大家分享一下并模拟一个例子：如何在 100 万人同时抢 1 万张火车票时，系统提供正常、稳定的服务。', 4, 16, 82, 0, '["前端", "后端", "GitHub"]', '2025-04-22 13:57:06', '2026-03-05 21:33:08', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (4, '2025年推荐一些程序员常逛的开发者社区', '在信息技术日新月异的今天，程序员作为推动技术进步的重要力量，始终在探索、学习和交流的道路上不断前行。为了帮助程序员们更好地拓宽视野、提升技能，本文大姚将给大家推荐12个程序员常逛的开发者社区。', '在信息技术日新月异的今天，程序员作为推动技术进步的重要力量，始终在探索、学习和交流的道路上不断前行。为了帮助程序员们更好地拓宽视野、提升技能，本文大姚将给大家推荐12个程序员常逛的开发者社区。', 5, 70, 79, 0, '["后端", "程序员"]', '2025-04-22 13:57:07', '2026-03-06 15:56:36', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (6, 'grid快速入门', '如果你已经掌握了Flex布局，那么学习Grid布局会相对容易，因为两者在概念上有相似之处，但Grid提供了更强大的二维布局能力。下面我将带你快速掌握Grid布局的核心概念和用法。', '如果你已经掌握了Flex布局，那么学习Grid布局会相对容易，因为两者在概念上有相似之处，但Grid提供了更强大的二维布局能力。下面我将带你快速掌握Grid布局的核心概念和用法。

## 一、Grid与Flex的核心区别

1. **维度差异**：
- Flex是一维布局系统，适合处理单行或单列的布局（如导航栏、卡片列表）
- Grid是二维布局系统，可以同时控制行和列（适合整体页面布局）

2. **应用场景**：
- Flex更适合局部组件布局
- Grid更适合整体页面规划

## 二、Grid基础概念

### 1. 容器与项目
- **容器**：设置`display: grid`的元素
- **项目**：容器的直接子元素（注意：孙子元素不算项目）

```css
.container {
display: grid; /* 或 inline-grid */
}
```

### 2. 轨道(Track)
- **行轨道**：水平方向的网格线之间的区域
- **列轨道**：垂直方向的网格线之间的区域

### 3. 单元格(Cell)
行和列的交叉区域，n行m列会产生n×m个单元格

### 4. 网格线(Grid Line)
划分网格的线，n行有n+1根水平网格线，m列有m+1根垂直网格线

## 三、容器属性详解

### 1. 定义行列
```css
.container {
grid-template-columns: 100px 1fr 2fr; /* 定义3列 */
grid-template-rows: 50px auto 100px; /* 定义3行 */
}
```
- `fr`单位：按比例分配剩余空间（类似flex-grow）
- `auto`：根据内容自动调整
- `repeat()`函数：简化重复值`repeat(3, 1fr)`相当于`1fr 1fr 1fr`

### 2. 间距设置
```css
.container {
gap: 20px 10px; /* 行间距20px，列间距10px */
/* 旧版写法：grid-gap: 20px 10px; */
}
```

### 3. 自动布局流
```css
.container {
grid-auto-flow: row; /* 默认，先行后列 */
/* 可选值：column(先列后行), row dense, column dense */
}
```
`dense`关键字会尝试填充网格中的空白

### 4. 区域模板
```css
.container {
grid-template-areas:
"header header header"
"sidebar main main"
"footer footer footer";
}

.header { grid-area: header; }
.sidebar { grid-area: sidebar; }
/* ... */
```
用`.`表示空单元格

## 四、项目属性

### 1. 项目定位
```css
.item {
grid-column: 1 / 3; /* 从第1列开始，跨越到第3列前 */
grid-row: 1 / span 2; /* 从第1行开始，跨越2行 */
}
```
- 可以使用`span`关键字表示跨越数量
- 网格线编号从1开始

### 2. 项目对齐
```css
.item {
justify-self: center; /* 水平对齐 */
align-self: end; /* 垂直对齐 */
place-self: center end; /* 简写形式 */
}
```

## 五、实用技巧

### 1. 响应式布局
```css
.container {
grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
}
```
- `auto-fill`：尽可能多地填充列
- `minmax()`：定义最小和最大尺寸

### 2. 隐式网格
当项目超出明确定义的网格时，会自动创建隐式轨道：
```css
.container {
grid-auto-rows: 100px; /* 隐式行高 */
grid-auto-columns: 1fr; /* 隐式列宽 */
}
```

### 3. 简写属性
```css
.container {
grid: auto-flow dense / 1fr 1fr; /* 隐式行/显式列 */
}

.item {
grid-area: 1 / 1 / 3 / 3; /* row-start/col-start/row-end/col-end */
}
```

## 六、从Flex到Grid的思维转换

1. **主轴/交叉轴 → 行/列**：
- Flex中你熟悉的主轴概念在Grid中分为明确的行和列

2. **对齐方式**：
- `justify-content` → `justify-items`(网格内)和`justify-content`(整个网格)
- `align-items` → `align-items`(网格内)和`align-content`(整个网格)

3. **项目大小控制**：
- Flex中用`flex-grow/flex-shrink`
- Grid中用`fr`单位和`minmax()`函数

## 七、实战示例

### 经典布局实现
```css
.container {
display: grid;
grid-template-columns: 200px 1fr;
grid-template-rows: 60px 1fr 60px;
grid-template-areas:
"header header"
"sidebar main"
"footer footer";
height: 100vh;
gap: 10px;
}

.header { grid-area: header; }
.sidebar { grid-area: sidebar; }
.main { grid-area: main; }
.footer { grid-area: footer; }
```

### 九宫格布局
```css
.container {
display: grid;
grid-template: repeat(3, 1fr) / repeat(3, 1fr);
gap: 5px;
aspect-ratio: 1/1;
}
```

## 八、浏览器支持与注意事项

1. 现代浏览器都已支持Grid布局
2. 注意旧版属性前缀（如`grid-gap`现已简化为`gap`）
3. 调试工具：浏览器开发者工具中的Grid检查器非常有用

## 学习资源推荐

1. [CSS Grid Garden](https://cssgridgarden.com/) - 互动式学习游戏
2. MDN文档 - 最全面的参考资源
3. 使用浏览器开发者工具实时调试网格布局

记住，Grid和Flex不是竞争关系，而是互补关系。在实际项目中，经常会在Grid容器中使用Flexbox进行更精细的项目内部布局。', 1, 2, 6, 0, '["前端", "CSS"]', '2025-04-22 23:17:01', '2026-03-06 15:58:59', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (8, '腾讯一面:40亿QQ号,不超过1G内存,如何去重?', '分享一道网上很火的腾讯面试题:40亿的QQ号,如何去重,1G的内存. 不过,有腾讯上班的朋友说,我们没出过这种面试题~ 哈哈~', '## 前言

大家好,我是**田螺**.

分享一道网上**很火的腾讯面试题**:40亿的QQ号,如何去重,1G的内存. 不过,有腾讯上班的朋友说,我们没出过这种面试题\\~ 哈哈\\~

![image.png](https://p9-xtjj-sign.byteimg.com/tos-cn-i-73owjymdk6/9f4227bd6bd24c00b56cf859af392f8d~tplv-73owjymdk6-jj-mark-v1:0:0:0:0:5o6Y6YeR5oqA5pyv56S-5Yy6IEAg5o2h55Sw6J6655qE5bCP55S35a2p:q75.awebp?rk3s=f64ab15b&x-expires=1767009604&x-signature=eSMZc7lTMxnwwGBWssIx%2Fi6UyEM%3D)

哈哈,anyway,这道题还是很有意思的. 它是一个非常经典的海量数据去重问题,并且做了内存限制,只能1G.本文田螺哥跟大家探讨一下.

* **公众号**：**捡田螺的小男孩** （有田螺精心原创的面试PDF）
* github地址，感谢每颗star：[github](https://link.juejin.cn?target=https%3A%2F%2Fgithub.com%2Fwhx123%2FJavaHome "https://github.com/whx123/JavaHome")

## 1. 常规思路

我们日常开发中,如果**谈到去重**,最容易想到的就是放到`HashSet`,直接放到`HashSet`就好:

```ini
ini
 体验AI代码助手
 代码解读
复制代码
Set<Long> qqSet = new HashSet<>();
qqSet.add(qqNumber); // 自动去重
```

但是呢,是有个1G的内存限制的! 如果放到`HashSet`,那40亿的QQ数据,都是在内存中的,我们来算一下,40亿的QQ,要多大的内存:

如果每个QQ号是64位整数（8字节），那么40亿个QQ号的总存储量为：

```ini
ini
 体验AI代码助手
 代码解读
复制代码
40亿 * 8字节 = 320亿字节
转化位KB 32,000,000,000 字节/1024 = 31,250,000 KB
KB转化为MB 31,250,000 KB/ 1024 ≈ 30,517.578125 MB
MB转化为GB  30,517.578125 MB/ 1024 ≈ 29.8023223876953125 GB
```

那就是`30GB`左右,如果每个QQ号码是32位整数(4字节),则是`15GB`左右. 显然,都远超1GB的内存.

因此,直接放到`HashSet`**并不可行**.

因此,这道题我们需要换个思路,就是在内存有限的情况下,**如何实现去重**? 我们可以考虑一种更高效的数据结构来处理这个问题。

我们可以考虑BitMap（位图)来解决这个问题.

## 2. BitMap

### 2.1 BitMap 到底是什么

> BitMap（位图）是一种非常高效的数据结构，特别适合处理大规模数据的去重和查询问题。它的基本思想是使用一个bit位来表示一个数字是否存在。

例如，如果我们有一个长度为10的BitMap，那么它可以表示数字0到9是否存在：

* 如果BitMap的第0位是1，表示数字0存在；
* 如果BitMap的第1位是1，表示数字1存在；
* 如果BitMap的第2位是1，表示数字2存在；

以此类推\\~

数字9表示的BitMap如下:

![](https://p9-xtjj-sign.byteimg.com/tos-cn-i-73owjymdk6/77b784b4933f4fe0ab1f2a452bcfbe01~tplv-73owjymdk6-jj-mark-v1:0:0:0:0:5o6Y6YeR5oqA5pyv56S-5Yy6IEAg5o2h55Sw6J6655qE5bCP55S35a2p:q75.awebp?rk3s=f64ab15b&x-expires=1767009604&x-signature=nJmMHzr7uxQ3QQbFFBaWonG8gFg%3D)

如果用BitMap,比如我要记录的QQ号码分别是9、5、2、7, 那么BitMap表示为

![](https://p9-xtjj-sign.byteimg.com/tos-cn-i-73owjymdk6/fb38e0b060264a99a8aa3950eb265ca1~tplv-73owjymdk6-jj-mark-v1:0:0:0:0:5o6Y6YeR5oqA5pyv56S-5Yy6IEAg5o2h55Sw6J6655qE5bCP55S35a2p:q75.awebp?rk3s=f64ab15b&x-expires=1767009604&x-signature=ZhGaBtCdcE9QMpEUyacTyOMNbJw%3D)

显然只需要一个10位就可以表示,如果用传统方法来记录,一个整型4字节,4个QQ号码就是,4*4=16字节,然后一个字节8位,那就是 16*8=128位啦\\~. 可以发现用BitMap 可以大大**节省存储空间**.

### 2.2 用BitMap给40亿QQ去重

#### 2.2.1 使用BitMap,40亿的QQ是否超过1GB内存

既然BitMap 可以大大节省存储空间,我们用BitMap来给40亿QQ去重,看看**会不会超1G的内存.**

我们来一起估算一下, 因为要40亿的QQ,那我们申请一个足够大的BitMap,假设就是40亿的位,那内存大概就是:

```ini
ini
 体验AI代码助手
 代码解读
复制代码
4,000,000,000/8 = 500,000,000 
500,000,000/1024/1024/1024 ≈ 0.466GB
```

可以发现,只需要`0.466GB`的内存就足够啦\\~ 在**内存**这方面,是符**合不超过1GB的限制的**\\~

#### 2.2.2 使用BitMap,给40亿QQ 去重流程

1. 首先,初始化好40亿位的BitMap
2. 其次,遍历这40亿的QQ,把每个QQ号码映射到BitMap中,给对应位置的bit,设置为1

> 比如,假设有个QQ号码为326443281,那么就在BitMap的对应位置,设置为1

3. 遍历BitMap，收集所有设置为1的位对应的QQ号码,即为去重后的QQ号码。

![](https://p9-xtjj-sign.byteimg.com/tos-cn-i-73owjymdk6/3e55d3a61c2d430e856d5d6cb2143459~tplv-73owjymdk6-jj-mark-v1:0:0:0:0:5o6Y6YeR5oqA5pyv56S-5Yy6IEAg5o2h55Sw6J6655qE5bCP55S35a2p:q75.awebp?rk3s=f64ab15b&x-expires=1767009604&x-signature=%2BrCS4buo7kMqt9Yo6iXs7K1roPE%3D)

### 2.3 BitMap去重的简单代码实现

给大家来个简单的代码模拟吧:

```java
java
 体验AI代码助手
 代码解读
复制代码
import java.util.*;

public class QQDeduplication {

    // 位图的大小为 4,294,967,296 bits，即 0.5 GB
    private static final long BITMAP_SIZE = 1L << 32; // 2^32
    private static final int BYTE_SIZE = 8; // 每个字节有8位

    private static List<Long> deduplicateQQNumbers(long[] qqNumbers) {
        // 创建位图，使用字节数组
        byte[] bitmap = new byte[(int) (BITMAP_SIZE / BYTE_SIZE)];

        // 更新位图
        for (long qqNumber : qqNumbers) {
            if (qqNumber >= 0 && qqNumber < BITMAP_SIZE) {
                // 计算字节索引和位索引
                int index = (int) (qqNumber / BYTE_SIZE);
                int bitPosition = (int) (qqNumber % BYTE_SIZE);
                // 设置对应的位
                bitmap[index] |= (1 << bitPosition);
            }
        }

        // 收集去重后的QQ号码
        List<Long> uniqueQQNumbers = new ArrayList<>();
        for (int i = 0; i < bitmap.length; i++) {
            for (int j = 0; j < BYTE_SIZE; j++) {
                if ((bitmap[i] & (1 << j)) != 0) {
                    long qqNumber = (long) i * BYTE_SIZE + j;
                    uniqueQQNumbers.add(qqNumber);
                }
            }
        }

        return uniqueQQNumbers;
    }
}
```

### 2.4 BitMap的优缺点

我们使用一种数据结构去解决问题,那肯定要知道它的优缺点对吧.

**Bitmap的优点**

* 空间效率高

> 相比哈希表存储原始数据，Bitmap仅用1位/元素。对于密集数据（如连续QQ号），空间利用率极高。

* 操作非常高效

> 插入和查询均为O(1)复杂度，位运算速度快，适合海量数据实时处理。

* 去重逻辑简单

> 只需遍历数据，置位存在标记，无需复杂结构。

**Bitmap的缺点**

* 存储空间依赖值域范围

> 若值域范围大但稀疏（如QQ号上限远大于实际数量），空间浪费严重。例如，若QQ号上限为1万亿，需125GB内存，难以承受。

* 无法存储额外信息,只能记录有还是没有

> 仅记录是否存在，无法保存出现次数等元数据。

## 最后

有些伙伴认为,使用布隆过滤器也可以实现,40亿的QQ号,不超过1G的内存,进行去重.大家觉得呢? 欢迎评论区留言讨论哈. 希望大家都找到心仪的offer \\~\\~

BitMap 的存储空间与值域强相关

BitMap 的存储空间需求直接取决于 值域的大小，而不是实际数据的数量。

值域：指数据可能的取值范围（如 QQ 号的最小值和最大值之间的范围）。

存储空间：BitMap 需要为值域中的每一个可能值分配一个 bit 位，无论该值是否实际存在。
', 6, 279, 214, 0, '["后端", "面试", "Java"]', '2025-04-22 23:42:12', '2026-03-05 21:26:48', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (9, '豆包手机为什么会被其他厂商抵制？它的工作原理是什么？', '之所以会想写这个，首先是因为在知乎收到了这个推荐的问题，实际上不管是 AutoGLM 还是豆包 AI 手机，会在这个阶段被第三方厂商抵制并不奇怪，比如微信和淘宝一直以来都很抵制这种外部自动化操作，而非这次中兴的 AI 豆包手机出来才抵制，毕竟以前搞过微信自动化客服应该都知道，一不小心就会被封号。', '之所以会想写这个，首先是因为在知乎收到了这个推荐的问题，实际上不管是 AutoGLM 还是豆包 AI 手机，会在这个阶段被第三方厂商抵制并不奇怪，**比如微信和淘宝一直以来都很抵制这种外部自动化操作**，而非这次中兴的 AI 豆包手机出来才抵制，毕竟以前搞过微信自动化客服应该都知道，一不小心就会被封号。

![image-20251212081056229](https://p9-xtjj-sign.byteimg.com/tos-cn-i-73owjymdk6/1dfd3aab7d104ebeadc286242f4f5894~tplv-73owjymdk6-jj-mark-v1:0:0:0:0:5o6Y6YeR5oqA5pyv56S-5Yy6IEAg5oGL54yrZGXlsI_pg60=:q75.awebp?rk3s=f64ab15b&x-expires=1767309463&x-signature=7xCUA6twD5lHT8n6ypEoh%2BtShb0%3D)

另外也是刚好看到， B 站的 UP 主老戴深入分析了豆包手机的内部工作机制的视频，视频介绍了**从 AI 助手如何读取屏幕、捕捉数据和模拟操作的真实流程**，所以对于 AI 手机又有了个更深刻的认知，在这个基础上，更不难理解为什么 AI 手机这种自动化 Agent 会被第三方厂商抵制，推荐大家看原视频：[b23.tv/pftlDX8](https://link.juejin.cn?target=https%3A%2F%2Fb23.tv%2FpftlDX8 "https://b23.tv/pftlDX8") 。

![](https://p9-xtjj-sign.byteimg.com/tos-cn-i-73owjymdk6/96382e7fc7f74911a477330507583b3e~tplv-73owjymdk6-jj-mark-v1:0:0:0:0:5o6Y6YeR5oqA5pyv56S-5Yy6IEAg5oGL54yrZGXlsI_pg60=:q75.awebp?rk3s=f64ab15b&x-expires=1767309463&x-signature=tvmhKrpDaPmV4w%2FjZhcjUSIteVU%3D)

那么豆包的 AI 手机是怎么工作的呢？实际上和大家想的可能不一样，**它并没有使用无障碍服务（Accessibility Service），而是使用了更底层的实现方案**：

> 豆包手机利用底层的系统权限，**直接从 GPU 缓冲区获取原始图像数据并注入输入事件，而非依赖截屏或无障碍服务**，此外手机还在一个独立的虚拟屏幕中执行后台任务，并将图像低频发送至云端进行推理，云端则返回操作指令。

在视频里， UP 主通过深度拆解豆包手机，分析手机在系统层面的服务分工、数据抓取和模型推理路径，例如`aikernel`被 UP 主推断为手机端侧 AI 的核心进程，内存占用特性（Native堆高达160M）表明它可能是一个本地AI推理框架：

![](https://p9-xtjj-sign.byteimg.com/tos-cn-i-73owjymdk6/1a8d1d5381a042609bac7dc5856ba83b~tplv-73owjymdk6-jj-mark-v1:0:0:0:0:5o6Y6YeR5oqA5pyv56S-5Yy6IEAg5oGL54yrZGXlsI_pg60=:q75.awebp?rk3s=f64ab15b&x-expires=1767309463&x-signature=7W2ro5WSJndwCHQiUlVKXzLR%2FVk%3D)

> 另外 `aikernel` 异常高的Binder数量，证明有大量外部进程通过 RPC 调用它，进一步印证了其系统级服务的角色 。

而 `autoaction`是豆包手机 AI 自动操作的关键，这个 APK 权限允许直接从 GPU 渲染的图形缓冲区读取数据，而不是通过上层截图：

![](https://p9-xtjj-sign.byteimg.com/tos-cn-i-73owjymdk6/bb9086c0a3c7485d983d13a132986632~tplv-73owjymdk6-jj-mark-v1:0:0:0:0:5o6Y6YeR5oqA5pyv56S-5Yy6IEAg5oGL54yrZGXlsI_pg60=:q75.awebp?rk3s=f64ab15b&x-expires=1767309463&x-signature=fcFKBCiKQ0kRCtBt5tdtYCLeLVA%3D)

而且目前看，豆包手机的 AI 能够捕获受保护的视频输出，**这意味着它可以绕过银行 App 等应用的反截图/录屏限制** ，因为很多银行 App 很多是通过 DRM（数字版权管理） 或应用内安全设置来防止截屏和录屏：

![](https://p9-xtjj-sign.byteimg.com/tos-cn-i-73owjymdk6/e5abc40fe572480a8a94680378d2091a~tplv-73owjymdk6-jj-mark-v1:0:0:0:0:5o6Y6YeR5oqA5pyv56S-5Yy6IEAg5oGL54yrZGXlsI_pg60=:q75.awebp?rk3s=f64ab15b&x-expires=1767309463&x-signature=JDgmgW06TYL%2BLhVW%2BPxY54rQNek%3D)

另外，Agent 在操作手机过程也不是直接使用系统的 Accessibility Service ，而是通过调用系统隐藏API `injectInputEvent` 来控制手机， AI 通过 `INJECT_EVENTS` 权限直接注入输入事件来模拟屏幕点击，权限高于无障碍 API，并且是系统签名：

![](https://p9-xtjj-sign.byteimg.com/tos-cn-i-73owjymdk6/8e56e56abf874133a28a61b4fd14aec2~tplv-73owjymdk6-jj-mark-v1:0:0:0:0:5o6Y6YeR5oqA5pyv56S-5Yy6IEAg5oGL54yrZGXlsI_pg60=:q75.awebp?rk3s=f64ab15b&x-expires=1767309463&x-signature=cpRhPNgHp2mkjqmKAgfBgCD4jvk%3D)

![](https://p9-xtjj-sign.byteimg.com/tos-cn-i-73owjymdk6/828f5b45bdc24a5483f4feef201de3d3~tplv-73owjymdk6-jj-mark-v1:0:0:0:0:5o6Y6YeR5oqA5pyv56S-5Yy6IEAg5oGL54yrZGXlsI_pg60=:q75.awebp?rk3s=f64ab15b&x-expires=1767309463&x-signature=PRcTmY%2BNGgmMhTLe3%2BC7vMCfZFI%3D)

同时，豆包手机在执行自动操作时，会利用一个与物理屏幕分辨率相同的“无头”虚拟屏幕在后台运行，且拥有独立的焦点，不影响用户在前台的操作，这其实就是内存副屏的概念， 虚拟屏幕的画面由 GPU 合成后，对应的缓冲区信息会直接被`autoaction`消费，再次证实 AI 无需通过截图 API 即可获取屏幕内容 ：

![image-20251212085211526](https://p9-xtjj-sign.byteimg.com/tos-cn-i-73owjymdk6/c403dfb7436e462d8f452ebce81bff99~tplv-73owjymdk6-jj-mark-v1:0:0:0:0:5o6Y6YeR5oqA5pyv56S-5Yy6IEAg5oGL54yrZGXlsI_pg60=:q75.awebp?rk3s=f64ab15b&x-expires=1767309463&x-signature=gry9uxLOvAoid7S5QMSHNceS91A%3D)

最后，豆包手机在自动化操作时，会频繁地（每3到5秒）与 `obriccloud.com `（字节的服务） 服务器通信，发送约 250K的单帧图片进行推理。

云端在接收图片后，会返回约 1K 的数据，内容是告诉手机下一步要执行的 7 种指令之一，如打开应用、点击、输入、滑动等等，整个自动化 Agent 的推理和路径规划主要在云端完成，云端思考后将执行步骤指令发回本地执行，本地任务很轻：

![](https://p9-xtjj-sign.byteimg.com/tos-cn-i-73owjymdk6/dfc50f47608a47bca475a16e456d4fef~tplv-73owjymdk6-jj-mark-v1:0:0:0:0:5o6Y6YeR5oqA5pyv56S-5Yy6IEAg5oGL54yrZGXlsI_pg60=:q75.awebp?rk3s=f64ab15b&x-expires=1767309463&x-signature=22VIi0lmpJFrL%2FIK6YneK6MjfM4%3D) ![](https://p9-xtjj-sign.byteimg.com/tos-cn-i-73owjymdk6/7f9f084ee4ed447ebcc951985d79c930~tplv-73owjymdk6-jj-mark-v1:0:0:0:0:5o6Y6YeR5oqA5pyv56S-5Yy6IEAg5oGL54yrZGXlsI_pg60=:q75.awebp?rk3s=f64ab15b&x-expires=1767309463&x-signature=NaZtp0lnTPGMdUGILLhA4c%2Fu8CE%3D)

> 那么，这整个过程你看下来有什么感觉？如果你是第三方厂商，你会不会同样抵制这种数据收集和处理的行为？特别是绕过现有大家对系统 API 的理解，这种操作途径是否能被友商们接受？

所以目前的这种操作，被微信和淘宝抵制很正常，不管是隐私的边界，还有安全操作的规范，用户对于自己某个产品内容被收集的信息程度，这些都还处于蛮荒状态，**数据安全和隐私的边界范围还不可控，并且 Agent 的托管行为，也明显侵犯到了友商们的利益链条**。

就像是 UP 主说的，**AI Agent 的出现将动摇移动互联网的底层商业逻辑——注意力经济**，使“注意力”这一硬通货的重要性降低 ，实际上换作另一个概念就是**碎片化时间**：

> 以前你的碎片化时间都是被各种 App 消费了，比如广告和沉浸引导，但是 Agent 的出现，它明显将这部分时间给托管了，那么数据和时间都被 Agent 服务收集，对于友商们来说，不就是成了单纯的功能性服务商了吗？

另外，说实话像 AutoGLM 这种功能目前的支持，最大受益者不是用户而是灰产，不管是用诈骗还是黄牛，他们都是这种自动化下的第一受益者，所以规范和监管，特别是安全和隐私条款是必须，比如就像 UP 主说的：

> 豆包手机的 AI 在自动化操作过程中，哪些数据会被发送到云端服务器？

很多人对于 agent 和自动化能力的范畴并不理解，它们可以获取隐私的边界是什么，安全操作的规范是什么，这些都是需要支持和统一边界。

> 比如 Android 16 实际上官方是有规划 Appfunction Api 的，它的目的是让应用只公布自己开放给 AI 的能力，这样也许边界感更强。

当然，从历史的角度看，Agent 手机势不可挡，就像谷歌自己未来新的 Android PC 系统 Aluminium OS 也是会结合 Gemini Agent 等特点，这是历史进程的必然，但是这个过程中，如何统一规范和监管这是很重要的过程，毕竟 AI 的效应和能力，可比之前更加强，就像 UP 主说的，**新的 AI 寡头可能会形成更中心化、更强势的权力，且马太效应更明显** 。

那么，你觉得未来谁家的 Agent 设备会成为新时达的寡头？或者不是手机而是眼镜？

## 视频链接

[b23.tv/pftlDX8](https://link.juejin.cn?target=https%3A%2F%2Fb23.tv%2FpftlDX8 "https://b23.tv/pftlDX8")
', 7, 46, 95, 0, '["Android", "AI编程"]', '2025-04-24 22:28:34', '2025-12-31 16:45:26', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (10, 'wegretsr', '', 'aa', 2, 0, 0, 0, null, '2025-04-26 02:13:38', '2025-04-26 02:13:38', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (11, 'tumuildrt', '', 'ss', 3, 0, 0, 0, null, '2025-04-26 02:13:39', '2025-04-26 02:13:39', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (12, 'fjtdsetjn', '', 'ss', 4, 0, 0, 0, null, '2025-04-26 02:13:41', '2025-04-26 02:13:41', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (13, 'wsrsfbfgc', '', 'dd', 2, 0, 0, 0, null, '2025-04-26 02:13:42', '2025-04-26 02:13:42', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (14, 'sfgdfbsrt', '', 'dd', 3, 0, 0, 0, null, '2025-04-26 02:13:43', '2025-04-26 02:13:43', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (15, 'wsrgdfb', '', 'srj', 4, 0, 0, 0, null, '2025-04-26 02:13:50', '2025-04-26 02:13:50', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (16, 'szrgeth', '', 'sryj', 1, 0, 0, 0, null, '2025-04-26 02:13:51', '2025-04-26 02:13:51', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (17, 'xcbdtjy', '', 'sryj', 2, 0, 0, 0, null, '2025-04-26 02:13:52', '2025-04-26 02:13:52', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (18, 'agertuhmhjf', '', 'srj', 3, 0, 0, 0, null, '2025-04-26 02:13:51', '2025-04-26 02:13:51', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (19, 'adsgfaerh', '', 'sryj', 4, 0, 0, 0, null, '2025-04-26 02:13:54', '2025-04-26 02:13:54', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (20, 'sfbhyjtrdaer', '', 'sryj', 1, 0, 0, 0, null, '2025-04-26 02:13:55', '2025-04-26 02:13:55', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (21, 'Dfhntyjsrthcf', '', 'srj', 2, 0, 0, 0, null, '2025-04-26 02:13:55', '2025-04-26 02:13:55', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (22, 'rhyijtdgfbs', '', 'ssj', 3, 0, 0, 0, null, '2025-04-26 02:13:48', '2025-04-26 02:13:48', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (23, 'Asdgerryhtscb', '', 'syj', 4, 0, 0, 0, null, '2025-04-26 02:13:57', '2025-04-26 02:13:57', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (24, 'yhkdtyuikdtaf', '', 'sryj', 1, 0, 0, 0, null, '2025-04-26 02:13:52', '2025-04-26 02:13:52', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (25, 'segyik7ufgbxfv', '', 'sj', 2, 0, 0, 0, null, '2025-04-26 02:13:51', '2025-04-26 02:13:51', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (26, 'bytdyjstgjnzfg', '', 'srj', 3, 0, 0, 0, null, '2025-04-26 02:13:54', '2025-04-26 02:13:54', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (27, 'Dtfjysryhzdfbd', '', 'syj', 4, 0, 0, 0, null, '2025-04-26 02:13:55', '2025-04-26 02:13:55', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (28, 'sRhtszdfbgxfSR', '', 'trsr', 1, 0, 0, 0, null, '2025-04-26 02:13:55', '2025-04-26 02:13:55', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (29, 'htrztrWegxcbv', '', 'ytuj', 2, 0, 0, 0, null, '2025-04-26 02:13:48', '2025-04-26 02:13:48', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (30, 'sdhysrbzdf', '', 'trtr', 3, 0, 0, 0, null, '2025-04-26 02:13:57', '2025-04-26 02:13:57', 0);
INSERT INTO realworld.article (id, title, `desc`, body, user_id, likes, views, comments, tags, created_at, updated_at, deleted) VALUES (31, 'muzthatrjy', '', 'ssd', 4, 0, 0, 0, null, '2025-04-26 02:13:52', '2025-04-26 02:13:52', 0);
