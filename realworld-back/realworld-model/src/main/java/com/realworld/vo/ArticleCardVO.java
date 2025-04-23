package com.realworld.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ArticleCardVO {

	private Integer id;
	private String title;
	private String description;
	private String body;
	private Integer likeCount;
	// 收藏数量
	private int favoritesCount;
	private LocalDateTime createdAt;
	private List<String> tagList;

	// 作者信息
	private String username;
	private String avatar;

	// 是否点赞
	private boolean liked;
	// 是否收藏
	private boolean favorited;
}
