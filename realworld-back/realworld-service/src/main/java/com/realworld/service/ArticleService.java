package com.realworld.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realworld.dto.ArticleCreateDTO;
import com.realworld.dto.ArticlePageQueryDTO;
import com.realworld.dto.ArticleUpdateDTO;
import com.realworld.dto.CommentDTO;
import com.realworld.vo.ArticleCardVO;
import com.realworld.vo.ArticleVO;
import com.realworld.vo.CommentVO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
public interface ArticleService {

	Page<ArticleCardVO> listArticle(ArticlePageQueryDTO articlePageQueryDTO, Integer userId);

	Integer saveArticle(ArticleCreateDTO articleCreateDTO);

	void updateArticleById(ArticleUpdateDTO articleUpdateDTO, Integer id);

	void removeArticleById(Integer id);

	List<CommentVO> getComments(Integer id);

	void saveComment(Integer id, @Valid CommentDTO commentDTO);

	void removeCommentById(Integer commentId);

	void favoriteArticle(Integer id);

	ArticleVO getArticle(Integer id);

	void likeArticle(Integer id);
}
