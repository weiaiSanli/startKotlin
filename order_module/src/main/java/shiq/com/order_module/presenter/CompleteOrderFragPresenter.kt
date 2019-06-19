package shiq.com.order_module.presenter

import android.support.v4.util.ArrayMap
import shiq.com.common.base.basemvp.BasePresenter
import shiq.com.common.retrofit.retrofit
import shiq.com.common.utils.log
import shiq.com.order_module.bean.CompleteOrderBean
import shiq.com.order_module.bean.OutStandingOrderBean
import shiq.com.order_module.contract.CompleteOrderFragContract
import shiq.com.order_module.utils.OrderApi

/**
 * created by shi on 2019/6/17/017
 */
class CompleteOrderFragPresenter(view: CompleteOrderFragContract.View) :
    BasePresenter<CompleteOrderFragContract.View>(view), CompleteOrderFragContract.Presenter {


    override fun loginNet() {

        retrofit<CompleteOrderBean>{
            val map = ArrayMap<String, String>()
            map.apply {
                this["userName"] = mvpView?.getUserName()
                this["pageNum"] = mvpView?.getPageNum()


            }
            api = OrderApi.instance.waterCompletedCall(map)

            onSuccess {



                if ( it?.data?.orderList != null){
                    mvpView?.loginSuccess(it?.data?.orderList)

                }

            }

            onFailed { error, code ->

                "shibai".log()

            }


        }


    }
}
