package com.realworld.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class TagsVO implements Serializable {
	private String name;
}
