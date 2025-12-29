export type UserLoginReq = {
	email: string,
	password: string
}

export type UserRegisterReq = {
	email: string,
	password: string
}

export type UserUpdateReq = {
	username: string,
	email: string,
	password: string,
	bio: string,
	avatar: string,
}