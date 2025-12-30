import { useEffect, useRef } from 'react';

interface UseIntersectionObserverOptions {
  /**
   * 触发加载的阈值（0-1之间的数字，表示元素可见比例）
   */
  threshold?: number;
  /**
   * 根元素（观察器的视口）
   */
  root?: Element | null;
  /**
   * 根元素的边距（格式：'top right bottom left'）
   */
  rootMargin?: string;
  /**
   * 是否还有更多数据
   */
  hasMore?: boolean;
  /**
   * 当前是否正在加载
   */
  loading?: boolean;
}

/**
 * 基于 IntersectionObserver 的无限滚动 Hook
 * @param loadMore - 加载更多数据的回调函数
 * @param options - 配置选项
 * @returns {Object} - 包含哨兵元素的 ref
 */
const useIntersectionObserver = (
  loadMore: () => void,
  {
    threshold = 0.1,
    root = null,
    rootMargin = '0px',
    hasMore = true,
    loading = false
  }: UseIntersectionObserverOptions = {}
): { targetRef: React.RefObject<HTMLDivElement | null> } => {
  const targetRef = useRef<HTMLDivElement | null>(null);
  const observerRef = useRef<IntersectionObserver | null>(null);

  useEffect(() => {
    // 如果没有更多数据或正在加载，不创建观察器
    if (!hasMore || loading) return;

    // 创建观察器
    const observer = new IntersectionObserver(
      (entries) => {
        entries.forEach((entry) => {
          if (entry.isIntersecting && hasMore && !loading) {
            loadMore();
          }
        });
      },
      { threshold, root, rootMargin }
    );

    observerRef.current = observer;

    // 观察哨兵元素
    if (targetRef.current) {
      observer.observe(targetRef.current);
    }

    // 清理函数
    return () => {
      if (observerRef.current) {
        observerRef.current.disconnect();
        observerRef.current = null;
      }
    };
  }, [loadMore, threshold, root, rootMargin, hasMore, loading]);

  return { targetRef };
};

export default useIntersectionObserver;