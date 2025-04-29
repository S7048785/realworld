package com.realworld.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.realworld.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentVO {

	@Schema(description = "评论ID", type = "integer")
	private Integer id;

	@Schema(description = "评论内容", type = "string")
	private String body;

	@Schema(description = "评论创建时间", type = "string")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;

	@Schema(description = "评论用户名称", type = "string")
	private String username;

	@Schema(description = "用户昵称", type = "string")
	private String nickname;

	@Schema(description = "评论用户头像链接", type = "string")
	private String avatar;
}