package com.realworld.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realworld.dto.ArticlePageQueryDTO;
import com.realworld.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.realworld.vo.ArticleVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 文章表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

	List<ArticleVO> selectListArticle(Page<Article> articlePage, ArticlePageQueryDTO articlePageQueryDTO, Integer userId);
}
