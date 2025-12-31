
from fastapi import APIRouter, Depends, HTTPException, Request
from sqlalchemy import func
from sqlalchemy.orm import selectinload
from sqlmodel import select
from sqlmodel.ext.asyncio.session import AsyncSession

from db.session import get_session
from dependencies.auth import get_current_user
from models.models import Article, UserFollow, Comment
from schemas.commet import CommentSimple, CommentCreate
from schemas.response_dto import Result, PageResult

router = APIRouter(prefix="/comments", tags=["comments"])

@router.get(
    "article/{article_id}",
    response_model=PageResult[CommentSimple]
)
async def get_comment_by_article_id(article_id: int, skip: int = 1, limit: int = 5, session: AsyncSession = Depends(get_session)):
    comments = await session.execute(
        select(Comment)
        .where(Comment.article_id == article_id)
        .where(Comment.deleted == False)
        .options(selectinload(Comment.user))
        .offset((skip - 1) * limit)
        .limit(limit)
    )
    total = await session.execute(
        select(func.count(Comment.id))
        .where(Comment.article_id == article_id)
        .where(Comment.deleted == False)
    )
    return PageResult(
        page=skip,
        page_size=limit,
        total=total.scalar_one(),
        list=comments.scalars().all()
    )

@router.post(
    "",
    response_model=Comment
)
async def create_comment(comment: CommentCreate, session: AsyncSession = Depends(get_session), current_user_id: int = Depends(get_current_user)):
    session.add(Comment(
        body=comment.body,
        user_id=current_user_id,
        article_id=comment.article_id,
        created_at=comment.created_at
    ))
    await session.commit()
    await session.refresh(comment)
    return comment
