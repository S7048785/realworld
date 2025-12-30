// components/BackToTop.tsx
import React, {useState, useEffect} from 'react';
import {motion} from 'motion/react';
import {useScroll} from "ahooks";
import {ArrowBigUpDash} from "lucide-react";

interface BackToTopProps {
	/**
	 * 滚动距离阈值（像素），超过此距离显示按钮
	 * @default 300
	 */
	threshold?: number;

	/**
	 * 按钮主色调（支持十六进制、颜色名称）
	 * @default '#6366f1'
	 */
	color?: string;

	/**
	 * 按钮尺寸（px）
	 * @default 56
	 */
	size?: number;

	/**
	 * 按钮圆角（px）
	 * @default 16
	 */
	borderRadius?: number;

	/**
	 * 按钮阴影强度
	 * @default 0.3
	 */
	shadowIntensity?: number;
		scrollRef?: React.RefObject<HTMLDivElement | null>
}


const BackToTop: React.FC<BackToTopProps> = ({
																							 threshold = 100,
																							 size = 56,
																							 scrollRef
																						 }) => {
	const [isVisible, setIsVisible] = useState(false);
	const scroll = useScroll(scrollRef);

	// 检查滚动位置
	const checkScroll = () => {

		setIsVisible((scroll?.top || 0) > threshold);
	};
	// 滚动监听
	useEffect(() => {
		checkScroll()
	}, [scroll]);

	// 滚动到顶部
	const scrollToTop = () => {
		scrollRef!.current?.scrollTo({
			top: 0,
			behavior: 'smooth'
		});
	};


	return (
			<motion.div
					initial={{opacity: 0, scale: 0.8}}
					animate={{
						opacity: isVisible ? 1 : 0,
						scale: isVisible ? 1 : 0.8,

					}}
					className="fixed bottom-18 right-18 z-50"
			>
				<motion.button
						onClick={scrollToTop}
						whileHover={{scale: 1.1, y: -2}}
						whileTap={{scale: 0.95}}
						style={{
							width: `${size}px`,
							height: `${size}px`,
						}}
						className="text-foreground shadow-2xl bg-background overflow-hidden flex items-center justify-center transition-all duration-200 relative rounded-full border"
				>
					<motion.div
							className="absolute inset-0 flex items-center justify-center"
					>
						<ArrowBigUpDash />
					</motion.div>

					{/* 悬停效果 - 顶部渐变 */}
					<motion.div
							initial={{opacity: 0}}
							animate={isVisible ? {opacity: 0.2} : {opacity: 0}}
							transition={{duration: 0.2}}
							className="absolute top-0 left-0 w-full h-full bg-gradient-to-b from-white/20 to-transparent"
					/>
				</motion.button>
			</motion.div>
	);
};

export default BackToTop;