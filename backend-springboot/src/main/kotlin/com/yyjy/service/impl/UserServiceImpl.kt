package com.yyjy.service.impl

import com.yyjy.common.BaseContext
import com.yyjy.common.BusinessException
import com.yyjy.models.entity.User
import com.yyjy.models.entity.UserFollow
import com.yyjy.models.entity.copy
import com.yyjy.models.entity.dto.UserDetail
import com.yyjy.models.entity.dto.UserUpdateInput
import com.yyjy.models.req.UserLoginReq
import com.yyjy.models.req.UserRegisterReq
import com.yyjy.models.res.UserLoginRes
import com.yyjy.repository.UserRepository
import com.yyjy.service.UserService
import com.yyjy.utils.JwtUtil
import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.babyfish.jimmer.sql.fetcher.Fetcher
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrNull

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {
    override fun login(user: UserLoginReq): UserLoginRes {
        val userEntity = userRepository.findByEmail(user.email)
        return userEntity?.let {
            if (user.password != userEntity.password) {
                throw IllegalArgumentException("用户名或密码错误")
            }
            // 生成token
            val token = JwtUtil.createJwt(userEntity.id.toString())
            UserLoginRes(token, UserDetail(userEntity))
        } ?: throw BusinessException("用户名或密码错误")
    }

    override fun register(user: UserRegisterReq): UserLoginRes {
        // 检查用户是否已存在
        userRepository.findByEmail(user.email)?.let {
            throw BusinessException("用户已存在")
        }
        // 创建新用户
        val userNew = User {
            username = user.email.split("@")[0]
            email = user.email
            avatar = "\\images\\9419024696.jpg"
            password = user.password
            val now = LocalDateTime.now()
            createdAt = now
            updatedAt = now
        }
        val userEntity = userRepository.save(userNew)
        val token = JwtUtil.createJwt(userEntity.id.toString())
        return UserLoginRes(token, UserDetail(userEntity))
    }

    override fun getUserDetail(): UserDetail {
        val userId = BaseContext.getCurrentId()
        return userRepository.findById(userId).getOrNull()?.let {
            UserDetail(it)
        } ?: throw BusinessException("用户不存在", 404)
    }

    override fun updateUser(user: UserUpdateInput): UserDetail {
        val userId = BaseContext.getCurrentId()
        return userRepository.findById(userId).getOrNull()?.let {
            // 更新用户信息
            val userEntity = userRepository.save(it.copy {
                username = user.username
                email = user.email
                password = user.password
                bio = user.bio
                avatar = user.avatar
            }, SaveMode.UPDATE_ONLY)
            UserDetail(userEntity)
        } ?: throw BusinessException("用户不存在", 404)
    }

    override fun toggleFollow(id: Int) {
        val currentUserId = BaseContext.getCurrentId()
        userRepository.followUser(currentUserId, UserFollow {
            userId = currentUserId
            followedUserId = id
            createdAt = LocalDateTime.now()
        })
    }
}