import {Button} from "@/components/ui/button.tsx";

export default function AboutPage() {
	return (
			<div className="max-w-3xl mx-auto px-6 py-8">
				<div className="mb-8">
					<p className="text-2xl font-bold mb-4">关于 Conduit</p>
					<p className="text-md mb-2">
						这是GitHub上的一个用于练习技术栈的 Demo 项目 <Button variant={"link"}><a
							href="https://github.com/gothinkster/realworld" className="text-primary text-lg">realworld</a></Button>
					</p>
					<p className="text-md mb-2">主要用于练习 React 和 Fast Api 等相关Web技术在在项目中的使用</p>
				</div>

				<div className="mb-8">
					<p className="text-2xl font-bold mb-4">技术栈</p>

					<ul className="list-disc pl-6 text-md space-y-2">
						<li>前端: React、Tailwind CSS、Motion、Shadcn/UI、Aceternity UI、Animate UI、UI Layouts、Zustand、Zod、Ahooks</li>
						<li>后端: Fast Api、SQLModel、JWT</li>
						<li>数据库: MySQL</li>
					</ul>
				</div>

				<div className="mb-8">
					<p className="text-2xl font-bold mb-4">UI/技术 参考</p>
						<Button variant={"link"}>
							<a href="https://github.com/gothinkster/realworld" target={"_blank"}
								 className="text-primary text-lg">realworld</a>
						</Button>
						<Button variant={"link"}><a href="https://blog.grtsinry43.com/" target={"_blank"}
																				className="text-primary text-lg">Grtsinry43的前端札记</a></Button>
						<Button variant={"link"}><a href="https://www.vio.vin/" target={"_blank"}
																				className="text-primary text-lg">薇尔薇</a></Button>
						<Button variant={"link"}><a href="https://heyxiaoli.com/" target={"_blank"}
																				className="text-primary text-lg">小李的生活志</a></Button>
						<Button variant={"link"}><a href="https://fuxiaochen.com/" target={"_blank"}
																				className="text-primary text-lg">付小晨</a></Button>
						<Button variant={"link"}><a href="https://juejin.cn/" target={"_blank"}
																				className="text-primary text-lg">稀土掘金</a></Button>

				</div>

				<div className="mb-8">
					<p className="text-2xl font-bold mb-4">参考文章</p>

					<p>
						<Button variant={"link"}><a href="https://juejin.cn/post/7017460681309945886" target={"_blank"}
																				className="text-primary text-lg">https://juejin.cn/post/7017460681309945886</a></Button>
					</p>
					<p>
						<Button variant={"link"}><a href="https://www.bilibili.com/opus/915654579034521632" target={"_blank"}
																				className="text-primary text-lg">https://www.bilibili.com/opus/915654579034521632</a></Button>
					</p>

				</div>


			</div>
	);
}














