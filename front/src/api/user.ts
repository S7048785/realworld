import request from "@/utils/request.ts"
import {getArticleAPI} from "@/api/article.ts";

// 登录
export function loginAPI(
  username: string, password: string
) {
  return request({
    url: '/users/login',
    method: 'post',
    data: {
      username,
      password
    }
  })
}

/**
 * 退出登录
 */
export function logoutAPI() {
  return request({
    url: '/users/logout',
    method: 'delete'
  })
}

// 注册
export function registerAPI(username: string, password: string) {
  return request({
    url: '/users/register',
    method: 'post',
    data: {
      username,
      password
    }
  })
}

// 获取用户信息
export function getUserInfoAPI(username: string) {
  return request({
    url: `/profiles/${username}`,
    method: 'get'
  })
}

/**
 * 更新当前用户信息
 */
export function updateUserInfoAPI({nickname, bio, avatar, password}: {
  nickname: string;
  bio: string;
  avatar: string;
  password: string
}) {
  return request({
    url: '/profiles',
    method: 'put',
    data: {
      nickname,
      bio,
      avatar,
      password: password || null
    }
  })
}

/**
 * 关注用户
 * @param userId 目标用户id
 */
export function followUserAPI(userId: number) {
  return request({
    url: `/profiles/follow/${userId}`,
    method: 'post'
  })
}
