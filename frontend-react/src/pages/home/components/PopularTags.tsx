import type {JSX} from "react";
import ArticleListTabsContent from "@/pages/home/components/ArticleListTabsContent.tsx";
import {getArticleListByTag} from "@/api/article.ts";

export type Tag = {
	name: string
	id: number
}
export default function PopularTags({tags, addTab}: {tags: Tag[], addTab: (tab: {label: string, value: string, content: JSX.Element}) => void}) {
	return (
			<>
				<p>Popular Tags</p>
				<div className="flex flex-wrap gap-2 mt-2">
					{
						tags.map((tag) => (
								<button
										key={tag.id}
										className="bg-card-foreground text-card rounded-full text-xs px-1.5 py-1 "
										onClick={() => addTab({label: `#${tag.name}`, value: tag.name, content:
											(
													<ArticleListTabsContent getData={(skip) => getArticleListByTag({tag_name: tag.name, skip})}/>
											)
											})}
								>
									{tag.name}
								</button>
						))
					}
				</div>
			</>
	)
}