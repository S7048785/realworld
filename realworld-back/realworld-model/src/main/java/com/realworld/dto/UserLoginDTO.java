package com.realworld.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginDTO {
	@Pattern(regexp = "^[\\u4E00-\\u9FA5a-zA-z0-9]{2,12}$", message = "用户名格式不正确")
	@Schema(description = "用户名")
	private String username;

	@Pattern(regexp = "^\\w{5,17}$", message = "密码格式不正确")
	@Schema(description = "密码")
	private String password;

//	@NotBlank
//	@Schema(description = "验证码")
//	private String code;
}
