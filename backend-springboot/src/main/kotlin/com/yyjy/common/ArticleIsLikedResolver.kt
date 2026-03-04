package com.yyjy.common

import com.yyjy.models.entity.ArticleLike
import com.yyjy.models.entity.article
import com.yyjy.models.entity.id
import com.yyjy.models.entity.userId
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.KTransientResolver
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.valueIn
import org.springframework.stereotype.Component

/**
 * 文章是否点赞
 */
@Component
class ArticleIsLikedResolver(
    private val sqlClient: KSqlClient
) : KTransientResolver<Int, Boolean> {

    // ids 是当前查询出来的一批文章的 ID 集合 (Jimmer 自动合并的)
    override fun resolve(ids: Collection<Int>): Map<Int, Boolean> {
        
        // 1. 获取当前登录用户的 ID 
        // (通常从 Spring SecurityContext、ThreadLocal 或 Token 解析器中获取)
        val currentUserId = BaseContext.getCurrentId() ?: return ids.associateWith { false }
        // 💡 核心优化：如果用户未登录，直接返回全部为 false 的 Map，完全跳过数据库查询
        // 2. 批量查询：在点赞表中，找出现前用户点赞过的这些文章的 ID
        val likedArticleIds = sqlClient.createQuery(ArticleLike::class) {
            where(table.article.id valueIn ids)
            where(table.userId eq currentUserId)
            select(table.article.id)
        }.execute()

        // 3. 组装结果：Jimmer 需要一个 Map<文章ID, 是否点赞>
        return ids.associateWith { articleId ->
            likedArticleIds.contains(articleId)
        }
    }
}
