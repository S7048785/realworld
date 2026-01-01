// import { useState } from 'react'
// import reactLogo from './assets/react.svg'
// import viteLogo from '/vite.svg'
// import {ModeToggle} from "@/components/mode-toggle.tsx";
// import NavBar from "@/components/navbar/NavBar.tsx";

import NavBar from "@/components/navbar/NavBar.tsx";
import AnimatedOutlet from "@/components/AnimatedOutlet.tsx";
import PageTitleUpdater from "@/hooks/PageTitleUpdater.tsx";
import {SparklesCore} from "@/components/ui/sparkles.tsx";
import {useTheme} from "@/components/theme/theme-provider.tsx";
import BackToTop from "@/components/BackToTop.tsx";
import {useEffect, useRef} from "react";
import {Toaster} from "react-hot-toast";
import {useUserStore} from "@/store/userStore.ts";

function App() {
  // const [count, setCount] = useState(0)
  const { theme } = useTheme();
  const scrollRef = useRef<HTMLDivElement>(null);

  const getUserInfo = useUserStore(state => state.getUserInfo)


  useEffect(() => {
    getUserInfo();
  }, [])

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
          <AnimatedOutlet/>

          <BackToTop
              threshold={200}
              size={50}
              scrollRef={scrollRef}
          />
        </div>

        <Toaster position="bottom-right"
                 reverseOrder={true}/>
      </>
  )
}

export default App