package com.realworld.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileVO {
	private String username;
	private String bio;
	private String avatar;
	private boolean following;
}
