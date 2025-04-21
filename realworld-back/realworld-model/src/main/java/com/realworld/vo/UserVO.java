package com.realworld.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class UserVO {
	private String username;
	private String email;
	private String token;
	private String bio;
	private String avatar;
}
