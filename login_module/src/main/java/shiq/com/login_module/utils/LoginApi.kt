package shiq.com.login_module.utils

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap
import shiq.com.common.retrofit.RetrofitHelper
import shiq.com.common.utils.ConstanceBase
import shiq.com.login_module.bean.LoginBean

/**
 * created by shi on 2019/6/12/012
 *
 */
interface LoginApi {

    companion object{

        val instance = RetrofitHelper.instance.create(LoginApi::class.java, ConstanceBase.BASE_URL)

    }

    /**
     * 登录操作的请求
     */
    @GET("appWater/userLogin.action")
    fun loginCall(@QueryMap map: Map<String, String>): Observable<LoginBean>

    /**
     * 登录操作的请求
     */
    @GET("appWater/userLogin.action")
    fun login(@QueryMap map: Map<String, String>): Call<LoginBean>
}