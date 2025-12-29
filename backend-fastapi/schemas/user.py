# schemas/user.py
from pydantic import BaseModel, EmailStr, Field
from datetime import datetime

class UserCreate(BaseModel):
    username: str = Field(..., min_length=3, max_length=50)
    email: str | None = None
    password: str = Field(..., min_length=6)
    bio: str | None = None

class UserUpdate(BaseModel):
    username: str
    email: str
    password: str
    avatar: str
    bio: str
    # 注意：不包含 password（除非有单独的“修改密码”接口）

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