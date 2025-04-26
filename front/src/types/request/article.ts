
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
  authorId?: number;
  tagList?: string;
  limit: number;
  offset: number;
}
