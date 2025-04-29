
export interface ProfileRes {
  id: string
  username: string
  bio: string
  avatar: string
  fansCount: number
  followCount: number
  following: boolean
}

export interface UserAuthRes {
  id: string;
  username: string;
  nickname: string;
  bio: string;
  avatar: string;
  followCount: number;
  fansCount: number;
}
