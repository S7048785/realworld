package com.realworld.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

	private final static SecureDigestAlgorithm<SecretKey, SecretKey> ALGORITHM = Jwts.SIG.HS256;

	// 私钥
	private final static String SECRET = "a8b3c1d7f9g2h5j8k1l4m7n0p3q6r9s2t5u8v1w4x7y0z3A6B9C2D5E8F1G4H7I";

	// 过期时间 12小时
	private static final long TTL = 28800000;

	// 密钥
	private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
	/**
	 * 生成jwt
	 * @param claims jwt的body
	 * @return
	 */
	public static String createJwt(Map<String, Object> claims) {
		return Jwts.builder()
				// 签名
				.signWith(KEY, ALGORITHM)
				// 过期时间
				.expiration(new Date(System.currentTimeMillis() + TTL))
				// 签发时间
				.issuedAt(new Date())
				// 设置自定义负载信息payload
				.claims(claims)
				.compact();
	}

	/**
	 * 解析token
	 * @return Claims
	 */
	public static Claims parseJwt(String token) {
		return Jwts.parser()
				.verifyWith(KEY)
				.build()
				.parseSignedClaims(token).getPayload();
	}
}
