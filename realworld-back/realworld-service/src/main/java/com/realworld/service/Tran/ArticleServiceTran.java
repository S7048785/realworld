package com.realworld.service.Tran;

import com.realworld.dao.ArticleDao;
import com.realworld.entity.ArticleFavorites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleServiceTran {

	@Autowired
	private ArticleDao articleDao;

}
