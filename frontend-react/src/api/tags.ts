import request from "@/utils/request.ts";
import type {Result} from "@/types/result.ts";

/**
 * 获取所有标签
 */
export const getTagsAll = async (): Result<string[]> => {
	return request({
		url: "/tags",
		method: "GET"
	})
}