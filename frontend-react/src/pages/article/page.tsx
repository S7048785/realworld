import {useEffect, useState} from "react"
import ArticleContent from "./components/ArticleContent"
import ArticleAuthor from "./components/ArticleAuthor"
import ArticleTags from "./components/ArticleTags"
import ArticleComments from "./components/ArticleComments"
import BackToTop from "./components/BackToTop"
import {useParams} from "react-router-dom";
import api from "@/api/article"
import type {ArticleDetail} from "@/types/response/article.ts";
import {Skeleton} from "@/components/ui/skeleton.tsx";

// 模拟文章数据
const article = {
	comments: [
		{
			id: "1",
			author: {
				name: "Jane Smith",
				avatar: "https://api.dicebear.com/7.x/avataaars/svg?seed=Jane"
			},
			content: "Great article! The explanations are very clear and helpful.",
			publishedAt: "2024-12-27T11:30:00Z"
		},
		{
			id: "2",
			author: {
				name: "Bob Wilson",
				avatar: "https://api.dicebear.com/7.x/avataaars/svg?seed=Bob"
			},
			content: "I've been waiting for these features. Thanks for sharing!",
			publishedAt: "2024-12-27T12:15:00Z"
		}
	]
}

interface Comment {
	id: string
	author: {
		name: string
		avatar: string
	}
	content: string
	publishedAt: string
}

export default function ArticlePage() {
	const params = useParams();

	const [comments, setComments] = useState<Comment[]>(article.comments)

	const [article1, setArticle] = useState<ArticleDetail>()

	useEffect(() => {
		const getData = async () => {
			const article_id = params.id
			if (!article_id) {
				return
			}
			const res = await api.getArticleDetail({article_id: Number(article_id)})
			if (res) {
				setArticle(res.data);
			}
			console.log(res)
		}
		getData()
	}, [params.id])


	const handleLike = () => {
		// setIsLiked(!isLiked)
		// setLikeCount(isLiked ? likeCount - 1 : likeCount + 1)
	}

	const handleFollow = () => {
		// setIsFollowing(!isFollowing)
	}

	const handleAddComment = (content: string) => {
		const comment: Comment = {
			id: String(comments.length + 1),
			author: {
				name: "Current User",
				avatar: "https://api.dicebear.com/7.x/avataaars/svg?seed=User"
			},
			content,
			publishedAt: new Date().toISOString()
		}
		setComments([...comments, comment])
	}

	const handleTagClick = (tag: string) => {
		console.log(`Tag clicked: ${tag}`)
	}

	return (
			<div className="max-w-5xl mx-auto px-6 py-8">
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
									publishedAt={new Date(article1.created_at).toLocaleDateString()}
									likes={article1.likes}
									isLiked={article1.isLike}
									onLike={handleLike}
                  onFollow={handleFollow}
              />

              {/* 文章标题和正文 */}
              <ArticleContent title={article1.title} content={article1.body}/>

              {/* 标签 */
              }
              <ArticleTags tags={JSON.parse(article1.tags || "[]")} onTagClick={handleTagClick}/>

              {/* 评论区 */
              }
              <ArticleComments comments={comments} onAddComment={handleAddComment}/>

              {/* 返回顶部按钮 */
              }
              <BackToTop/></>
            )}

</div>
)
}
