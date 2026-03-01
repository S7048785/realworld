package com.yyjy.service

import com.yyjy.models.entity.User
import com.yyjy.models.req.UserLoginReq
import com.yyjy.models.res.UserLoginRes
import org.babyfish.jimmer.sql.fetcher.Fetcher

interface UserService {

    fun login(user: UserLoginReq, userDetailFetcher: Fetcher<User>): UserLoginRes
}