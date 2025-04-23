package com.realworld.vo;

import jakarta.validation.constraints.NotNull;
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
	private Integer id;
	// 文章标题
	private String title;
	// 文章描述
	private String description;
	// 文章内容
	private String body;
	// 文章标签
	private List<String> tagList;
	// 文章创建时间
	private LocalDateTime createdAt;
	// 是否收藏
	private boolean favorited;
	// 收藏数量
	private int favoritesCount;
	// 评论数量
	private int commentCount;
	// 文章作者信息
	private ProfileVO author;
}
