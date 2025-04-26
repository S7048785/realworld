import {defineStore} from "pinia";
import {getUserInfoAPI, followUserAPI, loginAPI, updateUserInfoAPI, registerAPI} from "@/api/user"
import type {UserAuthRes} from "@/types/response/user.ts";
export const useUserStore = defineStore('user', () => {


  const userInfo = ref<UserAuthRes>()

  const login = async (username: string, password: string) => {
    const res: any = await loginAPI(username, password);
    if (res.code === 0) {
      return false
    }
    localStorage.setItem('token', res.data.token);
    userInfo.value = res.data;
    return true
  }

  const register = async (username: string, password: string) => {
    const res: any = await registerAPI(username, password)
    userInfo.value = res.data
  }

  const updateUserInfo = async () => {
    if (!userInfo.value) {
      // 用户未登录 则返回
      return;
    }
    await updateUserInfoAPI(userInfo.value)
  }

  const getUserInfo = async (userId: number) => {
    const res: any = await getUserInfoAPI(userId);
    return res.data;
  }

  const followUser = async (userId: number) => {
    await followUserAPI(userId)
  }

  return {
    userInfo,
    login,
    register,
    updateUserInfo,
    followUser,
    getUserInfo
  }
}, {
  persist: true
})
