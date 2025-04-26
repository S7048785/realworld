import {notify} from "@kyvg/vue3-notification";

export const loginValidToast = () => {
  notify({
    text: '请先登录后再操作',
    type: 'warn'
  })
}
