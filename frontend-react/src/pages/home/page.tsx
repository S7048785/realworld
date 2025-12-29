import {
	Tabs,
	TabsContent,
	TabsContents,
	TabsList,
	TabsTrigger,
} from '@/components/animate-ui/components/animate/tabs.tsx';

import PopularTags from "@/pages/home/components/PopularTags.tsx";
import GlobalFeedTabsContent from "@/pages/home/components/tabs-content/GlobalFeedTabsContent.tsx";
import YourFeedTabsContent from "@/pages/home/components/tabs-content/YourFeedTabsContent.tsx";
import {type JSX, useState} from "react";

const tags = [
	{
		name: "HuManlty",
		id: 1
	},
	{
		name: "Programming",
		id: 2
	},
	{
		name: "Design",
		id: 3
	},
	{
		name: "Data",
		id: 4
	},
	{
		name: "Machine",
		id: 5
	},
	{
		name: "Intelligence",
		id: 6
	},
	{
		name: "Things",
		id: 7
	}
]

const tabs = [
		{
			label: "Global Feed",
			value: "Global Feed",
			content: (
					<GlobalFeedTabsContent />
			),
		},
		{
			label: "Your Feed",
			value: "Your Feed",
			content: (
						<YourFeedTabsContent />
			),
		},
]

export default function HomePage() {
	const [tabsState, setTabsState] = useState(tabs);
	const [tabValue, setTabValue] = useState(tabs[0]);

	const addTab = (tab: { label: string, value: string, content: JSX.Element}) => {
		console.log(123)
		// 检查是否已存在相同的标签
		if (tabsState.find((item) => item.value === tab.value)) return;
		if (tabsState.length > 3) return;
		setTabsState([...(tabsState.slice(0,2)), tab]);
		setTabValue(tab);
		console.log(tab)
	}
	return (
			<div className="h-full">
				<div
						className="h-40 w-full space-y-2 py-10 flex flex-col items-center justify-center bg-primary inset-shadow-[0_0_10px_rgba(0,0,0,0.5)] text-gray-200">
					<span className="text-5xl font-bold text-shadow-lg">Conduit</span>
					<span className="tracking-widest">一个分享知识的地方。</span>
				</div>

				<div className="pt-10 px-25 min-h-100 flex gap-8">
					<div className="flex-1">
						<Tabs className="" value={tabValue.value}>
							<TabsList
												className="h-auto w-full flex justify-start gap-2 rounded-none border-b bg-transparent px-0 py-1 text-foreground text-lg">
								{
									tabsState.map((tab) => (
											<TabsTrigger
													onClick={() => setTabValue(tab)}
													className="after:-mb-1 relative after:absolute after:inset-x-0 after:bottom-0 after:h-0.5 hover:text-foreground data-[state=active]:bg-transparent data-[state=active]:text-primary data-[state=active]:shadow-none data-[state=active]:after:bg-primary"
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
								<YourFeedTabsContent />
							</TabsContents>

						</Tabs>
					</div>
					<div className="w-1/5 bg-sidebar p-2 rounded h-50">
						<PopularTags tags={tags} addTab={addTab}/>
					</div>
				</div>

			</div>
	)
}