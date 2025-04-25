package com.realworld.dao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDTO {

	@NotNull(message = "评论不能为空")
	@Schema(description = "评论内容")
	private String body;
}
