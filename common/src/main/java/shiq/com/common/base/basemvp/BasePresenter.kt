package shiq.com.common.base.basemvp

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * created by shi on 2019/6/12/012
 *
 */
open class BasePresenter<T> : IPresenter  , CoroutineScope {

    private lateinit var job: Job // 定义job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job // Activity的协程

    protected var mvpView: T? = null
    protected fun attachView(mvpView: T) {
        this.mvpView = mvpView
        job = Job()
    }

    constructor(t: T) {
        this.attachView(t)
    }


    override fun onCreate(owner: LifecycleOwner) {

    }

    override fun onDestroy(owner: LifecycleOwner) {
        detachView()

    }

    fun detachView() {
        this.mvpView = null
        this.job.cancel() // 关闭页面后，结束所有协程任务

    }

    override fun onLifecycleChanged(owner: LifecycleOwner, event: Lifecycle.Event) {

    }

}
