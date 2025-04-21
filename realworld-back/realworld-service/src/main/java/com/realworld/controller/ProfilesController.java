package com.realworld.controller;

import com.realworld.result.Result;
import com.realworld.vo.ProfileVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * 用户资料控制器
 */
@Tag(name = "用户资料管理")
@RequestMapping("/profiles")
@RestController
public class ProfilesController {

	@Operation(summary = "获取用户资料")
	@GetMapping("/{username}")
	public Result<ProfileVO> getProfile(@PathVariable String username) {
		return null;
	}

	@Operation(summary = "关注用户")
	@PostMapping("/follow/{username}")
	public Result<ProfileVO> follow(@PathVariable String username) {
		return null;
	}

	@Operation(summary = "取消关注用户")
	@DeleteMapping("/follow/{username}")
	public Result<ProfileVO> unfollow(@PathVariable String username) {
		return null;
	}
}
