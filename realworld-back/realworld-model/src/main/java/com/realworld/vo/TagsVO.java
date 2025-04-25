package com.realworld.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class TagsVO implements Serializable {
	@Schema(description = "标签名称")
	private String name;
}
