# api/articles.py
from fastapi import APIRouter, Depends, HTTPException, Request
from sqlmodel.ext.asyncio.session import AsyncSession

from core.jwt import verify_token
from db.session import get_session
from dependencies.auth import get_current_user
from schemas.response_dto import Result, PageResult
from services.article import ArticleService
from schemas.article_dto import ArticleEdit, ArticleSimple, ArticleDetail

router = APIRouter(prefix="/articles", tags=["articles"])


def get_article_service(session: AsyncSession = Depends(get_session)) -> ArticleService:
    return ArticleService(session)


@router.get(
    "/list",
    response_model=PageResult[ArticleSimple],
    summary="获取文章列表",
    description="返回所有未删除的文章列表"
)
async def read_articles(
        request: Request,
        skip: int = 1,
        limit: int = 5,
        service: ArticleService = Depends(get_article_service)
):
    token = request.headers.get("Authorization")
    user_id = None
    if token:
        try:
            user_id = verify_token(token, Exception)
        except Exception:
            pass

    articles, total = await service.get_article_list(user_id, skip, limit)
    return PageResult(page=skip, page_size=limit, total=total, list=articles)


@router.get(
    "/tag",
    response_model=PageResult[ArticleSimple],
    summary="获取标签下的文章列表",
    description="返回指定标签下的所有未删除文章列表"
)
async def read_article_by_tag(
    tag_name: str,
    skip: int = 1,
    limit: int = 5,
    service: ArticleService = Depends(get_article_service)
):
    articles, total = await service.get_article_by_tag(tag_name, skip, limit)
    return PageResult(page=skip, page_size=limit, total=total, list=articles)


@router.post(
    "/add",
    response_model=Result,
    summary="添加文章",
    description="添加文章"
)
async def add_article(
    article_edit: ArticleEdit,
    request: Request,
    service: ArticleService = Depends(get_article_service),
    current_user_id: int = Depends(get_current_user)
):
    await service.create_article(article_edit, current_user_id)
    return Result.success()


@router.get(
    "/user",
    response_model=PageResult[ArticleSimple],
    summary="获取用户文章列表",
    description="返回指定用户ID的所有未删除文章列表"
)
async def read_article_by_user(
    user_id: int,
    skip: int = 1,
    limit: int = 5,
    service: ArticleService = Depends(get_article_service)
):
    articles, total = await service.get_article_by_user(user_id, skip, limit)
    return PageResult(page=skip, page_size=limit, total=total, list=articles)


@router.delete(
    "/user/{user_id}",
    response_model=Result,
    summary="删除用户文章",
    description="删除指定用户ID的所有文章"
)
async def delete_article_by_user(
    user_id: int,
    service: ArticleService = Depends(get_article_service)
):
    await service.delete_articles_by_user(user_id)
    return Result.success()


@router.post(
    "/like/{article_id}",
    response_model=Result,
    summary="点赞文章",
    description="对文章进行点赞，需要用户认证"
)
async def like_article(
    article_id: int,
    service: ArticleService = Depends(get_article_service),
    user_id: int = Depends(get_current_user)
):
    success, msg = await service.like_article(article_id, user_id)
    if not success:
        return Result.error(msg)
    return Result.success(msg=msg)


@router.get(
    "/{article_id}",
    response_model=Result[ArticleDetail],
    summary="获取文章详情",
    description="返回指定ID的文章详情"
)
async def read_article(
    article_id: int,
    request: Request,
    service: ArticleService = Depends(get_article_service)
):
    token = request.headers.get("Authorization")
    user_id = None
    if token:
        try:
            user_id = verify_token(token, Exception)
        except Exception:
            pass

    article_detail = await service.get_article_detail(article_id, user_id)
    if not article_detail:
        return Result.error("Article not found")
    return Result.success(data=article_detail)


@router.get(
    "/user/{user_id}/liked",
    response_model=PageResult[ArticleSimple],
    summary="获取用户点赞的文章列表",
    description="返回指定用户ID点赞的所有未删除文章列表"
)
async def read_liked_articles_by_user(
    user_id: int,
    request: Request,
    skip: int = 1,
    limit: int = 5,
    service: ArticleService = Depends(get_article_service)
):
    token = request.headers.get("Authorization")
    current_user_id = None
    if token:
        try:
            current_user_id = verify_token(token, Exception)
        except Exception:
            pass

    articles, total = await service.get_liked_articles_by_user(user_id, current_user_id, skip, limit)
    return PageResult(page=skip, page_size=limit, total=total, list=articles)


@router.get(
    "/following",
    response_model=PageResult[ArticleSimple],
    summary="获取关注用户的文章列表",
    description="返回所有关注用户的未删除文章列表"
)
async def read_following_articles(
    request: Request,
    skip: int = 1,
    limit: int = 5,
    service: ArticleService = Depends(get_article_service),
    current_user_id: int = Depends(get_current_user)
):
    articles, total = await service.get_following_articles(current_user_id, skip, limit)
    return PageResult(page=skip, page_size=limit, total=total, list=articles)


@router.delete(
    "/{article_id}",
    response_model=Result,
    summary="删除文章",
    description="删除指定ID的文章"
)
async def delete_article(
    article_id: int,
    service: ArticleService = Depends(get_article_service)
):
    success = await service.delete_article(article_id)
    if not success:
        raise HTTPException(status_code=404, detail="Article not found")
    return Result.success()
