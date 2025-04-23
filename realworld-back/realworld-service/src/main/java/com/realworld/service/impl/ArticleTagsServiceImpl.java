package com.realworld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.realworld.dto.ArticleTagsDao;
import com.realworld.entity.ArticleTags;
import com.realworld.mapper.ArticleTagsMapper;
import com.realworld.service.ArticleTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YYJYP
 */
@Service
public class ArticleTagsServiceImpl implements ArticleTagsService {

	@Autowired
	private ArticleTagsDao articleTagsDao;
}
