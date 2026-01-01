import request from "@/utils/request.ts";
import type {PageData, Result} from "@/types/result.ts";
import type {ArticleDetail, ArticleSimple} from "@/types/response/article.ts";

/**
 * 获取用户文章列表
 * @param userId 用户ID
 * @param skip
 * @param limit
 * @returns 文章列表
 */
export const getArticleListByUserId = async ({userId, skip = 1, limit = 5}: {
	userId: number,
	skip?: number,
	limit?: number
}): PageData<ArticleSimple> => {
	return request({
		url: "/articles/user",
		method: "GET",
		params: {
			user_id: userId,
			skip: skip,
			limit: limit
		}
	})
}

/**
 * 获取标签文章列表
 * @param tag_name 标签名称
 * @param skip
 * @param limit
 * @returns 文章列表
 */
const getArticleListByTag = async ({tag_name, skip = 1, limit = 5}: {
	tag_name: string,
	skip?: number,
	limit?: number
}): PageData<ArticleSimple> => {
	return request({
		url: "/articles/tag",
		method: "GET",
		params: {
			tag_name: tag_name,
			skip: skip,
			limit: limit
		}
	})
}

/**
 * 获取所有文章列表
 * @param skip
 * @param limit
 * @returns 文章列表
 */
const getArticleAllList = async ({skip = 1, limit = 5}: { skip?: number, limit?: number }): PageData<ArticleSimple> => {
	return request({
		url: "/articles/list",
		method: "GET",
		params: {
			skip: skip,
			limit: limit
		}
	})
}

/**
 * 获取文章详情
 * @param article_id 文章ID
 * @returns 文章详情
 */
const getArticleDetail = async ({article_id}: { article_id: number }): Result<ArticleDetail> => {
	return request({
		url: `/articles/${article_id}`,
		method: "GET",
	})
}

/**
 * 点赞文章
 */
const likeArticle = async (article_id: number ): Result<void> => {
	return request({
		url: `/articles/like/${article_id}`,
		method: "POST",
	})
}

/**
 * 获取用户点赞文章列表
 * @param user_id 用户ID
 * @param skip 跳过数量
 * @param limit 每页数量
 * @returns 文章列表
 */
const getArticleListByLike = async ({user_id, skip = 1, limit = 5}: {
	user_id: number,
	skip?: number,
	limit?: number
}): PageData<ArticleSimple> => {
	return request({
		url: `/articles/user/${user_id}/liked`,
		method: "GET",
		params: {
			user_id: user_id,
			skip: skip,
			limit: limit
		}
	})
}

/**
 * 获取用户关注文章列表
 * @param skip 跳过数量
 * @param limit 每页数量
 * @returns 文章列表
 */
const getArticleListByFollow = async ({skip = 1, limit = 5}: { skip?: number, limit?: number }): PageData<ArticleSimple> => {
	return request({
		url: "/articles/following",
		method: "GET",
		params: {
			skip: skip,
			limit: limit
		}
	})
}

export default {
	getArticleListByUserId,
	getArticleListByTag,
	getArticleAllList,
	getArticleDetail,
	likeArticle,
	getArticleListByLike,
	getArticleListByFollow
}