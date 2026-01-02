import {Clock, Heart, UserPlus, UserCheck} from "lucide-react"
import {Button} from "@/components/ui/button"
import {Avatar, AvatarFallback, AvatarImage} from "@/components/ui/avatar"
import type {UserAuthor} from "@/types/response/user.ts";
import {useState} from "react";
import toast from "react-hot-toast";
import {useUserStore} from "@/store/userStore.ts";

interface ArticleAuthorProps {
	author: UserAuthor
	publishedAt: string
	likes: number
	isLiked: boolean,
	onLike: () => void
	onFollow?: () => void
}


export default function ArticleAuthor({
																				author,
																				publishedAt,
																				likes,
																				isLiked,
																				onLike,
																				onFollow,
																			}: ArticleAuthorProps) {

	const [isLikeTemp, setIsLikeTemp] = useState(isLiked)
	const [isFollowTemp, setIsFollowTemp] = useState(author.following);
	const isAuthenticated = useUserStore((state) => state.isAuthenticated)

	const handleLike = () => {
		if (!isAuthenticated) {
			toast('请先登录!', {
				icon: '🚫',
			});
			return;
		}
		if (isLikeTemp) {
			toast('你已经点赞过了!', {
				icon: '😘',
			});
		} else {
			setIsLikeTemp(true);
			onLike()
		}
	}
	const handleFollow = () => {
		if (!isAuthenticated) {
			toast('请先登录!', {
				icon: '🚫',
			});
			return;
		}
		setIsFollowTemp(!isFollowTemp);
		onFollow?.();
	}

	return (
			<div className="mb-6 border-b pb-4">
				<div className="flex items-center justify-between">
					<div className="flex items-center gap-4">
						<Avatar className="h-14 w-14">
							<AvatarImage src={author.avatar} alt={author.avatar}/>
							<AvatarFallback>{author.username}</AvatarFallback>
						</Avatar>
						<div>
							<p className="font-semibold text-lg">{author.username}</p>
							<div className="flex items-center gap-1 text-sm text-muted-foreground">
								<Clock className="h-3 w-3"/>
								<span>{publishedAt}</span>
							</div>
							<div className="flex items-center gap-4 mt-2 text-sm text-muted-foreground">
								<span>{author.articles || 0} articles</span>
								<span>{author.followers || 0} followers</span>
							</div>
						</div>
					</div>
					<div className="flex items-center gap-2">
						<Button variant={"ghost"} className="gap-2" onClick={handleLike}>
							<Heart fill={isLikeTemp ? "red" : "none"} ria-hidden="true" className="h-4 w-4 text-red-500"/>
							<span
							>{likes + (isLikeTemp ? 1 : 0)}</span>
						</Button>
						<Button
								variant={isFollowTemp ? "outline" : "default"}
								onClick={handleFollow}
								className="gap-2"
						>
							{isFollowTemp ? (
									<>
										<UserCheck className="h-4 w-4"/>
										<span>Following</span>
									</>
							) : (
									<>
										<UserPlus className="h-4 w-4"/>
										<span>Follow</span>
									</>
							)}
						</Button>
					</div>
				</div>
			</div>
	)
}
