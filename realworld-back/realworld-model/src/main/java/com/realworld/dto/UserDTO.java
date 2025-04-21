package com.realworld.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDTO {
	@Schema(name = "用户名")
	private String username;

	@Schema(name = "邮箱地址")
	private String email;

	@Schema(name = "个人简介")
	private String bio;

	@Schema(name = "头像")
	private String avatar;

	@Schema(name = "密码")
	private String password;
}
