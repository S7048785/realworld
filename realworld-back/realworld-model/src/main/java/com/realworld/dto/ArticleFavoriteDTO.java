package com.realworld.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 收藏: true / 取消收藏: false
 */
@Data
public class ArticleFavoriteDTO {
	@Schema(description = "收藏: true / 取消收藏: false")
	private boolean action;
}
