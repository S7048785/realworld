from pydantic import BaseModel, EmailStr, Field
from datetime import datetime

from schemas.user import UserDetail, UserSimple


class ArticleEdit(BaseModel):
    title: str
    body: str
    tags: str | None = None

class ArticleDetail(BaseModel):
    id: int
    title: str
    body: str
    tags: str | None = None
    author: UserSimple
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