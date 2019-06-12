package shiq.com.login_module.activity

import android.widget.Button
import android.widget.EditText
import shiq.com.common.base.basemvp.MvpActivity
import shiq.com.common.utils.toEditable
import shiq.com.common.utils.toast
import shiq.com.login_module.R
import shiq.com.login_module.contract.LoginActyContract
import shiq.com.login_module.presenter.LoginActyPresenter

class LoginActivity : MvpActivity<LoginActyPresenter>(), LoginActyContract.View {

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

            mvpPresenter.loginUser()


        }

    }

}
