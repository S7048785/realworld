package com.realworld.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realworld.context.BaseContext;
import com.realworld.dto.*;
import com.realworld.result.PageResult;
import com.realworld.result.Result;
import com.realworld.service.ArticleService;
import com.realworld.vo.ArticleCardVO;
import com.realworld.vo.ArticleVO;
import com.realworld.vo.CommentVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "文章/评论管理")
@RequestMapping("/articles")
@RestController
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@Operation(summary = "获取文章卡片列表")
	@GetMapping("/list")
	public PageResult<ArticleCardVO> getArticles(ArticlePageQueryDTO articlePageQueryDTO) {
		// 探索全站内容、按标签/作者搜索、未登录用户浏览
		// 返回多篇文章 ，按最近的顺序排在最前面
		Page<ArticleCardVO> page = articleService.listArticle(articlePageQueryDTO, null);
		List<ArticleCardVO> records = page.getRecords();
		return new PageResult<>(records.size(), records, page.getCurrent(), page.getSize());
	}

	@Operation(summary = "获取文章卡片列表（登录校验）")
	@GetMapping("/feed")
	public PageResult<ArticleCardVO> getArticlesFees(ArticlePageQueryDTO articlePageQueryDTO) {
		// 登录用户查看个性化内容、追踪关注对象的更新（类似社交平台的“好友动态”）
		// 返回多篇文章 ，按最近的顺序排在最前面
		Page<ArticleCardVO> page = articleService.listArticle(articlePageQueryDTO, BaseContext.getCurrentId());
		return new PageResult<>(page.getSize(), page.getRecords(), page.getCurrent(), page.getSize());
	}

	@Operation(summary = "获取文章详情")
	@GetMapping("/{id}")
	public Result<ArticleVO> getArticle(@PathVariable Integer id) {
		// 获取文章详情
		ArticleVO articleVO = articleService.getArticle(id);
		return Result.success(articleVO);
	}

	@Operation(summary = "创建文章")
	@PostMapping
	public Result<Integer> createArticle(@RequestBody @Valid ArticleCreateDTO articleCreateDTO) {
		// 创建文章
		Integer id = articleService.saveArticle(articleCreateDTO);
		return Result.success(id);
	}

	@Operation(summary = "更新文章")
	@PutMapping("/{id}")
	public Result<Void> updateArticle(@RequestBody @Valid ArticleUpdateDTO articleUpdateDTO, @PathVariable Integer id) {
		articleService.updateArticleById(articleUpdateDTO, id);
		return Result.success();
	}

	@Operation(summary = "删除文章")
	@DeleteMapping("/{id}")
	public Result<Void> deleteArticle(@PathVariable Integer id) {
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
	public Result<Void> addComment(@PathVariable Integer id, @RequestBody @Valid CommentDTO commentDTO) {
		// 添加评论
		articleService.saveComment(id, commentDTO);
		return Result.success();
	}

	@Operation(summary = "删除评论")
	@DeleteMapping("/comments/{commentId}")
	public Result<Void> deleteComment(@PathVariable Integer commentId) {
		// 删除评论
		articleService.removeCommentById(commentId);
		return Result.success();
	}

	@Operation(summary = "收藏文章")
	@PostMapping("/favorite/{id}")
	public Result<Void> favoriteArticle(@PathVariable Integer id) {
		// 收藏文章
		articleService.favoriteArticle(id);
		return Result.success();
	}

	@Operation(summary = "点赞文章")
	@PostMapping(("/like/{id}"))
	public Result<Void> likeArticle(@PathVariable Integer id) {
		// 点赞文章
		articleService.likeArticle(id);
		return Result.success();
	}
}
