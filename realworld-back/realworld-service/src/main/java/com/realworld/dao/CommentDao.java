package com.realworld.dao;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.realworld.context.BaseContext;
import com.realworld.entity.Article;
import com.realworld.entity.Comment;
import com.realworld.mapper.ArticleMapper;
import com.realworld.mapper.CommentMapper;
import com.realworld.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentDao extends ServiceImpl <CommentMapper, Comment>{

	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private ArticleMapper articleMapper;
	/**
	 * 根据文章id查询评论
	 * @param articleId
	 * @return
	 */
	public List<CommentVO> list(Integer articleId) {
		return commentMapper.list(articleId);
	}

	/**
	 * 添加评论
	 * @param articleId
	 * @param commentDTO
	 */
	public void save(Integer articleId, CommentDTO commentDTO) {
		Comment comment = new Comment().setBody(commentDTO.getBody()).setArticleId(articleId).setUserId(BaseContext.getCurrentId());
		save(comment);
	}

	/**
	 * 删除评论
	 * @param articleId
	 * @param commentId
	 * @return
	 */
	@Transactional
	public boolean removeById(Integer articleId, Integer commentId) {
		boolean updated = update(Wrappers.lambdaUpdate(Comment.class).eq(Comment::getId, commentId).set(Comment::getIsDel, 1));
		// 评论数量-1
		articleMapper.update(Wrappers.lambdaUpdate(Article.class).setSql("comment_count = comment_count - 1").eq(Article::getId, articleId));
		return updated;
	}

	/**
	 * 更新评论数量
	 * @param articleId
	 */
	public void updateCommentCount(Integer articleId, boolean action) {
		String s = "commentCount = commentCount " + (action ? "+" : "-") + " 1";
		articleMapper.update(Wrappers.lambdaUpdate(Article.class)
				.setSql(s)
				.eq(Article::getId, articleId)
				.eq(Article::getIsDel, 0));
	}
}
