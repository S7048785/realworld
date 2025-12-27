from fastapi import FastAPI, Request, Response
from starlette.middleware.base import BaseHTTPMiddleware
from starlette.responses import RedirectResponse
import re

# 白名单：不需要登录就能访问的路径（支持正则）
EXCLUDE_PATHS = [
    r"^/login$",
    r"^/register",
    r"^/static/.*$",
    r"^/docs$",          # Swagger UI
    r"^/openapi.json$",  # OpenAPI schema
    r"^/favicon.ico$",
]

class AuthMiddleware(BaseHTTPMiddleware):
    async def dispatch(self, request: Request, call_next):
        # 检查是否在白名单中
        path = request.url.path
        for pattern in EXCLUDE_PATHS:
            if re.match(pattern, path):
                return await call_next(request)

        # 检查是否已登录（session 中有 username）
        if "user_id" not in request.session:
            return Response(status_code=401, content="Unauthorized")

        # 已登录，继续处理
        response = await call_next(request)
        return response

