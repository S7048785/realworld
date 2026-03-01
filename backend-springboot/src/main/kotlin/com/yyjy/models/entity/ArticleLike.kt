package com.yyjy.models.entity

import org.babyfish.jimmer.sql.*
import java.time.LocalDateTime

/**
 * Entity for table "article_like"
 */
@Entity
interface ArticleLike {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    val id: Int

    @Key
    val userId: Long

    @Key
    val articleId: Long

    val createdAt: LocalDateTime

    @Default("0")
    @LogicalDeleted("1")
    val deleted: Int
}

