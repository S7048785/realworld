
export interface UserAuth {
  email: string
  token: string
  username: string
  bio: string
  avatar: string
}

export interface Profile {
  id: string
  username: string
  bio: string
  avatar: string
  fansCount: number
  followCount: number
  following: boolean
}

