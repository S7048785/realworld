package com.yyjy.service.impl

import com.yyjy.models.entity.User
import com.yyjy.models.req.UserLoginReq
import com.yyjy.models.res.UserLoginRes
import com.yyjy.repository.UserRepository
import com.yyjy.service.UserService
import com.yyjy.utils.JwtUtil
import org.babyfish.jimmer.sql.fetcher.Fetcher
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {
    override fun login(user: UserLoginReq, userDetailFetcher: Fetcher<User>): UserLoginRes {
        val userEntity = userRepository.findByEmail(user.email)
        userEntity?.let {
            if (user.password != userEntity.password) {
                throw IllegalArgumentException("用户名或密码错误")
            }
            // 生成token
            val token = JwtUtil.createJwt(userEntity.id.toString())
            return UserLoginRes(token, userDetailFetcher)
        } ?: throw IllegalArgumentException("用户名或密码错误")
    }
}