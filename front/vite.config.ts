import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'
import AutoImport from 'unplugin-auto-import/vite'

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    vueDevTools(),
    AutoImport({
      imports: [
        'vue',          // 自动导入 Vue 相关 API（如 ref、reactive）
        'vue-router',   // 自动导入 Vue Router 相关 API（如 useRouter）
        'pinia'         // 自动导入 Pinia 相关 API（如 defineStore）
      ],
      dts: 'src/auto-imports.d.ts', // 生成类型声明文件
    })
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
  },

})
