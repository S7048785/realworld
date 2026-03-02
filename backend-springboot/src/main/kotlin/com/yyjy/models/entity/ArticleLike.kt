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
    val userId: Int

    @Key
    @ManyToOne
    val article: Article

    val createdAt: LocalDateTime

    @Default("0")
    @LogicalDeleted("1")
    val deleted: Int
}

