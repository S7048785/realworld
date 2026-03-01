package com.yyjy.models.entity

import org.babyfish.jimmer.sql.*

/**
 * Entity for table "tags"
 */
@Entity
interface Tags {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    val id: Int

    /**
     * 标签名称
     */
    @Key
    val name: String

    /**
     * 标记删除状态
     */
    @Default("0")
    @LogicalDeleted("1")
    val deleted: Int
}

