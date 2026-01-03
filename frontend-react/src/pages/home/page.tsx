import {
	Tabs,
	TabsContent,
	TabsContents,
	TabsList,
	TabsTrigger,
} from '@/components/animate-ui/components/animate/tabs.tsx';

import PopularTags from "@/pages/home/components/PopularTags.tsx";
import {type JSX, useState} from "react";
import api from "@/api/article.ts";
import ArticleListTabsContent from "@/pages/home/components/ArticleListTabsContent.tsx";

export default function HomePage() {
	const [tabsState, setTabsState] = useState([
		{
			label: "Global Feed",
			value: "Global Feed",
			content: (
					<ArticleListTabsContent getData={(skip: number) => api.getArticleAllList({skip})}/>
			),
		},
		{
			label: "Your Feed",
			value: "Your Feed",
			content: (
					<ArticleListTabsContent getData={(skip: number) => api.getArticleListByFollow({skip})}/>
			),
		},
	]);

	// 初始化时默认选中第一个标签
	const [tabValue, setTabValue] = useState(tabsState[0]);

	const addTab = (tab: { label: string, value: string, content: JSX.Element }) => {
		// 检查是否已存在相同的标签
		if (tabsState.find((item) => item.value === tab.value)) return;
		if (tabsState.length > 3) return;
		setTabsState([...(tabsState.slice(0, 2)), tab]);
		setTabValue(tab);
	}
	return (
			<div className="h-full">
				<div
						className="h-40 w-full space-y-2 py-10 flex flex-col items-center justify-center bg-primary inset-shadow-[0_0_10px_rgba(0,0,0,0.5)] text-gray-200">
					<span className="text-5xl font-bold text-shadow-lg">Conduit</span>
					<span className="tracking-widest">一个分享知识的地方。</span>
				</div>

				<div className="pt-10 px-5 md:px-25 min-h-100 flex gap-8">
					<div className="md:w-4/5 w-full">
						<Tabs className="" value={tabValue.value}>
							<TabsList
									className="h-auto w-full flex justify-start gap-2 rounded-none border-b bg-transparent px-0 py-1 text-foreground text-lg">
								{
									tabsState.map((tab) => (
											<TabsTrigger
													onClick={() => setTabValue(tab)}
													className="py-2 after:-mb-1 relative after:absolute after:inset-x-0 after:bottom-0 after:h-0.5 hover:text-foreground data-[state=active]:bg-transparent data-[state=active]:text-primary data-[state=active]:shadow-none data-[state=active]:after:bg-primary"
													key={tab.value}
													value={tab.value}
											>{tab.label}</TabsTrigger>
									))
								}
							</TabsList>
							<TabsContents>
								{
									tabsState.map((tab) => (
											<TabsContent key={tab.value} value={tab.value}>
												{tab.content}
											</TabsContent>
									))
								}
							</TabsContents>
						</Tabs>
					</div>
					<div className="flex-1 bg-sidebar p-2 rounded h-50 hidden md:block">
						<PopularTags addTab={addTab}/>
					</div>
				</div>
			</div>
	)
}