package com.realworld.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realworld.constant.ArticleConstant;
import com.realworld.constant.CacheConstant;
import com.realworld.context.BaseContext;
import com.realworld.dao.*;
import com.realworld.dto.*;
import com.realworld.entity.*;
import com.realworld.exception.BaseException;
import com.realworld.service.ArticleService;
import com.realworld.service.Tran.ArticleServiceTran;
import com.realworld.vo.ArticleCardVO;
import com.realworld.vo.ArticleVO;
import com.realworld.vo.CommentVO;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author YYJYP
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private TagsDao tagsDao;
	@Autowired
	private ArticleTagsDao articleTagsDao;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private RedissonClient redissonClient;
	@Autowired
	private ArticleServiceTran articleServiceTran;
	@Autowired
	private ArticleFavoritesDao articleFavoritesDao;
	@Autowired
	private ArticleLikeDao articleLikeDao;

	@Override
	public Page<ArticleCardVO> listArticle(ArticlePageQueryDTO articlePageQueryDTO, Integer userId) {
		int limit = articlePageQueryDTO.getLimit();
		limit = (limit - 1) * articlePageQueryDTO.getOffset();
		articlePageQueryDTO.setLimit(limit);
		Page<Article> articlePage = new Page<>(articlePageQueryDTO.getLimit(), articlePageQueryDTO.getOffset());

		// 标签封装成List
		List<String> tagList = null;
		if (StrUtil.isNotBlank(articlePageQueryDTO.getTagList())) {
			tagList = StrUtil.split(articlePageQueryDTO.getTagList(), ",");
		}

		return articleDao.list(articlePage, articlePageQueryDTO, tagList, userId);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Integer saveArticle(ArticleCreateDTO articleCreateDTO) {
		// 新增文章
		Article article = BeanUtil.copyProperties(articleCreateDTO, Article.class);
		article.setAuthorId(BaseContext.getCurrentId());
		articleDao.save(article);
		// 新增标签前查看是否有已存在的标签
		List<String> tagNameList = articleCreateDTO.getTagList();
		// 判断非空
		if (CollUtil.isNotEmpty(tagNameList)) {
			// 查询已存在的标签
			List<String> existsTags = tagsDao.listObjs(tagNameList);
			// 过滤出未存在的标签
			List<String> filter = CollUtil.filter(tagNameList, item -> !existsTags.contains(item));
			// 封装成 标签对象
			List<Tags> map = CollUtil.map(filter, item -> new Tags().setName(item), true);
			// 保存
			tagsDao.saveBatch(map);
			// 新增文章标签关系
			List<ArticleTags> articleTags = new ArrayList<>();
			for (Tags tags : map) {
				articleTags.add(new ArticleTags().setArticleId(article.getId()).setTagId(tags.getId()));
			}
			articleTagsDao.saveBatch(articleTags);
		}
		return article.getId();
	}

	@Override
	public void updateArticleById(ArticleUpdateDTO articleUpdateDTO, Integer id) {
		Article article = BeanUtil.copyProperties(articleUpdateDTO, Article.class);
		article.setId(id);
		articleDao.updateById(article);
	}

	@Transactional
	@Override
	public void removeArticleById(Integer id) {
		// 删除对应关系
		articleTagsDao.update(id);
		// 删除评论
		commentDao.removeByArticleId(id);
		// 删除收藏
		articleFavoritesDao.removeByArticleId(id);
		// 删除点赞
		articleLikeDao.removeByArticleId(id);
		// 删除文章
		articleDao.delete(id);
	}

	@Override
	public List<CommentVO> getComments(Integer articleId) {
		return commentDao.list(articleId);
	}

	@Transactional
	@Override
	public void saveComment(Integer id, CommentDTO commentDTO) {
		commentDao.save(id, commentDTO);
	}

	@Transactional
	@Override
	public void removeCommentById(Integer commentId) {
		commentDao.removeById(commentId);
	}


	@Override
	public void favoriteArticle(Integer id) {
		// TODO: 使用Redis加锁

		// 查询有没有收藏
		ArticleFavorites favoritesd = articleFavoritesDao.isFavoritesd(id, BaseContext.getCurrentId());

		if (favoritesd == null) {
			ArticleFavorites articleFavorites = new ArticleFavorites().setArticleId(id).setUserId(BaseContext.getCurrentId());
			articleFavoritesDao.save(articleFavorites);
		} else {
			articleFavoritesDao.updateFavorites(favoritesd.getIsDel());
		}

	}

	@Override
	public ArticleVO getArticle(Integer id) {
		ArticleVO article = articleDao.getArticle(id);
		if (article == null) {
			throw new BaseException(ArticleConstant.ARTICLE_NOT_EXIST);
		}
		return article;
	}

	@Override
	public void likeArticle(Integer id) {
		// TODO: 使用Redis加锁
		// 查询有没有点赞
		ArticleLike articleLike = articleLikeDao.isLiked(id, BaseContext.getCurrentId());

		if (articleLike == null) {
			// 执行点赞操作
			articleLikeDao.save(new ArticleLike().setArticleId(id).setUserId(BaseContext.getCurrentId()));
		} else {
			articleLikeDao.updateLike(id, articleLike.getIsDel());
		}
	}

	@Override
	public Page<ArticleCardVO> listLikedArticles(ArticlePageQueryDTO articlePageQueryDTO) {

		return articleDao.listLikedArticles(articlePageQueryDTO);
	}


}
