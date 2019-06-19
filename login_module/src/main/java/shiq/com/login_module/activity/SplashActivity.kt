package shiq.com.login_module.activity

import android.content.Context
import android.widget.TextView
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import shiq.com.common.base.BaseActivity
import shiq.com.common.utils.*
import shiq.com.login_module.R
import java.util.concurrent.TimeUnit

/**
 * created by shi on 2019/6/13/013
 */
class SplashActivity : BaseActivity(){

    //进入页面的时间
    private var initTime: Long = 0
    //结束时的时间
    private var endTime: Long = 0

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {

        initTime = System.currentTimeMillis()

    }

    override fun initData() {

        enitAcitivity()

    }

    /**
     * 进入页面的设置
     */
    private fun enitAcitivity() {

        endTime = System.currentTimeMillis()
        val startTime = endTime - initTime
        if (startTime >= 2000) {
            initMainActy()
        } else {
            //使用RxJava避免使用Handler导致的内存泄露,使用Handler需要使用静态内部类
            val timer = Observable.timer(1500, TimeUnit.MILLISECONDS)
            timer.observeOn(AndroidSchedulers.mainThread())
                .subscribe { aLong ->
                    initMainActy()
                }
        }
    }

    private fun initMainActy() {

        startActivity(ShopMainActivity::class.java)
        finish()

//        ARouter.getInstance().build(ArouterConst.OrderModule.order_orderActivity).navigation(this)
    }


    override fun setListener() {

    }

}
