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

    @Default("0")
    @LogicalDeleted("1")
    val deleted: Int
}

