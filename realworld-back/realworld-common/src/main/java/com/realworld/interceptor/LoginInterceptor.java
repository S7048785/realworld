package com.realworld.interceptor;

import cn.hutool.core.util.StrUtil;
import com.realworld.constant.CacheConstant;
import com.realworld.context.BaseContext;
import com.realworld.utils.CacheUtil;
import com.realworld.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


/**
 * @author YYJYP
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
	private final CacheUtil cacheUtil;

	public LoginInterceptor(CacheUtil cacheUtil) {
		this.cacheUtil = cacheUtil;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		// 如果是OPTIONS请求，直接放行
		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			return true;
		}

		String token = request.getHeader("Authorization");
//		if ("http://localhost:8080/doc.html".equals(request.getHeader("referer"))) {
//			token = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3NDU2MzE0MjIsImlhdCI6MTc0NTYwMjYyMiwidXNlcm5hbWUiOiJhZG1pbiIsImlkIjoxfQ.yO4QDflrLj9IzX_xyYIeAU0bVbA7NwKH-GRoy2PoFpQ";
//		}
		try {
			// 解析token
			Claims claims = JwtUtil.parseJwt(token);

			// 查询是否在黑名单内
			String str = cacheUtil.getStr(CacheConstant.USER_TOKEN_BLACKLIST + token);
			if (StrUtil.isNotBlank(str)) {
				// 在黑名单内 抛出异常
				throw new Exception();
			}

			// 拿到用户id
			String id = claims.get("id").toString();
			Integer userId = Integer.parseInt(id);
			// 将用户id存入ThreadLocal
			BaseContext.setCurrentId(userId);
		} catch (Exception e) {
			// 解析失败 返回401
			response.setStatus(401);
			return false;
		}
		return true;
	}
}
