package com.realworld.mapper;

import com.realworld.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.realworld.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 文章评论表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

	List<CommentVO> list(Integer articleId);
}
