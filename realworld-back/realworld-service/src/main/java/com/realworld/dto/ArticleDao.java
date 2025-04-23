package com.realworld.dto;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.realworld.entity.Article;
import com.realworld.entity.Tags;
import com.realworld.mapper.ArticleMapper;
import com.realworld.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YYJYP
 */
@Service
public class ArticleDao extends ServiceImpl<ArticleMapper, Article> {
	@Autowired
	private ArticleMapper articleMapper;

	public List<Article> listArticle() {
		return list(Wrappers.lambdaQuery(Article.class)
				.eq(Article::getIsDel, 0));
	}

	public List<ArticleVO> list(Page<Article> articlePage, ArticlePageQueryDTO articlePageQueryDTO, Integer userId) {
		return articleMapper.selectListArticle(articlePage, articlePageQueryDTO, userId);
	}

	public void delete(Integer id) {
		update(Wrappers.lambdaUpdate(Article.class)
				.eq(Article::getId, id)
				.set(Article::getIsDel, 1));
	}



}
