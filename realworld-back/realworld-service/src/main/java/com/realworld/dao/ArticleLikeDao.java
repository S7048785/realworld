package com.realworld.dao;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.realworld.entity.ArticleLike;
import com.realworld.mapper.ArticleLikeMapper;
import org.springframework.stereotype.Service;

/**
 * @author YYJYP
 */
@Service
public class ArticleLikeDao extends ServiceImpl<ArticleLikeMapper, ArticleLike> {
	public void removeByArticleId(Integer id) {
		update(Wrappers.lambdaUpdate(ArticleLike.class)
				.set(ArticleLike::getIsDel, 1)
				.eq(ArticleLike::getArticleId, id));
	}
}
