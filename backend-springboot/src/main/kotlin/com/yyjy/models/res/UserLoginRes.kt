package com.yyjy.models.res

import com.yyjy.models.entity.User
import org.babyfish.jimmer.sql.fetcher.Fetcher

data class UserLoginRes(
    val token: String,
    val user: Fetcher<User>
)
