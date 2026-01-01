# repositories/base.py
from typing import Generic, TypeVar, Optional, List
from sqlmodel.ext.asyncio.session import AsyncSession
from sqlmodel import select, delete
from sqlalchemy import func

T = TypeVar("T")


class BaseRepository(Generic[T]):
    """基础仓储类，提供通用的数据库操作方法"""

    def __init__(self, model: type[T], session: AsyncSession):
        self.model = model
        self.session = session

    async def get_by_id(self, id: int) -> Optional[T]:
        """根据ID获取单条记录"""
        result = await self.session.get(self.model, id)
        return result

    async def get_all(self, skip: int = 0, limit: int = 100) -> List[T]:
        """获取所有未删除记录（支持分页）"""
        result = await self.session.execute(
            select(self.model).where(self.model.deleted == False).offset(skip).limit(limit)
        )
        return result.scalars().all()

    async def count(self) -> int:
        """统计未删除记录总数"""
        result = await self.session.execute(
            select(func.count(self.model.id)).where(self.model.deleted == False)
        )
        return result.scalar_one()

    async def add(self, obj: T) -> T:
        """添加记录"""
        self.session.add(obj)
        await self.session.commit()
        await self.session.refresh(obj)
        return obj

    async def update(self, obj: T) -> T:
        """更新记录"""
        self.session.add(obj)
        await self.session.commit()
        await self.session.refresh(obj)
        return obj

    async def soft_delete(self, id: int) -> bool:
        """软删除记录"""
        obj = await self.get_by_id(id)
        if obj:
            obj.deleted = True
            self.session.add(obj)
            await self.session.commit()
            return True
        return False

    async def bulk_soft_delete(self, ids: List[int]) -> int:
        """批量软删除"""
        count = 0
        for id in ids:
            if await self.soft_delete(id):
                count += 1
        return count
