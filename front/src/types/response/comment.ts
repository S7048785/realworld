import type {ProfileRes} from "@/types/response/user.ts";

export interface CommentRes {
  id: string;
  createdAt: string;
  body: string;
  author: ProfileRes;
}
