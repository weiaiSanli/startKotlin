package shiq.com.order_module.fragment

import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.fragment_outstanding_order.*
import shiq.com.common.base.basemvp.MvpFragment
import shiq.com.common.utils.ArouterConst
import shiq.com.common.utils.ConstanceBase
import shiq.com.common.utils.SPreferenceUtil
import shiq.com.order_module.R
import shiq.com.order_module.adapter.OutStandingOrderAdapter
import shiq.com.order_module.bean.OutStandingOrderBean
import shiq.com.order_module.contract.StoreManagerFragContract
import shiq.com.order_module.presenter.StoreManagerFragPresenter

/**
 * created by shi on 2019/6/13/013
 */
@Route(path = ArouterConst.OrderModule.order_storemanagerfrag)
class StoreManagerFragment: MvpFragment<StoreManagerFragPresenter>(),
        StoreManagerFragContract.View {

    private var goods_userName: String by SPreferenceUtil(ConstanceBase.USER_NAME, "") //获取Sp中存储的库存预警数

    private val tvQuit by bindView<TextView>(R.id.tv_quit)

    override fun setOnClickListener() {
        tvQuit.setOnClickListener {
            goods_userName = ""
            ARouter.getInstance().build(ArouterConst.LoginModule.login_loginActivity).navigation()
            activity!!.finish()
        }
    }

    override fun initView() {

        tvQuit.text = "quite"
    }

    override fun setResourceId(): Int {
        return R.layout.fragment_store_manager
    }

    override fun createPresenter(): StoreManagerFragPresenter {
        return StoreManagerFragPresenter(this)
    }

    override fun initdata() {

    }


}

