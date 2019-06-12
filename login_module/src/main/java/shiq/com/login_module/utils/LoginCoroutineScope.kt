package shiq.com.login_module.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import shiq.com.common.utils.log
import java.io.IOException
import java.net.ConnectException

/**
 * created by shi on 2019/6/12/012
 */
fun <ResultType> CoroutineScope.retrofit(
    dsl: RetrofitCoroutineDsl<ResultType>.() -> Unit //传递方法，需要哪个，传递哪个
) {
    this.launch(Dispatchers.Main) {
        val retrofitCoroutine = RetrofitCoroutineDsl<ResultType>()
        retrofitCoroutine.dsl()
        retrofitCoroutine.api?.let { it ->
            val work = async(Dispatchers.IO) { // io线程执行
                try {
                    it.execute()
                } catch (e: ConnectException) {
                    e.printStackTrace()
                    retrofitCoroutine.onFailed?.invoke("网络连接出错", -100)
                    null
                } catch (e: IOException) {
                    retrofitCoroutine.onFailed?.invoke("未知网络错误", -1)
                    null
                }
            }
            work.invokeOnCompletion { _ ->
                // 协程关闭时，取消任务
                if (work.isCancelled) {
                    it.cancel()
                    retrofitCoroutine.clean()
                }
            }
            val response = work.await()
            retrofitCoroutine.onComplete?.invoke()
            response?.let {
                if (response.isSuccessful) {

                    response.body().toString().log()

                    retrofitCoroutine.onSuccess?.invoke(response.body())
                } else {
                    // 处理 HTTP code
                    when (response.code()) {
                        401 -> {
                        }
                        500 -> {
                        }
                    }
                    retrofitCoroutine.onFailed?.invoke(response.errorBody().toString(), response.code())
                }
            }
        }
    }
}

