import {Eye, Heart} from "lucide-react";
import ButtonCreativeRight from "@/pages/home/components/ui/ButtonCreativeRight.tsx";
import type {ArticleSimple} from "@/types/response/article.ts";
import toast from "react-hot-toast";
import {useState} from "react";
import {Link} from "react-router-dom";
export default function ArticleItem({article, handleLike}: {
	article: ArticleSimple,
	handleLike: (id: number) => Promise<void>
}) {

	const tags: string[] = JSON.parse(article.tags) || []

	const [isLike, setIsLike] = useState(article.isLike)

	return (
			<div
					className="flex flex-col gap-3 px-4 border-b border-gray-300 dark:border-gray-700 py-4 relative rounded after:content-[''] after:absolute after:inset-0 after:top-0 after:bottom-0 after:m-auto after:w-[80%] after:h-[80%] after:z-[-1] after:opacity-0 after:rounded after:bg-sidebar-ring/50 after:transition-all after:duration-300 hover:after:w-[99%] hover:after:h-[90%] hover:after:opacity-20"
			>
				<div className="flex items-center justify-between">
					<div className="flex items-center gap-2">
						<img
								src={article.author.avatar}
								alt={article.author.username}
								className="w-8 h-8 rounded-full"
						/>
						<div>
							<div className="">{article.author.username}</div>
							<div className="text-xs text-gray-500">
								{new Date(article.created_at).toLocaleString()}
							</div>
						</div>
					</div>
					<div className="inline-flex items-center gap-4 text-sm">
						<div className="inline-flex items-center gap-1">
							<Eye aria-hidden="true" className="opacity-60" />
							<span>{article.views}</span>
						</div>
						<div className="inline-flex items-center gap-1 cursor-pointer" onClick={ () => {
							if (isLike) {
								toast('你已经点赞过了!', {
									icon: '😘',
								});
							} else {
								setIsLike(true);
								handleLike(article.id)
						}}}>
							<Heart fill={isLike ? "red" : "none"} ria-hidden="true" className="opacity-60 text-red-500"/>
							<span
									>{article.likes + (isLike ? 1 : 0)}</span>
						</div>

					</div>
				</div>
				<div className="">
					<Link to={"/article/" + article.id} title={article.title}
						 className="text-lg mb-1 block">{article.title}</Link>
					<div title={article.desc} className="text-sm text-gray-500">
						{article.desc.substring(0, 50)}
					</div>
				</div>
				<div className="flex">
					<div className="inline-flex items-center">
						<Link to={"/article/" + article.id} className="text-sm text-gray-500">
							<ButtonCreativeRight />
						</Link>
					</div>
					<div className="ml-auto">
						<div className="inline-flex items-center gap-2">
							{
								tags.map((tag, index) => (
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