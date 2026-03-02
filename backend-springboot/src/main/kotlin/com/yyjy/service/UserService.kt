package com.yyjy.service

import com.yyjy.models.entity.User
import com.yyjy.models.entity.dto.UserDetail
import com.yyjy.models.entity.dto.UserUpdateInput
import com.yyjy.models.req.UserLoginReq
import com.yyjy.models.req.UserRegisterReq
import com.yyjy.models.res.UserLoginRes
import org.babyfish.jimmer.sql.fetcher.Fetcher

interface UserService {

    fun login(user: UserLoginReq): UserLoginRes
    fun register(user: UserRegisterReq): UserLoginRes
    fun getUserDetail(): UserDetail
    fun updateUser(user: UserUpdateInput): UserDetail
    fun toggleFollow(id: Int)
}