import {Link, NavLink, useLocation} from "react-router-dom";
import LoginDialog from "@/components/navbar/LoginDialog.tsx";
import {useState} from "react";
import {Button} from "@/components/ui/button.tsx";
import {User} from "lucide-react";
import ThemeTogglerButtonDemo from "@/components/theme-toggler.tsx";
import { motion} from "motion/react";
import {clsx} from "clsx";

const navList = [
	{
		name: "首页",
		path: "/"
	},
	{
		name: "排行榜",
		path: "/rank"
	},
	{
		name: "写文章",
		path: "/editor"
	},
	{
		name: "设置",
		path: "/settings"
	},
]

export default function NavBar() {
	const location = useLocation()
	const [isLoginShow, setIsLoginShow] = useState(false);
	return (
			<div className={clsx("sticky top-0 z-2", location.pathname == "/" ? "bg-background" : "dark:bg-neutral-800/20 backdrop-blur-[8px]")}>
				<div className="relative flex justify-between items-center top-0 px-20 py-3 border-b h-18">
					{/* Logo */}
					<div className="flex-1">
						<Link to="/" className="text-primary font-bold text-xl">Conduit</Link>
					</div>

					{/* 导航菜单 */}
					<div
							className="hidden md:flex justify-center items-center text-sm "
					>

							{
								navList.map((item, index) => (
										<NavLink key={index} to={item.path} className={
											"px-8 py-2 navitem"
										}>
											<div
													className="relative bg-transparent hover:text-primary! text-shadow-sm text-center transition-all">
												<motion.span
														initial={{y: -40}}
														animate={{y: 0}}
														transition={{delay: index * 0.05 + 1, duration: 0.2}}
														className="block pb-0"
												>
													{item.name}
												</motion.span>
												<div
														className={"bottom-line absolute left-0 right-0 mx-auto bottom-0 bg-primary h-0.5 w-0 "}
												/>
											</div>

										</NavLink>
								))
							}
					</div>

					<div className="flex flex-1 justify-end items-center space-x-4">
						<div className="inline-flex">
							<ThemeTogglerButtonDemo/>
						</div>
						<div className="flex space-x-4">
							<Button variant={"outline"} onClick={() => setIsLoginShow(true)}>
								<User className="" size={16}/>
								登录
							</Button>
						</div>
					</div>
				</div>
				<LoginDialog
						isOpen={isLoginShow}
						onClose={() => setIsLoginShow(false)}
				/>
			</div>

	)
}