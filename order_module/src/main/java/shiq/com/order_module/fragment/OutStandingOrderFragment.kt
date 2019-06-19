package shiq.com.order_module.fragment

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import shiq.com.common.utils.ArouterConst
import shiq.com.common.utils.log
import shiq.com.order_module.adapter.OutStandingOrderAdapter
import shiq.com.order_module.bean.OutStandingOrderBean
import shiq.com.order_module.contract.OutStandingOrderFragContract
import shiq.com.order_module.presenter.OutStandingOrderFragPresenter

/**
 * created by shi on 2019/6/13/013
 */
@Route(path = ArouterConst.OrderModule.order_outstandingfrag)
class OutStandingOrderFragment: BaseRecycleFragment<OutStandingOrderFragPresenter>(),
    OutStandingOrderFragContract.View{

    override fun createPresenter(): OutStandingOrderFragPresenter {

        return OutStandingOrderFragPresenter(this)
    }

    @Autowired(name = "type")
    @JvmField var type : Int ?= -1


    private var goodsList = ArrayList<OutStandingOrderBean>()

    private lateinit var adapter:OutStandingOrderAdapter

    override fun refreshRecycleListner() {

    }


    override fun setEventBus() {
        //必须注册才能使用
        ARouter.getInstance().inject(this)  // Start auto inject.

    }

    override fun loadMoreDataListener() {

    }

    override fun initdata() {

        adapter = OutStandingOrderAdapter(context, goodsList)

        myRecyclerView?.adapter = adapter

        "type: $type".log()
    }


}
