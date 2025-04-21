package com.realworld.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author YYJYP
 */
@Data
@Builder
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
	private String createdAt;
	// 文章更新时间
	private String updatedAt;
	// 是否收藏
	private boolean favorited;
	// 收藏数量
	private int favoritesCount;
	// 文章作者信息
	private ProfileVO author;
}
