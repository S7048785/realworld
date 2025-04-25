package com.realworld.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileVO {

	@Schema(description = "用户ID", type = "integer")
	private Integer id;

	@Schema(description = "用户名", type = "string")
	private String username;

	@Schema(description = "用户简介", type = "string")
	private String bio;

	@Schema(description = "用户头像链接", type = "string")
	private String avatar;

	@Schema(description = "粉丝数量", type = "integer")
	private Integer fansCount;

	@Schema(description = "关注数量", type = "integer")
	private Integer followCount;

	@Schema(description = "是否已关注该用户", type = "boolean")
	private boolean following;
}
