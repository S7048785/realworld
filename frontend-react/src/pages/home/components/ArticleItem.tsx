import {Button} from "@/components/ui/button.tsx";
import {Heart} from "lucide-react";
import ButtonCreativeRight from "@/pages/home/components/ui/ButtonCreativeRight.tsx";

export type Article = {
	id: number
	title: string
	content: string
	authorName: string
	avatar: string
	datetime: string
	tags: string[]
	likes: number
}

export default function ArticleItem({article}: { article: Article }) {


	return (
			<div
					className="flex flex-col gap-3 border-b border-gray-300 py-4"
			>
				<div className="flex items-center justify-between">
					<div className="flex items-center gap-2">
						<img
								src={article.avatar}
								alt={article.authorName}
								className="w-8 h-8 rounded-full"
						/>
						<div>
							<div className="">{article.authorName}</div>
							<div className="text-xs text-gray-500">
								{article.datetime}
							</div>
						</div>
					</div>
					<div>
						<Button className="py-0 pe-0" variant="outline" onClick={ () => {
							// TODO: 点赞请求
						}}>
							<Heart aria-hidden="true" className="opacity-60" size={16}/>
							Like
							<span
									className="relative ms-1 inline-flex h-full items-center justify-center rounded-full px-3 font-medium text-xs before:absolute before:inset-0 before:left-0 before:w-px before:bg-input">{article.likes}</span>
						</Button>

					</div>
				</div>
				<div className="">
					<a href={"/article/123"} target={"_blank"} title={article.title}
						 className="text-lg mb-1 block">{article.title}</a>
					<div title={article.content} className="text-sm text-gray-500">
						{article.content.substring(0, 50)}
					</div>
				</div>
				<div className="flex">
					<div className="inline-flex items-center">
						<a href={"/article/123"} className="text-sm text-gray-500">
							<ButtonCreativeRight />
						</a>
					</div>
					<div className="ml-auto">
						<div className="inline-flex items-center gap-2">
							{
								article.tags.map((tag, index) => (
										<span
												 key={index}
												className="border rounded-full text-xs py-1 px-2"
										>{tag}</span>
								))
							}
						</div>
					</div>
				</div>
			</div>
	)
}