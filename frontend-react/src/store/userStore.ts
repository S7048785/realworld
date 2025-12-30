import { create } from "zustand";
import type { UserDetail } from "@/types/response/user";
import api from "@/api/user.ts";
import {clearToken} from "@/utils/token.ts";

interface UserState {
  user: UserDetail | null;
  isAuthenticated: boolean;
  setUser: (user: UserDetail | null) => void;
  clearUser: () => void;
  getUserInfo: () => Promise<void>;
}

export const useUserStore = create<UserState>((set) => ({
  user: null,
  isAuthenticated: false,
  setUser: (user) => set({ user, isAuthenticated: !!user }),
  clearUser: () => {
    clearToken();
    set({user: null, isAuthenticated: false})
  },
  getUserInfo: async () => {
    const res = await api.getUserInfo();
    if (res.data) {
      set({ user: res.data, isAuthenticated: true });
    }
  },
}));
