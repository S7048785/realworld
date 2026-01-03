import {useCallback, useId, useState} from "react"
import {useNavigate} from "react-router-dom"
import {type Tag, TagInput} from "emblor"
import {Button} from "@/components/ui/button"
import {useUserStore} from "@/store/userStore.ts";
import toast from "react-hot-toast";
import {MdEditor} from 'md-editor-rt';
import 'md-editor-rt/lib/style.css';
import "./page.css"
import {useTheme} from "@/components/theme/theme-provider.tsx";
import {useArticleStore} from "@/store/articleStore.ts";

export default function EditorPage() {

  const {article, setArticle} = useArticleStore()

  const navigate = useNavigate()
  const id = useId()
  const [title, setTitle] = useState(article?.title || "")
  const [content, setContent] = useState(article?.body || "")
  const [tags, setTags] = useState<Tag[]>(article?.tags.map((tag) => ({text: tag, id: tag})) || [])
  const [activeTagIndex, setActiveTagIndex] = useState<number | null>(null)
  const [isSubmitting, setIsSubmitting] = useState(false)

  const {theme} = useTheme()

  const isLogin = useUserStore(state => state.isAuthenticated)

  const handleSubmit = useCallback(async () => {
    // 检查登录状态
    if (!isLogin) {
      toast.error("请先登录")
      return
    }

    if (!title.trim() || !content.trim()) return

    setIsSubmitting(true)
    try {
      // 模拟提交
      const articleData = {
        title,
        content,
        tags: tags.map((tag) => tag),
      }
      console.log("Submitting:", articleData)

      // 提交成功后跳转
      navigate("/")
    } catch (error) {
      console.error("Failed to submit:", error)
    } finally {
      setIsSubmitting(false)
    }
  }, [])

  const handleSave = () => {
    console.log({title, body: content, tags: tags.map((tag) => tag.text)})
    setArticle({title, body: content, tags: tags.map((tag) => tag.text)})
    toast.success("文章保存成功")
  }

  return (
      <div className="">
        {/* 标题 */}
        <div className="w-full flex items-center justify-between">
          <input
            id="title"
            placeholder="Enter Article title..."
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            className="text-2xl py-1 pl-10 w-[70%] font-semibold border-none shadow-none outline-none"
          />
          <Button className="px-20" onClick={handleSubmit}
                  disabled={!title.trim() || !content.trim() || isSubmitting}>
            {isSubmitting ? "发布中..." : "发布"}
          </Button>
      </div>
        <MdEditor className={"w-full h-[80vh]!"} modelValue={content} onChange={setContent}
                  theme={theme === "dark" ? "dark" : "light"} onSave={handleSave}/>
        {/* 标签 */}
        <div className="">
          <TagInput
              activeTagIndex={activeTagIndex}
              id={id}
              placeholder="Add tags, up to three"
              setActiveTagIndex={setActiveTagIndex}
              setTags={(newTags) => setTags(newTags)}
              styleClasses={{
                inlineTagsContainer:
                    "border-input rounded-none bg-background shadow-xs transition-[color,box-shadow] outline-none p-1 gap-1",
                input: "w-full min-w-[80px] shadow-none px-2 h-7 border-none outline-none",
                tag: {
                  body: "h-7 relative bg-secondary text-foreground border border-input hover:bg-secondary rounded-md font-medium text-sm ps-2 pe-7",
                  closeButton:
                      "absolute -inset-y-px -end-px p-0 rounded-e-md flex size-7 transition-[color,box-shadow] outline-none focus-visible:border-ring focus-visible:ring-ring/50 focus-visible:ring-[3px] text-muted-foreground/80 hover:text-foreground",
                },
              }}
              tags={tags}
          />
        </div>
    </div>
  )
}
