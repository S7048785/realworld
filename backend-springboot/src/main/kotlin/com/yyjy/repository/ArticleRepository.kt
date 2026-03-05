package com.yyjy.repository

import cn.dev33.satoken.stp.StpUtil
import com.yyjy.common.BaseContext
import com.yyjy.common.BusinessException
import com.yyjy.common.PageRes
import com.yyjy.models.entity.*
import com.yyjy.models.entity.dto.ArticleDetail
import com.yyjy.models.entity.dto.ArticleLikeSimple
import com.yyjy.models.entity.dto.ArticleSimple
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.like
import org.babyfish.jimmer.sql.kt.ast.expression.minus
import org.babyfish.jimmer.sql.kt.ast.expression.plus
import java.time.LocalDateTime

/**
 * 文章仓库接口
 * 提供文章相关的数据库操作方法
 */
interface ArticleRepository : KRepository<Article, Int> {
    /**
     * 获取文章列表
     * @param skip 跳过的记录数
     * @param limit 每页记录数
     * @return 文章列表分页结果
     */
    fun list1(skip: Int, limit: Int): PageRes<ArticleSimple> {
        val page = sql.createQuery(Article::class) {
            select(
                table.fetch(ArticleSimple::class)
            )
        }.fetchPage(skip - 1, limit)

        return PageRes(page.totalRowCount, skip, limit, page.rows)

    }

    /**
     * 根据标签获取文章列表
     * @param tagName 标签名称
     * @param skip 跳过的记录数
     * @param limit 每页记录数
     * @return 标签下的文章列表分页结果
     */
    fun listByTag(tagName: String, skip: Int, limit: Int): PageRes<ArticleSimple> {
        val page = sql.createQuery(Article::class) {
            where(table.tags like tagName)
            select(
                table.fetch(ArticleSimple::class)
            )
        }.fetchPage(skip - 1, limit)

        return PageRes(page.totalRowCount, skip, limit, page.rows)
    }

    /**
     * 根据用户ID获取文章列表
     * @param userId 用户ID
     * @param skip 跳过的记录数
     * @param limit 每页记录数
     * @return 用户的文章列表分页结果
     */
    fun findByUserId(userId: Int, skip: Int, limit: Int) = sql.createQuery(Article::class) {
        where(table.authorId eq userId)
        select(table.fetch(ArticleSimple::class))
    }.fetchPage(skip - 1, limit)

    /**
     * 检查用户是否点赞过文章
     * @param articleId 文章ID
     * @param userId 用户ID
     * @return 点赞记录，如果未点赞则返回null
     */
    fun isLike(articleId: Int, userId: Int): ArticleLike? = sql.createQuery(ArticleLike::class) {
        where(table.articleId eq articleId)
        where(table.userId eq userId)
        select(table)
    }.execute().firstOrNull()

    /**
     * 获取用户点赞的文章列表
     * @param userId 用户ID
     * @param skip 跳过的记录数
     * @param limit 每页记录数
     * @return 用户点赞的文章列表分页结果
     */
    fun listByUserLike(userId: Int, skip: Int, limit: Int) = sql.createQuery(ArticleLike::class) {
        where(table.userId eq userId)
        select(table.fetch(ArticleLikeSimple::class))
    }.fetchPage(skip - 1, limit)

    /**
     * 获取用户关注的作者的文章列表
     * @param skip 跳过的记录数
     * @param limit 每页记录数
     * @return 关注作者的文章列表分页结果
     */
    fun listByUserFollowing(skip: Int, limit: Int): PageRes<ArticleSimple> {
        val userId = StpUtil.getLoginIdAsInt()
        val fetchPage = sql.createQuery(Article::class) {
            where(table.asTableEx().author.followings.userId eq userId)
            select(
                table.fetch(ArticleSimple::class)
            )
        }.fetchPage(skip - 1, limit)
        return PageRes(fetchPage.totalRowCount, skip, limit, fetchPage.rows)
    }

    /**
     * 获取文章详情
     * @param articleId 文章ID
     * @return 文章详情
     * @throws BusinessException 如果文章不存在
     */
    fun findDetail(articleId: Int): ArticleDetail {
        return sql.createQuery(Article::class) {
            where(table.id eq articleId)
            select(table.fetch(ArticleDetail::class))
        }.fetchFirstOrNull() ?: throw BusinessException("文章不存在")
    }

    /**
     * 增加文章点赞数
     * @param articleId 文章ID
     */
    fun addLikes(articleId: Int) {
        sql.createUpdate(Article::class) {
            set(table.likes, table.likes.plus(1))
            where(table.id eq articleId)
        }.execute()
    }

    /**
     * 增加文章浏览量
     * @param articleId 文章ID
     */
    fun addViews(articleId: Int) {
        sql.createUpdate(Article::class) {
            set(table.views, table.views.plus(1))
            where(table.id eq articleId)
        }.execute()
    }

    /**
     * 点赞文章
     * @param articleId 文章ID
     * @param userId 用户ID
     */
    fun like(articleId: Int, userId: Int) {
        sql.save(ArticleLike {
            this.userId = userId
            this.articleId = articleId
            this.createdAt = LocalDateTime.now()
        }, SaveMode.INSERT_IF_ABSENT)
    }

    /**
     * 取消点赞
     * @param likeId 点赞记录ID
     */
    fun cancelLike(likeId: Int) {
        sql.deleteById(ArticleLike::class, likeId)
    }

    /**
     * 减少文章点赞数
     * @param articleId 文章ID
     */
    fun subLikes(articleId: Int) {
        sql.createUpdate(Article::class) {
            set(table.likes, table.likes.minus(1))
            where(table.id eq articleId)
        }.execute()
    }
}