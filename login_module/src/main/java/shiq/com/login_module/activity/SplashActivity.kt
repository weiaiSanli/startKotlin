package shiq.com.login_module.activity

import android.content.Context
import android.media.session.MediaSession
import android.widget.TextView
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

        GlobalScope.launch {

            val token = requestToken()
            val post = createPost(token , "123")
            processPost(post)
        }



//        enitAcitivity()

    }
    //被suspend用作修饰会被暂停的函数，被标记为 suspend 的函数只能运行在协程或者其他 suspend 函数当中。
    suspend fun requestToken(): Token {
        return Token("shiq")
    }

    /**
     * 设置suspend函数
     */
    suspend fun createPost(token:Token , pwd:String): Post{
        if (token.token == "shiq" && pwd == "123"){
            return Post("success")
        }
        return Post("error")
    }

    fun processPost(post: Post) {

        if (post.success == "success"){
            toast("登录成功")
        }else{
            toast("登录失败")
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
