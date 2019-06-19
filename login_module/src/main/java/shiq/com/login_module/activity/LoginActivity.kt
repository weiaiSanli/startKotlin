package shiq.com.login_module.activity

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import shiq.com.common.base.basemvp.MvpActivity
import shiq.com.common.utils.*
import shiq.com.login_module.R
import shiq.com.login_module.contract.LoginActyContract
import shiq.com.login_module.presenter.LoginActyPresenter

@Route(path = ArouterConst.LoginModule.login_loginActivity)
class LoginActivity : MvpActivity<LoginActyPresenter>(), LoginActyContract.View {


    private var goods_userName: String by SPreferenceUtil(ConstanceBase.USER_NAME, "") //获取Sp中存储的库存预警数
    private var finger_type: Boolean by SPreferenceUtil(ConstanceBase.finger_type, false) //获取是否添加了指纹识别


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


   private val etUser by bindView<EditText>(R.id.et_user)
   private val etPassWord by bindView<EditText>(R.id.et_passWord)
   private val btLogin by bindView<Button>(R.id.bt_login)

    override fun initView() {
        etUser.text = "17610551702".toEditable()
        etPassWord.text = "sq1761055".toEditable()
    }

    private var canLoginNet: Boolean = true
    override fun initData() {
        if (!goods_userName.isNullOrEmpty()) {

            if (finger_type) {
                startActivity(FingerMarkActivity::class.java)
            }else{
                startActivity(SplashActivity::class.java)
                finish()
            }
        }
    }

    override fun setListener() {

        etUser.onTextChange {
            afterTextChanged { s ->
                "当前值为: ${s.toString()}".log()
            }

        }


        btLogin.setOnClickListener {

            if (getUserName().isNullOrEmpty()) {
                toast("请输入用户名")
                return@setOnClickListener
            }

            if (getPassword().isNullOrEmpty()) {
                toast("请输入密码")
                return@setOnClickListener
            }

            if (canLoginNet) {

                canLoginNet = false
                mvpPresenter.loginUser()
            } else {
                toast("正在请求,请稍等...")
            }
        }
    }

    override fun loginError(error: String?) {
        canLoginNet = true
        toast(error ?: ConstanceBase.NET_ERROR)
    }

    override fun loginSuccess(username :String) {
        canLoginNet = true
        toast("登录成功!")

        goods_userName = username
        startActivity(SplashActivity::class.java)
        finish()
    }
}
