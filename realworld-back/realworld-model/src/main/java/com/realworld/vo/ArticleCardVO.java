package com.realworld.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ArticleCardVO {

	@Schema(description = "文章ID", type = "integer")
	private Integer id;

	@Schema(description = "文章标题", type = "string")
	private String title;

	@Schema(description = "文章描述", type = "string")
	private String description;

	@Schema(description = "文章正文内容", type = "string")
	private String body;

	@Schema(description = "点赞数量", type = "integer")
	private Integer likeCount;

	// 收藏数量
	@Schema(description = "收藏数量", type = "integer")
	private int favoritesCount;

	@Schema(description = "文章创建时间", type = "string")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;

	@Schema(description = "标签列表", type = "array")
	private String tags;

	// 作者信息
	@Schema(description = "作者用户名", type = "string")
	private String author;

	@Schema(description = "作者昵称", type = "string")
	private String nickname;

	@Schema(description = "作者头像链接", type = "string")
	private String avatar;

	// 是否点赞
	@Schema(description = "是否已点赞", type = "boolean")
	private boolean liked;

	// 是否收藏
	@Schema(description = "是否已收藏", type = "boolean")
	private boolean favorited;
}