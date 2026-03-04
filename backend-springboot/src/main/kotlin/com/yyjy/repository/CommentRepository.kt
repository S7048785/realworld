package com.yyjy.repository

import com.yyjy.common.BaseContext
import com.yyjy.common.PageRes
import com.yyjy.models.entity.Comment
import com.yyjy.models.entity.articleId
import com.yyjy.models.entity.dto.CommentCreateInput
import com.yyjy.models.entity.dto.CommentSimple
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.kt.ast.expression.eq

interface CommentRepository : KRepository<Comment, Int> {

    /**
     * 分页获取文章的评论
     */
    fun page(articleId: Int, skip: Int, limit: Int) = sql.createQuery(Comment::class) {
        where(table.articleId eq articleId)
        select(table.fetch(CommentSimple::class))
    }.fetchPage(skip - 1, limit)

}