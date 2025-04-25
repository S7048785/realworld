package com.realworld.dao;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.realworld.context.BaseContext;
import com.realworld.dto.CommentDTO;
import com.realworld.entity.Comment;
import com.realworld.mapper.ArticleCommentMapper;
import com.realworld.mapper.ArticleMapper;
import com.realworld.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentDao extends ServiceImpl <ArticleCommentMapper, Comment>{

	@Autowired
	private ArticleCommentMapper commentMapper;
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
	 * 删除文章下的所有评论
	 * @param articleId
	 */
	public void removeByArticleId(Integer articleId) {
		update(Wrappers.lambdaUpdate(Comment.class)
				.set(Comment::getIsDel, 1)
				.eq(Comment::getArticleId, articleId));
	}
}
