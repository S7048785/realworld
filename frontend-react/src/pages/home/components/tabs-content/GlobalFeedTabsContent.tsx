import ArticleItem from "@/pages/home/components/ArticleItem.tsx";
import {useEffect, useState} from "react";
import {getArticleAllList} from "@/api/article.ts";
import type {ArticleSimple} from "@/types/response/article.ts";
import ArticleItemSkeleton from "@/pages/home/components/ui/ArticleItemSkeleton.tsx";
import useIntersectionObserver from "@/hooks/useIntersectionObserver.tsx";

export default function GlobalFeedTabsContent() {
	const [skip, setSkip] = useState(1);
	const [articleList, setArticleList] = useState<ArticleSimple[]>([])
	const [hasMore, setHasMore] = useState(true)
	const [loading, setLoading] = useState(false)
	
	const loadMore = async () => {
		console.log(skip)
		if (!hasMore || loading) return
		
		setLoading(true)
		try {
			const res = await getArticleAllList(skip)
			setArticleList(prev => [...prev, ...res.list])
			setSkip(prevSkip => prevSkip + 1)
			if (res.list.length == 0) {
				setHasMore(false)
			}
			console.log(res.list)
		} finally {
			setLoading(false)
		}
	}
	
	// 使用自定义 hook
	const { targetRef } = useIntersectionObserver(loadMore, {
		threshold: 0.1,
		hasMore,
		loading
	});

	useEffect(() => {
		// 初始加载数据
		loadMore();
	}, []);


	return (
		<div>
			{
				articleList.map((article, index) => (
					<ArticleItem key={index} article={article}/>
				))
			}
			{
				hasMore && (
					<div ref={targetRef}>
						<ArticleItemSkeleton/>
					</div>
				)
			}
		</div>
	)
}