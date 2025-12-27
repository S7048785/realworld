import {ModeToggle} from "@/components/mode-toggle.tsx";
import {Link, NavLink} from "react-router-dom";
import LoginDialog from "@/components/navbar/LoginDialog.tsx";
import {useState} from "react";
import {Button} from "@/components/ui/button.tsx";
import {User} from "lucide-react";

export default function NavBar() {
	const [isLoginShow, setIsLoginShow] = useState(false);
	return (
			<div className="flex justify-between items-center px-25 py-2.5 shadow-md">
				<div className="text-xl flex items-center">
					<Link to="/" className="text-primary font-bold">Conduit</Link>
					<div className="ml-12 text-sm inline-flex gap-4">
						<NavLink to="/">首页</NavLink>
						<NavLink to="/rank">排行榜</NavLink>
						<NavLink to="/editor">写文章</NavLink>
						<NavLink to="/settings">设置</NavLink>
					</div>
				</div>

				<div className="flex items-center space-x-4">
						<div className="inline-flex">
							<ModeToggle />
						</div>
					<div className="flex space-x-4">
						<Button variant={"outline"} onClick={() => setIsLoginShow(true)}>
							<User className="" size={16} />
							登录</Button>
					</div>
				</div>

				<LoginDialog
						isOpen={isLoginShow}
						onClose={() => setIsLoginShow(false)}
				/>
			</div>
	)
}