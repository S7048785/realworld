// hooks/useInfiniteScroll.ts
import { useEffect, useRef } from 'react';

interface UseInfiniteScrollOptions {
	/**
	 * 距离底部多少像素时触发加载（默认 100px）
	 */
	rootMargin?: number;
	/**
	 * 滚动容器的 ref（如果不提供，则监听 window / document）
	 */
	containerRef?: React.RefObject<HTMLElement>;
	/**
	 * 当前是否正在加载
	 */
	loading?: boolean;
	/**
	 * 是否还有更多数据
	 */
	hasMore?: boolean;
}

/**
 * 触底自动加载 Hook
 * @param loadMore - 加载更多数据的回调函数
 * @param options - 配置选项
 */
const useInfiniteScroll = (
		loadMore: () => void,
		{
			rootMargin = 100,
			containerRef,
			loading = false,
			hasMore = true
		}: UseInfiniteScrollOptions = {}
): void => {
	const sentinelRef = useRef<HTMLDivElement | null>(null);

	useEffect(() => {
		// 如果没有更多数据，不创建观察器
		if (!hasMore) return;

		// 创建哨兵元素（sentinel）
		const sentinel = document.createElement('div');
		sentinel.style.height = '1px';
		sentinel.style.width = '100%';
		sentinel.style.visibility = 'hidden';
		sentinel.setAttribute('aria-hidden', 'true'); // 无障碍优化

		const targetContainer = containerRef?.current ?? document.body;
		targetContainer.appendChild(sentinel);
		sentinelRef.current = sentinel;

		// IntersectionObserver 回调
		const handleIntersect: IntersectionObserverCallback = (entries) => {
			entries.forEach((entry) => {
				if (entry.isIntersecting && !loading && hasMore) {
					loadMore();
				}
			});
		};

		// 创建 Observer
		const observer = new IntersectionObserver(handleIntersect, {
			root: containerRef?.current ?? null, // null 表示 viewport
			rootMargin: `0px 0px ${rootMargin}px 0px`, // 只在底部添加 margin
			threshold: 0.1 // 当 10% 的 sentinel 元素可见时触发
		});

		observer.observe(sentinel);

		// 清理函数
		return () => {
			observer.disconnect();
			if (sentinel.parentNode) {
				sentinel.parentNode.removeChild(sentinel);
			}
			sentinelRef.current = null;
		};
	}, [loadMore, rootMargin, containerRef, loading, hasMore]);
};

export default useInfiniteScroll;