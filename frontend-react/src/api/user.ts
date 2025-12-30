import request from "@/utils/request.ts";
import type {Result} from "@/types/result.ts";
import type {UserDetail, UserLoginRes} from "@/types/response/user.ts";

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
		url: '/users/register',
		data: {
				email,
				password
		}
	})
}

/**
 * 获取用户信息
 */
const getUserInfo = async (): Result<UserDetail> => {
	return request({
		method: 'GET',
		url: '/users/me'
	})
}

const updateUserInfo = async (
		{username, email, password, avatar, bio}: {username: string, email: string, password: string, avatar: string, bio: string}
): Result<UserDetail> => {
	return request({
		method: 'PUT',
		url: '/users',
		data: {
				username,
				email,
				password,
				avatar,
				bio
		}
	})
}

export default {
	login,
	register,
	getUserInfo,
	updateUserInfo
}
