package shiq.com.common.retrofit

import retrofit2.Call

/**
 * created by shi on 2019/6/12/012
 *
 */
class RetrofitCoroutineDsl<ResultType> {
    var api: (Call<ResultType>)? = null

    internal var onSuccess: ((ResultType?) -> Unit)? = null
        private set  // setter()访问器的私有化，并且它拥有kotlin的默认实现,其他类不允许设置set()值
    internal var onComplete: (() -> Unit)? = null
        private set
    internal var onFailed: ((error: String?, code: Int) -> Unit)? = null
        private set

    var showFailedMsg = false

    internal fun clean() {
        onSuccess = null
        onComplete = null
        onFailed = null
    }


    fun onSuccess(block: (ResultType?) -> Unit) {
        this.onSuccess = block
    }

    fun onComplete(block: () -> Unit) {
        this.onComplete = block
    }

    fun onFailed(block: (error: String?, code: Int) -> Unit) {
        this.onFailed = block
    }

}
