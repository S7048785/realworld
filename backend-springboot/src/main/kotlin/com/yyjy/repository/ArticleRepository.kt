package com.yyjy.repository

import com.yyjy.common.PageRes
import com.yyjy.models.entity.Article
import com.yyjy.models.entity.ArticleLike
import com.yyjy.models.entity.articleId
import com.yyjy.models.entity.authorId
import com.yyjy.models.entity.dto.ArticleLikeSimple
import com.yyjy.models.entity.dto.ArticleSimple
import com.yyjy.models.entity.id
import com.yyjy.models.entity.tags
import com.yyjy.models.entity.userId
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.kt.ast.expression.eq

interface ArticleRepository : KRepository<Article, Int> {
    fun list(skip: Int, limit: Int): PageRes<ArticleSimple> {
        val page = sql.createQuery(Article::class) {
            select(table.fetch(ArticleSimple::class))

        }.fetchPage(skip - 1, limit)
        return PageRes(page.totalRowCount, skip, limit, page.rows)
    }

    fun listByTag(tagName: String, skip: Int, limit: Int): PageRes<ArticleSimple> {
        val page = sql.createQuery(Article::class) {
            where(table.tags eq tagName)
            select(table.fetch(ArticleSimple::class))
        }.fetchPage(skip - 1, limit)
        return PageRes(page.totalRowCount, skip, limit, page.rows)
    }

    fun findByUserId(userId: Int, skip: Int, limit: Int) = sql.createQuery(Article::class) {
        where(table.authorId eq userId)
        select(table.fetch(ArticleSimple::class))
    }.fetchPage(skip - 1, limit)

    fun like(articleId: Int, userId: Int) {
        sql.createQuery(ArticleLike::class) {
            where(table.articleId eq articleId)
            where(table.userId eq userId)
            select(table)
        }.execute().firstOrNull()?.let {
            sql.deleteById(ArticleLike::class, it.id)
            return
        }
        sql.save(ArticleLike {
            this.userId = userId
            this.articleId = articleId
        })
    }

    fun listByUserLike(userId: Int, skip: Int, limit: Int) = sql.createQuery(ArticleLike::class) {
        where(table.userId eq userId)
        select(table.fetch(ArticleLikeSimple::class))
    }.fetchPage(skip - 1, limit)
}