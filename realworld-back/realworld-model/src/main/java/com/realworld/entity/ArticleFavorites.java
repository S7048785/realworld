package com.realworld.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 文章收藏关系表
 * @author YYJYP
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("article_favorites")
@NoArgsConstructor
public class ArticleFavorites {
	private Integer id;
	private Integer articleId;
	private Integer userId;
	private LocalDateTime createdAt;
	private boolean isDel;
}
