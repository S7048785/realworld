package com.yyjy.service

import cn.dev33.satoken.stp.StpUtil
import com.yyjy.common.BaseContext
import com.yyjy.common.PageRes
import com.yyjy.models.entity.Comment
import com.yyjy.models.entity.dto.CommentCreateInput
import com.yyjy.models.entity.dto.CommentSimple
import com.yyjy.repository.CommentRepository
import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.springframework.stereotype.Service
import java.time.LocalDateTime

/**
 * @author Nyxcirea
 * @date 2026/3/4
 * @description: 评论服务
 */
@Service
class CommentService(
    private val commentRepository: CommentRepository
) {

    fun page(articleId: Int, skip: Int, limit: Int): PageRes<CommentSimple> {
        val page = commentRepository.page(articleId, skip, limit)
        return PageRes(page.totalRowCount, skip, limit, page.rows)
    }

    fun create(commentInput: CommentCreateInput) {
        val currentUserId = StpUtil.getLoginIdAsInt()

        commentRepository.save(
            Comment {
                body = commentInput.body
                articleId = commentInput.article_id
                userId = currentUserId
                createdAt = LocalDateTime.now()
            }, SaveMode.INSERT_ONLY
        )
    }
}