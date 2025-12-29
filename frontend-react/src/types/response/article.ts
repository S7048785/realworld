import type {UserSimple} from "@/types/response/user.ts";

export type ArticleSimple = {
	id: number,
	title: string,
	desc: string,
	author: UserSimple,
	likes: number,
	tags: string,
	created_at: string,
}