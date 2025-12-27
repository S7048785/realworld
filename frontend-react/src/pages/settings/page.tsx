import {Label} from "@/components/ui/label.tsx";
import {Input} from "@/components/ui/input.tsx";
import {Textarea} from "@/components/ui/textarea.tsx";
import {Button} from "@/components/ui/button.tsx";
import {useState} from "react";

export default function SettingsPage() {
	const [name, setName] = useState("")
	const [email, setEmail] = useState("")
	const [password, setPassword] = useState("")
	const [avatar, setAvatar] = useState("")
	const [introduction, setIntroduction] = useState("")

	const [isSubmitting, setIsSubmitting] = useState(false)
	const handleSubmit = async () => {
		if (!name.trim() || !email.trim() || !password.trim() ) return

		setIsSubmitting(true)
		try {
			// 模拟提交
			const articleData = {
				name,
				email,
				password,
				avatar,
				introduction,
			}
			console.log("Submitting:", articleData)

			// 提交成功后跳转
			// navigate("/")
		} catch (error) {
			console.error("Failed to submit:", error)
		} finally {
			setIsSubmitting(false)
		}
	}
  return (
			<div className="max-w-3xl mx-auto px-6 py-8">
				{/*<h1 className="text-3xl font-bold mb-8">Editor</h1>*/}

				<div className="space-y-6">
					{/* 用户名 */}
					<div className="space-y-2">
						<Label htmlFor="name">用户名 <span className="text-destructive">*</span></Label>
						<Input
								id="name"
								required
								placeholder="Your username"
								value={name}
								onChange={(e) => setName(e.target.value)}
								className="text-lg font-semibold"
						/>
					</div>

					{/* 邮箱 */}
					<div className="space-y-2">
						<Label htmlFor="name">
							邮箱 <span className="text-destructive">*</span>
						</Label>
						<Input
								id="email"
								type="email"
							 required
								placeholder="Your email"
								value={email}
								onChange={(e) => setEmail(e.target.value)}
								className="text-lg font-semibold"
						/>
					</div>

					{/* 密码 */}
					<div className="space-y-2">
						<Label htmlFor="password">
							密码 <span className="text-destructive">*</span>
						</Label>
						<Input
								id="password"
								type="password"
								 required
								placeholder="Your password"
								value={password}
								onChange={(e) => setPassword(e.target.value)}
								className="text-lg font-semibold"
						/>
					</div>

					{/*	头像*/}
					<div className="space-y-2">
						<Label htmlFor="avatar">
							个人头像的URL
						</Label>
						<Input
								id="avatar"
								placeholder="Your avatar"
								value={avatar}
								onChange={(e) => setAvatar(e.target.value)}
								className="text-lg font-semibold"
						/>
					</div>

					{/*	个人简介*/}
					<div className="space-y-2">
						<Label htmlFor="content">个人简介</Label>
						<Textarea
								id="content"
								placeholder="Write your article content here..."
								value={introduction}
								onChange={(e) => setIntroduction(e.target.value)}
								className="min-h-[100px] resize-y"
						/>
					</div>

					{/* 操作按钮 */}
					<div className="flex items-center justify-end gap-4 pt-4 text-lg">
						<Button className="px-10" onClick={handleSubmit} disabled={!name.trim() || !email.trim() || !password.trim() || isSubmitting}>
							{isSubmitting ? "修改中..." : "确定"}
						</Button>
					</div>
				</div>
			</div>
	);
}
