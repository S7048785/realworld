package com.realworld.dao;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.realworld.context.BaseContext;
import com.realworld.entity.Article;
import com.realworld.entity.ArticleFavorites;
import com.realworld.mapper.ArticleFavoritesMapper;
import com.realworld.mapper.ArticleMapper;
import com.realworld.vo.ArticleCardVO;
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
	@Autowired
	private ArticleFavoritesMapper articleFavoritesMapper;

	public List<Article> listArticle() {
		return list(Wrappers.lambdaQuery(Article.class)
				.eq(Article::getIsDel, 0));
	}

	public Page<ArticleCardVO> list(Page<Article> articlePage, ArticlePageQueryDTO articlePageQueryDTO, Integer userId) {
		return articleMapper.selectListArticleCard(articlePage, articlePageQueryDTO, userId);
	}

	public void delete(Integer id) {
		update(Wrappers.lambdaUpdate(Article.class)
				.eq(Article::getId, id)
				.set(Article::getIsDel, 1));
	}

	public void addFavorite(ArticleFavorites articleFavorites) {
		articleFavoritesMapper.insert(articleFavorites);
	}

	public void deleteFavorite(Integer id) {
		articleFavoritesMapper.update(Wrappers.lambdaUpdate(ArticleFavorites.class)
				.set(ArticleFavorites::isDel, 1)
				.eq(ArticleFavorites::getArticleId, id)
				.eq(ArticleFavorites::getUserId, BaseContext.getCurrentId()));
	}

	/**
	 * 增/减收藏数量
	 * @param id 文章id
	 * @param action 收藏true 取消收藏false
	 */
	public void updateFavoriteCount(Integer id, boolean action) {
		String s = "favorites_count = favorites_count " + (action ? "+" : "-") + " 1";
		articleMapper.update(Wrappers.lambdaUpdate(Article.class)
				.setSql(s)
				.eq(Article::getId, id)
				.eq(Article::getIsDel, 0));
	}

	public boolean isFavorite(Integer id, Integer currentId) {
		Long count = articleFavoritesMapper.selectCount(Wrappers.lambdaQuery(ArticleFavorites.class)
				.eq(ArticleFavorites::getArticleId, id)
				.eq(ArticleFavorites::getUserId, currentId)
				.eq(ArticleFavorites::isDel, 0));
		return count > 0;
	}
}
