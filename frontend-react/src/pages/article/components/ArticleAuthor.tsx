import { Clock, Heart, UserPlus, UserCheck } from "lucide-react"
import { Button } from "@/components/ui/button"
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar"
import { cn } from "@/lib/utils"

interface Author {
  name: string
  avatar: string
  bio?: string
  articleCount?: number
  followers?: number
  following?: boolean
}

interface ArticleAuthorProps {
  author: Author
  publishedAt: string
  likes: number
  isLiked: boolean
  onLike: () => void
  onFollow?: () => void
}

function formatDate(dateString: string): string {
  const date = new Date(dateString)
  return date.toLocaleDateString("en-US", {
    year: "numeric",
    month: "long",
    day: "numeric"
  })
}

export default function ArticleAuthor({
  author,
  publishedAt,
  likes,
  isLiked,
  onLike,
  onFollow,
}: ArticleAuthorProps) {
  return (
    <div className="mb-8 border-b pb-6">
      <div className="flex items-center justify-between">
        <div className="flex items-center gap-4">
          <Avatar className="h-14 w-14">
            <AvatarImage src={author.avatar} alt={author.name} />
            <AvatarFallback>{author.name[0]}</AvatarFallback>
          </Avatar>
          <div>
            <p className="font-semibold text-lg">{author.name}</p>
            <div className="flex items-center gap-1 text-sm text-muted-foreground">
              <Clock className="h-3 w-3" />
              <span>{formatDate(publishedAt)}</span>
            </div>
            <div className="flex items-center gap-4 mt-2 text-sm text-muted-foreground">
              <span>{author.articleCount || 0} articles</span>
              <span>{author.followers || 0} followers</span>
            </div>
          </div>
        </div>
        <div className="flex items-center gap-2">
          <Button
            variant={"ghost"}
            onClick={onLike}
            className={cn("gap-2", isLiked && "text-red-500 hover:text-red-500")}
          >
            <Heart className={cn("h-4 w-4", isLiked && "fill-current")} />
            <span>{likes}</span>
          </Button>
          <Button
            variant={author.following ? "outline" : "default"}
            onClick={onFollow}
            className="gap-2"
          >
            {author.following ? (
              <>
                <UserCheck className="h-4 w-4" />
                <span>Following</span>
              </>
            ) : (
              <>
                <UserPlus className="h-4 w-4" />
                <span>Follow</span>
              </>
            )}
          </Button>
        </div>
      </div>
    </div>
  )
}
