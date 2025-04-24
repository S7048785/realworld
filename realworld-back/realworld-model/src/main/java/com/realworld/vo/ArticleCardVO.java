package com.realworld.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ArticleCardVO {

	@Schema(description = "文章ID")
	private Integer id;

	@Schema(description = "文章标题")
	private String title;

	@Schema(description = "文章描述")
	private String description;

	@Schema(description = "文章正文内容")
	private String body;

	@Schema(description = "点赞数量")
	private Integer likeCount;

	// 收藏数量
	@Schema(description = "收藏数量")
	private int favoritesCount;

	@Schema(description = "文章创建时间")
	private LocalDateTime createdAt;

	@Schema(description = "标签列表")
	private List<String> tagList;

	// 作者信息
	@Schema(description = "作者用户名")
	private String username;

	@Schema(description = "作者头像链接")
	private String avatar;

	// 是否点赞
	@Schema(description = "是否已点赞")
	private boolean liked;

	// 是否收藏
	@Schema(description = "是否已收藏")
	private boolean favorited;
}