package shiq.com.common.retrofit

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import shiq.com.common.base.BaseBean
import shiq.com.common.utils.ConstanceBase
import shiq.com.common.utils.MainThreadExecutor
import shiq.com.common.utils.log
import java.io.IOException

/**
 * Retrofit网络请求的协程封装类
 * created by shi on 2019/6/12/012
 */
fun <ResultType> CoroutineScope.retrofit(
    dsl: RetrofitCoroutineDsl<ResultType>.() -> Unit //传递方法，需要哪个，传递哪个
) {
    this.launch(Dispatchers.Main) {
        val retrofitCoroutine = RetrofitCoroutineDsl<ResultType>()

        retrofitCoroutine.dsl()
        retrofitCoroutine.api?.let { it ->
            val work = async(Dispatchers.IO) {
                // io线程执行
                try {
                    it.execute()
                } catch (e: IOException) {
                    "我是IOException".log()
                    val executor = MainThreadExecutor()
                    executor.execute {
                        retrofitCoroutine.onFailed?.invoke(ConstanceBase.NET_ERROR, -1)
                    }
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

                    var bean = response.body() as BaseBean
                    if (bean.infoCode in 200..299) {
                        retrofitCoroutine.onSuccess?.invoke(response.body())
                    } else {
                        retrofitCoroutine.onFailed?.invoke(bean.message, bean.infoCode)
                    }

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

