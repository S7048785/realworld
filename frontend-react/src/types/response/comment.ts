import type {UserSimple} from "@/types/response/user.ts";

export type CommentSimple = {
	id: number
	body: string
	user: UserSimple
	created_at: string
}