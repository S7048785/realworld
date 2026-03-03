package com.yyjy.models.entity

import org.babyfish.jimmer.sql.*
import java.time.LocalDateTime

/**
 * 用户表
 */
@Entity
interface User {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    val id: Int

    /**
     * 用户名
     */
    @Key
    val username: String

    @Key
    val email: String

    /**
     * 密码
     */
    val password: String

    /**
     * 个人简介
     */
    val bio: String?

    /**
     * 头像
     */
    val avatar: String?

    val createdAt: LocalDateTime

    val updatedAt: LocalDateTime

    @OneToMany(mappedBy = "author")
    val articles: List<Article>

    /**
     * 我关注的人（我作为 follower 的记录）
     */
    @OneToMany(mappedBy = "user")
    val followings: List<UserFollow>

    /**
     * 我的粉丝（我作为 target 的记录）
     */
    @OneToMany(mappedBy = "followedUser")
    val followers: List<UserFollow>

    @Default("0")
    @LogicalDeleted("1")
    val deleted: Int
}

