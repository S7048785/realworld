package com.realworld.vo;

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

	@Schema(description = "文章ID")
	private Integer id;

	// 文章标题
	@Schema(description = "文章标题")
	private String title;

	// 文章描述
	@Schema(description = "文章描述")
	private String description;

	// 文章内容
	@Schema(description = "文章正文内容")
	private String body;

	// 文章标签
	@Schema(description = "文章标签列表")
	private List<String> tagList;

	// 文章创建时间
	@Schema(description = "文章创建时间")
	private LocalDateTime createdAt;

	// 是否收藏
	@Schema(description = "是否已收藏")
	private boolean favorited;

	// 收藏数量
	@Schema(description = "收藏数量")
	private int favoritesCount;
	
	// 点赞数量
	@Schema(description = "点赞数量")
	private int likeCount;

	// 评论数量
	@Schema(description = "评论数量")
	private int commentCount;

	// 文章作者信息
	@Schema(description = "文章作者信息")
	private ProfileVO author;
}