package com.realworld.dto;

import com.realworld.constant.ValidConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateDTO {

	@Pattern(regexp = "^[\\u4E00-\\u9FA5a-zA-z0-9]{2,12}$", message = "昵称格式不正确")
	@Schema(description = "昵称")
	private String nickname;

	@Schema(description = "密码")
	@Pattern(regexp = "^\\w{5,17}$", message = "密码格式不正确")
	private String password;

	@Size(max = 255, message = "个人简介不能超过255个字符")
	@Schema(description = "个人简介")
	private String bio;

	@Schema(description = "头像url")
	private String avatar;
}
