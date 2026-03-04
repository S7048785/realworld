package com.yyjy.controller

import com.yyjy.common.ApiRes
import com.yyjy.common.PageRes
import com.yyjy.models.entity.dto.CommentCreateInput
import com.yyjy.models.entity.dto.CommentSimple
import com.yyjy.service.CommentService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "评论模块")
@RequestMapping("/comments")
@RestController
class CommentController(
    private val commentService: CommentService
) {

    @GetMapping
    fun getComments(@RequestParam("article_id") articleId: Int, skip: Int = 1, limit: Int = 5): PageRes<CommentSimple> {
        return commentService.page(articleId, skip, limit)
    }

    @PostMapping
    fun createComment(@RequestBody commentInput: CommentCreateInput): ApiRes<String?> {
        commentService.create(commentInput)
        return ApiRes.ok()
    }
}