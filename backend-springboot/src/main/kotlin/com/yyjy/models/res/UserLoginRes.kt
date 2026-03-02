package com.yyjy.models.res

import com.yyjy.models.entity.User
import com.yyjy.models.entity.dto.UserDetail
import org.babyfish.jimmer.sql.fetcher.Fetcher

data class UserLoginRes(
    val access_token: String,
    val user: UserDetail
)
