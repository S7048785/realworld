import {create} from "zustand";
import type {ArticleCreate} from "@/types/response/article.ts";
import {createJSONStorage, persist} from 'zustand/middleware'

interface ArticleDraftState {
	article: ArticleCreate | null;
	setArticle: (article: ArticleCreate | null) => void;
}

export const useArticleDraftStore = create<ArticleDraftState>()(
		persist((set) => ({
					article: null,
					setArticle: (article) => set({article}),
				}), {
					name: "article",
					storage: createJSONStorage(() => localStorage), // (optional) by default, 'localStorage' is used
				}
		)
)