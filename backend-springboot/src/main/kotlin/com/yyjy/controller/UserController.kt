package com.yyjy.controller

import cn.dev33.satoken.annotation.SaCheckLogin
import cn.dev33.satoken.stp.StpUtil
import com.yyjy.common.ApiRes
import com.yyjy.common.BaseContext
import com.yyjy.common.BusinessException
import com.yyjy.models.entity.User
import com.yyjy.models.entity.by
import com.yyjy.models.entity.dto.UserDetail
import com.yyjy.models.entity.dto.UserUpdateInput
import com.yyjy.models.req.UserLoginReq
import com.yyjy.models.req.UserRegisterReq
import com.yyjy.models.res.UserLoginRes
import com.yyjy.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "用户模块")
@RequestMapping("/users")
@RestController
class UserController(
    private val userService: UserService
) {

    @Operation(summary = "用户登录", description = "通过邮箱和密码进行身份验证")
    @PostMapping("/login")
    fun login(@RequestBody user: UserLoginReq): ApiRes<UserLoginRes> {
        val userRes = userService.login(user)
        StpUtil.login(userRes.user.id)
        return ApiRes.ok(userRes)
    }

    @Operation(summary = "用户注册", description = "通过用户名、邮箱和密码注册一个新用户")
    @PostMapping("/register")
    fun register(@RequestBody user: UserRegisterReq): ApiRes<UserLoginRes> {
        val userRes = userService.register(user)
        StpUtil.login(userRes.user.id)
        return ApiRes.ok()
    }


    @Operation(summary = "获取当前用户信息", description = "返回当前登录用户的详细信息")
    @GetMapping("/me")
    fun readCurrentUser(): ApiRes<UserDetail> {

        if (!StpUtil.isLogin()) {
            throw BusinessException("用户未登录")
        }
        return ApiRes.ok(userService.getUserDetail())
    }

    @SaCheckLogin
    @Operation(summary = "更新个人信息", description = "更新当前登录用户的信息，返回更新后的用户信息")
    @PutMapping("/")
    fun updateUser(@RequestBody user: UserUpdateInput): ApiRes<UserDetail> {
        return ApiRes.ok(userService.updateUser(user))
    }

    @SaCheckLogin
    @Operation(summary = "关注/取消关注用户", description = "关注或取消关注指定用户")
    @PutMapping("/follow/{id}")
    fun toggleFollow(@PathVariable id: Int): ApiRes<String?> {
        userService.toggleFollow(id)
        return ApiRes.ok()
    }

    companion object {
        private val UserDetail1 =
            newFetcher(User::class).by {
                username()
                email()
                bio()
                avatar()
                createdAt()
            }
        val UserSimple = newFetcher(User::class).by {
            username()
            avatar()
        }
    }
}