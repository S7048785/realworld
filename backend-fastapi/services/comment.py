# services/comment.py
from typing import List, Tuple
from sqlmodel.ext.asyncio.session import AsyncSession

from models.models import Comment
from schemas.commet_dto import CommentSimple, CommentCreate
from repositories.comment import CommentRepository


class CommentService:
    """评论服务类"""

    def __init__(self, session: AsyncSession):
        self.session = session
        self.repository = CommentRepository(session)

    async def get_comments_by_article(
        self, article_id: int, skip: int, limit: int
    ) -> Tuple[List[CommentSimple], int]:
        """获取文章的评论列表"""
        comments, total = await self.repository.get_by_article(article_id, skip, limit)

        comment_list = []
        for comment in comments:
            comment_list.append(CommentSimple(
                id=comment.id,
                body=comment.body,
                created_at=comment.created_at,
                user_id=comment.user_id,
                article_id=comment.article_id,
                username=comment.user.username if comment.user else None,
                avatar=comment.user.avatar if comment.user else None
            ))

        return comment_list, total

    async def create_comment(
        self, user_id: int, article_id: int, body: str
    ) -> Comment:
        """创建评论"""
        return await self.repository.create(user_id, article_id, body)

    async def delete_comment(self, comment_id: int) -> bool:
        """删除评论"""
        return await self.repository.soft_delete(comment_id)
