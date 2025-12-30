import request from "@/utils/request.ts";
import type {Result} from "@/types/result.ts";
import type {UserLoginRes} from "@/types/response/user.ts";

/**
	* 登录用户
	* @param email 用户邮箱
	* @param password 用户密码
 */
const login = async (
		{email, password}: {email: string, password: string}
): Result<UserLoginRes> => {
	return request({
		method: 'POST',
		url: '/users/login',
		data: {
				email,
				password
		}
	})
}

/**
 * 注册用户
 * @param email 用户邮箱
 * @param password 用户密码
 */
const register = async (
		{email, password}: {email: string, password: string}
): Result<UserLoginRes> => {
	return request({
		method: 'POST',
		url: '/users',
		data: {
				email,
				password
		}
	})
}

export default {
	login,
	register
}