package shiq.com.login_module.presenter

import android.support.v4.util.ArrayMap
import shiq.com.common.base.basemvp.BasePresenter
import shiq.com.login_module.bean.LoginBean
import shiq.com.login_module.contract.LoginActyContract
import shiq.com.login_module.utils.LoginApi
import shiq.com.common.retrofit.retrofit

/**
 * created by shi on 2019/6/12/012
 */
class LoginActyPresenter(view: LoginActyContract.View) :
    BasePresenter<LoginActyContract.View>(view), LoginActyContract.Presenter{

    //登录逻辑,需要token验证信息保存到Sp中.SP使用lazy函数
    override fun loginUser() {

        retrofit<LoginBean> {
            val map = ArrayMap<String, String>()
            map.apply {
                this["userName"] = mvpView?.getUserName()
                this["passWord"] = mvpView?.getPassword()
            }
            api = LoginApi.instance.login(map)

            onSuccess {
                mvpView?.loginSuccess(mvpView?.getUserName()!!)
            }

            onFailed { error, _ ->
                mvpView?.loginError(error)
            }
        }
    }
}


