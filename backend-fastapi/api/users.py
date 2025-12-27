# app/api/v1/users.py
import os
from datetime import timedelta

from fastapi import APIRouter, Depends, HTTPException, status, Request
from sqlmodel.ext.asyncio.session import AsyncSession

from core.utils.jwt import create_access_token
from db.session import get_session
from sqlalchemy import select
from models.models import User
from typing import List
# 从配置文件中读取Token过期时间

from schemas.response import Result
from schemas.user import UserPublic, UserLogin

ACCESS_TOKEN_EXPIRE_MINUTES = int(os.getenv("ACCESS_TOKEN_EXPIRE_MINUTES", 30))
router = APIRouter(prefix="/users", tags=["users"])


@router.post(
    "/login",
    response_model=Result[UserPublic],
    status_code=status.HTTP_200_OK,
    summary="用户登录",
    description="通过邮箱和密码进行身份验证，成功后在session中保存用户信息"
)
async def login(user_login: UserLogin, session: AsyncSession = Depends(get_session)):
    result = await session.execute(
        select(User).where(User.email == user_login.email)
    )
    user = result.scalars().first()
    if not user or not user.verify_password(user_login.password):
        raise HTTPException(status_code=401, detail="Invalid email or password")

    # 登录成功 生成Token
    access_token_expires = timedelta(minutes=ACCESS_TOKEN_EXPIRE_MINUTES)
    access_token = create_access_token(
        data={"sub": user["id"]}, expires_delta=access_token_expires
    )
    return {"access_token": access_token, "token_type": "bearer"}

@router.post(
    "/logout",
    response_model=Result[UserPublic],
    status_code=status.HTTP_200_OK,
    summary="用户登出",
    description="清除session中的user_id，实现用户登出"
)
async def logout(request: Request):
    # 清除session中的user_id，实现登出
    request.session.pop("user_id", None)
    return Result.success(data=None)

@router.post(
    "/",
    response_model=Result[UserPublic],
    status_code=status.HTTP_201_CREATED,
    summary="创建用户",
    description="创建一个新用户，返回用户信息"
)
async def create_user(user: User, session: AsyncSession = Depends(get_session)):
    # 将新用户对象添加到数据库会话中
    session.add(user)
    # 异步提交事务，将数据持久化到数据库
    await session.commit()
    # 刷新用户对象，获取数据库生成的ID等最新数据
    await session.refresh(user)
    return Result.success(data=user)


@router.get(
    "/",
    response_model=Result[List[UserPublic]],
    status_code=status.HTTP_200_OK,
    summary="获取用户列表",
    description="返回所有未删除的用户列表"
)
async def read_users(skip: int = 0, limit: int = 20, session: AsyncSession = Depends(get_session)):
    result = await session.execute(
        select(User).where(User.deleted == False).offset(skip).limit(limit)
    )
    return Result.success(data=result.scalars().all())


@router.get(
    "/{user_id}",
    response_model=Result[User],
    summary="获取用户详情",
    description="返回指定ID的用户详情，仅返回未删除的用户"
)
async def read_user(user_id: int, session: AsyncSession = Depends(get_session)):
    user = await session.get(User, user_id)
    print(user)
    if not user or user.deleted:
        raise HTTPException(status_code=404, detail="User not found")
    return Result.success(data=user)


@router.put(
    "/{user_id}",
    response_model=Result[User],
    summary="更新用户",
    description="更新指定ID的用户信息，仅更新未删除的用户"
)
async def update_user(user_id: int, user_update: User, session: AsyncSession = Depends(get_session)):
    user = await session.get(User, user_id)
    if not user or user.deleted:
        raise HTTPException(status_code=404, detail="User not found")

    for key, value in user_update.model_dump(exclude_unset=True).items():
        setattr(user, key, value)

    session.add(user)
    await session.commit()
    await session.refresh(user)
    return Result.success(data=user)


@router.delete(
    "/{user_id}",
    response_model=Result,
    summary="删除用户",
    description="软删除指定ID的用户，仅删除未删除的用户"
)
async def delete_user(user_id: int, session: AsyncSession = Depends(get_session)):
    # 查询用户是否存在
    user = await session.get(User, user_id)
    if not user:
        raise HTTPException(status_code=404, detail="User not found")

    user.deleted = True  # 软删除
    await session.commit()