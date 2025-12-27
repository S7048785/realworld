import ReactMarkdown from 'react-markdown';
import "@/styles/markdown.css"
import remarkGfm from 'remark-gfm';
// @ts-ignore
import { Prism as SyntaxHighlighter } from 'react-syntax-highlighter';
// @ts-ignore
import { okaidia } from 'react-syntax-highlighter/dist/esm/styles/prism';
import {Code, CodeHeader,CodeBlock} from "@/components/animate-ui/components/animate/code.tsx";

// 使用 rehypeHighlight
const markdown = `
# CSS Grid布局快速入门指南

如果你已经掌握了Flex布局，那么学习Grid布局会相对容易，因为两者在概念上有相似之处，但Grid提供了更强大的二维布局能力。下面我将带你快速掌握Grid布局的核心概念和用法。
# 标题
- [x] 已完成任务
- [ ] 未完成任务

| 表头1 | 表头2 |
|-------|-------|
| 内容1 | 内容2 |
## 一、Grid与Flex的核心区别

1. **维度差异**：
- Flex是一维布局系统，适合处理单行或单列的布局（如导航栏、卡片列表）
- Grid是二维布局系统，可以同时控制行和列（适合整体页面布局）

2. **应用场景**：
- Flex更适合局部组件布局
- Grid更适合整体页面规划

## 二、Grid基础概念

### 1. 容器与项目
- **容器**：设置\`display: grid\`的元素
- **项目**：容器的直接子元素（注意：孙子元素不算项目）

\`\`\`tsx
'use client';
 
import * as React from 'react';
  
type MyComponentProps = {
  myProps: string;
} & React.ComponentProps<'div'>;
  
function MyComponent(props: MyComponentProps) {
  return (
    <div {...props}>
      <p>My Component</p>
    </div>
  );
}

export { MyComponent, type MyComponentProps };
\`\`\`

### 2. 轨道(Track)
- **行轨道**：水平方向的网格线之间的区域
- **列轨道**：垂直方向的网格线之间的区域

### 3. 单元格(Cell)`; // 你的内容

export default function RankPage() {
	return (
			<div className="max-w-4xl mx-auto px-6 py-8 markdown">
				<ReactMarkdown
						remarkPlugins={[remarkGfm]}
						components={{
							code({ node, inline, className, children, ...props }) {
								const match = /language-(\w+)/.exec(className || '');
								return !inline && match ? (
										<Code code={String(children).replace(/\n$/, '')}>
											<CodeHeader copyButton> <span>{match[1]}</span> </CodeHeader>
											<CodeBlock lang={match[1]}/>
										</Code>
								) : (
										<code className={className} {...props}>
											{children}
										</code>
								);
								// return !inline && match ? (
								// 		<SyntaxHighlighter
								// 				style={okaidia}
								// 				language={match[1]}
								// 				PreTag="div"
								// 				{...props}
								// 		>
								// 			{String(children).replace(/\n$/, '')}
								// 		</SyntaxHighlighter>
								// ) : (
								// 		<code className={className} {...props}>
								// 			{children}
								// 		</code>
								// );
							},
						}}
				>
					{markdown}
				</ReactMarkdown>
			</div>
	);
}