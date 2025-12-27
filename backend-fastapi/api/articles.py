# app/api/v1/articles.py
import json
from datetime import datetime

from fastapi import APIRouter, Depends, HTTPException, status, Request
from sqlmodel.ext.asyncio.session import AsyncSession
from db.session import get_session
from models.models import Article
from typing import List

from schemas.article import ArticleEdit
from schemas.response import Result
from schemas.validators import StringArrayValidator

router = APIRouter(prefix="/articles", tags=["articles"])

@router.post("/", response_model=Result, status_code=status.HTTP_201_CREATED)
async def create_article(article: Article, session: AsyncSession = Depends(get_session)):
    session.add(article)
    await session.commit()
    await session.refresh(article)
    return Result.success()

@router.get(
    "/",
    response_model=Result[List[Article]],
    summary="获取文章列表",
    description="返回所有未删除的文章列表"
)
async def read_articles(skip: int = 0, limit: int = 10, session: AsyncSession = Depends(get_session)):
    result = await session.exec(
        Article
        .select()
        .where(Article.deleted == False)
        .offset(skip).limit(limit)
    )
    return Result.success(data=result.all())

@router.get(
    "/{article_id}",
    response_model=Result[Article],
    summary="获取文章详情",
    description="返回指定ID的文章详情"
)
async def read_article(article_id: int, session: AsyncSession = Depends(get_session)):
    article = await session.get(Article, article_id)
    if not article or article.deleted:
        raise HTTPException(status_code=404, detail="Article not found")
    return Result.success(data=article)

@router.get(
    "/{tag_id}",
    response_model=Result[List[Article]],
    summary="获取标签下的文章列表",
    description="返回指定标签下的所有未删除文章列表"
)
async def read_article_by_tag(tag_id: int, session: AsyncSession = Depends(get_session)):
    articles = await session.exec(
        Article
        .select()
        .where(Article.deleted == False)
        .where(Article.tag_id == tag_id)
    )
    return Result.success(data=articles.all())

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


