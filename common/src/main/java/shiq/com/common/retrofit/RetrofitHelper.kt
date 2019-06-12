package shiq.com.common.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import shiq.com.common.fastjsonfoctory.FastJsonConverterFactory

/**
 * created by shi on 2019/6/12/012
 *
 */
class RetrofitHelper private constructor(){

    companion object{
        val instance = SingletonHolder.holder
    }

    //使用静态内部类创建一个单例模式
    private object SingletonHolder{
        val holder = RetrofitHelper()
    }


    private var storeApi: Any? = null
    fun <T> create(a: Class<T>, baseUrl: String): T {
        if (storeApi == null) {
            synchronized(RetrofitHelper::class.java) {
                if (storeApi == null) {
                    storeApi = getRetrofit(baseUrl).create(a)
                }
            }
        }
        return storeApi as T

    }

    fun getRetrofit(baseUrl: String): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(FastJsonConverterFactory.create())
            .build()

        println("创建了retrofit$baseUrl")
        return retrofit
    }


}