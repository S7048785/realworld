import ArticleItem from "@/pages/home/components/ArticleItem.tsx";

const articles = [
	{
		id: 1,
		title: "How to train your dragon",
		content: "¥You can adjust the opacity of an inset shadow using the opacity modifier, like inset-shadow-sm/50. The default inset shadow opacities are quite low (5%), so increasing the opacity (to like 50%) will make the inset shadows more pronounced.",
		authorName: "Eric Simons",
		authorId: 1,
		avatar: "/images/2230369404.jpg",
		datetime: "Dec 7, 2023",
		tags: ["HuManlty", "Programming"],
		likes: 10,
		views: 165,
	},
	{
		id: 2,
		title: "🏆2025 AI/Vibe Coding 对我的影响 | 年终技术征文",
		content: "当岁末的钟声临近，我们又站在了一年的重点回望。2025年，对你而言，是怎样的轮廓呢？它或许是由一行行被AI重构的代码勾勒，是某个深夜与新技术“顿悟时刻”的灵光一现，也可能是生活中因为智能体工具而悄然改变的工作状态。从智能体（Agent）的横空出世到多模态技术的经验突破，技术愈加深入地流淌尽我们的工作和生活日常，塑造着属于每个人的独特“Vibe”。",
		authorName: "答案answer",
		authorId: 2,
		avatar: "/images/1839039375.jpg",
		datetime: "Dec 3, 2025",
		tags: ["前端", "three.js"],
		likes: 10,
		views: 165,
	},
	{
		id: 3,
		title: "Go 语言未来会取代 Java 吗？",
		content: "Go 语言作为一种新兴的编程语言，自2009年发布以来，已经经历了多个版本的迭代。它的设计目标是简单、高效、并发安全。与 Java 相比，Go 语言在语法上更加简洁，同时提供了更好的并发支持。",
		authorName: "天天摸鱼的Java工程师",
		authorId: 3,
		avatar: "/images/9419024696.jpg",
		datetime: "Dec 7, 2023",
		tags: ["后端", "Java"],
		likes: 24,
		views: 165,
	},
]
export default function GlobalFeedTabsContent() {
	return (
			<div className="flex flex-col">
				{
					articles.map((article) => (
							<ArticleItem key={article.id} article={article}/>
					))
				}
			</div>
	)
}