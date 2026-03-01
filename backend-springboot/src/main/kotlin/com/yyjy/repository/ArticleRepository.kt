package com.yyjy.repository

import com.yyjy.models.entity.Article
import org.babyfish.jimmer.spring.repository.KRepository

interface ArticleRepository : KRepository<Article, Int> {
}