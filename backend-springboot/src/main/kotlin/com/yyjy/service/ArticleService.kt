package com.yyjy.service

import com.yyjy.common.BaseContext
import com.yyjy.common.PageRes
import com.yyjy.models.entity.copy
import com.yyjy.models.entity.dto.ArticleDetail
import com.yyjy.models.entity.dto.ArticleEdit
import com.yyjy.models.entity.dto.ArticleLikeSimple
import com.yyjy.models.entity.dto.ArticleSimple
import com.yyjy.repository.ArticleRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ArticleService(
    private val articleRepository: ArticleRepository
)  {
     fun list(
        skip: Int,
        limit: Int
    ): PageRes<ArticleSimple> = articleRepository.list(skip, limit)

    fun listByUserLike(
        userId: Int,
        skip: Int,
        limit: Int
    ): PageRes<ArticleLikeSimple> {
        val page = articleRepository.listByUserLike(userId, skip, limit)
        return PageRes(page.totalRowCount, skip, limit, page.rows)
    }

    fun getArticle(articleId: Int): ArticleDetail {
        val article = articleRepository.findDetail(articleId)
        // 增加浏览量
        articleRepository.addViews(articleId)
        return article
    }

    fun listByUserFollowing(
        skip: Int,
        limit: Int
    ): PageRes<ArticleSimple> {
        return articleRepository.listByUserFollowing(skip, limit)
    }

    fun likeArticle(articleId: Int) {
        val userId = BaseContext.getCurrentId()
        articleRepository.like(articleId, userId!!)
        // 增加点赞数
        articleRepository.addLikes(articleId)
    }

    fun listByTag(
        tagName: String,
        skip: Int,
        limit: Int
    ): PageRes<ArticleSimple> = articleRepository.listByTag(tagName, skip, limit)

    fun addArticle(articleEdit: ArticleEdit) {
        articleRepository.save(articleEdit.toEntity().copy {
            desc = this.body.substring(0, 50)
            authorId = BaseContext.getCurrentId()!!
            likes = 0
            views = 0
            comments = 0
            tags = articleEdit.tags
            val now = LocalDateTime.now()
            createdAt = now
            updatedAt = now
        })
    }

     fun listByUser(
        userId: Int,
        skip: Int,
        limit: Int
    ): PageRes<ArticleSimple> {
        val page = articleRepository.findByUserId(userId, skip, limit)
        return PageRes(page.totalRowCount, skip, limit, page.rows)
    }
}
