# services/article.py
import json
from datetime import datetime
from typing import List, Optional, Tuple
from sqlmodel.ext.asyncio.session import AsyncSession

from models.models import Article, ArticleLike
from schemas.article_dto import ArticleEdit, ArticleSimple, ArticleDetail
from schemas.user_dto import UserSimple, UserAuthor
from schemas.validators import StringArrayValidator
from repositories.article import ArticleRepository
from sqlmodel import select


class ArticleService:
    """文章服务类"""

    def __init__(self, session: AsyncSession):
        self.session = session
        self.repository = ArticleRepository(session)

    async def get_article_list(
        self, user_id: Optional[int], skip: int, limit: int
    ) -> Tuple[List[ArticleSimple], int]:
        """获取文章列表"""
        articles, total = await self.repository.get_all(skip=skip, limit=limit)
        article_list = []
        for article in articles:
            article_list.append(ArticleSimple(
                id=article.id,
                title=article.title,
                desc=article.desc,
                author=UserSimple(
                    id=article.author.id,
                    username=article.author.username,
                    avatar=article.author.avatar
                ),
                isLike=False,
                views=article.views,
                likes=article.likes,
                tags=article.tags,
                created_at=article.created_at
            ))
        return article_list, total

    async def get_article_by_tag(
        self, tag_name: str, skip: int, limit: int
    ) -> Tuple[List[ArticleSimple], int]:
        """根据标签获取文章列表"""
        return await self.repository.get_by_tag(tag_name, skip, limit)

    async def create_article(
        self, article_edit: ArticleEdit, user_id: int
    ) -> Article:
        """创建文章"""
        article = Article(
            title=article_edit.title,
            body=article_edit.body,
            tags=StringArrayValidator.from_json_string(
                json.dumps(article_edit.tags if article_edit.tags else [])
            ),
            created_at=datetime.now(),
            updated_at=datetime.now(),
            user_id=user_id,
            deleted=False,
            likes=0,
            views=0,
            comments=0
        )
        return await self.repository.add(article)

    async def get_article_by_user(
        self, user_id: int, skip: int, limit: int
    ) -> Tuple[List[Article], int]:
        """获取用户的文章列表"""
        return await self.repository.get_by_user(user_id, skip, limit)

    async def delete_articles_by_user(self, user_id: int) -> int:
        """删除用户的所有文章"""
        return await self.repository.delete_by_user(user_id)

    async def like_article(self, article_id: int, user_id: int) -> tuple[bool, str]:
        """点赞文章"""
        article = await self.repository.get_by_id(article_id)
        if not article or article.deleted:
            return False, "Article not found"

        # 检查是否已点赞
        result = await self.session.execute(
            select(ArticleLike)
            .where(ArticleLike.article_id == article_id)
            .where(ArticleLike.user_id == user_id)
            .where(ArticleLike.deleted == False)
        )
        if result.scalar_one_or_none():
            return False, "Already liked"

        # 添加点赞
        new_like = ArticleLike(user_id=user_id, article_id=article_id)
        self.session.add(new_like)
        article.likes += 1
        self.session.add(article)
        await self.session.commit()
        return True, "点赞成功"

    async def get_article_detail(
        self, article_id: int, current_user_id: Optional[int]
    ) -> Optional[ArticleDetail]:
        """获取文章详情"""
        from sqlalchemy.orm import selectinload
        result = await self.session.execute(
            select(Article)
            .where(Article.id == article_id, Article.deleted == False)
            .options(selectinload(Article.author))
        )
        article = result.scalar_one_or_none()
        if not article or article.deleted:
            return None

        is_like = False
        is_following = False

        if current_user_id is not None:
            # 检查是否点赞
            like_result = await self.session.execute(
                select(ArticleLike)
                .where(ArticleLike.article_id == article_id)
                .where(ArticleLike.user_id == current_user_id)
                .where(ArticleLike.deleted == False)
            )
            is_like = bool(like_result.scalar_one_or_none())

            # 检查是否关注作者
            from models.models import UserFollow
            follow_result = await self.session.execute(
                select(UserFollow)
                .where(UserFollow.user_id == current_user_id)
                .where(UserFollow.followed_user_id == article.author.id)
                .where(UserFollow.deleted == False)
            )
            is_following = bool(follow_result.scalar_one_or_none())

        # 获取作者统计信息
        from repositories.user import UserRepository
        user_repo = UserRepository(self.session)
        articles_count = await user_repo.get_articles_count(article.author.id)
        followers_count = await user_repo.get_followers_count(article.author.id)

        return ArticleDetail(
            id=article.id,
            title=article.title,
            body=article.body,
            tags=article.tags,
            likes=article.likes,
            author=UserAuthor(
                id=article.author.id,
                username=article.author.username,
                avatar=article.author.avatar,
                articles=articles_count,
                followers=followers_count,
                following=is_following,
            ),
            isLike=is_like,
            created_at=article.created_at,
            updated_at=article.updated_at,
        )

    async def get_liked_articles_by_user(
        self, user_id: int, current_user_id: Optional[int], skip: int, limit: int
    ) -> Tuple[List[ArticleSimple], int]:
        """获取用户点赞的文章列表"""
        # 先查询用户点赞的 ArticleLike 记录
        result = await self.session.execute(
            select(ArticleLike.article_id)
            .where(ArticleLike.user_id == user_id)
            .where(ArticleLike.deleted == False)
        )
        liked_article_ids = [row[0] for row in result.all()]

        if not liked_article_ids:
            return [], 0

        articles = await self.repository.get_by_ids(liked_article_ids, skip, limit)

        article_list = []
        for article in articles:
            is_liked = article.id in liked_article_ids
            article_list.append(ArticleSimple(
                id=article.id,
                title=article.title,
                desc=article.desc,
                author=UserSimple(
                    id=article.author.id,
                    username=article.author.username,
                    avatar=article.author.avatar
                ),
                isLike=is_liked,
                views=article.views,
                likes=article.likes,
                tags=article.tags,
                created_at=article.created_at
            ))

        return article_list, len(liked_article_ids)

    async def get_following_articles(
        self, current_user_id: int, skip: int, limit: int
    ) -> Tuple[List[ArticleSimple], int]:
        """获取关注用户的文章列表"""
        # 获取关注列表
        from repositories.user import UserFollowRepository
        follow_repo = UserFollowRepository(self.session)
        followed_ids = await follow_repo.get_followed_ids(current_user_id)

        if not followed_ids:
            return [], 0

        # 获取文章列表
        articles = await self.repository.get_by_ids(followed_ids, skip, limit)

        article_list = []
        for article in articles:
            article_list.append(ArticleSimple(
                id=article.id,
                title=article.title,
                desc=article.desc,
                author=UserSimple(
                    id=article.author.id,
                    username=article.author.username,
                    avatar=article.author.avatar
                ),
                isLike=False,
                views=article.views,
                likes=article.likes,
                tags=article.tags,
                created_at=article.created_at
            ))

        return article_list, len(followed_ids)

    async def delete_article(self, article_id: int) -> bool:
        """删除文章"""
        return await self.repository.soft_delete(article_id)
