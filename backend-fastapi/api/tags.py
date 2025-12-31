from typing import List

from fastapi import APIRouter, Depends
from sqlalchemy import select
from sqlmodel.ext.asyncio.session import AsyncSession

from db.session import get_session
from dependencies.auth import get_current_user
from models.models import Tag
from schemas.response_dto import Result

router = APIRouter(prefix="/tags", tags=["tags"])

@router.get(
    "",
    response_model=Result[List[str]],
    summary="获取所有标签",
    description="返回所有未删除文章的标签列表，每个标签只返回一次"
)
async def read_tags(
        session: AsyncSession = Depends(get_session),
):
    tags = await session.execute(
        select(Tag.name).where(Tag.deleted == False).distinct()
    )
    return Result.success(tags.scalars().all())
