from pydantic import BaseModel, EmailStr, Field
from datetime import datetime

from schemas.user import UserPublic


class ArticleEdit(BaseModel):
    title: str
    body: str
    tags: list[str] | None = None

class ArticleDetail(BaseModel):
    id: int
    title: str
    body: str
    tags: list[str] | None = None
    author: UserPublic
    created_at: datetime
    updated_at: datetime
