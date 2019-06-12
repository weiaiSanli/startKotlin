package shiq.com.login_module.utils

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap
import shiq.com.login_module.bean.LoginBean

/**
 * created by shi on 2019/6/12/012
 *
 */
interface LoginApi {

    /**
     * 登录操作的请求
     */
    @GET("appWater/userLogin.action")
    fun loginCall(@QueryMap map: Map<String, String>): Observable<LoginBean>
}