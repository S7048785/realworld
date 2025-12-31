import {type JSX, useEffect, useState} from "react";
import ArticleListTabsContent from "@/pages/home/components/ArticleListTabsContent.tsx";
import api from "@/api/article.ts";
import apiTags from "@/api/tags.ts";

export default function PopularTags({ addTab}: {addTab: (tab: {label: string, value: string, content: JSX.Element}) => void}) {

	const [tags, setTags] = useState<string[] | null>(null)
	useEffect(() => {

		(async () => {
			const res = await apiTags.getTagsAll()
			setTags( res.data);
		})()
	}, []);

	return (
			<>
				<p>Popular Tags</p>
				<div className="flex flex-wrap gap-2 mt-2">
					{
						tags?.map((tag) => (
								<button
										key={tag}
										className="bg-card-foreground text-card rounded-full text-xs px-1.5 py-1 "
										onClick={() => addTab({label: `#${tag}`, value: tag, content:
											(
													<ArticleListTabsContent getData={(skip) => api.getArticleListByTag({tag_name: tag, skip})}/>
											)
											})}
								>
									{tag}
								</button>
						))
					}
				</div>
			</>
	)
}