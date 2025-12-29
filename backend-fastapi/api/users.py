# app/api/v1/users.py
from datetime import timedelta, datetime

from fastapi import APIRouter, Depends, HTTPException, status, Request
from sqlmodel.ext.asyncio.session import AsyncSession

from core.utils.jwt import create_access_token, verify_token
from db.session import get_session
from sqlalchemy import select

from dependencies.auth import get_current_user
from models.models import User
from typing import List

from schemas.response import Result
from schemas.user import UserDetail, UserLogin, UserRegister, UserUpdate, UserSimple

router = APIRouter(prefix="/users", tags=["users"])


@router.post(
    "/login",
    response_model=Result,
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
    access_token = create_access_token(user.id)
    return Result.success(data={"access_token": access_token, "user": UserDetail.model_validate(user)})

@router.post(
    "/register",
    response_model=Result,
    status_code=status.HTTP_200_OK,
    summary="用户注册",
    description="通过用户名、邮箱和密码注册一个新用户"
)
async def register(user: UserRegister, session: AsyncSession = Depends(get_session)):
    # 检查邮箱是否已存在
    result = await session.execute(
        select(User).where(User.email == user.email)
    )
    if result.scalars().first():
        raise HTTPException(status_code=400, detail="Email already registered")

    # 创建新用户
    user = User (
        username=user.email.split('@')[0],
        email=user.email,
        avatar=r"\images\9419024696.jpg",
        password=user.password,
        bio=user.bio,
        created_at=datetime.now(),
        updated_at=datetime.now(),
    )
    session.add(user)
    # 异步提交事务，将数据持久化到数据库
    await session.commit()
    # 刷新用户对象，获取数据库生成的ID等最新数据
    await session.refresh(user)

    # 生成token
    access_token = create_access_token(user.id)
    return Result.success(data={"access_token": access_token, "user": UserDetail.model_validate(user)})

@router.post(
    "/",
    response_model=Result[UserDetail],
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

@router.put(
    "/",
    response_model=Result[UserDetail],
    summary="更新用户",
    description="更新当前登录用户的信息，返回更新后的用户信息"
)
async def update_user(user_update: UserUpdate, session: AsyncSession = Depends(get_session),
                      current_user_id: int = Depends(get_current_user)):
    user = await session.get(User, current_user_id)
    if not user or user.deleted:
        raise HTTPException(status_code=404, detail="User not found")

    for key, value in user_update.model_dump(exclude_unset=True).items():
        setattr(user, key, value)
    # 更新更新时间
    user.updated_at = datetime.now()

    session.add(user)
    await session.commit()
    await session.refresh(user)
    return Result.success(data=user)

@router.get(
    "/",
    response_model=Result[List[UserSimple]],
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
    response_model=Result[UserDetail],
    summary="获取用户详情",
    description="返回指定ID的用户详情，仅返回未删除的用户"
)
async def read_user(user_id: int, session: AsyncSession = Depends(get_session)):
    user = await session.get(User, user_id)
    print(user)
    if not user or user.deleted:
        raise HTTPException(status_code=404, detail="User not found")
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

@router.get(
    "/me",
    response_model=Result[UserDetail],
    summary="获取当前用户",
    description="返回当前登录用户的信息"
)
async def read_current_user(session: AsyncSession = Depends(get_session),current_user_id: int = Depends(get_current_user)):
    current_user = await session.get(User, current_user_id)
    if not current_user or current_user.deleted:
        raise HTTPException(status_code=404, detail="User not found")
    return Result.success(data=current_user)