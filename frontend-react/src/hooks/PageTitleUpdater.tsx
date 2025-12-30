import { useMatches } from 'react-router-dom';
import { useEffect } from 'react';

/** 定义路由处理对象接口 */
interface RouteHandle {
	title?: string;
}

/**
 * 页面标题更新钩子
 * 根据当前路由匹配情况动态更新页面标题
 * @constructor
 */
export default function PageTitleUpdater() {
	// 获取当前路由匹配信息
	const matches = useMatches();

	// 监听路由变化并更新页面标题
	useEffect(() => {
		// 获取最新的路由匹配项
		const latestMatch = matches[matches.length - 1];
		// 获取路由处理对象
		const handle = latestMatch?.handle as RouteHandle;
		// 提取标题
		const title = handle?.title;

		if (title) {
			// 如果存在标题，则设置为 "标题 - Conduit"
			document.title = `${title} - Conduit`;
		} else {
			// 否则设置默认标题
			document.title = 'Conduit';
		}
	}, [matches]);

	return null;
}