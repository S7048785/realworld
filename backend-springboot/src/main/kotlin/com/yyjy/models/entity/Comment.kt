package com.yyjy.models.entity

import org.babyfish.jimmer.sql.*
import java.time.LocalDateTime

/**
 * 文章评论表
 */
@Entity
interface Comment {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    val id: Int

    /**
     * 评论内容
     */
    val body: String

    /**
     * 评论者id
     */
    @ManyToOne
    val user: User

    /**
     * 文章id
     */
    @ManyToOne
    val article: Article

    /**
     * 评论时间
     */
    val createdAt: LocalDateTime

    /**
     * 标记删除状态
     */
    @Default("0")
    @LogicalDeleted("1")
    val deleted: Int
}

