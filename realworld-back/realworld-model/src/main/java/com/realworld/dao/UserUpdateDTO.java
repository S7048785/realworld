package com.realworld.dao;

import com.realworld.constant.ValidConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateDTO {
	@NotBlank(message = ValidConstant.USER_NAME_NOT_BLANK)
	@Schema(description = "用户名")
	private String username;
	@Schema(description = "个人简介")
	private String bio;
	@Schema(description = "头像url")
	private String avatar;
}
