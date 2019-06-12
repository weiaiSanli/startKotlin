package shiq.com.login_module.presenter

import android.support.v4.util.ArrayMap
import android.support.v4.util.Consumer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.ResourceObserver
import io.reactivex.schedulers.Schedulers
import shiq.com.common.base.basemvp.BasePresenter
import shiq.com.common.retrofit.RetrofitHelper
import shiq.com.common.utils.ConstanceBase
import shiq.com.common.utils.log
import shiq.com.login_module.bean.LoginBean
import shiq.com.login_module.contract.LoginActyContract
import shiq.com.login_module.utils.LoginApi

/**
 * created by shi on 2019/6/12/012
 */
class LoginActyPresenter(view: LoginActyContract.View) :
    BasePresenter<LoginActyContract.View>(view), LoginActyContract.Presenter {


    override fun loginUser() {

        val map = ArrayMap<String, String>()
        map.apply {
            this["userName"] = mvpView?.getUserName()
            this["passWord"] = mvpView?.getPassword()

        }


        RetrofitHelper.instance.create(LoginApi::class.java, ConstanceBase.BASE_URL)
            .loginCall(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ResourceObserver<LoginBean>() {
                override fun onComplete() {

                }

                override fun onNext(value: LoginBean?) {

                    "${value?.message}成功".log()


                }

                override fun onError(e: Throwable?) {
                    e?.printStackTrace()

                }

            })


    }
}

