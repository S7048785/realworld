// Plugins
import {createPinia} from "pinia"
import router from "../router";
import Notifications from '@kyvg/vue3-notification'

// Types
import type { App } from 'vue'

export function registerPlugins (app: App) {
  app.use(createPinia())
    .use(router)
    .use(Notifications)
}
