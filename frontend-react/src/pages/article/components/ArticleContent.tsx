interface ArticleContentProps {
  title: string
  content: string
}

export default function ArticleContent({ title, content }: ArticleContentProps) {
  return (
    <>
      <h1 className="text-3xl font-bold mb-6">{title}</h1>

      <article className="prose prose-neutral dark:prose-invert max-w-none mb-12">
        {content.split("\n\n").map((paragraph, index) => (
          <p key={index} className="mb-4 leading-relaxed text-lg">
            {paragraph}
          </p>
        ))}
      </article>
    </>
  )
}
