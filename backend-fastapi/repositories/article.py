# repositories/article.py
from typing import List, Optional, Tuple
from sqlalchemy import func, literal, case
from sqlalchemy.orm import selectinload
from sqlmodel import select
from sqlmodel.ext.asyncio.session import AsyncSession

from models.models import Article, ArticleLike
from repositories.base import BaseRepository
from schemas.article_dto import ArticleSimple
from schemas.user_dto import UserSimple


def _create_article_simple(article, is_liked: bool = False) -> ArticleSimple:
    """辅助函数：创建ArticleSimple对象"""
    return ArticleSimple(
        id=article.id,
        title=article.title,
        desc=article.desc,
        author=UserSimple(
            id=article.author.id,
            username=article.author.username,
            avatar=article.author.avatar
        ),
        isLike=is_liked,
        views=article.views,
        likes=article.likes,
        tags=article.tags,
        created_at=article.created_at
    )


class ArticleRepository(BaseRepository[Article]):
    """文章仓储类"""

    def __init__(self, session: AsyncSession):
        super().__init__(Article, session)

    async def get_all(self, skip: int = 0, limit: int = 5) -> Tuple[List[ArticleSimple], int]:
        """获取所有文章"""
        result = await self.session.execute(
            select(Article)
            .where(Article.deleted == False)
            .options(selectinload(Article.author))
            .offset((skip - 1) * limit if skip > 0 else 0)
            .limit(limit)
        )
        articles = result.scalars().all()
        article_list = [_create_article_simple(article) for article in articles]
        total_result = await self.session.execute(
            select(func.count(Article.id))
            .where(Article.deleted == False)
        )
        total = total_result.scalar_one()

        return article_list, total

    async def get_by_tag(
        self, tag_name: str, skip: int = 0, limit: int = 5
    ) -> Tuple[List[ArticleSimple], int]:
        """根据标签获取文章列表"""
        result = await self.session.execute(
            select(Article)
            .where(Article.deleted == False)
            .where(Article.tags.like(f"%{tag_name}%"))
            .options(selectinload(Article.author))
            .offset((skip - 1) * limit if skip > 0 else 0)
            .limit(limit)
        )
        articles = result.scalars().all()
        article_list = [_create_article_simple(article) for article in articles]

        total_result = await self.session.execute(
            select(func.count(Article.id))
            .where(Article.deleted == False)
            .where(Article.tags.like(f"%{tag_name}%"))
        )
        total = total_result.scalar_one()

        return article_list, total

    async def get_by_user(
        self, user_id: int, skip: int = 0, limit: int = 5
    ) -> Tuple[List[ArticleSimple], int]:
        """根据用户ID获取文章列表"""
        result = await self.session.execute(
            select(Article)
            .where(Article.deleted == False)
            .where(Article.user_id == user_id)
            .options(selectinload(Article.author))
            .offset((skip - 1) * limit if skip > 0 else 0)
            .limit(limit)
        )
        articles = result.scalars().all()
        article_list = [_create_article_simple(article) for article in articles]

        total_result = await self.session.execute(
            select(func.count(Article.id))
            .where(Article.deleted == False)
            .where(Article.user_id == user_id)
        )
        total = total_result.scalar_one()

        return article_list, total

    async def get_by_ids(
        self, ids: List[int], skip: int = 0, limit: int = 5
    ) -> List[ArticleSimple]:
        """根据ID列表获取文章"""
        if not ids:
            return []
        result = await self.session.execute(
            select(Article)
            .where(Article.id.in_(ids))
            .where(Article.deleted == False)
            .options(selectinload(Article.author))
            .offset((skip - 1) * limit if skip > 0 else 0)
            .limit(limit)
        )
        articles = result.scalars().all()
        article_list = [_create_article_simple(article) for article in articles]
        return article_list
