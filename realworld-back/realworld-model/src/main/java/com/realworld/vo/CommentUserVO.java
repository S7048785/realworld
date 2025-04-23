package com.realworld.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentUserVO {
	private String username;
	private String bio;
	private String avatar;
	private boolean following;
}
