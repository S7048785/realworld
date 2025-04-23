package com.realworld.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author YYJYP
 */
@Data
public class ArticleCreateDTO {
	@Schema(name = "文章标题")
	@NotNull(message = "文章标题不能为空")
	private String title;

	@Schema(name = "文章描述")
	@NotNull(message = "文章描述不能为空")
	private String description;

	@Schema(name = "文章内容")
	@NotNull(message = "文章内容不能为空")
	private String body;

	@Schema(name = "文章标签")
	private List<String> tagList;
}
