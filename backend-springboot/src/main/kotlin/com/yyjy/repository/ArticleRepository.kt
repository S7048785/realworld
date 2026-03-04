package com.yyjy.repository

import com.yyjy.common.BaseContext
import com.yyjy.common.BusinessException
import com.yyjy.common.PageRes
import com.yyjy.models.entity.*
import com.yyjy.models.entity.dto.ArticleDetail
import com.yyjy.models.entity.dto.ArticleLikeSimple
import com.yyjy.models.entity.dto.ArticleSimple
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.spring.transaction.JimmerTransactionManager.sqlClient
import org.babyfish.jimmer.sql.ast.impl.query.Queries.createQuery
import org.babyfish.jimmer.sql.ast.impl.table.AssociationTableProxyImpl.table
import org.babyfish.jimmer.sql.kt.ast.expression.constant
import org.babyfish.jimmer.sql.kt.ast.expression.desc
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.`eq?`
import org.babyfish.jimmer.sql.kt.ast.expression.exists
import org.babyfish.jimmer.sql.kt.ast.expression.plus
import org.babyfish.jimmer.sql.kt.ast.expression.value
import org.babyfish.jimmer.sql.runtime.Selectors.select

interface ArticleRepository : KRepository<Article, Int> {
    fun list(skip: Int, limit: Int): PageRes<ArticleSimple> {
        val page = sql.createQuery(Article::class) {
            select(
                table.fetch(ArticleSimple::class)
            )
        }.fetchPage(skip - 1, limit)

        return PageRes(page.totalRowCount, skip, limit, page.rows)

    }

    fun listByTag(tagName: String, skip: Int, limit: Int): PageRes<ArticleSimple> {
        val page = sql.createQuery(Article::class) {
            where(table.tags eq tagName)
            select(
                table.fetch(ArticleSimple::class)
            )
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
            sql.createUpdate(Article::class) {
                set(table.likes, table.likes.plus(1))
            }
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

    fun listByUserFollowing(skip: Int, limit: Int): PageRes<ArticleSimple> {
        val userId = BaseContext.getCurrentId()!!
        val fetchPage = sql.createQuery(Article::class) {
            where(table.asTableEx().author.followings.userId eq userId)
            select(
                table.fetch(ArticleSimple::class)
            )
        }.fetchPage(skip - 1, limit)
        return PageRes(fetchPage.totalRowCount, skip, limit, fetchPage.rows)
    }

    fun findDetail(articleId: Int): ArticleDetail {
        return sql.createQuery(Article::class) {
            where(table.id eq articleId)
            select(table.fetch(ArticleDetail::class))
        }.fetchFirstOrNull() ?: throw BusinessException("文章不存在")
    }

    fun addLikes(articleId: Int) {
        sql.createUpdate(Article::class) {
            set(table.likes, table.likes.plus(1))
            where(table.id eq articleId)
        }.execute()
    }

    fun addViews(articleId: Int) {
        sql.createUpdate(Article::class) {
            set(table.views, table.views.plus(1))
            where(table.id eq articleId)
        }.execute()
    }
}