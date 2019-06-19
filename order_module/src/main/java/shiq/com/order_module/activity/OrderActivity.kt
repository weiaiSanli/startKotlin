package shiq.com.order_module.activity

import com.alibaba.android.arouter.facade.annotation.Route
import shiq.com.common.base.BaseActivity
import shiq.com.common.utils.ArouterConst
import shiq.com.common.utils.ConstanceBase
import shiq.com.order_module.R


/**
 * created by shi on 2019/6/13/013
 *
 */
@Route(path = ArouterConst.OrderModule.order_orderActivity)
class OrderActivity : BaseActivity() {
    override fun getLayoutResourceId(): Int {

       return R.layout.activity_order
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun setListener() {

    }
}