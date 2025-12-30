import axios from "axios";
import {clearToken, getToken} from "@/utils/token.ts";
import toast from "react-hot-toast";

// 读取 BACKEND_API_URL 环境变量
const backendUrl = import.meta.env.VITE_BACKEND_API_URL;

const request = axios.create({
  baseURL: backendUrl,
  timeout: 10000,
  withCredentials: true,
  headers: {
    "Content-Type": "application/json",
  },
});

request.interceptors.request.use(
  (config) => {
    const token = getToken();
    if (token) {
      config.headers["Authorization"] = token;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

request.interceptors.response.use(
  (response) => {

    if (response.data.code === 1004) {
      alert("当前用户无操作权限");
      window.location.href = "/";
      // 清除登录状态,但不调用 logout 接口避免循环
      // const userStore = useUserStore.getState();
      // 这里不能调用 logout 方法，因为会导致死循环
      // userStore.setUser(undefined);
      // userStore.setIsLogin(false);
      // 抛出异常,阻止后续操作
      // return Promise.reject("用户未登录");
    }
    return response.data;
  },
  (error) => {
    if (error.response?.status === 401) {
      // 清除登录状态
      clearToken();
      toast.error(error.response.message || "用户未登录");
    }
    console.log(error);
    return Promise.reject(error);
  }
);

export default request;
