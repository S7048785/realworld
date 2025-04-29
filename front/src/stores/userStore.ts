import {defineStore} from "pinia";
import {
  getUserInfoAPI,
  followUserAPI,
  loginAPI,
  updateUserInfoAPI,
  registerAPI,
  logoutAPI
} from "@/api/user"
import type {UserAuthRes} from "@/types/response/user.ts";
import {notify} from "@kyvg/vue3-notification";
export const useUserStore = defineStore('user', () => {
  const userInfo = ref<UserAuthRes>();

  const isLogin = ref(false);

  const login = async (username: string, password: string) => {
    const res: any = await loginAPI(username, password);
    if (res.code === 0) {
      return false
    }
    localStorage.setItem('token', res.data.token);
    userInfo.value = res.data;
    isLogin.value = true;
    return true
  }

  /**
   * 退出登录
   */
  const logout = async () => {
    await logoutAPI();
    // 清空用户信息
    clearUserInfo()
    isLogin.value = false;
    notify({
      text: '已退出账号',
      type: 'success'
    })
  }

  const register = async (username: string, password: string) => {
    const res: any = await registerAPI(username, password)
    if (res.code === 0) {
      return false;
    }
    userInfo.value = res.data
    isLogin.value = true;
    return true;
  }

  /**
   * 更新用户信息
   */
  const updateUserInfo = async (profile: any) => {
    if (!userInfo.value) {
      // 用户未登录 则返回
      return;
    }
    const res: any = await updateUserInfoAPI(profile)
    console.log(res)
    if (res.code === 0) {
      return false;
    }
    userInfo.value = res.data
    return true;
  }

  const getUserInfo = async (username: string) => {
    const res: any = await getUserInfoAPI(username);
    return res.data;
  }

  const followUser = async (userId: number) => {
    await followUserAPI(userId)
  }

  const clearUserInfo = () => {
    userInfo.value = {} as any;
    isLogin.value = false
  }

  return {
    userInfo,
    isLogin,
    login,
    logout,
    register,
    updateUserInfo,
    followUser,
    getUserInfo,
    clearUserInfo
  }
}, {
  persist: true
})
