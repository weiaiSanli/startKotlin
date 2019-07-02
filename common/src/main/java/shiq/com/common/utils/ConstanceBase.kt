package shiq.com.common.utils

import shiq.com.common.BuildConfig

/**
 * created by shi on 2019/6/12/012
 *
 */
interface ConstanceBase {

    companion object {
        const val NET_ERROR :String = "服务器异常,请稍后重试"
        const val SHAREP_NAME :String = "agood_water"
        const val USER_NAME :String = "user_name" //用户名
        const val finger_type :String = "finger_type" //指纹识别
        const val BASE_URL: String = BuildConfig.BASE_URL //base网络请求地址
    }

    interface ConstanceLogin{

        companion object{

            const val intentFigerType = "intent_figer_type" //intent传递的指纹type

        }


    }

}