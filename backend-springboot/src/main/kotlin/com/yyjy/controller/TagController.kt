package com.yyjy.controller

import com.yyjy.service.TagService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "标签模块")
@RequestMapping("/tags")
@RestController
class TagController(
    private val tagService: TagService
) {
    @GetMapping
    fun getAllTags(): List<String> {
        return tagService.getAllTags()
    }
}