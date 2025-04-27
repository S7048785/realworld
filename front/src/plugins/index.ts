// Plugins
import {createPinia} from "pinia"
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import router from "../router";
import Notifications from '@kyvg/vue3-notification'
import {lazyPlugin} from "@/plugins/lazy.ts";

// Types
import type { App } from 'vue'

export function registerPlugins (app: App) {
  const pinia = createPinia();
  pinia.use(piniaPluginPersistedstate)

  app.use(pinia)
    .use(router)
    .use(Notifications)
    .use(lazyPlugin)
}
