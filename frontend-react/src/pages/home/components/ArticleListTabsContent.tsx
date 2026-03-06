import { type JSX, useState } from "react";
import type { ArticleSimple } from "@/types/response/article.ts";
import useIntersectionObserver from "@/hooks/useIntersectionObserver.tsx";
import ArticleItem from "@/pages/home/components/ArticleItem.tsx";
import ArticleItemSkeleton from "@/pages/home/components/ui/ArticleItemSkeleton.tsx";
import type { PageData } from "@/types/result.ts";
import { useRequest } from "ahooks";
import api from "@/api/article";
import toast from "react-hot-toast";
import { cubicBezier, motion } from "motion/react";
import { cn } from "@/lib/utils";

/**
 * 文章列表标签页内容组件
 * 实现了无限滚动加载更多文章的功能
 * @param {Object} props - 组件属性
 * @param {Function} props.getData - 获取文章数据的异步函数，接收页码参数，返回分页数据
 * @returns {JSX.Element} 渲染的文章列表组件
 */
export default function ArticleListTabsContent({
  getData,
}: {
  getData: (...params: any) => PageData<ArticleSimple>;
}): JSX.Element {
  {
    // 初始化分页参数
    const [skip, setSkip] = useState(1);
    // 文章列表状态
    const [articleList, setArticleList] = useState<ArticleSimple[]>([]);
    // 是否还有更多数据
    const [hasMore, setHasMore] = useState(true);
    // 加载状态
    const [loading, setLoading] = useState(false);
    const [isEmpty, setIsEmpty] = useState(false);
    const [hoverCard, setHoverCard] = useState({
      top: 0,
      height: 0,
      opacity: 0,
    });

    // 加载更多文章的异步函数
    const loadMore = async () => {
      if (!hasMore || loading) return;

      setLoading(true);
      try {
        const res = await getData(skip);
        // 检查是否返回空数据
        if (res.total === 0) {
          setIsEmpty(true);
          setHasMore(false);
          return;
        }
        // 将新获取的文章添加到现有列表
        setArticleList((prev) => [...prev, ...res.list]);
        // 更新页码
        setSkip((prevSkip) => prevSkip + 1);
        // 检查是否已加载完所有数据
        if (res.list.length < res.page_size) {
          setHasMore(false);
        }
      } catch (err) {
        setIsEmpty(true);
        setHasMore(false);
      } finally {
        setLoading(false);
      }
    };

    const handleLike = async (id: number) => {
      const res = await api.likeArticle(id);
      if (res.code === 200) {
      } else {
        toast("你已经点赞过了", {
          icon: "😘",
        });
      }
    };
    const handleCardMouseEnter = (
      index: number,
      event: React.MouseEvent<HTMLDivElement>,
    ) => {
      setHoverCard({
        height: event.currentTarget.offsetHeight,
        top: event.currentTarget.offsetTop,
        opacity: 0.5,
      });
    };

    const handleCardMouseLeave = () => {
      setHoverCard((prev) => ({ ...prev, opacity: 0 }));
    };

    // 使用 useRequest 钩子，对 loadMore 函数进行防抖处理
    const { run } = useRequest(loadMore, {
      debounceWait: 300,
      manual: true,
    });

    // 使用 IntersectionObserver 监听目标元素，实现无限滚动
    const { targetRef } = useIntersectionObserver(run, {
      threshold: 0.1,
      hasMore,
      loading,
    });
    return (
      <div onMouseLeave={handleCardMouseLeave}>
        <motion.div
          transition={{
            stiffness: 0.15,
            damping: 0.7,
            ease: cubicBezier(0.4, 0, 0.2, 1),
            duration: 0.3,
          }}
          animate={{
            top: hoverCard.top,
            height: `${hoverCard.height}px`,
            opacity: hoverCard.opacity,
          }}
          className="absolute left-0 w-full bg-sidebar-ring/50 dark:bg-jade-800/20 rounded-default pointer-events-none -z-10"
        ></motion.div>
        {/*渲染文章列表*/}
        {articleList.map((article, index) => (
          <ArticleItem
            key={article.id}
            article={article}
            onLike={handleLike}
            onMouseEnter={(event) => handleCardMouseEnter(index, event)}
          />
        ))}
        {/*当还有更多数据时，显示加载骨架屏*/}
        {hasMore && (
          <div ref={targetRef} className="mt-4">
            <ArticleItemSkeleton />
          </div>
        )}
        {/*当列表为空时，显示提示信息*/}
        {isEmpty && (
          <div className="mt-4 text-center text-gray-500">暂无文章</div>
        )}
      </div>
    );
  }
}
