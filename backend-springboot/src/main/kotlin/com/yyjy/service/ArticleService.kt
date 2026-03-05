package com.yyjy.service

import cn.dev33.satoken.stp.StpUtil
import com.yyjy.common.PageRes
import com.yyjy.models.entity.copy
import com.yyjy.models.entity.dto.ArticleDetail
import com.yyjy.models.entity.dto.ArticleEdit
import com.yyjy.models.entity.dto.ArticleLikeSimple
import com.yyjy.models.entity.dto.ArticleSimple
import com.yyjy.repository.ArticleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

/**
 * 文章服务类
 * 提供文章相关的业务逻辑处理
 */
@Service
class ArticleService(
    private val articleRepository: ArticleRepository
)  {
    /**
     * 获取文章列表
     * @param skip 跳过的记录数
     * @param limit 每页记录数
     * @return 文章列表分页结果
     */
     fun list(
        skip: Int,
        limit: Int
    ): PageRes<ArticleSimple> = articleRepository.list1(skip, limit)

    /**
     * 获取用户点赞的文章列表
     * @param userId 用户ID
     * @param skip 跳过的记录数
     * @param limit 每页记录数
     * @return 用户点赞的文章列表分页结果
     */
    fun listByUserLike(
        userId: Int,
        skip: Int,
        limit: Int
    ): PageRes<ArticleLikeSimple> {
        val page = articleRepository.listByUserLike(userId, skip, limit)
        return PageRes(page.totalRowCount, skip, limit, page.rows)
    }

    /**
     * 获取文章详情
     * @param articleId 文章ID
     * @return 文章详情
     */
    fun getArticle(articleId: Int): ArticleDetail {
        val article = articleRepository.findDetail(articleId)
        // 增加浏览量
        articleRepository.addViews(articleId)
        return article
    }

    /**
     * 获取用户关注的作者的文章列表
     * @param skip 跳过的记录数
     * @param limit 每页记录数
     * @return 关注作者的文章列表分页结果
     */
    fun listByUserFollowing(
        skip: Int,
        limit: Int
    ): PageRes<ArticleSimple> {
        return articleRepository.listByUserFollowing(skip, limit)
    }

    /**
     * 点赞/取消点赞文章
     * @param articleId 文章ID
     */
    @Transactional
    fun likeArticle(articleId: Int) {
        val userId = StpUtil.getLoginIdAsInt()
        articleRepository.isLike(articleId, userId)?.let {
            // 取消点赞
            articleRepository.cancelLike(it.id)
            // 减少点赞数
            articleRepository.subLikes(articleId)
            return
        }
        articleRepository.like(articleId, userId)
        // 增加点赞数
        articleRepository.addLikes(articleId)
    }

    /**
     * 根据标签获取文章列表
     * @param tagName 标签名称
     * @param skip 跳过的记录数
     * @param limit 每页记录数
     * @return 标签下的文章列表分页结果
     */
    fun listByTag(
        tagName: String,
        skip: Int,
        limit: Int
    ): PageRes<ArticleSimple> = articleRepository.listByTag(tagName, skip, limit)

    /**
     * 添加文章
     * @param articleEdit 文章编辑对象
     */
    fun addArticle(articleEdit: ArticleEdit) {
        articleRepository.save(articleEdit.toEntity().copy {
            desc = this.body.substring(0, 50)
            authorId = StpUtil.getLoginIdAsInt()
            likes = 0
            views = 0
            comments = 0
            tags = articleEdit.tags
            val now = LocalDateTime.now()
            createdAt = now
            updatedAt = now
        })
    }

    /**
     * 根据用户ID获取文章列表
     * @param userId 用户ID
     * @param skip 跳过的记录数
     * @param limit 每页记录数
     * @return 用户的文章列表分页结果
     */
     fun listByUser(
        userId: Int,
        skip: Int,
        limit: Int
    ): PageRes<ArticleSimple> {
        val page = articleRepository.findByUserId(userId, skip, limit)
        return PageRes(page.totalRowCount, skip, limit, page.rows)
    }
}