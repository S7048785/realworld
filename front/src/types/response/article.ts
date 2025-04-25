import type {Profile} from "@/types/response/user.ts";

export interface Article {
  id: string;
  title: string;
  description: string;
  body: string
  tagList: string[]
  createdAt: string
  updatedAt: string
  favorited: boolean
  favoritesCount: number
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
  author: {
    username: string;
    avatar: string;
  }
}
