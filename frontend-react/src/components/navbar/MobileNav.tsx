"use client"

import * as React from "react"

import {cn} from "@/lib/utils"
import {Button} from "@/components/ui/button"
import {Popover, PopoverContent, PopoverTrigger,} from "@/components/ui/popover"
import {NavLink, useLocation} from "react-router-dom";
import {motion} from "motion/react";

/**
 * 移动端导航栏
 * 采用Shadcn/ui官网的移动端导航栏组件
 * @param items
 * @constructor
 */

export function MobileNav({items}: {items: {name: string, path: string}[]}) {
	const [open, setOpen] = React.useState(false)
	const location = useLocation();

	return (
			<Popover open={open} onOpenChange={setOpen}>
				<PopoverTrigger asChild>
					<Button
							variant="ghost"
							className={cn(
									"extend-touch-target h-8 touch-manipulation items-center justify-start gap-2.5 !p-0 hover:bg-transparent focus-visible:bg-transparent focus-visible:ring-0 active:bg-transparent dark:hover:bg-transparent",

							)}
					>
						<div className="relative flex h-8 w-4 items-center justify-center">
							<div className="relative size-4">
              <span
									className={cn(
											"bg-foreground absolute left-0 block h-0.5 w-4 transition-all duration-100",
											open ? "top-[0.4rem] -rotate-45" : "top-1"
									)}
							/>
								<span
										className={cn(
												"bg-foreground absolute left-0 block h-0.5 w-4 transition-all duration-100",
												open ? "top-[0.4rem] rotate-45" : "top-2.5"
										)}
								/>
							</div>
							<span className="sr-only">Toggle Menu</span>
						</div>
						<span className="flex h-8 items-center text-lg leading-none font-medium">
            Menu
          </span>
					</Button>
				</PopoverTrigger>
				<PopoverContent
						className="bg-background/90 no-scrollbar h-(--radix-popper-available-height) w-(--radix-popper-available-width) overflow-y-auto rounded-none border-none p-0 shadow-none backdrop-blur duration-100"
						align="start"
						side="bottom"
						alignOffset={-16}
						sideOffset={14}
				>
					<div className="flex flex-col gap-12 overflow-auto px-6 py-6">
						<div className="flex flex-col gap-4">
							<div className="text-muted-foreground text-sm font-medium">
								菜单
							</div>
							<div className="flex flex-col gap-2">
								{items.map(({name, path}, index) => (
										<motion.button
												initial={{y: -10, opacity: 0}}
												animate={{y: 0, opacity: 1}}
												transition={{type: "spring", stiffness: 260, damping: 20, delay: index * 0.1}}
												key={name}
												onClick={() => setOpen(false)}>
												<NavLink to={path} className={cn("block w-full text-lg py-2 rounded-md hover:text-primary", location.pathname === path ? "bg-ring/5" : "")}>
													{name}
												</NavLink>
										</motion.button>
								))}
							</div>
						</div>
					</div>
				</PopoverContent>
			</Popover>
	)
}
