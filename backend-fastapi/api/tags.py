# api/tags.py
from typing import List

from fastapi import APIRouter, Depends
from sqlmodel.ext.asyncio.session import AsyncSession

from db.session import get_session
from schemas.response_dto import Result
from services.tag import TagService

router = APIRouter(prefix="/tags", tags=["tags"])


def get_tag_service(session: AsyncSession = Depends(get_session)) -> TagService:
    return TagService(session)


@router.get(
    "",
    response_model=Result[List[str]],
    summary="获取所有标签",
    description="返回所有未删除文章的标签列表，每个标签只返回一次"
)
async def read_tags(
    service: TagService = Depends(get_tag_service)
):
    tags = await service.get_all_tags()
    return Result.success(tags)
