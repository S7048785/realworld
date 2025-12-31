from pydantic import BaseModel, EmailStr, Field
from datetime import datetime

from schemas.user import UserDetail, UserSimple, UserAuthor


class ArticleEdit(BaseModel):
    title: str
    body: str
    tags: str | None = None

class ArticleDetail(BaseModel):
    id: int
    title: str
    body: str
    tags: str | None = None
    likes: int = 0
    author: UserAuthor
    isLike: bool = False
    created_at: datetime
    updated_at: datetime

class ArticleSimple(BaseModel):
    id: int
    title: str
    desc: str
    author: UserSimple
    likes: int
    tags: str | None = None
    created_at: datetime