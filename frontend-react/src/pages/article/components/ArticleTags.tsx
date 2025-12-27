import { Tag } from "lucide-react"

interface ArticleTagsProps {
  tags: string[]
  onTagClick?: (tag: string) => void
}

export default function ArticleTags({ tags, onTagClick }: ArticleTagsProps) {
  if (tags.length === 0) return null

  return (
    <div className="flex items-center gap-2 mb-8">
      <Tag className="h-4 w-4 text-muted-foreground" />
      <div className="flex flex-wrap gap-2">
        {tags.map((tag) => (
          <button
            key={tag}
            onClick={() => onTagClick?.(tag)}
            className="px-3 py-1 text-sm bg-secondary text-secondary-foreground rounded-full hover:bg-secondary/80 transition-colors"
          >
            {tag}
          </button>
        ))}
      </div>
    </div>
  )
}
