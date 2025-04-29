package com.realworld.entity;

import com.baomidou.mybatisplus.annotation.*;
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
	@TableField(value = "created_at", fill = FieldFill.INSERT)
	private LocalDateTime createdAt;
	private int isDel;
}
