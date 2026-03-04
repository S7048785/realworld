package com.yyjy.repository

import com.yyjy.models.entity.*
import com.yyjy.models.entity.dto.UserFollowers
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.kt.ast.expression.eq

interface UserRepository : KRepository<User, Int> {
    fun findByEmail(email: String) = sql.createQuery(User::class) {
        where(table.email eq email)
        select(table)
    }.execute().firstOrNull()

    fun followUser(currentUserId: Int, userFollow: UserFollow) {
        sql.createQuery(UserFollow::class) {
            where(table.userId eq currentUserId)
            where(table.followedUserId eq userFollow.followedUserId)
            select(table)
        }.execute().firstOrNull()?.let {
            sql.deleteById(UserFollow::class, it.id)
            return
        }
        sql.save(userFollow)
    }

    /**
     * 查询指定用户关注列表
     */
    fun findFollowingList(userId: Int) = sql.createQuery(UserFollow::class) {
        where(table.userId eq userId)
        select(table.fetch(UserFollowers::class))
    }.execute()

}