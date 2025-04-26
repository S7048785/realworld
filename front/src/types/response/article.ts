import type {ProfileRes} from "@/types/response/user.ts";

export interface ArticleRes {
  id: string;
  title: string;
  description: string;
  body: string
  tags: string
  createdAt: string
  liked: boolean
  favorited: boolean
  favoritesCount: number
  commentCount: number
  likeCount: number
  author: ProfileRes
}

export interface ArticleCardRes {
  id: string;
  title: string;
  description: string;
  tags: string
  createdAt: string
  likeCount: number;
  favoritesCount: number;
  liked: boolean;
  favorited: boolean;
  author: string;
  avatar: string;
}
