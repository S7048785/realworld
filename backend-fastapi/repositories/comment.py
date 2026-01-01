# repositories/comment.py
from typing import List, Tuple
from sqlalchemy import func
from sqlalchemy.orm import selectinload
from sqlmodel import select
from sqlmodel.ext.asyncio.session import AsyncSession

from models.models import Comment
from repositories.base import BaseRepository


class CommentRepository(BaseRepository[Comment]):
    """评论仓储类"""

    def __init__(self, session: AsyncSession):
        super().__init__(Comment, session)

    async def get_by_article(
        self, article_id: int, skip: int = 0, limit: int = 10
    ) -> Tuple[List[Comment], int]:
        """根据文章ID获取评论列表"""
        result = await self.session.execute(
            select(Comment)
            .where(Comment.article_id == article_id)
            .where(Comment.deleted == False)
            .options(selectinload(Comment.user))
            .offset((skip - 1) * limit if skip > 0 else 0)
            .limit(limit)
        )
        comments = result.scalars().all()

        total_result = await self.session.execute(
            select(func.count(Comment.id))
            .where(Comment.article_id == article_id)
            .where(Comment.deleted == False)
        )
        total = total_result.scalar_one()

        return comments, total

    async def get_by_user(self, user_id: int, skip: int = 0, limit: int = 10) -> List[Comment]:
        """根据用户ID获取评论列表"""
        result = await self.session.execute(
            select(Comment)
            .where(Comment.user_id == user_id)
            .where(Comment.deleted == False)
            .options(selectinload(Comment.user))
            .offset((skip - 1) * limit if skip > 0 else 0)
            .limit(limit)
        )
        return result.scalars().all()

    async def create(self, user_id: int, article_id: int, body: str) -> Comment:
        """创建评论"""
        comment = Comment(
            user_id=user_id,
            article_id=article_id,
            body=body
        )
        self.session.add(comment)
        await self.session.commit()
        await self.session.refresh(comment)
        return comment
