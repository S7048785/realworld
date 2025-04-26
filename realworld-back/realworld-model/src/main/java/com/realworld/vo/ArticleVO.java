package com.realworld.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author YYJYP
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVO {

	@Schema(description = "文章ID", type = "integer")
	private Integer id;

	// 文章标题
	@Schema(description = "文章标题", type = "string")
	private String title;

	// 文章描述
	@Schema(description = "文章描述", type = "string")
	private String description;

	// 文章内容
	@Schema(description = "文章正文内容", type = "string")
	private String body;

	// 文章标签
	@Schema(description = "文章标签列表", type = "array")
	private String tags;

	// 文章创建时间
	@Schema(description = "文章创建时间", type = "string")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;

	// 是否收藏
	@Schema(description = "是否已收藏", type = "boolean")
	private boolean favorited;

	@Schema(description = "是否已点赞", type = "boolean")
	private boolean liked;

	// 文章作者信息
	@Schema(description = "文章作者信息")
	private ProfileVO author;
}