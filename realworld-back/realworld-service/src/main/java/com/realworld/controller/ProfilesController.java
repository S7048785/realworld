package com.realworld.controller;

import com.realworld.result.Result;
import com.realworld.service.UserService;
import com.realworld.vo.ProfileVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
	@GetMapping("/{id}")
	public Result<ProfileVO> getProfile(@PathVariable Integer id) {
		ProfileVO profileVO = userService.getInfo(id);
		return Result.success(profileVO);
	}

	@Operation(summary = "关注/取消关注用户")
	@PostMapping("/follow/{id}")
	public Result<ProfileVO> follow(@PathVariable Integer id) {
		// TODO: 关注功能
		userService.follow(id);
		return null;
	}

}
