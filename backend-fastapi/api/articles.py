# app/api/v1/articles.py
import json
from datetime import datetime

from sqlalchemy import func
from fastapi import APIRouter, Depends, HTTPException, status, Request
from sqlmodel import select
from sqlalchemy.orm import selectinload
from sqlmodel.ext.asyncio.session import AsyncSession
from db.session import get_session
from models.models import Article
from typing import List

from schemas.article_dto import ArticleEdit, ArticleSimple, ArticleDetail
from schemas.response import Result, PageResult
from schemas.validators import StringArrayValidator

router = APIRouter(prefix="/articles", tags=["articles"])

@router.post("", response_model=Result)
async def create_article(article: Article, session: AsyncSession = Depends(get_session)):
    session.add(article)
    await session.commit()
    await session.refresh(article)
    return Result.success()

@router.get(
    "",
    response_model=PageResult[ArticleSimple],
    summary="获取文章列表",
    description="返回所有未删除的文章列表"
)
async def read_articles(skip: int = 1, limit: int = 5, session: AsyncSession = Depends(get_session)):
    result = await session.execute(
        select(Article)
        .where(Article.deleted == False)
        .options(selectinload(Article.author))
        .offset((skip - 1) * limit)
        .limit(limit)
    )
    total = await session.execute(
        select(func.count(Article.id))
        .where(Article.deleted == False)
    )
    return PageResult(
        page=skip,
        page_size=limit,
        total=total.scalar_one(),
        list=result.scalars().all()
    )

@router.get(
    "/tag",
    response_model=PageResult[ArticleSimple],
    summary="获取标签下的文章列表",
    description="返回指定标签下的所有未删除文章列表"
)
async def read_article_by_tag(tag_name: str, skip: int = 1, limit: int = 5, session: AsyncSession = Depends(get_session)):
    articles = await session.execute(
        select(Article)
        .where(Article.deleted == False)
        .where(Article.tags.like(f"%{tag_name}%"))
        .options(selectinload(Article.author))
        .offset((skip - 1) * limit)
        .limit(limit)
    )

    total = await session.execute(
        select(func.count(Article.id))
        .where(Article.deleted == False)
        .where(Article.tags.like(f"%{tag_name}%"))
    )
    return PageResult(
        page=skip,
        page_size=limit,
        total=total.scalar_one(),
        list=articles.scalars().all()
    )

@router.post(
    "/add",
    response_model=Result,
    summary="添加文章",
    description="添加文章"
)
async def add_article(article_edit: ArticleEdit, request: Request, session: AsyncSession = Depends(get_session)):
    # 将 Pydantic 模型转换为 SQLModel 模型
    article = Article(
        title=article_edit.title,
        body=article_edit.body,
        tags=StringArrayValidator.from_json_string(json.dumps(article_edit.tags if article_edit.tags else [])),
        created_at=datetime.now(),
        updated_at=datetime.now(),
        user_id=request.session["user_id"],
        deleted=False,
        likes=0,
        comments=0
    )

    session.add(article)
    await session.commit()
    return Result.success()

@router.get(
    "/user",
    response_model=PageResult[ArticleSimple],
    summary="获取用户文章列表",
    description="返回指定用户ID的所有未删除文章列表"
)
async def read_article_by_user(user_id: int, skip: int = 1, limit: int = 5, session: AsyncSession = Depends(get_session)):
    articles = await session.execute(
        select(Article)
        .where(Article.deleted == False)
        .where(Article.user_id == user_id)
        .options(selectinload(Article.author))
        .offset((skip - 1) * limit)
        .limit(limit)
    )

    total = await session.execute(
        select(func.count(Article.id))
        .where(Article.deleted == False)
        .where(Article.user_id == user_id)
    )

    return PageResult(
            page=skip,
            page_size=limit,
            total=total.scalar_one(),
            list=articles.scalars().all()
        )

@router.get(
    "/{article_id}",
    response_model=Result[ArticleDetail],
    summary="获取文章详情",
    description="返回指定ID的文章详情"
)
async def read_article(article_id: int, session: AsyncSession = Depends(get_session)):
    # 使用select查询并预加载author关系
    result = await session.execute(
        select(Article)
        .where(Article.id == article_id, Article.deleted == False)
        .options(selectinload(Article.author))
    )
    article = result.scalar_one_or_none()

    if not article:
        raise HTTPException(status_code=404, detail="Article not found")
    return Result.success(data=article)


@router.delete(
    "/{article_id}",
    response_model=Result,
    summary="删除文章",
    description="删除指定ID的文章"
)
async def delete_article(article_id: int, session: AsyncSession = Depends(get_session)):
    article = await session.get(Article, article_id)
    if not article:
        raise HTTPException(status_code=404, detail="Article not found")
    article.deleted = True
    session.add(article)
    await session.commit()
    return Result.success()


@router.delete(
    "/user/{user_id}",
    response_model=Result,
    summary="删除用户文章",
    description="删除指定用户ID的所有文章"
)
async def delete_article_by_user(user_id: int, session: AsyncSession = Depends(get_session)):

    articles = await session.execute(
        Article
        .select()
        .where(Article.user_id == user_id)
    )
    articles = articles.scalars().all()
    for article in articles:
        article.deleted = True
        session.add(article)
    await session.commit()
    return Result.success()




