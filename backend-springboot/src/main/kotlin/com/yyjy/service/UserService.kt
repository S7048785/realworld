package com.yyjy.service

import cn.dev33.satoken.stp.StpUtil
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
import com.yyjy.utils.JwtUtil
import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrNull

/**
 * 用户服务类
 * 提供用户相关的业务逻辑处理
 */
@Service
class UserService(
    private val userRepository: UserRepository
)  {
    /**
     * 用户登录
     * @param user 登录请求对象，包含邮箱和密码
     * @return 登录响应对象，包含JWT令牌和用户详情
     * @throws BusinessException 如果用户不存在或密码错误
     */
     fun login(user: UserLoginReq): UserLoginRes {
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

    /**
     * 用户注册
     * @param user 注册请求对象，包含邮箱和密码
     * @return 登录响应对象，包含JWT令牌和用户详情
     * @throws BusinessException 如果用户已存在
     */
     fun register(user: UserRegisterReq): UserLoginRes {
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

    /**
     * 获取当前用户详情
     * @return 用户详情对象
     * @throws BusinessException 如果用户未登录或不存在
     */
     fun getUserDetail(): UserDetail {

         val userId = StpUtil.getLoginIdAsInt()
         return userRepository.findById(userId).getOrNull()?.let {
            UserDetail(it)
        } ?: throw BusinessException("用户不存在", 404)
    }

    /**
     * 更新当前用户信息
     * @param user 用户更新请求对象，包含要更新的用户信息
     * @return 更新后的用户详情对象
     * @throws BusinessException 如果用户不存在
     */
     fun updateUser(user: UserUpdateInput): UserDetail {
        val userId = StpUtil.getLoginIdAsInt()
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

    /**
     * 切换关注/取消关注用户
     * @param id 要关注/取消关注的用户ID
     */
     fun toggleFollow(id: Int) {
        val currentUserId = StpUtil.getLoginIdAsInt()
        userRepository.followUser(currentUserId, UserFollow {
            userId = currentUserId
            followedUserId = id
            createdAt = LocalDateTime.now()
        })
    }
}