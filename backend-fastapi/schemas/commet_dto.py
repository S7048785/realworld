from pydantic import BaseModel, EmailStr, Field
from datetime import datetime

from schemas.user_dto import UserSimple


class CommentSimple(BaseModel):
    body: str
    user: UserSimple
    created_at: datetime

class CommentCreate(BaseModel):
    body: str
    user_id: int
    article_id: int
    created_at: datetime
