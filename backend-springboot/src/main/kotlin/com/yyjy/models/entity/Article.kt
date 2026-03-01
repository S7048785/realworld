package com.yyjy.models.entity

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
     * 作者id
     */
    val userId: Long

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
}

