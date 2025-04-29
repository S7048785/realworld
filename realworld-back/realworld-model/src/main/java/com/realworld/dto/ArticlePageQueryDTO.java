package com.realworld.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author YYJYP
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePageQueryDTO {

//	@Schema(description = "文章作者id")
//	private Integer authorId;
	@Schema(description = "文章作者用户名")
	private String username;
	@Schema(description = "文章标签")
	private String tagList;
	@Schema(description = "页码", defaultValue = "1")
	private int limit = 1;
	@Schema(description = "每页记录数", defaultValue = "5")
	private int offset = 5;
}
