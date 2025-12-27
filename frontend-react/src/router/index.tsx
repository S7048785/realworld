import {  createBrowserRouter} from "react-router-dom"
import App from "@/App.tsx";
import HomePage from "@/pages/home/page.tsx";
import ArticlePage from "@/pages/article/page.tsx";
import ProfilePage from "@/pages/profile/page.tsx";
import SettingsPage from "@/pages/settings/page.tsx";
import EditorPage from "@/pages/editor/page.tsx";

export default createBrowserRouter(  [
	{
		path: "/",
		element: <App/>,
		children:[
			{
				index: true,
				element: <HomePage/>,
				handle: {
					title: "首页"
				}
			},
			{
				path: "/article/:id",
				element: <ArticlePage/>,
				handle: {
					title: "文章详情"
				}
			},
			{
				path: "/profile/:id",
				element: <ProfilePage/>,
				handle: {
					title: "用户详情"
				}
			},
			{
				path: "/editor",
				element: <EditorPage/>,
				handle: {
					title: "文章编辑/创建"
				}
			},
			{
				path: "/settings",
				element: <SettingsPage/>,
				handle: {
					title: "设置"
				}
			}
		]
	}
])
