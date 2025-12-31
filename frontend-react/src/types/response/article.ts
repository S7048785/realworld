import type {UserAuthor, UserSimple} from "@/types/response/user.ts";

export type ArticleSimple = {
	id: number,
	title: string,
	desc: string,
	author: UserSimple,
	likes: number,
	tags: string,
	created_at: string,
}
export type ArticleDetail = {
	id: number,
	title: string,
	body: string,
	tags: string,
	likes: number,
	author: UserAuthor,
	isLike: boolean,
	created_at: Date,
	updated_at: Date,
}