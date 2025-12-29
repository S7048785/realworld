import ArticleItem from "@/pages/home/components/ArticleItem.tsx";
import {useEffect, useRef, useState} from "react";
import {getArticleAllList} from "@/api/article.ts";
import type {ArticleSimple} from "@/types/response/article.ts";
import {useInfiniteScroll} from "ahooks";

export default function GlobalFeedTabsContent() {
	const ref = useRef<HTMLDivElement>(null);

	const [isLoad, setIsLoad] = useState(true)

	const getLoadMoreList = async (skip: number): Promise<{
		list: ArticleSimple[];
		total: number;
		hasMore: boolean;
		skip: number;
	}> => {

		const res = await getArticleAllList(skip)
		// setArticleList(prev => [...prev, ...res.list])
		// setSkip(skip + 1)
		console.log("skip",skip)
		console.log(res.list)

		return new Promise((resolve) => {
			setTimeout(() => {
				resolve({
					list: res.list,
					total: res.total,
					hasMore: res.list.length > 0,
					skip: skip + 1,
				});
			}, 1000);
		});
	}


	const { data , loading, loadingMore, loadMore, noMore } = useInfiniteScroll(
			(d) => getLoadMoreList(d?.skip || 2),
			{ target: ref, isNoMore: (d) => d?.hasMore === false, threshold: 100}
	);

	console.log("noMore", noMore)

	useEffect(() => {
		(async () => {
			await getLoadMoreList(1)
			setIsLoad(false)
		})()

	}, []);
	return (
			<div ref={ref} className="flex flex-col min-h-500">

				{loading ? (
						<p>loading</p>
				) : (
						<>
							{
								data?.list?.map((article, index) => (
										<ArticleItem key={index} article={article}/>
								))
							}
						</>
				)}

				<div style={{marginTop: 8}}>
					{!noMore && (
							<button  type="button" onClick={loadMore} >
								{loadingMore ? 'Loading more...' : 'Click to load more'}
							</button>
					)}

					{noMore && <span>No more data</span>}
				</div>


			</div>
	)
}