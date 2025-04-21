import request from "@/utils/request.ts"

// 登录
export function login(data: any) {
  return request({
    url: '/login',
    method: 'post',
    data
  })
}

// 注册
export function register(data: any) {
  return request({
    url: '/register',
    method: 'post',
    data
  })
}

// 获取用户信息
export function getUserInfo() {
  return request({
    url: '/user/info',
    method: 'get'
  })
}
