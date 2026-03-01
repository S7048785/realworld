package com.yyjy.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

/**
 * @author Nyxcirea
 * @date 2026/2/14
 * @description: JWT工具类
 */
class JwtUtil {

    companion object {
        private const val SECRET_KEY = "123456"
        private const val TTL = 60 * 1000L

        fun createJwt(userId: String): String {
            val algorithm = Algorithm.HMAC256(SECRET_KEY)
            return JWT.create()
                .withSubject(userId)
                .withIssuedAt(Date())
                .withExpiresAt(Date(System.currentTimeMillis() + TTL))
                .sign(algorithm)
        }

        fun parseJwt(token: String): String {
            val algorithm = Algorithm.HMAC256(SECRET_KEY)
            return JWT.require(algorithm)
                .build()
                .verify(token)
                .subject
        }
    }
}