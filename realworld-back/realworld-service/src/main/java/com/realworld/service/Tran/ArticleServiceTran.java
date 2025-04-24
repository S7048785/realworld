package com.realworld.service.Tran;

import com.realworld.dao.ArticleDao;
import com.realworld.entity.ArticleFavorites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleServiceTran {

	@Autowired
	private ArticleDao articleDao;

	@Transactional
	public void favoriteArticle1(boolean isFavorited, ArticleFavorites articleFavorites, Integer id) {
		if (isFavorited) {
			// 如果已收藏，则取消收藏
			articleDao.addFavorite(articleFavorites);
		} else {
			// 如果未收藏，则添加收藏
			articleDao.deleteFavorite(articleFavorites.getId());
		}
		articleDao.updateFavoriteCount(id, isFavorited);
	}
}
