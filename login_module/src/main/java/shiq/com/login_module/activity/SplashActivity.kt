package shiq.com.login_module.activity

import android.content.Context
import android.media.session.MediaSession
import android.widget.TextView
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.*
import shiq.com.common.base.BaseActivity
import shiq.com.common.utils.*
import shiq.com.login_module.R
import shiq.com.login_module.bean.Post
import shiq.com.login_module.bean.Token
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

/**
 * created by shi on 2019/6/13/013
 */
class SplashActivity : BaseActivity() {

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
        "我是开始".log()
        val job = GlobalScope.launch(Dispatchers.Main) {
            //默认为DIspatchers.Default

            val token = requestToken()
            val post = createPost(token, "123")
            val wait = async(Dispatchers.IO) { //设置在IO线程中计算数据
                delay(3000) //只能在协程中使用,挂起协程,但不会阻塞线程
                processPost(post)
            }
            wait.await().log() //await得到async返回的结果,运行线程跟launch协程一致
            "我是结果".log()
            //需要设置协程运行的线程为UI线程,不设置使用默认线程池会报子线程不能更新UI
            toast("我是结果")
        }
        "我是结束".log()

        job.cancel() //取消协程任务

//        enitAcitivity()

    }

    //被suspend用作修饰会被暂停的函数，被标记为 suspend 的函数只能运行在协程或者其他 suspend 函数当中。
    suspend fun requestToken(): Token {
        return Token("shiq")
    }

    /**
     * 设置suspend函数
     */
    suspend fun createPost(token: Token, pwd: String): Post {
        if (token.token == "shiq" && pwd == "123") {
            return Post("success")
        }
        return Post("error")
    }

    suspend fun processPost(post: Post): String {

//        delay(3000)
        return if (post.success == "success") {
//            ("登录成功").log()
            "登录成功"
        } else {
//            ("登录失败").log()
            "登录失败"
        }
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

//        ARouter.getInstance().build(ArouterConst.OrderModule.order_orderActivity)
//            .navigation(this , navigationCallback {
//                onFound {
//                    "找到了界面".log()
//                }
//            })
    }


    override fun setListener() {

    }

}
