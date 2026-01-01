# repositories/tag.py
from typing import List
from sqlalchemy import select
from sqlmodel.ext.asyncio.session import AsyncSession

from models.models import Tag
from repositories.base import BaseRepository


class TagRepository(BaseRepository[Tag]):
    """标签仓储类"""

    def __init__(self, session: AsyncSession):
        super().__init__(Tag, session)

    async def get_all_names(self) -> List[str]:
        """获取所有标签名称"""
        result = await self.session.execute(
            select(Tag.name).where(Tag.deleted == False).distinct()
        )
        return result.scalars().all()
