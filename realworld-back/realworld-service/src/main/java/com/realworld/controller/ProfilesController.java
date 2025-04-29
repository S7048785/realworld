package com.realworld.controller;

import com.realworld.dto.UserUpdateDTO;
import com.realworld.result.Result;
import com.realworld.service.UserService;
import com.realworld.vo.ProfileVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户资料控制器
 */
@Tag(name = "用户资料管理")
@RequestMapping("/profiles")
@RestController
public class ProfilesController {

	@Autowired
	private UserService userService;
	@Operation(summary = "获取用户资料")
	@GetMapping("/{username}")
	public Result<ProfileVO> getProfile(@PathVariable String username) {
		ProfileVO profileVO = userService.getInfo(username);
		return Result.success(profileVO);
	}

	@Operation(summary = "更新当前用户信息")
	@PutMapping
	public Result<ProfileVO> updateUser(@RequestBody @Validated UserUpdateDTO userDTO) {
		ProfileVO profileVO = userService.updateInfo(userDTO);
		return Result.success(profileVO);
	}

	@Operation(summary = "关注用户")
	@PostMapping("/follow/{id}")
	public Result<Void> follow(@PathVariable Integer id) {
		userService.follow(id);
		return Result.success();
	}

}
