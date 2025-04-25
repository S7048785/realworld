package com.realworld.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author YYJYP
 */
@Data
@NoArgsConstructor
public class ArticleCreateDTO {
	@Schema(description = "文章标题")
	@NotNull(message = "文章标题不能为空")
	private String title;

	@Schema(description = "文章描述")
	@NotNull(message = "文章描述不能为空")
	private String description;

	@Schema(description = "文章内容")
	@NotNull(message = "文章内容不能为空")
	private String body;

	@Schema(description = "文章标签")
	private List<String> tagList;
}
