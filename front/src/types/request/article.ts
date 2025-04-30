
export interface ArticleUpdateReq {
  title: string;
  description: string;
  body: string;
}

export interface ArticleCreateReq {
  title: string;
  description: string;
  body: string;
  tagList: string[];
}

export interface ArticlePageReq {
  username?: string;
  likerId?:string | number;
  tagList?: string;
  limit: number;
  offset: number;
}
