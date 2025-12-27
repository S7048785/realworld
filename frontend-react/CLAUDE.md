# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 常用命令

```bash
# 启动开发服务器
pnpm dev

# 构建生产版本
pnpm build

# 代码检查
pnpm lint

# 预览生产构建
pnpm preview
```

## 项目架构

这是一个 React 19 + TypeScript 前端项目，使用 Vite 构建，目标是实现 RealWorld 应用。

### 目录结构

- `src/pages/` - 页面组件，每个功能对应一个文件夹（如 `home/`、`article/`、`Login.tsx`、`Register.tsx`）
- `src/components/` - 通用组件
  - `ui/` - 基于 Radix UI 的基础 UI 组件（Button、DropdownMenu、Tabs 等）
  - `animate-ui/` - 动画组件（基于 motion 库）
  - `navbar/` - 导航栏组件
- `src/components/ui/tabs2.tsx` - 自定义 Tabs 组件
- `src/lib/` - 工具函数
  - `utils.ts` - `cn()` 函数用于合并 Tailwind class
  - `get-strict-context.tsx` - 严格上下文类型
- `src/router/index.tsx` - 路由配置（使用 React Router）
- `src/App.tsx` - 根组件，包含 NavBar 和 Outlet

### 关键技术

- **Tailwind CSS v4** - 样式框架
- **Radix UI** - 无样式 UI 组件库
- **class-variance-authority (CVA)** - 管理组件变体（如 Button 的 variant、size）
- **motion** - 动画库
- **React Router v7** - 路由管理
- **ThemeProvider** - 深色/浅色主题切换（`@/components/theme-provider.tsx`）

### 路径别名

`@` 指向 `src/` 目录，导入时使用如 `@/pages/home/page.tsx`。

### 代码规范

- 使用 ESLint + TypeScript ESLint 进行代码检查
- 组件文件使用 `.tsx` 扩展名
- 遵循 Flat Config 格式的 ESLint 配置
