package com.yyjy.common

/**
 * @author Nyxcirea
 * @date 2026/2/13
 * @description: TODO
 */
data class ApiRes<T>(
    var code: Int?,
    var msg: String?,
    var data: T?
) {
    companion object {
        fun <T> ok(): ApiRes<T> = ApiRes(code = 1, msg = "ok", data = null)

        fun <T> ok(data: T): ApiRes<T> = ApiRes(code = 1, msg = "ok", data = data)

        fun <T> fail(msg: String): ApiRes<T> = ApiRes(code = 0, msg = msg, data = null)

        fun <T> fail(code: Int, msg: String): ApiRes<T> = ApiRes(code = code, msg = msg, data = null)
    }
}

data class PageRes<T>(
    var total: Long = 0,
    var page: Int = 1,
    var page_size: Int = 10,
    var list: List<T>
)