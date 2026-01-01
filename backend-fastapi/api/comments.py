# api/comments.py
from fastapi import APIRouter, Depends
from sqlmodel.ext.asyncio.session import AsyncSession

from db.session import get_session
from dependencies.auth import get_current_user
from schemas.response_dto import Result, PageResult
from services.comment import CommentService
from schemas.commet_dto import CommentCreate

router = APIRouter(prefix="/comments", tags=["comments"])


def get_comment_service(session: AsyncSession = Depends(get_session)) -> CommentService:
    return CommentService(session)


@router.get(
    "/article/{article_id}",
    response_model=PageResult,
    summary="获取文章的评论列表"
)
async def get_comment_by_article_id(
    article_id: int,
    skip: int = 1,
    limit: int = 5,
    service: CommentService = Depends(get_comment_service)
):
    comments, total = await service.get_comments_by_article(article_id, skip, limit)
    return PageResult(page=skip, page_size=limit, total=total, list=comments)


@router.post(
    "",
    response_model=Result,
    summary="创建评论"
)
async def create_comment(
    comment: CommentCreate,
    service: CommentService = Depends(get_comment_service),
    current_user_id: int = Depends(get_current_user)
):
    await service.create_comment(current_user_id, comment.article_id, comment.body)
    return Result.success()
