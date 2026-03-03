package com.yyjy.repository

import com.yyjy.common.BaseContext
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
        TODO()
//        val userId = BaseContext.getCurrentId()
//        val page = sql.createQuery(Article::class) {
//            where(table.tags eq tagName)
//            select(
//                table.fetch(ArticleSimple::class),
//                exists(
//                    subQuery(ArticleLike::class) {
//                        userId?.let {
//                            where(
//                                table.article eq parentTable,
//                                table.userId eq userId  // 指定用户ID
//                            )
//                            select(constant(1))
//                        } ?: select(constant(0))
//                    }
//                )
//            )
//        }.fetchPage(skip - 1, limit)
//        val rows = page.rows.map {
//            it._1.copy(isLike = it._2)
//        }
//        return PageRes(page.totalRowCount, skip, limit, rows)
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
        TODO()
//        val currentUserId = BaseContext.getCurrentId()
//        val page = sql.createQuery(Article::class) {
//            where(
//                table.asTableEx().author.followings.userId eq currentUserId,
//                table.asTableEx().join<User>("author")           // Article -> User (Author)
//                    .join<UserFollow>("followers")  // User -> UserFollow (被关注记录)
//                    .userId eq currentUserId
//            )
//            orderBy(table.createdAt.desc())
//            select(table.fetch(ArticleSimple::class), exists(
//                subQuery(ArticleLike::class) {
//                    currentUserId?.let {
//                        where(
//                            table.article eq parentTable,
//                            table.userId eq currentUserId
//                        )
//                        select(constant(1))
//                    } ?: select(constant(0))
//                }
//            ))
//        }.fetchPage(skip - 1, limit)
//        val rows = page.rows.map {
//            it._1.copy(isLike = it._2)
//        }
//        return PageRes(page.totalRowCount, skip, limit, rows)
    }

    fun findDetail(articleId: Int): ArticleDetail {
        TODO()

//        return sql.createQuery(Article::class) {
//            where(table.id eq articleId)
//            select(table.fetch(ArticleDetail::class))
//        }.execute()
    }
}