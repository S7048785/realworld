package com.yyjy.repository

import com.yyjy.models.entity.Comment
import org.babyfish.jimmer.spring.repository.KRepository

interface CommentRepository : KRepository<Comment, Int> {
}