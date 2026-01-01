import {createRoot} from 'react-dom/client'
import './styles/index.css'
import {ThemeProvider} from "@/components/theme/theme-provider.tsx";
import router from "@/router/index.tsx"
import {RouterProvider} from "react-router-dom";
import {StrictMode} from "react";

createRoot(document.getElementById('root')!).render(
    <StrictMode>
        <ThemeProvider defaultTheme="dark" storageKey="vite-ui-theme">
          <RouterProvider router={router}></RouterProvider>
        </ThemeProvider>
		</StrictMode>
)
