package com.realworld.controller;

import com.realworld.dto.UserLoginDTO;
import com.realworld.dto.UserRegisterDTO;
import com.realworld.result.Result;
import com.realworld.service.UserService;
import com.realworld.vo.UserLoginVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户管理")
@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@Operation(summary = "用户登录")
	@PostMapping("/login")
	public Result<UserLoginVO> login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
		UserLoginVO userLoginVO = userService.login(userLoginDTO);
		return Result.success(userLoginVO);
	}

	@Operation(summary = "用户注册")
	@PostMapping("/register")
	public Result<UserLoginVO> register(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {
		return Result.success(userService.register(userRegisterDTO));
	}

	@Operation(summary = "退出登录")
	@DeleteMapping("/logout")
	public Result<Void> logout(@RequestHeader("Authorization") String token) {
		userService.logout(token);
		return Result.success();
	}

//	@Operation(summary = "获取验证码")
//	@GetMapping("/code")
//	public Result<Void> getCode(@RequestParam String email) {
//		return null;
//	}
}
