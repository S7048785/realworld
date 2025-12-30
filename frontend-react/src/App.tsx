// import { useState } from 'react'
// import reactLogo from './assets/react.svg'
// import viteLogo from '/vite.svg'
// import {ModeToggle} from "@/components/mode-toggle.tsx";
// import NavBar from "@/components/navbar/NavBar.tsx";

import NavBar from "@/components/navbar/NavBar.tsx";
import {Outlet} from "react-router-dom";
import PageTitleUpdater from "@/hooks/PageTitleUpdater.tsx";
import {SparklesCore} from "@/components/ui/sparkles.tsx";
import {useTheme} from "@/components/theme-provider.tsx";
import BackToTop from "@/components/BackToTop.tsx";
import { useRef} from "react";

function App() {
  // const [count, setCount] = useState(0)
  const { theme } = useTheme();
  const scrollRef = useRef<HTMLDivElement>(null);

  return (
      <>
        <PageTitleUpdater/>
        <div className="w-full absolute inset-0 h-screen">
          <SparklesCore
              id="tsparticlesfullpage"
              background={theme === "dark" ? "primary" : "background"}
              minSize={0.6}
              maxSize={1.4}
              particleDensity={100}
              className="w-full h-full"
              particleColor={theme === "dark" ? "#ffffff" : "#000000"}
          />
        </div>
        <div ref={scrollRef} className="relative h-screen overflow-y-scroll">
            <NavBar/>
            <Outlet/>
          {/* 使用自定义配置的组件 */}
          <BackToTop
              threshold={200}
              size={50}
              scrollRef={scrollRef}
          />
        </div>


      </>
  )
}

export default App
