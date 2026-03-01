package com.yyjy.repository

import com.yyjy.models.entity.User
import com.yyjy.models.entity.email
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.kt.ast.expression.eq

interface UserRepository : KRepository<User, Int> {
    fun findByEmail(email: String) = sql.createQuery(User::class) {
        where(table.email eq email)
        select(table)
    }.execute().firstOrNull()
}