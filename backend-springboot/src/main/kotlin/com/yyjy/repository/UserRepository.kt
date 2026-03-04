package com.yyjy.repository

import com.yyjy.models.entity.*
import com.yyjy.models.entity.dto.UserFollowers
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.babyfish.jimmer.sql.kt.ast.expression.eq

/**
 * 用户仓库接口
 * 提供用户相关的数据库操作方法
 */
interface UserRepository : KRepository<User, Int> {
    /**
     * 根据邮箱查找用户
     * @param email 用户邮箱
     * @return 用户对象，如果不存在则返回null
     */
    fun findByEmail(email: String) = sql.createQuery(User::class) {
        where(table.email eq email)
        select(table)
    }.execute().firstOrNull()

    /**
     * 关注/取消关注用户
     * @param currentUserId 当前用户ID
     * @param userFollow 用户关注关系对象
     */
    fun followUser(currentUserId: Int, userFollow: UserFollow) {
        sql.createQuery(UserFollow::class) {
            where(table.userId eq currentUserId)
            where(table.followedUserId eq userFollow.followedUserId)
            select(table)
        }.execute().firstOrNull()?.let {
            sql.deleteById(UserFollow::class, it.id)
            return
        }
        sql.save(userFollow, SaveMode.INSERT_IF_ABSENT)
    }

    /**
     * 查询指定用户关注列表
     * @param userId 用户ID
     * @return 用户关注列表
     */
    fun findFollowingList(userId: Int) = sql.createQuery(UserFollow::class) {
        where(table.userId eq userId)
        select(table.fetch(UserFollowers::class))
    }.execute()

}