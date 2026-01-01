# services/user.py
from datetime import datetime
from typing import Optional
from sqlmodel.ext.asyncio.session import AsyncSession

from core.jwt import create_access_token
from models.models import User
from schemas.user_dto import UserDetail, UserLogin, UserRegister, UserUpdate
from repositories.user import UserRepository, UserFollowRepository


class UserService:
    """用户服务类"""

    def __init__(self, session: AsyncSession):
        self.session = session
        self.repository = UserRepository(session)

    async def login(self, user_login: UserLogin) -> tuple[bool, Optional[str], Optional[UserDetail], str]:
        """
        用户登录
        返回: (是否成功, token, 用户信息, 错误消息)
        """
        user = await self.repository.get_by_email(user_login.email)
        if not user or not user.verify_password(user_login.password):
            return False, None, None, "Invalid email or password\n邮箱或密码错误"

        # 生成 token
        access_token = create_access_token(user.id)
        return True, access_token, UserDetail.model_validate(user), ""

    async def register(self, user_register: UserRegister) -> tuple[bool, Optional[str], Optional[UserDetail], str]:
        """
        用户注册
        返回: (是否成功, token, 用户信息, 错误消息)
        """
        # 检查邮箱是否已存在
        existing = await self.repository.get_by_email(user_register.email)
        if existing:
            return False, None, None, "Email already registered | 邮箱已注册"

        # 创建新用户
        user = User(
            username=user_register.email.split('@')[0],
            email=user_register.email,
            avatar=r"\images\9419024696.jpg",
            password=user_register.password,
            created_at=datetime.now(),
            updated_at=datetime.now(),
        )
        user = await self.repository.add(user)

        # 生成 token
        access_token = create_access_token(user.id)
        return True, access_token, UserDetail.model_validate(user), ""

    async def get_user_by_id(self, user_id: int) -> Optional[User]:
        """根据ID获取用户"""
        return await self.repository.get_by_id(user_id)

    async def get_user_detail(self, user_id: int) -> Optional[UserDetail]:
        """获取用户详情"""
        user = await self.repository.get_by_id(user_id)
        if not user or user.deleted:
            return None
        return UserDetail.model_validate(user)

    async def update_user(self, user_id: int, user_update: UserUpdate) -> Optional[User]:
        """更新用户信息"""
        user = await self.repository.get_by_id(user_id)
        if not user or user.deleted:
            return None

        for key, value in user_update.model_dump(exclude_unset=True).items():
            setattr(user, key, value)

        if not user_update.password:
            user_update.password = user.password

        user.updated_at = datetime.now()
        return await self.repository.update(user)

    async def get_user_list(self, skip: int, limit: int) -> list[User]:
        """获取用户列表"""
        return await self.repository.get_all(skip=skip, limit=limit)

    async def delete_user(self, user_id: int) -> bool:
        """删除用户"""
        return await self.repository.soft_delete(user_id)

    async def toggle_follow(self, user_id: int, target_user_id: int) -> tuple[bool, str]:
        """
        切换关注状态
        返回: (是否关注成功, 消息)
        """
        if user_id == target_user_id:
            return False, "Cannot follow yourself"

        # 检查目标用户是否存在
        target_user = await self.repository.get_by_id(target_user_id)
        if not target_user or target_user.deleted:
            return False, "User not found"

        follow_repo = UserFollowRepository(self.session)
        return await follow_repo.toggle_follow(user_id, target_user_id)
