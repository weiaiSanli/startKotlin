package shiq.com.login_module.utils

import retrofit2.Call
import shiq.com.common.retrofit.RetrofitHelper
import shiq.com.common.utils.ConstanceBase

/**
 * created by shi on 2019/6/12/012
 *
 */
class RetrofitCoroutineDsl<ResultType> {
    var api: (Call<ResultType>)? = null

    internal var onSuccess: ((ResultType?) -> Unit)? = null
        private set
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
