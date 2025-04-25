package com.realworld.vo;

import lombok.Builder;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
public class UserLoginVO {

	@Schema(description = "用户ID")
	private Integer id;

	@Schema(description = "用户名")
	private String username;

	@Schema(description = "用户简介")
	private String bio;

	@Schema(description = "用户头像链接")
	private String avatar;

	@Schema(description = "关注数量")
	private Integer followCount;

	@Schema(description = "粉丝数量")
	private Integer fansCount;

	@Schema(description = "用户登录令牌")
	private String token;
}
