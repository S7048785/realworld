import {Eye, Heart} from "lucide-react";
import type {ArticleSimple} from "@/types/response/article.ts";
import toast from "react-hot-toast";
import {useState} from "react";
import {Link} from "react-router-dom";
import {useUserStore} from "@/store/userStore.ts";
import "./ArticleItem.css"
import {Avatar, AvatarFallback, AvatarImage} from "@/components/ui/avatar.tsx";
export default function ArticleItem({article, onLike}: {
	article: ArticleSimple,
	onLike: (id: number) => Promise<void>
}) {

	const tags: string[] = JSON.parse(article.tags) || []

	const [isLike, setIsLike] = useState(article.isLike)

	const isAuthenticated = useUserStore((state) => state.isAuthenticated)

	const handleLike = () => {
		if (!isAuthenticated) {
			toast('请先登录!', {
				icon: '🚫',
			});
			return;
		}
		if (isLike) {
			toast('你已经点赞过了!', {
				icon: '😘',
			});
		} else {
			setIsLike(true);
			onLike(article.id)
		}
	}

	return (
			<div
					className="flex flex-col gap-3 px-4 border-b border-gray-300 dark:border-gray-700 py-4 relative rounded after:content-[''] after:absolute after:inset-0 after:top-0 after:bottom-0 after:m-auto after:scale-90 after:z-[-1] after:opacity-0 after:rounded after:bg-sidebar-ring/50 after:transition-all after:duration-300 after:will-change-transform hover:after:scale-100 hover:after:opacity-20 "
			>
				<div className="flex items-center justify-between">
					<div className="flex items-center gap-2">
						{/*<img*/}
						{/*		loading="lazy"*/}
						{/*		src={article.author.avatar}*/}
						{/*		alt={article.author.username}*/}
						{/*		className="w-8 h-8 rounded-full"*/}
						{/*/>*/}
						<img
								src={article.author.avatar}
								alt={article.author.username}
								className="w-8 h-8 rounded-full"
								width={32}
								height={32}
								loading="lazy"
						/>
						<div>
							<div className="">{article.author.username}</div>
							<div className="text-xs text-gray-500">
								{new Date(article.created_at).toLocaleString()}
							</div>
						</div>
					</div>

				</div>
				<div className="">
						<Link to={"/article/" + article.id} title={article.title}
									className="text-lg title">{article.title}阿斯顿水水v会突然是v巴菲特撒Greg上的干得好不如她和别人挺好的是法国东部犯规被罚你阿三发射点如果多个</Link>
					<div title={article.desc} className="text-md text-gray-400 truncate mt-2">
						{article.desc}
					</div>
				</div>
				<div className="flex">
					<div className="inline-flex items-center gap-4 text-sm">
						<div className="inline-flex items-center gap-1">
							<Eye aria-hidden="true" className="opacity-60"/>
							<span>{article.views}</span>
						</div>
						<div className="inline-flex items-center gap-1 cursor-pointer" onClick={handleLike}>
							<Heart fill={isLike ? "red" : "none"} ria-hidden="true" className="opacity-60 text-red-500"/>
							<span
							>{article.likes + (isLike ? 1 : 0)}</span>
						</div>

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