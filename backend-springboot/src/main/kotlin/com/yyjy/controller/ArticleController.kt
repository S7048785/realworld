package com.yyjy.controller

import com.yyjy.common.ApiRes
import com.yyjy.common.PageRes
import com.yyjy.models.entity.dto.ArticleEdit
import com.yyjy.models.entity.dto.ArticleLikeSimple
import com.yyjy.models.entity.dto.ArticleSimple
import com.yyjy.service.ArticleService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "文章模块")
@RequestMapping("/articles")
@RestController
class ArticleController(
    private val articleService: ArticleService
) {

    @Operation(summary = "获取文章列表", description = "获取文章列表")
    @GetMapping("/list")
    fun list(skip: Int = 1, limit: Int = 5): PageRes<ArticleSimple> {
        return articleService.list(skip, limit)
    }

    @Operation(summary = "获取标签下的文章列表", description = "返回指定标签下的所有文章列表")
    @GetMapping("/tag")
    fun listByTag(tag_name: String, skip: Int = 1, limit: Int = 5): PageRes<ArticleSimple> {
        return articleService.listByTag(tag_name, skip, limit)
    }

    @Operation(summary = "添加文章")
    @PostMapping("/add")
    fun addArticle(@RequestParam("article_edit") articleEdit: ArticleEdit): ApiRes<String?> {
        articleService.addArticle(articleEdit)
        return ApiRes.ok()
    }

    @Operation(summary = "获取用户文章列表", description = "返回指定用户ID的所有文章列表")
    @GetMapping("/user")
    fun listByUser(@RequestParam("user_id") userId: Int, skip: Int = 1, limit: Int = 5): PageRes<ArticleSimple> {
        return articleService.listByUser(userId, skip, limit)
    }

    @Operation(summary = "点赞文章", description = "对文章进行点赞")
    @PostMapping("/list/{article_id}")
    fun likeArticle(@PathVariable("article_id") articleId: Int): ApiRes<String?> {
        articleService.likeArticle(articleId)
        return ApiRes.ok()
    }

    @Operation(summary = "获取用户点赞的文章列表", description = "返回指定用户ID的所有点赞文章列表")
    @GetMapping("/user/{user_id}/like")
    fun listByUserLike(@PathVariable("user_id") userId: Int, skip: Int = 1, limit: Int = 5): PageRes<ArticleLikeSimple> {
        return articleService.listByUserLike(userId, skip, limit)
    }

    companion object {

    }
}