package com.realworld.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realworld.constant.CacheConstant;
import com.realworld.context.BaseContext;
import com.realworld.dao.*;
import com.realworld.entity.*;
import com.realworld.service.ArticleService;
import com.realworld.service.Tran.ArticleServiceTran;
import com.realworld.vo.ArticleCardVO;
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

	@Override
	public Page<ArticleCardVO> listArticle(ArticlePageQueryDTO articlePageQueryDTO, Integer userId) {
		Page<Article> articlePage = new Page<>(articlePageQueryDTO.getLimit(), articlePageQueryDTO.getOffset());
		return articleDao.list(articlePage, articlePageQueryDTO, userId);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void saveArticle(ArticleCreateDTO articleCreateDTO) {
		// 新增文章
		Article article = BeanUtil.copyProperties(articleCreateDTO, Article.class);
		article.setAuthorId(BaseContext.getCurrentId());
		articleDao.save(article);
		// 新增标签前查看是否有已存在的标签
		List<String> tagNameList = articleCreateDTO.getTagList();
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

	@Override
	public void updateArticleById(ArticleUpdateDTO articleUpdateDTO, Integer id) {
		Article article = BeanUtil.copyProperties(articleUpdateDTO, Article.class);
		article.setId(id);
		articleDao.updateById(article);
	}

	@Override
	public void removeArticleById(Integer id) {
		// 删除对应关系
		articleTagsDao.update(id);
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
		// 增加评论数量
		commentDao.updateCommentCount(id, true);
	}

	@Transactional
	@Override
	public void removeCommentById(Integer articleId, Integer commentId) {
		commentDao.removeById(articleId, commentId);
		// 减少评论数量
		commentDao.updateCommentCount(articleId, false);
	}


	@Override
	public void favoriteArticle(Integer id) {
		// TODO: 使用Redis加锁
		// 获取锁对象
		RLock lock = redissonClient.getLock(CacheConstant.CACHE_FAVORITES + id);
		// 尝试获取锁
		boolean isLock = false;
		try {
			isLock = lock.tryLock(1, 30, TimeUnit.SECONDS);
			if (!isLock) {
				// 未获取到锁 直接返回
				return;
			}
			// 查询有没有收藏
			boolean isFavorited = articleDao.isFavorite(id, BaseContext.getCurrentId());
			ArticleFavorites articleFavorites = new ArticleFavorites().setArticleId(id).setUserId(BaseContext.getCurrentId());
			// 执行收藏操作
			articleServiceTran.favoriteArticle1(isFavorited, articleFavorites, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放锁
			lock.unlock();
		}
	}


}
