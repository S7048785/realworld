# schemas/user.py
from pydantic import BaseModel, Field, computed_field, model_validator
from datetime import datetime

class UserCreate(BaseModel):
    username: str = Field(..., min_length=3, max_length=50)
    email: str
    password: str = Field(..., min_length=6)
    bio: str | None = None

class UserUpdate(BaseModel):
    username: str
    email: str
    password: str
    avatar: str
    bio: str

class UserLogin(BaseModel):
    email: str
    password: str

class UserRegister(BaseModel):
    email: str
    password: str


class UserDetail(BaseModel):
    id: int
    username: str
    email: str
    bio: str | None
    avatar: str
    created_at: datetime

    class Config:
        from_attributes = True  # 允许从 ORM 对象自动转换（Pydantic v2）

class UserSimple(BaseModel):
    id: int
    username: str
    avatar: str

class UserAuthor(BaseModel):
    id: int
    username: str
    avatar: str

    articles: int = 0
    followers: int = 0
    following: bool = False

