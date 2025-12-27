import axios from "axios";

const request = axios.create({
  baseURL: "http://localhost:8081",
  timeout: 10000,
  withCredentials: true,
  headers: {
    "Content-Type": "application/json",
  },
});

request.interceptors.request.use(
  (config) => {
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

request.interceptors.response.use(
  (response) => {
    if (response.data.code === 1000) {
      // 清除登录状态
      console.log("用户未登录");
    }
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
    return response;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default request;
