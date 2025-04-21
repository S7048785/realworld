package com.realworld.controller;

import com.realworld.dto.UserDTO;
import com.realworld.dto.UserLoginDTO;
import com.realworld.dto.UserRegisterDTO;
import com.realworld.dto.UserUpdateDTO;
import com.realworld.result.Result;
import com.realworld.service.IUserService;
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

	private final IUserService userService;

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
