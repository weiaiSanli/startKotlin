package shiq.com.common.utils

import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback

/**
 * 创建Arouter跳转路由找不到回调的DSL
 * created by shi on 2019/6/14/014
 */

class NavigationCallbackDsl :NavigationCallback{

    //丢失界面
    private var onLost:((postcard: Postcard?) -> Unit)? = null
    //找到界面
    private var onFound:((postcard: Postcard?) -> Unit)? = null
    //被拦截了
    private var onInterrupt:((postcard: Postcard?) -> Unit)? = null
    //跳转完成
    private var onArrival:((postcard: Postcard?) -> Unit)? = null

    override fun onLost(postcard: Postcard?) {
        onLost?.invoke(postcard)
    }


    override fun onFound(postcard: Postcard?) {
        onFound?.invoke(postcard)
    }

    override fun onInterrupt(postcard: Postcard?) {
        onInterrupt?.invoke(postcard)
    }

    override fun onArrival(postcard: Postcard?) {
        onArrival?.invoke(postcard)
    }

    fun onLost(lost :(postcard: Postcard?)->Unit){
        onLost = lost
    }

    fun onFound(found :(postcard: Postcard?)->Unit){
        onFound = found
    }

    fun onInterrupt(interrupt :(postcard: Postcard?)->Unit){
        onInterrupt = interrupt
    }

    fun onArrival(arrival :(postcard: Postcard?)->Unit){
        onArrival = arrival
    }



}