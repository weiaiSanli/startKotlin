package shiq.com.common.base.basemvp

import android.app.Activity
import android.support.v4.app.Fragment
import android.view.View
import shiq.com.common.base.ViewPagerBaseFragment

/**
 * created by shi on 2019/6/17/017
 *
 */
abstract class MvpFragment<P : BasePresenter<*>> : ViewPagerBaseFragment() {

    protected lateinit var mvpPresenter: P

    override fun initPresenter() {
        mvpPresenter = createPresenter()
        lifecycle.addObserver(mvpPresenter)
    }

    abstract fun createPresenter(): P

    override fun onDestroyView() {
        super.onDestroyView()
        if(mvpPresenter!=null){
            mvpPresenter.detachView()
        }
    }


    //kotlin 封装：使用lazy懒加载,只有用到控件的时候才会对其进行初始化findView
    fun <V : View> Fragment.bindView(id: Int): Lazy<V> = lazy {
        viewFinder(id) as V
    }

    //acitivity中扩展调用
    private val viewFinder: Fragment.(Int) -> View?
        get() = { mRootView.findViewById(it) }


    override fun onVisible() {

    }


}