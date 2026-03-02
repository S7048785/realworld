package com.yyjy.common

/**
 * @author Nyxcirea
 * @date 2026/2/14
 * @description: TODO
 */
class BaseContext {
    companion object {
        private val threadLocal = ThreadLocal<Int>()

        fun setCurrentId(id: Int) {
            threadLocal.set(id)
        }
        fun getCurrentId(): Int {
            return threadLocal.get()
        }
        fun removeCurrentId() {
            threadLocal.remove()
        }
    }
}