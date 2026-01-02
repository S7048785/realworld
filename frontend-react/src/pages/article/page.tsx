import {useCallback, useEffect, useState} from "react"
import ArticleContent from "./components/ArticleContent"
import ArticleAuthor from "./components/ArticleAuthor"
import ArticleTags from "./components/ArticleTags"
import ArticleComments from "./components/ArticleComments"
import BackToTop from "./components/BackToTop"
import {useParams} from "react-router-dom";
import {articleApi, userApi, commentApi} from "@/api"
import type {ArticleDetail} from "@/types/response/article.ts";
import {Skeleton} from "@/components/ui/skeleton.tsx";
import type {CommentSimple} from "@/types/response/comment.ts";
import {useUserStore} from "@/store/userStore.ts";
import toast from "react-hot-toast";

export default function ArticlePage() {
	const params = useParams();

	const isAuthenticated = useUserStore((state) => state.isAuthenticated)
	const [article1, setArticle] = useState<ArticleDetail>();
	const [comments, setComments] = useState<CommentSimple[]>([]);
	const [page, setPage] = useState({
		currentPage: 1,
		totalPages: 1,
		pageSize: 5,
	})

	const getComment = useCallback(async (skip?: number) => {
		const article_id = params.id
		if (!article_id) {
			return
		}
		const res = await commentApi.getCommentByArticleId({article_id: Number(article_id), skip, limit: page.pageSize})
		if (res) {
			setComments(res.list);
			setPage({
				currentPage: res.page,
				totalPages: res.total,
				pageSize: res.page_size,
			})
		}
	}, [])

	useEffect(() => {
		const getArticle = async () => {
			const article_id = params.id
			if (!article_id) {
				return
			}
			const res = await articleApi.getArticleDetail({article_id: Number(article_id)})
			if (res) {
				setArticle(res.data);
			}
		}

		getArticle()
		getComment()
	}, [params.id])


	const handleLike = useCallback(() => {
				articleApi.likeArticle(article1?.id || 0)
			}, [isAuthenticated])

	const handleFollow = useCallback(() => {
		userApi.followUser(article1?.author.id || 0)
	}, [isAuthenticated])
	const handleAddComment = useCallback((content: string) => {

		if (!isAuthenticated) {
				toast.error("请先登录")
				return
		}
		commentApi.addComment({article_id: Number(params.id), content}).then(res => {
			if (res) {
				toast.success("评论成功")
				getComment()
			}
		})
		// const comment: CommentSimple = {
		// 	id: String(comments.length + 1),
		// 	author: {
		// 		name: "Current User",
		// 		avatar: "https://api.dicebear.com/7.x/avataaars/svg?seed=User"
		// 	},
		// 	content,
		// 	publishedAt: new Date().toISOString()
		// }
		// setComments([...comments, comment])
	}, [isAuthenticated])

	const handleTagClick = useCallback((tag: string) => {
		console.log(`Tag clicked: ${tag}`)
	}, [])

	return (
			<div className="max-w-3xl mx-auto px-6 py-8">
				{!article1 ? (
						<div>
							<div className="flex items-center space-x-4 mb-12">
								<Skeleton className="h-14 w-14 rounded-full"/>
								<div className="space-y-2">
									<Skeleton className="h-4 w-[120px]"/>
									<Skeleton className="h-4 w-[90px]"/>
								</div>
								<Skeleton className="h-8 w-[100px] ml-auto"/>
							</div>

							<div>
								<Skeleton className="h-18 w-full mb-18"/>
								<Skeleton className="h-8 w-[80%] mb-4"/>
								<Skeleton className="h-8 w-142 mb-4"/>
								<Skeleton className="h-8 w-120 mb-4"/>
							</div>
						</div>
				) : (
						<>
							<ArticleAuthor
									author={article1.author}
									publishedAt={new Date(article1.created_at).toLocaleString()}
									likes={article1.likes}
									isLiked={article1.isLike}
									onLike={handleLike}
									onFollow={handleFollow}
							/>

							{/* 文章标题和正文 */}
							<ArticleContent title={article1.title} content={article1.body}/>

							{/* 标签 */}
							<ArticleTags tags={JSON.parse(article1.tags || "[]")} onTagClick={handleTagClick}/>

							{/* 评论区 */}
							<ArticleComments comments={comments} onAddComment={handleAddComment} currentPage={page.currentPage} totalPages={Math.ceil(page.totalPages / page.pageSize)} onPageChange={getComment} />

							{/* 返回顶部按钮 */}
							<BackToTop/></>
				)}
			</div>
	)
}
