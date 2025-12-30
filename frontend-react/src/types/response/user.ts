export type UserSimple = {
	id: number,
	username: string,
	avatar: string,
}

export type UserDetail = {
	id: number,
	username: string,
	email: string,
	bio: string,
	avatar: string,
	created_at: Date
}

export type UserLoginRes = {
	access_token: string,
	user: UserDetail,
}