import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import {ThemeProvider} from "@/components/theme-provider.tsx";
import router from "@/router/index.ts"
import {RouterProvider} from "react-router-dom";

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <ThemeProvider defaultTheme="system" storageKey="vite-ui-theme">
        <RouterProvider router={router}></RouterProvider>
    </ThemeProvider>
  </StrictMode>,
)
