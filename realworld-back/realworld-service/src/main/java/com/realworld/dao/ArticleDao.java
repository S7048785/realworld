package com.realworld.dao;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.realworld.context.BaseContext;
import com.realworld.dto.ArticlePageQueryDTO;
import com.realworld.entity.Article;
import com.realworld.entity.ArticleFavorites;
import com.realworld.mapper.ArticleFavoritesMapper;
import com.realworld.mapper.ArticleMapper;
import com.realworld.vo.ArticleCardVO;
import com.realworld.vo.ArticleVO;
import com.realworld.vo.ProfileVO;
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
	@Autowired
	private UserDao userDao;

	public ArticleVO getArticle(Integer id) {
		ArticleVO articleVO = articleMapper.selectArticle(id);
		ProfileVO profileByArticleId = userDao.getProfileByArticleId(articleVO.getId(), BaseContext.getCurrentId());
		articleVO.setAuthor(profileByArticleId);
		return articleVO;
	}

	public Page<ArticleCardVO> list(Page<Article> articlePage, ArticlePageQueryDTO articlePageQueryDTO, List<String> tagList, Integer userId) {

		return articleMapper.selectListArticleCard(articlePage, articlePageQueryDTO, tagList, userId);
	}

	public void delete(Integer id) {
		update(Wrappers.lambdaUpdate(Article.class)
				.eq(Article::getId, id)
				.set(Article::getIsDel, 1));
	}


}
