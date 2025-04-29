import axios from "axios"
import {notify} from "@kyvg/vue3-notification";
import router from "@/router";
import {useUserStore} from "@/stores/userStore.ts";
const request = axios.create({
  baseURL: "http://localhost:8080",
  timeout: 3000,
  headers: {
    "Content-Type": "application/json",
  }
})

// 请求拦截器
request.interceptors.request.use(
  config => {

    config.headers.Authorization = localStorage.getItem('token');
    return config;
  },
  error => {

    return Promise.reject(error)
  }
)

// 响应拦截器
request.interceptors.response.use(
  response => {
    if (response.data.code === 0) {
      notify({
        text: response.data.msg,
        type: 'error'
      })
      router.push('/404')
    }
    return response.data
  },
  error => {

    if (error.status === 401) {
      notify({
        text: '登录已过期，请重新登录',
        type: 'warn'
      })
      // 清空用户信息
      useUserStore().clearUserInfo();
      router.push('/login')
    }

    return Promise.reject(error)
  }
)

export default request;
