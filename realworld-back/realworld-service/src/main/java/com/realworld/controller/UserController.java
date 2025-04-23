package com.realworld.controller;

import com.realworld.dao.UserLoginDTO;
import com.realworld.dao.UserRegisterDTO;
import com.realworld.dao.UserUpdateDTO;
import com.realworld.result.Result;
import com.realworld.service.UserService;
import com.realworld.vo.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
	public Result<UserVO> login(@RequestBody UserLoginDTO userLoginDTO) {
		UserVO userVO = userService.login(userLoginDTO);
		return Result.success(userVO);
	}

	@Operation(summary = "用户注册")
	@PostMapping("/register")
	public Result<UserVO> register(@RequestBody UserRegisterDTO userRegisterD) {
		return null;
	}

	@Operation(summary = "获取当前用户信息")
	@GetMapping
	public Result<UserVO> getCurrentUser() {
		return null;
	}

	@Operation(summary = "更新当前用户信息")
	@PutMapping
	public Result<UserVO> updateUser(@RequestBody UserUpdateDTO userDTO) {
		return null;
	}
}
