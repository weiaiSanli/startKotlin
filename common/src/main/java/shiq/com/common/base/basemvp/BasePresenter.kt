package shiq.com.common.base.basemvp

/**
 * created by shi on 2019/6/12/012
 *
 */
open class BasePresenter<T> {

    protected var mvpView: T? = null
    protected fun attachView(mvpView: T) {
        this.mvpView = mvpView
    }

    constructor(t: T) {
        this.attachView(t)
    }


}
