package com.realworld.dao;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.realworld.entity.ArticleTags;
import com.realworld.mapper.ArticleTagsMapper;
import org.springframework.stereotype.Service;

@Service
public class ArticleTagsDao extends ServiceImpl<ArticleTagsMapper, ArticleTags> {


	/**
	 * 删除文章标签关系
	 * @param articleId
	 * @return
	 */
	public boolean update(Integer articleId) {
		return super.update(Wrappers.lambdaUpdate(ArticleTags.class).eq(ArticleTags::getArticleId, articleId).set(ArticleTags::getIsDel, 1));
	}
}
