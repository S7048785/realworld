package com.realworld.controller;

import cn.hutool.core.bean.BeanUtil;
import com.realworld.context.BaseContext;
import com.realworld.dto.ArticleCreateDTO;
import com.realworld.dto.ArticlePageQueryDTO;
import com.realworld.dto.ArticleUpdateDTO;
import com.realworld.dto.CommentDTO;
import com.realworld.result.Result;
import com.realworld.service.ArticleService;
import com.realworld.vo.ArticleVO;
import com.realworld.vo.CommentVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "文章/评论管理")
@RequestMapping("/articles")
@RestController
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@Operation(summary = "获取文章列表")
	@GetMapping("/list")
	public Result<List<ArticleVO>> getArticles(ArticlePageQueryDTO articlePageQueryDTO) {
		// 探索全站内容、按标签/作者搜索、未登录用户浏览
		// 返回多篇文章 ，按最近的顺序排在最前面
		List<ArticleVO> list = articleService.listArticle(articlePageQueryDTO, null);
		return Result.success(list);
	}

	@Operation(summary = "获取文章列表（登录校验）")
	@GetMapping("/feed")
	public Result<List<ArticleVO>> getArticlesFees(ArticlePageQueryDTO articlePageQueryDTO) {
		// 登录用户查看个性化内容、追踪关注对象的更新（类似社交平台的“好友动态”）
		// 返回多篇文章 ，按最近的顺序排在最前面
		return Result.success(articleService.listArticle(articlePageQueryDTO, BaseContext.getCurrentId()));
	}

	@Operation(summary = "创建文章")
	@PostMapping
	public Result<ArticleVO> createArticle(@RequestBody @Valid ArticleCreateDTO articleCreateDTO) {
		// 创建文章
		articleService.saveArticle(articleCreateDTO);
		return Result.success();
	}

	@Operation(summary = "更新文章")
	@PutMapping("/{id}")
	public Result<ArticleVO> updateArticle(@RequestBody ArticleUpdateDTO articleUpdateDTO, @PathVariable Integer id) {

		articleService.updateArticleById(articleUpdateDTO, id);
		return Result.success();
	}

	@Operation(summary = "删除文章")
	@DeleteMapping("/{id}")
	public Result<ArticleVO> deleteArticle(@PathVariable Integer id) {
		// 删除文章
		articleService.removeArticleById(id);
		return Result.success();
	}

	@Operation(summary = "获取评论")
	@GetMapping("/comments")
	public Result<List<CommentVO>> getComments(Integer id) {
		// 获取评论
		return Result.success(articleService.getComments(id));
	}

	@Operation(summary = "添加评论")
	@PostMapping("/comments/{id}")
	public Result<CommentVO> addComment(@PathVariable Integer id, @RequestBody @Valid CommentDTO commentDTO) {
		// 添加评论
		return Result.success();
	}

	@Operation(summary = "删除评论")
	@DeleteMapping("/comments/{id}")
	public Result deleteComment(@PathVariable Integer id) {
		// 删除评论
		return Result.success();
	}

	@Operation(summary = "收藏文章")
	@PostMapping("/favorite/{id}")
	public Result<ArticleVO> favoriteArticle(@PathVariable Integer id) {
		// 收藏文章
		return Result.success();
	}
}
