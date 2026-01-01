# repositories/user.py
from typing import Optional
from sqlalchemy import select
from sqlmodel.ext.asyncio.session import AsyncSession

from models.models import User, UserFollow
from repositories.base import BaseRepository


class UserRepository(BaseRepository[User]):
    """用户仓储类"""

    def __init__(self, session: AsyncSession):
        super().__init__(User, session)

    async def get_by_email(self, email: str) -> Optional[User]:
        """根据邮箱获取用户"""
        result = await self.session.execute(
            select(User).where(User.email == email, User.deleted == False)
        )
        return result.scalar_one_or_none()

    async def get_by_username(self, username: str) -> Optional[User]:
        """根据用户名获取用户"""
        result = await self.session.execute(
            select(User).where(User.username == username, User.deleted == False)
        )
        return result.scalar_one_or_none()

    async def get_followers_count(self, user_id: int) -> int:
        """获取用户粉丝数"""
        result = await self.session.execute(
            select(UserFollow.id)
            .where(UserFollow.followed_user_id == user_id)
            .where(UserFollow.deleted == False)
        )
        return len(result.all())

    async def get_following_count(self, user_id: int) -> int:
        """获取用户关注数"""
        result = await self.session.execute(
            select(UserFollow.id)
            .where(UserFollow.user_id == user_id)
            .where(UserFollow.deleted == False)
        )
        return len(result.all())

    async def get_articles_count(self, user_id: int) -> int:
        """获取用户文章数"""
        from models.models import Article
        result = await self.session.execute(
            select(Article.id)
            .where(Article.user_id == user_id)
            .where(Article.deleted == False)
        )
        return len(result.all())


class UserFollowRepository(BaseRepository[UserFollow]):
    """用户关注仓储类"""

    def __init__(self, session: AsyncSession):
        super().__init__(UserFollow, session)

    async def is_following(self, user_id: int, target_user_id: int) -> bool:
        """检查用户是否关注了目标用户"""
        result = await self.session.execute(
            select(UserFollow)
            .where(UserFollow.user_id == user_id)
            .where(UserFollow.followed_user_id == target_user_id)
            .where(UserFollow.deleted == False)
        )
        return result.scalar_one_or_none() is not None

    async def get_followed_ids(self, user_id: int) -> list[int]:
        """获取用户关注的所有用户ID列表"""
        result = await self.session.execute(
            select(UserFollow.followed_user_id)
            .where(UserFollow.user_id == user_id)
            .where(UserFollow.deleted == False)
        )
        return [row[0] for row in result.all()]

    async def toggle_follow(self, user_id: int, target_user_id: int) -> tuple[bool, str]:
        """
        切换关注状态
        返回: (是否关注成功, 消息)
        """
        existing = await self.is_following(user_id, target_user_id)

        if existing:
            # 已关注，取消关注
            result = await self.session.execute(
                select(UserFollow)
                .where(UserFollow.user_id == user_id)
                .where(UserFollow.followed_user_id == target_user_id)
                .where(UserFollow.deleted == False)
            )
            follow = result.scalar_one_or_none()
            if follow:
                follow.deleted = True
                self.session.add(follow)
                await self.session.commit()
                return False, "取消关注成功"
        else:
            # 未关注，添加关注
            new_follow = UserFollow(user_id=user_id, followed_user_id=target_user_id)
            self.session.add(new_follow)
            await self.session.commit()
            return True, "关注成功"

        return False, "操作失败"
