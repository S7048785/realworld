package com.realworld.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentDTO {

	@NotNull(message = "评论不能为空")
	private String body;
}
