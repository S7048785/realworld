package com.realworld.service;

import com.realworld.dto.ArticleCreateDTO;
import com.realworld.dto.ArticlePageQueryDTO;
import com.realworld.dto.ArticleUpdateDTO;
import com.realworld.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
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

	List<ArticleVO> listArticle(ArticlePageQueryDTO articlePageQueryDTO, Integer userId);

	void saveArticle(@Valid ArticleCreateDTO articleCreateDTO);

	void updateArticleById(ArticleUpdateDTO articleUpdateDTO, Integer id);

	void removeArticleById(Integer id);

	List<CommentVO> getComments(Integer id);
}
