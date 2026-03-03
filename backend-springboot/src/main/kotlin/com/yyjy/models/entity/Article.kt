package com.yyjy.models.entity

import com.yyjy.common.ArticleIsLikedResolver
import org.babyfish.jimmer.sql.*
import java.time.LocalDateTime

/**
 * 文章表
 */
@Entity
interface Article {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    val id: Int

    /**
     * 文章标题
     */
    val title: String

    /**
     * 描述
     */
    val desc: String

    /**
     * 文章内容
     */
    val body: String

    /**
     * 作者
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    val author: User

    val likes: Long

    val views: Long

    val comments: Long

    val tags: String?

    /**
     * 创建时间
     */
    val createdAt: LocalDateTime

    /**
     * 更新时间
     */
    val updatedAt: LocalDateTime

    /**
     * 标记删除状态
     */
    @Default("0")
    @LogicalDeleted("1")
    val deleted: Int

    // 这是一个虚拟属性，数据库里没有这个字段
    @Transient(ArticleIsLikedResolver::class)
    val isLike: Boolean
}

