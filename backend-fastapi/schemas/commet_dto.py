from pydantic import BaseModel, EmailStr, Field
from datetime import datetime

from schemas.user_dto import UserSimple


class CommentSimple(BaseModel):
    id: int
    body: str
    user: UserSimple
    created_at: datetime

class CommentCreate(BaseModel):
    body: str
    article_id: int
