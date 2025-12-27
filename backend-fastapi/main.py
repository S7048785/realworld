# app/main.py
from contextlib import asynccontextmanager

from fastapi import FastAPI

from api import users, articles
from db.session import init_db


@asynccontextmanager
async def lifespan(app: FastAPI):
    # 启动时初始化数据库（仅开发用！）
    await init_db()
    yield
    # 关闭时清理（可选）

app = FastAPI(
    title="My FastAPI Blog",
    version="1.0.0",
    lifespan=lifespan
)

# 添加认证中间件
# app.add_middleware(AuthMiddleware)

app.include_router(users.router)
app.include_router(articles.router)

@app.get("/")
def root():
    return {"message": "Welcome to FastAPI + MySQL!"}