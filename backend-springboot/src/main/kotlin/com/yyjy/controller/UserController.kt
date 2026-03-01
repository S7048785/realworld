package com.yyjy.controller

import com.yyjy.common.ApiRes
import com.yyjy.models.entity.User
import com.yyjy.models.entity.by
import com.yyjy.models.req.UserLoginReq
import com.yyjy.models.res.UserLoginRes
import com.yyjy.service.UserService
import io.swagger.v3.oas.annotations.tags.Tag
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "用户模块")
@RequestMapping("/user")
@RestController
class UserController(
    private val userService: UserService
) {

    @PostMapping("/login")
    fun login(@RequestBody user: UserLoginReq): ApiRes<UserLoginRes> {
        val userRes = userService.login(user, UserDetail)
        return ApiRes.ok(userRes)
    }

    companion object {
        private val UserDetail =
            newFetcher(User::class).by {
                username()
                email()
                bio()
                avatar()
                createdAt()
            }
    }
}