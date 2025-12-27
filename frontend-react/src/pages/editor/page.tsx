import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { type Tag, TagInput } from "emblor"
import { useId } from "react"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Textarea } from "@/components/ui/textarea"
import { Label } from "@/components/ui/label"

export default function EditorPage() {
  const navigate = useNavigate()
  const id = useId()
  const [title, setTitle] = useState("")
  const [content, setContent] = useState("")
  const [tags, setTags] = useState<Tag[]>([])
  const [activeTagIndex, setActiveTagIndex] = useState<number | null>(null)
  const [isSubmitting, setIsSubmitting] = useState(false)

  const handleSubmit = async () => {
    if (!title.trim() || !content.trim()) return

    setIsSubmitting(true)
    try {
      // 模拟提交
      const articleData = {
        title,
        content,
        tags: tags.map((tag) => tag.text),
      }
      console.log("Submitting:", articleData)

      // 提交成功后跳转
      navigate("/")
    } catch (error) {
      console.error("Failed to submit:", error)
    } finally {
      setIsSubmitting(false)
    }
  }

  return (
    <div className="max-w-3xl mx-auto px-6 py-8">
      {/*<h1 className="text-3xl font-bold mb-8">Editor</h1>*/}

      <div className="space-y-6">
        {/* 标题 */}
        <div className="space-y-2">
          <Label htmlFor="title">标题</Label>
          <Input
            id="title"
            placeholder="Article title"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            className="text-lg font-semibold"
          />
        </div>

        {/* 正文 */}
        <div className="space-y-2">
          <Label htmlFor="content">内容</Label>
          <Textarea
            id="content"
            placeholder="Write your article content here..."
            value={content}
            onChange={(e) => setContent(e.target.value)}
            className="min-h-[300px] resize-y"
          />
        </div>

        {/* 标签 */}
        <div className="space-y-2">
          <Label htmlFor={id}>标签：( 回车添加标签 )</Label>
          <TagInput
            activeTagIndex={activeTagIndex}
            id={id}
            placeholder="Add a tag"
            setActiveTagIndex={setActiveTagIndex}
            setTags={(newTags) => setTags(newTags)}
            styleClasses={{
              inlineTagsContainer:
                "border-input rounded-md bg-background shadow-xs transition-[color,box-shadow] focus-within:border-ring outline-none focus-within:ring-[3px] focus-within:ring-ring/50 p-1 gap-1",
              input: "w-full min-w-[80px] shadow-none px-2 h-7",
              tag: {
                body: "h-7 relative bg-background border border-input hover:bg-background rounded-md font-medium text-xs ps-2 pe-7",
                closeButton:
                  "absolute -inset-y-px -end-px p-0 rounded-e-md flex size-7 transition-[color,box-shadow] outline-none focus-visible:border-ring focus-visible:ring-ring/50 focus-visible:ring-[3px] text-muted-foreground/80 hover:text-foreground",
              },
            }}
            tags={tags}
          />
        </div>

        {/* 操作按钮 */}
        <div className="flex items-center justify-end gap-4 pt-4">
          <Button className="px-10" onClick={handleSubmit} disabled={!title.trim() || !content.trim() || isSubmitting}>
            {isSubmitting ? "发布中..." : "发布"}
          </Button>
        </div>
      </div>
    </div>
  )
}
