package com.yyjy.service.impl

import com.yyjy.common.BaseContext
import com.yyjy.common.PageRes
import com.yyjy.models.entity.copy
import com.yyjy.models.entity.dto.ArticleEdit
import com.yyjy.models.entity.dto.ArticleSimple
import com.yyjy.repository.ArticleRepository
import com.yyjy.service.ArticleService
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ArticleServiceImpl(
    private val articleRepository: ArticleRepository
) : ArticleService {
    override fun list(
        skip: Int,
        limit: Int
    ): PageRes<ArticleSimple> = articleRepository.list(skip, limit)

    override fun listByUserLike(
        userId: Int,
        skip: Int,
        limit: Int
    ): PageRes<ArticleLikeSimple> {
        return articleRepository.listByUserLike(userId, skip, limit)
    }

    override fun likeArticle(articleId: Int) {
        val userId = BaseContext.getCurrentId()
        articleRepository.like(articleId, userId)
    }

    override fun listByTag(
        tagName: String,
        skip: Int,
        limit: Int
    ): PageRes<ArticleSimple> = articleRepository.listByTag(tagName, skip, limit)

    override fun addArticle(articleEdit: ArticleEdit) {
        articleRepository.save(articleEdit.toEntity().copy {
            desc = this.body.substring(0, 50)
            authorId = BaseContext.getCurrentId()
            likes = 0
            views = 0
            comments = 0
            tags = articleEdit.tags
            val now = LocalDateTime.now()
            createdAt = now
            updatedAt = now
        })
    }

    override fun listByUser(
        userId: Int,
        skip: Int,
        limit: Int
    ): PageRes<ArticleSimple> {
        val page = articleRepository.findByUserId(userId, skip, limit)
        return PageRes(page.totalRowCount, skip, limit, page.rows)
    }
}
