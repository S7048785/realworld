package com.yyjy.models.entity

import org.babyfish.jimmer.sql.*
import java.time.LocalDateTime

/**
 * 用户关注关系表
 */
@Entity
interface UserFollow {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    val id: Int

    /**
     * 关注ID
     */
    @Key
    val userId: Int

    /**
     * 被关注ID
     */
    @Key
    val followedUserId: Int

    /**
     * 关注时间
     */
    val createdAt: LocalDateTime

    /**
     * 标记删除状态
     */
    @Default("0")
    @LogicalDeleted("1")
    val deleted: Int
}

