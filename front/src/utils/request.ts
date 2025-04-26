import axios from "axios"
import {notify} from "@kyvg/vue3-notification";
import {useUserStore} from "@/stores/userStore.ts";
import type {UserAuthRes} from "@/types/response/user.ts";
// const userStore = useUserStore()
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
    // 存入token
    // if (userStore.userInfo) {
    //   config.headers.Authorization = userStore.userInfo.token
    // }
    const userJson = localStorage.getItem('user') || '{}'
    const user: UserAuthRes = JSON.parse(userJson)
    config.headers.Authorization = user.token
    return config;
  },
  error => {
    // notify({
    //   text: error.data.msg,
    //   type: "error"
    // })
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
    }
    return response.data
  },
  error => {
    return Promise.reject(error)
  }
)

export default request;
