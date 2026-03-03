package com.yyjy.models.entity

import org.babyfish.jimmer.sql.*
import java.time.LocalDateTime

/**
 * 用户关注关系表
 */
@Entity
interface UserFollow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int

    /**
     * 发起关注的用户 (Follower)
     */
    @Key
    @ManyToOne
    // 如果数据库列名不是 user_id，可以用 @JoinColumn(name = "user_id") 指定
    val user: User

    /**
     * 被关注的用户 (Target)
     */
    @Key
    @ManyToOne
    val followedUser: User

    // --- 辅助属性：IdView ---
    // 允许你直接通过 follow.userId 获取 ID，而不需要访问整个 user 对象
    @IdView("user")
    val userId: Int

    @IdView("followedUser")
    val followedUserId: Int

    val createdAt: LocalDateTime

    @Default("0")
    @LogicalDeleted("1")
    val deleted: Int
}
