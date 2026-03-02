package com.yyjy.repository

import com.yyjy.models.entity.User
import com.yyjy.models.entity.UserFollow
import com.yyjy.models.entity.email
import com.yyjy.models.entity.followedUserId
import com.yyjy.models.entity.id
import com.yyjy.models.entity.userId
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.ast.impl.table.AssociationTableProxyImpl.table
import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.exists
import java.time.LocalDateTime

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

}