package shiq.com.order_module.utils

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap
import shiq.com.common.retrofit.RetrofitHelper
import shiq.com.common.utils.ConstanceBase
import shiq.com.order_module.bean.CompleteOrderBean
import shiq.com.order_module.bean.OutStandingOrderBean

/**
 * created by shi on 2019/6/17/017
 *
 */
interface OrderApi{

    companion object{

        val instance = RetrofitHelper.instance.createOrder(OrderApi::class.java, ConstanceBase.BASE_URL)

    }


    /**
     * 已完成送水订单
     */
    @GET("appWater/orderList.action")
    fun waterCompletedCall(@QueryMap map: Map<String, String>): Call<CompleteOrderBean>




}