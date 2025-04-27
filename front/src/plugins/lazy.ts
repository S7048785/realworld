import { useIntersectionObserver } from "@vueuse/core";

export const lazyPlugin = {
  install(app: any) {
    app.directive("card-lazy", (el: any, binding: any) => {
      const { stop } = useIntersectionObserver(
        el,
        ([event]) => {
          if (event.isIntersecting) {
            // 调用函数
            binding.value()
            stop(); // 停止监听
          }
        }
      )
    })
  }
}
