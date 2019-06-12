package shiq.com.common.base.basemvp

import android.os.Bundle
import shiq.com.common.base.BaseActivity

/**
 * created by shi on 2019/6/12/012
 *
 */
abstract class MvpActivity<P : BasePresenter<*>> : BaseActivity() {

    protected lateinit var mvpPresenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        mvpPresenter = createPresenter()
        super.onCreate(savedInstanceState)
    }

    protected abstract fun createPresenter(): P

}