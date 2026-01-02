import request from "@/utils/request.ts";
import type {PageData} from "@/types/result.ts";
import type {CommentSimple} from "@/types/response/comment.ts";

/**
 * 获取文章评论列表
 * @param article_id 文章ID
 * @param skip 跳过数量
 * @param limit 每页数量
 * @returns 评论列表
 */
const getCommentByArticleId = async ({article_id, skip = 1, limit = 5}: {
	article_id: number,
	skip?: number,
	limit?: number
}): PageData<CommentSimple> => {
	return request({
		url: "/comments",
		method: "GET",
		params: {
			article_id,
			skip: skip,
			limit: limit
		}
	})
}

const addComment = async ({article_id, content}: {
	article_id: number,
	content: string
}) => {
	return request({
		url: "/comments",
		method: "POST",
		data: {
			article_id: article_id,
			body: content
		}
	})
}

export default {
	addComment,
	getCommentByArticleId,
}