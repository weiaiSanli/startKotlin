package shiq.com.common.retrofit

import io.reactivex.Observable
import retrofit2.http.*

/**
 * created by shi on 2019/6/12/012
 *
 */
interface NetApi {

    @POST("oauth/login")
    @FormUrlEncoded
    fun login(@Field("name") name: String, @Field("pwd") pwd: String): Observable<String>

    /**
     * 登录操作的请求
     */
//    @GET("appWater/userLogin.action")
//    fun loginCall(@QueryMap map: Map<String, String>): Observable<LoginBean>

}