import { useState } from "react"
import ArticleContent from "./components/ArticleContent"
import ArticleAuthor from "./components/ArticleAuthor"
import ArticleTags from "./components/ArticleTags"
import ArticleComments from "./components/ArticleComments"
import BackToTop from "./components/BackToTop"

// 模拟文章数据
const article = {
  id: "1",
  title: "Building Modern React Applications",
  content: `React 19 introduces several exciting new features that make building user interfaces more intuitive and powerful. In this article, we'll explore some of these improvements and how they can enhance your development experience.

The new hooks API provides more flexibility in managing component logic, while the improved concurrent rendering capabilities ensure smoother user interactions. Let's dive into practical examples that demonstrate these concepts.

First, consider the new use hook pattern that allows for cleaner separation of concerns. This approach enables developers to extract and reuse logic across multiple components without the complexity of custom hooks.

Additionally, the improvements to server components offer better performance and reduced bundle sizes. By leveraging these features, teams can build more scalable applications that load faster and provide a better user experience.`,
  author: {
    name: "John Doe",
    avatar: "https://api.dicebear.com/7.x/avataaars/svg?seed=John",
    bio: "Full-stack developer passionate about React and modern web technologies.",
    articleCount: 42,
    followers: 1234,
    following: false
  },
  publishedAt: "2024-12-27T10:00:00Z",
  likes: 42,
  isLiked: false,
  tags: ["React", "JavaScript", "Web Development", "Frontend"],
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
  const [isLiked, setIsLiked] = useState(article.isLiked)
  const [likeCount, setLikeCount] = useState(article.likes)
  const [isFollowing, setIsFollowing] = useState(article.author.following)
  const [comments, setComments] = useState<Comment[]>(article.comments)

  const handleLike = () => {
    setIsLiked(!isLiked)
    setLikeCount(isLiked ? likeCount - 1 : likeCount + 1)
  }

  const handleFollow = () => {
    setIsFollowing(!isFollowing)
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
    <div className="max-w-4xl mx-auto px-6 py-8">
      {/* 作者信息 - 放在顶部 */}
      <ArticleAuthor
        author={article.author}
        publishedAt={article.publishedAt}
        likes={likeCount}
        isLiked={isLiked}
        onLike={handleLike}
        onFollow={handleFollow}
      />

      {/* 文章标题和正文 */}
      <ArticleContent title={article.title} content={article.content} />

      {/* 标签 */}
      <ArticleTags tags={article.tags} onTagClick={handleTagClick} />

      {/* 评论区 */}
      <ArticleComments comments={comments} onAddComment={handleAddComment} />

      {/* 返回顶部按钮 */}
      <BackToTop />
    </div>
  )
}
