package com.realworld.dao;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserDTO {
	@Schema(description = "用户名")
	private String username;

	@Schema(description = "个人简介")
	private String bio;

	@Schema(description = "头像")
	private String avatar;

	@Schema(description = "密码")
	private String password;
}
