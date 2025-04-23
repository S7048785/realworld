package com.realworld.vo;

import com.realworld.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentVO {
	private Integer id;
	private String body;
	private LocalDateTime createdAt;
	private CommentUserVO author;
}
