package com.realworld.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginDTO {
	@NotBlank
	@Schema(description = "用户名")
	private String username;

	@NotBlank
	@Schema(description = "密码")
	private String password;

//	@NotBlank
//	@Schema(description = "验证码")
//	private String code;
}
