package com.realworld.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ArticlePageQueryDTO {
	@Schema(name = "文章标签")
	private List<String> tagList;
	@Schema(name = "是否已关注")
	private String favorited;
	@Schema(name = "文章作者")
	private String author;
	@Schema(name = "页码")
	private Integer limit = 20;
	@Schema(name = "每页记录数")
	private Integer offset = 0;
}
