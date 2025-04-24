package com.realworld.dao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleUpdateDTO {

	@Schema(description = "文章标题")
	@NotBlank(message = "文章标题不能为空")
	private String title;

	@Schema(description = "文章描述")
	@NotBlank(message = "文章描述不能为空")
	private String description;

	@Schema(description = "文章内容")
	@NotBlank(message = "文章内容不能为空")
	private String body;
}
