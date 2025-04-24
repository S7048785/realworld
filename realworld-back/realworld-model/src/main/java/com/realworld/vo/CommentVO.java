package com.realworld.vo;

import com.realworld.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentVO {

	@Schema(description = "评论ID")
	private Integer id;

	@Schema(description = "评论内容")
	private String body;

	@Schema(description = "评论创建时间")
	private LocalDateTime createdAt;

	@Schema(description = "评论用户名称")
	private String username;

	@Schema(description = "评论用户头像链接")
	private String avatar;
}