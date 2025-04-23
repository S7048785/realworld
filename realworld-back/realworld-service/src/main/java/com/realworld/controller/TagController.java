package com.realworld.controller;

import com.realworld.result.Result;
import com.realworld.service.TagsService;
import com.realworld.vo.TagsVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "标签管理")
@RequestMapping("/tags")
@RestController
public class TagController {

	@Autowired
	private TagsService tagsService;

	@Operation(summary = "获取标签列表")
	@GetMapping
	public Result<List<TagsVO> > getTags() {
		return Result.success(tagsService.listTag());
	}
}
