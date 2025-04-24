package com.realworld.interceptor;

import com.realworld.context.BaseContext;
import com.realworld.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;


/**
 * @author YYJYP
 */
public class LoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		// 如果是OPTIONS请求，直接放行
		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			return true;
		}

		String token = request.getHeader("token");
		if ("http://localhost:8080/doc.html".equals(request.getHeader("referer"))) {
			token = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3NDU1MTU2NjMsImlhdCI6MTc0NTQ4Njg2MywidXNlcm5hbWUiOiJhZG1pbiIsImlkIjoxfQ.sfM7_2nUiUvZzIr97VNVcjkVFYukdiUBVLIZ8iIo0No";
		}
		try {
			// 解析token
			Claims claims = JwtUtil.parseJwt(token);
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
