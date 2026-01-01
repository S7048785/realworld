import {Link, NavLink, useNavigate} from "react-router-dom";
import LoginDialog from "@/components/user/LoginDialog.tsx";
import {useState} from "react";
import {Button} from "@/components/ui/button.tsx";
import {ChevronDownIcon, LogOutIcon, Settings, User} from "lucide-react";
import ThemeTogglerButtonDemo from "@/components/theme/theme-toggler.tsx";
import { motion} from "motion/react";
import {clsx} from "clsx";
import toast from "react-hot-toast";
import {useUserStore} from "@/store/userStore.ts";
import {Avatar, AvatarFallback, AvatarImage} from "@/components/ui/avatar.tsx";
import {
	DropdownMenu,
	DropdownMenuContent,
	DropdownMenuItem,
	DropdownMenuTrigger
} from "@/components/ui/dropdown-menu.tsx";
import "./navbar.css"

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
		path: "/editor",
	},
	{
		name: "关于",
		path: "/about"
	},
]

export default function NavBar() {
	const navigate = useNavigate()
	const [isLoginShow, setIsLoginShow] = useState(false);
	const user = useUserStore(state => state.user)
	const clearUser = useUserStore(state => state.clearUser)
	return (
			<div className={clsx("sticky top-0 z-2 bg-white/80 dark:bg-neutral-800/20 backdrop-blur-[28px]")}>
				<div className="relative flex justify-between items-center top-0 px-20 py-3 border-b h-18">
					{/* Logo */}
					<div className="flex-1">
						<Link to="/" className="text-primary font-bold text-xl">Conduit</Link>
					</div>

					{/* 导航菜单 */}
					<div
							className="hidden md:flex justify-center items-center  "
					>
							{
								navList.map((item, index) => (
										<NavLink key={index} to={item.path} className={
											"px-8 py-2 navitem overflow-hidden"
										}>
											<div
													className="relative bg-transparent text-shadow-sm text-center transition-all">
												<motion.span
														initial={{y: -40}}
														animate={{y: 0}}
														transition={{delay: index * 0.05, duration: 0.2}}
														className="block pb-0 "
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
							{user ? (
									<>
										<DropdownMenu>
											<DropdownMenuTrigger asChild>
												<Button className="h-auto p-0 hover:bg-transparent" variant="ghost">
													<Avatar>
														<AvatarImage src={user.avatar} alt={user.username} />
														<AvatarFallback>{user.username[0]}</AvatarFallback>
													</Avatar>
													<ChevronDownIcon
															aria-hidden="true"
															className="opacity-60"
															size={16}
													/>
												</Button>
											</DropdownMenuTrigger>
											<DropdownMenuContent className="max-w-64">

												<DropdownMenuItem className="p-2 hover:bg-none!">
														<User aria-hidden="true" className="opacity-60" size={16} />
														<span>{user.username}</span>
												</DropdownMenuItem>
												<DropdownMenuItem className="p-2" onClick={() => {
													navigate('/settings')
												}}>
														<Settings aria-hidden="true" className="opacity-60" size={16} />
														<span>Settings</span>
												</DropdownMenuItem>
												<DropdownMenuItem className="p-2" onClick={() => {
													clearUser()
													toast.success("退出成功")
												}}>
													<LogOutIcon aria-hidden="true" className="opacity-60" size={16} />
													<span>Logout</span>
												</DropdownMenuItem>
											</DropdownMenuContent>
										</DropdownMenu>
									</>
							) : (
									<LoginDialog
											isOpen={isLoginShow}
											onClose={() => setIsLoginShow(false)}
									/>

							)}
						</div>
					</div>
				</div>

			</div>

	)
}