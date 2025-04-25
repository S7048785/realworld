import type {Profile} from "@/types/response/user.ts";

export interface Comment {
  id: string;
  createdAt: string;
  updatedAt: string;
  body: string;
  author: Profile;
}
