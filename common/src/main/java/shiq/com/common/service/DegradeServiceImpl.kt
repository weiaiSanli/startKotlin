package shiq.com.common.service

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.DegradeService
import shiq.com.common.utils.ArouterConst
import shiq.com.common.utils.log
import shiq.com.common.utils.toast

/**
 * 用于设置Arouter降级:全局找不到界面的处理逻辑
 * created by shi on 2019/6/14/014
 *
 */
@Route(path = ArouterConst.CommonModule.common_degradeServiceImpl)
class DegradeServiceImpl : DegradeService{

    //初始化的时候调用一次
    override fun init(context: Context?) {

        "DegradeServiceImpl的init()".log()
    }


    override fun onLost(context: Context?, postcard: Postcard?) {
        "DegradeServiceImpl的onLost()".log()
        context?.toast("界面已经失效,请你稍后重试!")
    }

}