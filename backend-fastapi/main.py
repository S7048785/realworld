# app/main.py
from contextlib import asynccontextmanager

from fastapi import FastAPI
from starlette.middleware.cors import CORSMiddleware

from api import users, articles, tags
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

# 定义允许的跨域源
origins = [
"http://localhost:5173",
]

# 添加 CORS 中间件
app.add_middleware(
CORSMiddleware,
allow_origins=origins, # 允许的源
allow_credentials=True, # 是否允许携带凭据（如 Cookies）
allow_methods=["*"], # 允许的 HTTP 方法
allow_headers=["*"] # 允许的请求头
)

app.include_router(users.router)
app.include_router(articles.router)
app.include_router(tags.router)

@app.get("/")
def root():
    return {"message": "Welcome to FastAPI + MySQL!"}