import type {Profile} from "@/types/response/user.ts";

export interface Article {
  id: string;
  title: string;
  description: string;
  body: string
  tagList: string[]
  createdAt: string
  favorited: boolean
  favoritesCount: number
  commentCount: number
  likeCount: number
  author: Profile
}

export interface ArticleCard {
  id: string;
  title: string;
  description: string;
  tagList: string[]
  createdAt: string
  likeCount: number;
  liked: boolean;
  author: Profile
}
