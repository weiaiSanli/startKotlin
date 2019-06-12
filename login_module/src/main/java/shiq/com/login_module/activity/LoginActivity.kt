package shiq.com.login_module.activity

import android.support.v4.util.ArrayMap
import android.widget.Button
import android.widget.EditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import shiq.com.common.base.basemvp.MvpActivity
import shiq.com.common.retrofit.RetrofitHelper
import shiq.com.common.utils.ConstanceBase
import shiq.com.common.utils.log
import shiq.com.common.utils.toEditable
import shiq.com.common.utils.toast
import shiq.com.login_module.R
import shiq.com.login_module.bean.LoginBean
import shiq.com.login_module.contract.LoginActyContract
import shiq.com.login_module.presenter.LoginActyPresenter
import shiq.com.login_module.utils.LoginApi
import shiq.com.login_module.utils.retrofit
import kotlin.coroutines.CoroutineContext

class LoginActivity : MvpActivity<LoginActyPresenter>(), LoginActyContract.View , CoroutineScope {

    private lateinit var job: Job // 定义job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job // Activity的协程

    override fun getUserName(): String {
        return etUser.text.toString().trim()
    }

    override fun getPassword(): String {
        return etPassWord.text.toString().trim()
    }


    override fun createPresenter(): LoginActyPresenter {
        return LoginActyPresenter(this)
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_login
    }

    private lateinit var etUser:EditText
    private lateinit var etPassWord:EditText
    private lateinit var btLogin:Button
    override fun initView() {

        job = Job()

        etUser = findViewById(R.id.et_user)
        etPassWord = findViewById(R.id.et_passWord)
        btLogin = findViewById(R.id.bt_login)


    }

    override fun initData() {

    }

    override fun setListener() {

        btLogin.setOnClickListener{

            if (getUserName().isNullOrEmpty()){
                toast("请输入用户名")
                return@setOnClickListener
            }

            if (getPassword().isNullOrEmpty()){
                toast("请输入密码")
                return@setOnClickListener
            }

//            mvpPresenter.loginUser()


            retrofit<LoginBean> {

                val map = ArrayMap<String, String>()
                map.apply {
                    this["userName"] = getUserName()
                    this["passWord"] = getPassword()

                }


                api = RetrofitHelper.instance.create(LoginApi::class.java, ConstanceBase.BASE_URL).login(map)

                onComplete {
                    "我是接受的onComplete".log()
                }

                onSuccess { loginBean ->

                    "我是接受的onSuccess".log()
                    "${loginBean.toString()}wonull".log()
                }

                onFailed { error, code ->

                    "我是接受的onFailed".log()

                }
            }




        }

    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel() // 关闭页面后，结束所有协程任务
    }

}
