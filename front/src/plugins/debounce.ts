import {useIntersectionObserver} from "@vueuse/core";

/**
 * 防抖指令
 */
export const debounceDirective = {
  install(app: any) {
    app.directive("debounce", (el: any, binding: any) => {
      let timer: number;
      el.addEventListener('click', () => {
        if (timer) clearTimeout(timer);
        timer = setTimeout(() => {
          binding.value();
        }, 500);
      });
    })
  }
}
