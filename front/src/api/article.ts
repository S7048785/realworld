import request from "@/utils/request.ts"
import type {ArticleCreateReq, ArticlePageReq, ArticleUpdateReq} from "@/types/request/article.ts";
import type {UnwrapRef} from "vue";
/**
 * 获取文章详情
 * @param id
 */
export function getArticleAPI(id: number) {
  return request({
    url: `/articles/${id}`,
    method: 'get'
  })
}

/**
 * 更新文章信息
 * @param id
 * @param article
 */
export function updateArticleAPI(id: number, article: ArticleUpdateReq) {
  return request({
    url: `/articles/${id}`,
    method: 'put',
    data: article
  })
}

/**
 * 删除文章
 * @param id
 */
export function deleteArticleAPI(id: number) {
  return request({
    url: `/articles/${id}`,
    method: 'delete'
  })
}

/**
 * 创建文章
 * @param article
 */
export function createArticleAPI(article: ArticleCreateReq) {
  return request({
    url: 'articles',
    method: 'post',
    data: article
  })
}

/**
 * 收藏文章
 * @param id
 */
export function favoriteArticleAPI(id: number) {
  return request({
    url: `/articles/favorite/${id}`,
    method: 'post'
  })
}

/**
 * 创建评论
 * @param articleId
 * @param body
 */
export function commentCreateAPI(articleId: number, body: string) {
  return request({
    url: `/articles/comments/${articleId}`,
    method: 'post',
    data: {
      body
    }
  })
}

/**
 * 根据文章id获取所有评论
 * @param articleId
 */
export function getCommentAPI(articleId: number) {
  return request({
    url: `/articles/comments`,
    method: 'get',
    params: {
      id: articleId
    }
  })
}

export function deleteCommentAPI(id: number) {
  return request({
    url: `/articles/comments/${id}`
  })
}

/**
 * 获取文章卡片列表
 * @param articleCard
 */
export function getArticleCardAPI(articleCard: ArticlePageReq) {
  return request({
    url: `/articles/list`,
    method: 'get',
    params: articleCard
  })
}

/**
 * 获取文章卡片列表 (需要登录校验)
 * @param articleCard
 */
export function getArticleCardFeedAPI(articleCard: ArticlePageReq) {
  return request({
    url: `/articles/feed`,
    method: 'get',
    params: articleCard
  })
}

/**
 * 获取已点赞的卡片
 * @param articleCard
 */
export function getArticleCardLikedAPI(articleCard: ArticlePageReq) {
  return request({
    url: `/articles/likes`,
    method: 'get',
    params: articleCard
  })
}

/**
 * 点赞文章
 * @param articleId
 */
export function likeArticleAPI(articleId: number | string) {
  return request({
    url: `/articles/like/${articleId}`,
    method: 'post'
  })
}
