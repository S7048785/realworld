import { createRoot } from 'react-dom/client'
import './index.css'
import {ThemeProvider} from "@/components/theme/theme-provider.tsx";
import router from "@/router/index.tsx"
import {RouterProvider} from "react-router-dom";

createRoot(document.getElementById('root')!).render(
    <ThemeProvider defaultTheme="dark" storageKey="vite-ui-theme">
        <RouterProvider router={router}></RouterProvider>
    </ThemeProvider>
)
