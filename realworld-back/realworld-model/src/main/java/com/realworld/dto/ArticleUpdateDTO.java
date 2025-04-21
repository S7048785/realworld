package com.realworld.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ArticleUpdateDTO {
	@Schema(name = "文章标题")
	@NotBlank(message = "文章标题不能为空")
	private String title;

	@Schema(name = "文章描述")
	@NotBlank(message = "文章描述不能为空")
	private String description;

	@Schema(name = "文章内容")
	@NotBlank(message = "文章内容不能为空")
	private String body;
}
