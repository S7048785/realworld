import { useState } from "react"
import { MessageSquare } from "lucide-react"
import { Button } from "@/components/ui/button"
import { Card, CardContent } from "@/components/ui/card"
import { Textarea } from "@/components/ui/textarea"
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar"

interface Comment {
  id: string
  author: {
    name: string
    avatar: string
  }
  content: string
  publishedAt: string
}

interface CommentItemProps {
  comment: Comment
}

function formatDate(dateString: string): string {
  const date = new Date(dateString)
  return date.toLocaleDateString("en-US", {
    year: "numeric",
    month: "long",
    day: "numeric"
  })
}

function CommentItem({ comment }: CommentItemProps) {
  return (
    <Card>
      <CardContent className="pt-4">
        <div className="flex gap-3">
          <Avatar className="h-8 w-8">
            <AvatarImage src={comment.author.avatar} alt={comment.author.name} />
            <AvatarFallback>{comment.author.name[0]}</AvatarFallback>
          </Avatar>
          <div className="flex-1">
            <div className="flex items-center justify-between">
              <span className="font-semibold text-sm">{comment.author.name}</span>
              <span className="text-xs text-muted-foreground">
                {formatDate(comment.publishedAt)}
              </span>
            </div>
            <p className="mt-1 text-sm text-muted-foreground">{comment.content}</p>
          </div>
        </div>
      </CardContent>
    </Card>
  )
}

interface ArticleCommentsProps {
  comments: Comment[]
  onAddComment: (content: string) => void
}

export default function ArticleComments({ comments, onAddComment }: ArticleCommentsProps) {
  const [newComment, setNewComment] = useState("")

  const handleSubmit = () => {
    if (!newComment.trim()) return
    onAddComment(newComment)
    setNewComment("")
  }

  return (
    <div className="mb-8">
      <div className="flex items-center gap-2 mb-4">
        <MessageSquare className="h-5 w-5" />
        <h2 className="text-xl font-semibold">Comments ({comments.length})</h2>
      </div>

      {/* 评论输入框 */}
      <Card className="mb-6">
        <CardContent className="pt-4">
          <Textarea
            placeholder="Write a comment..."
            value={newComment}
            onChange={(e) => setNewComment(e.target.value)}
            className="min-h-[100px] mb-3"
          />
          <div className="flex justify-end">
            <Button onClick={handleSubmit} disabled={!newComment.trim()}>
              Post Comment
            </Button>
          </div>
        </CardContent>
      </Card>

      {/* 评论列表 */}
      <div className="space-y-4">
        {comments.map((comment) => (
          <CommentItem key={comment.id} comment={comment} />
        ))}
      </div>
    </div>
  )
}
