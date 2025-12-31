import ReactMarkdown from 'react-markdown';
import "@/styles/markdown.css"
import remarkGfm from 'remark-gfm';
import {CodeBlock, CodeHeader ,Code} from "@/components/animate-ui/components/animate/code.tsx";

interface ArticleContentProps {
  title: string
  content: string
}

export default function ArticleContent({ title, content }: ArticleContentProps) {
  return (
      <>
        <article className="markdown">
          <h1 className="">{title}</h1>
          <ReactMarkdown
              remarkPlugins={[remarkGfm]}
              components={{
                code({node, className, children, ...props}) {
                  const match = /language-(\w+)/.exec(className || '');
                  return match ? (
                      <Code code={String(children).replace(/\n$/, '')}>
                        <CodeHeader copyButton> <span>{match[1]}</span> </CodeHeader>
                        <CodeBlock lang={match[1]}/>
                      </Code>
                  ) : (
                      <code className={className} {...props}>
                        {children}
                      </code>
                  );
                },
              }}
          >
            {content}
          </ReactMarkdown>

        </article>
      </>
  )
}
