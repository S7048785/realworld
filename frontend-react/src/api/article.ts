import request from "@/utils/request.ts";
import type {PageData} from "@/types/result.ts";
import type {ArticleSimple} from "@/types/response/article.ts";

/**
 * 获取用户文章列表
 * @param userId 用户ID
 * @param skip
 * @param limit
 * @returns 文章列表
 */
export const getArticleListByUserId = async ({userId, skip = 1, limit = 5}: { userId: number, skip?: number, limit?: number}): PageData<ArticleSimple> => {
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
export const getArticleListByTag = async ({tag_name, skip = 1, limit = 5}: { tag_name: string, skip?: number, limit?: number}): PageData<ArticleSimple> => {
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
export const getArticleAllList = async ({skip = 1, limit = 5}: { skip?: number, limit?: number}): PageData<ArticleSimple> => {
	return request({
		url: "/articles",
		method: "GET",
		params: {
			skip: skip,
			limit: limit
		}
	})
}