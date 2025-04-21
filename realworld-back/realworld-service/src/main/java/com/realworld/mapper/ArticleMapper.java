package com.realworld.mapper;

import com.realworld.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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

}
