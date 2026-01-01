# api/users.py
from fastapi import APIRouter, Depends, HTTPException, status, Request
from sqlmodel.ext.asyncio.session import AsyncSession

from db.session import get_session
from dependencies.auth import get_current_user
from schemas.response_dto import Result
from schemas.user_dto import UserDetail, UserLogin, UserRegister, UserUpdate
from services.user import UserService

router = APIRouter(prefix="/users", tags=["users"])


def get_user_service(session: AsyncSession = Depends(get_session)) -> UserService:
    return UserService(session)


@router.post(
    "/login",
    response_model=Result,
    status_code=status.HTTP_200_OK,
    summary="用户登录",
    description="通过邮箱和密码进行身份验证"
)
async def login(
    user_login: UserLogin,
    service: UserService = Depends(get_user_service)
):
    success, token, user, msg = await service.login(user_login)
    if not success:
        return Result.success(data=None, msg=msg)
    return Result.success(data={"access_token": token, "user": user})


@router.post(
    "/register",
    response_model=Result,
    status_code=status.HTTP_200_OK,
    summary="用户注册",
    description="通过用户名、邮箱和密码注册一个新用户"
)
async def register(
    user: UserRegister,
    service: UserService = Depends(get_user_service)
):
    success, token, user_detail, msg = await service.register(user)
    if not success:
        return Result.success(data=None, msg=msg)
    return Result.success(data={"access_token": token, "user": user_detail})


@router.get(
    "/me",
    response_model=Result[UserDetail],
    summary="获取当前用户",
    description="返回当前登录用户的信息"
)
async def read_current_user(
    request: Request,
    service: UserService = Depends(get_user_service)
):
    from core.jwt import verify_token
    auth_header = request.headers.get("Authorization")
    try:
        current_user_id = verify_token(auth_header)
    except Exception:
        return Result.success()

    user = await service.get_user_detail(current_user_id)
    if not user:
        raise HTTPException(status_code=404, detail="User not found")
    return Result.success(data=user)


@router.post(
    "",
    response_model=Result[UserDetail],
    status_code=status.HTTP_201_CREATED,
    summary="创建用户",
    description="创建一个新用户，返回用户信息"
)
async def create_user(
    user: UserDetail,
    service: UserService = Depends(get_user_service)
):
    from models.models import User
    user_obj = User.model_validate(user)
    user_obj = await service.repository.add(user_obj)
    return Result.success(data=UserDetail.model_validate(user_obj))


@router.put(
    "",
    response_model=Result[UserDetail],
    summary="更新用户",
    description="更新当前登录用户的信息，返回更新后的用户信息"
)
async def update_user(
    user_update: UserUpdate,
    service: UserService = Depends(get_user_service),
    current_user_id: int = Depends(get_current_user)
):
    user = await service.update_user(current_user_id, user_update)
    if not user:
        raise HTTPException(status_code=404, detail="User not found")
    return Result.success(data=UserDetail.model_validate(user))


@router.get(
    "",
    response_model=Result,
    status_code=status.HTTP_200_OK,
    summary="获取用户列表",
    description="返回所有未删除的用户列表"
)
async def read_users(
    skip: int = 0,
    limit: int = 20,
    service: UserService = Depends(get_user_service)
):
    users = await service.get_user_list(skip, limit)
    return Result.success(data=users)


@router.get(
    "/{user_id}",
    response_model=Result[UserDetail],
    summary="获取用户详情",
    description="返回指定ID的用户详情，仅返回未删除的用户"
)
async def read_user(
    user_id: int,
    service: UserService = Depends(get_user_service)
):
    user = await service.get_user_detail(user_id)
    if not user:
        raise HTTPException(status_code=404, detail="User not found")
    return Result.success(data=user)


@router.delete(
    "/{user_id}",
    response_model=Result,
    summary="删除用户",
    description="软删除指定ID的用户，仅删除未删除的用户"
)
async def delete_user(
    user_id: int,
    service: UserService = Depends(get_user_service)
):
    success = await service.delete_user(user_id)
    if not success:
        raise HTTPException(status_code=404, detail="User not found")
    return Result.success()


@router.post(
    "/follow/{target_user_id}",
    response_model=Result,
    summary="关注/取消关注用户",
    description="关注或取消关注指定用户，需要用户认证"
)
async def toggle_follow_user(
    target_user_id: int,
    service: UserService = Depends(get_user_service),
    current_user_id: int = Depends(get_current_user)
):
    success, msg = await service.toggle_follow(current_user_id, target_user_id)
    if not success:
        return Result.error(msg)
    return Result.success(msg=msg)
