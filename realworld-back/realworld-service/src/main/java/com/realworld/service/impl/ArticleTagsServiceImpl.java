package com.realworld.service.impl;

import com.realworld.dao.ArticleTagsDao;
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
