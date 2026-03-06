import emitter from "@/lib/emitter";
import { cubicBezier, motion } from "motion/react";
import { useEffect, useState } from "react";

export default function ArticleItemIndicator() {
  const [hoverCard, setHoverCard] = useState({
    top: 0,
    height: 0,
    opacity: 0,
  });

  useEffect(() => {
    const handleCardMouseEnter = (event: React.MouseEvent<HTMLDivElement>) => {
      setHoverCard({
        height: event.currentTarget.offsetHeight,
        top: event.currentTarget.offsetTop,
        opacity: 0.5,
      });
    };

    const handleCardMouseLeave = () => {
      setHoverCard((prev) => ({ ...prev, opacity: 0 }));
    };
    emitter.on("card-hover", (event: unknown) => {
      handleCardMouseEnter(event as React.MouseEvent<HTMLDivElement>);
    });
    emitter.on("card-hover-end", handleCardMouseLeave);
  }, []);
  return (
    <motion.div
      transition={{
        stiffness: 0.15,
        damping: 0.7,
        ease: cubicBezier(0.4, 0, 0.2, 1),
        duration: 0.3,
      }}
      animate={{
        top: hoverCard.top,
        height: `${hoverCard.height}px`,
        opacity: hoverCard.opacity,
      }}
      className="absolute left-0 w-full bg-sidebar-ring/50 dark:bg-jade-800/20 rounded-default pointer-events-none -z-10"
    ></motion.div>
  );
}
