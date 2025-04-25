package com.realworld.dao;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.realworld.entity.ArticleFavorites;
import com.realworld.mapper.ArticleFavoritesMapper;
import org.springframework.stereotype.Service;

@Service
public class ArticleFavoritesDao extends ServiceImpl <ArticleFavoritesMapper, ArticleFavorites>{

	/**
	 * 根据文章id删除
	 * @param id
	 */
	public void removeByArticleId(Integer id) {
		update(Wrappers.lambdaUpdate(ArticleFavorites.class)
				.set(ArticleFavorites::getIsDel, 1)
				.eq(ArticleFavorites::getArticleId, id));
	}

	/**
	 * 判断是否已收藏
	 * @param id
	 * @return
	 */
	public ArticleFavorites isFavoritesd(Integer id, Integer userId) {
		return getOne(Wrappers.lambdaQuery(ArticleFavorites.class)
				.eq(ArticleFavorites::getArticleId, id)
				.eq(ArticleFavorites::getUserId, userId));
	}

	public void updateFavorites(int del) {
		update(Wrappers.lambdaUpdate(ArticleFavorites.class)
				.set(ArticleFavorites::getIsDel, del == 1 ? 0 : 1));
	}
}
