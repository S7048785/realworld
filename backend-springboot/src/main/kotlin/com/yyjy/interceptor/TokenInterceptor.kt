package com.yyjy.interceptor

import com.yyjy.common.BaseContext
import com.yyjy.utils.JwtUtil
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.servlet.HandlerInterceptor

/**
 * @author Nyxcirea
 * @date 2026/2/14
 * @description: 拦截器，用于校验token
 */
class TokenInterceptor : HandlerInterceptor {

    private val logger = KotlinLogging.logger {}

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {

        logger.info { "请求路径: ${request.requestURI}" }

        // 1. 从 Cookie 中读取 AUTH_TOKEN
        val token = request.cookies?.find { it.name == "AUTH_TOKEN" }?.value?: run {
            // 校验失败
            // 返回401状态码
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            return false
        }

        // 获取token
//        val token = request.getHeader("Authorization")
        try {
            val userId = JwtUtil.parseJwt(token)
            // 存入ThreadLocal
            BaseContext.setCurrentId(userId.toLong())
        } catch (e: Exception) {
            // 校验失败
            // 返回401状态码
            logger.error { "Token校验失败: ${e.message}" }
//            log.error("Token校验失败: ${e.message}", e)
        }
        return super.preHandle(request, response, handler)
    }

}