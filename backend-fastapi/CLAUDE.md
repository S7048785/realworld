# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

FastAPI + MySQL 博客系统后端，采用异步数据库操作和 JWT 认证。

## Commands

```bash
# 启动开发服务器 (热重载)
uvicorn main:app --reload --host 0.0.0.0 --port 8000

# 运行测试 (需安装 pytest)
pytest
```

## Architecture

### 分层架构
- **api/** - API 路由层，每个模块独立 router
- **models/** - SQLModel 数据模型定义（User, Article, Comment, Tag, UserFollow, ArticleLike）
- **schemas/** - Pydantic 模式（请求/响应 DTO）
- **db/** - 数据库引擎和异步会话管理
- **dependencies/** - 依赖注入（认证、数据库会话）
- **core/** - 工具函数（JWT）

### 数据库配置
- 异步引擎：`AsyncEngine` + `AsyncSession`
- 驱动：`asyncmy`（MySQL）
- 连接池：`NullPool`（FastAPI + Uvicorn 推荐）
- 软删除：通过 `SoftDeleteMixin.deleted` 字段实现

### 认证流程
- JWT Token 存储 user_id 于 `sub` 字段
- Token 通过 `Authorization` 请求头传递
- 受保护端点使用 `get_current_user` 依赖注入

### 统一响应格式
- `Result[T]` - 通用响应（`code`, `data`, `msg`）
- `PageResult[T]` - 分页响应（`page`, `page_size`, `total`, `list`）

## Key Files

| 文件 | 作用 |
|------|------|
| `main.py:11-16` | 应用启动时自动创建数据库表 |
| `db/session.py:15-21` | 异步数据库引擎配置 |
| `dependencies/auth.py:8-25` | JWT 认证依赖 |
| `models/models.py:9-10` | 软删除混入类 |
