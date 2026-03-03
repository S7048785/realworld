package com.yyjy.service

import com.yyjy.common.PageRes
import com.yyjy.models.entity.dto.ArticleDetail
import com.yyjy.models.entity.dto.ArticleEdit
import com.yyjy.models.entity.dto.ArticleLikeSimple
import com.yyjy.models.entity.dto.ArticleSimple

/**
 * @author Nyxcirea
 * @date 2026/3/2
 * @description: TODO
 */
interface ArticleService {
    fun list(skip: Int, limit: Int): PageRes<ArticleSimple>
    fun listByTag(tagName: String, skip: Int, limit: Int): PageRes<ArticleSimple>
    fun addArticle(articleEdit: ArticleEdit)
    fun listByUser(userId: Int, skip: Int, limit: Int): PageRes<ArticleSimple>
    fun likeArticle(articleId: Int)
    fun listByUserLike(userId: Int, skip: Int, limit: Int): PageRes<ArticleLikeSimple>
    fun listByUserFollowing(skip: Int, limit: Int): PageRes<ArticleSimple>
    fun getArticle(articleId: Int): ArticleDetail
}