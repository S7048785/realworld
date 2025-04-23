package com.realworld.dto;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.realworld.entity.Comment;
import com.realworld.mapper.CommentMapper;
import com.realworld.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentDao extends ServiceImpl <CommentMapper, Comment>{

	@Autowired
	private CommentMapper commentMapper;
	/**
	 * 根据文章id查询评论
	 * @param articleId
	 * @return
	 */
	public List<CommentVO> list(Integer articleId) {
		return commentMapper.list(articleId);
	}
}
