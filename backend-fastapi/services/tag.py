# services/tag.py
from typing import List
from sqlmodel.ext.asyncio.session import AsyncSession

from repositories.tag import TagRepository


class TagService:
    """标签服务类"""

    def __init__(self, session: AsyncSession):
        self.session = session
        self.repository = TagRepository(session)

    async def get_all_tags(self) -> List[str]:
        """获取所有标签"""
        return await self.repository.get_all_names()
