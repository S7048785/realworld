# app/models/models.py
from typing import Optional, List
from datetime import datetime

from sqlalchemy.orm import relationship
from sqlmodel import SQLModel, Field, Relationship
from sqlalchemy import Column, DateTime, func

class SoftDeleteMixin(SQLModel):
    deleted: bool = Field(default=False)


class User( SoftDeleteMixin, SQLModel, table=True):
    __tablename__ = "user"

    id: int = Field(default=None, primary_key=True)
    username: str = Field(unique=True, index=True)
    email: str = Field(unique=True, index=True)
    password: str
    bio: Optional[str] = Field(description="个人简介")
    avatar: Optional[str] = Field(
        default="https://i0.hdslb.com/bfs/article/b6b843d84b84a3ba5526b09ebf538cd4b4c8c3f3.jpg",
        description="头像"
    )

    articles: List["Article"] = Relationship(back_populates="author")
    liked_articles: List["ArticleLike"] = Relationship(back_populates="user")
    comments: List["Comment"] = Relationship(back_populates="user")
    # 添加关注相关的反向关系
    followed_users: List["UserFollow"] = Relationship(
        back_populates="user",
        sa_relationship_kwargs={"foreign_keys": "[UserFollow.user_id]"}
    )
    followers: List["UserFollow"] = Relationship(
        back_populates="followed_user",
        sa_relationship_kwargs={"foreign_keys": "[UserFollow.followed_user_id]"}
    )

    created_at: datetime = Field(
        sa_column=Column(DateTime, default=func.now(), nullable=False)
    )
    updated_at: datetime = Field(
        sa_column=Column(DateTime, default=func.now(), onupdate=func.now(), nullable=False)
    )
    
    def verify_password(self, plain_password: str) -> bool:
        return plain_password == self.password


class Article( SoftDeleteMixin, SQLModel, table=True):
    __tablename__ = "article"

    id: int = Field(default=None, primary_key=True)
    title: str = Field(description="文章标题")
    desc: str = Field(description="文章描述")
    body: str = Field(description="文章内容")
    user_id: int = Field(foreign_key="user.id")
    author: User = Relationship(back_populates="articles")
    likes: int = Field(default=0)
    comments: int = Field(default=0)
    tags: Optional[str] = None

    article_likes: List["ArticleLike"] = Relationship(back_populates="article")
    comments_list: List["Comment"] = Relationship(back_populates="article")

    created_at: datetime = Field(
        sa_column=Column(DateTime, default=func.now(), nullable=False)
    )
    updated_at: datetime = Field(
        sa_column=Column(DateTime, default=func.now(), onupdate=func.now(), nullable=False)
    )


class Comment(SoftDeleteMixin, SQLModel, table=True):
    __tablename__ = "comment"

    id: int = Field(default=None, primary_key=True)
    body: str
    user_id: int = Field(foreign_key="user.id")
    article_id: int = Field(foreign_key="article.id")  # ← 已补充！

    # 对应的反向关系
    user: User = Relationship(back_populates="comments")
    article: Article = Relationship(back_populates="comments_list")

    created_at: datetime = Field(
        sa_column=Column(DateTime, default=func.now(), nullable=False)
    )

class Tag(SoftDeleteMixin, SQLModel, table=True):
    __tablename__ = "tags"

    id: int = Field(default=None, primary_key=True)
    name: str = Field(unique=True, description="标签名称")

class UserFollow(SoftDeleteMixin, SQLModel, table=True):
    __tablename__ = "user_follow"

    id: int = Field(default=None, primary_key=True)
    user_id: int = Field(foreign_key="user.id", description="关注ID")
    followed_user_id: int = Field(foreign_key="user.id", description="被关注ID")

    # 关系定义
    user: "User" = Relationship(back_populates="followed_users",
                             sa_relationship_kwargs={"foreign_keys": "[UserFollow.user_id]"})
    followed_user: "User" = Relationship(back_populates="followers",
                                      sa_relationship_kwargs={"foreign_keys": "[UserFollow.followed_user_id]"})

    created_at: datetime = Field(
        sa_column=Column(DateTime, default=func.now(), nullable=False),
        description="关注时间"
    )

class ArticleLike(SoftDeleteMixin, SQLModel, table=True):
    __tablename__ = "article_like"

    id: int = Field(default=None, primary_key=True)
    user_id: int = Field(foreign_key="user.id")
    article_id: int = Field(foreign_key="article.id")

    # 关系定义
    user: "User" = Relationship(back_populates="liked_articles")
    article: "Article" = Relationship(back_populates="article_likes")

    created_at: datetime = Field(
        sa_column=Column(DateTime, default=func.now(), nullable=False)
    )
