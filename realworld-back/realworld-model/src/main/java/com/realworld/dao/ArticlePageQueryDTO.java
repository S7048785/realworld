package com.realworld.dao;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ArticlePageQueryDTO {

	@Schema(name = "文章作者id")
	private Integer authorId;
	@Schema(name = "文章标签")
	private List<String> tagList;
	@Schema(name = "页码")
	private Integer limit = 0;
	@Schema(name = "每页记录数")
	private Integer offset = 20;
}
