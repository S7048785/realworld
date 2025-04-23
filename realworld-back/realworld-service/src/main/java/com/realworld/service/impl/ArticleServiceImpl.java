package com.realworld.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realworld.context.BaseContext;
import com.realworld.dto.*;
import com.realworld.entity.Article;
import com.realworld.entity.ArticleTags;
import com.realworld.entity.Comment;
import com.realworld.entity.Tags;
import com.realworld.service.ArticleService;
import com.realworld.vo.ArticleVO;
import com.realworld.vo.CommentVO;
import com.realworld.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public List<ArticleVO> listArticle(ArticlePageQueryDTO articlePageQueryDTO,Integer userId) {
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

		List<CommentVO> list = commentDao.list(articleId);
		// 封装成VO

		return CollUtil.map(list, item -> BeanUtil.copyProperties(item, CommentVO.class), true);
	}
}
