import {createBrowserRouter} from "react-router-dom"
import App from "@/App.tsx";
import HomePage from "@/pages/home/page.tsx";
import ArticlePage from "@/pages/article/page.tsx";
import ProfilePage from "@/pages/profile/page.tsx";
import SettingsPage from "@/pages/settings/page.tsx";
import EditorPage from "@/pages/editor/page.tsx";

export default createBrowserRouter([
	{
		path: "/",
		Component: App,
		children:[
			{
				path: "/",
				Component: HomePage
			},
			{
				path: "/article/:id",
				Component: ArticlePage
			},
			{
				path: "/profile/:id",
				Component: ProfilePage
			},
			{
				path: "/editor",
				Component: EditorPage
			},
				{
					path: "/settings",
					Component: SettingsPage
				}
		]
	},

])