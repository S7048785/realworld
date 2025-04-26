package com.realworld.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@TableName("article_like")
@NoArgsConstructor
@Accessors(chain = true)
public class ArticleLike {
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	private Integer articleId;
	private Integer userId;
	private LocalDateTime createdAt;
	private int isDel;
}
