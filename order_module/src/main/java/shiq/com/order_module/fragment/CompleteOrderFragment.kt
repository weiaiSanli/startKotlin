package shiq.com.order_module.fragment

import android.provider.SyncStateContract
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import shiq.com.common.utils.*
import shiq.com.order_module.R
import shiq.com.order_module.adapter.CompleteOrderAdapter
import shiq.com.order_module.bean.CompleteOrder
import shiq.com.order_module.contract.CompleteOrderFragContract
import shiq.com.order_module.presenter.CompleteOrderFragPresenter

/**
 * created by shi on 2019/6/13/013
 */
@Route(path = ArouterConst.OrderModule.order_completefrag)
class CompleteOrderFragment: BaseRecycleFragment<CompleteOrderFragPresenter>() ,
    CompleteOrderFragContract.View{



    private var goods_userName: String by SPreferenceUtil(ConstanceBase.USER_NAME, "") //获取Sp中存储的库存预警数


    override fun getPageNum(): String {
        return offeset.toString()
    }

    override fun getUserName(): String {
        return goods_userName
    }

    override fun createPresenter(): CompleteOrderFragPresenter {
        return CompleteOrderFragPresenter(this)
    }

    @Autowired(name = "type")
    @JvmField var type : Int ?= -1

    private var goodsList = ArrayList<CompleteOrder>()

    private lateinit var adapter: CompleteOrderAdapter

    override fun setEventBus() {
        ARouter.getInstance().inject(this)  // Start auto inject.
    }


    override fun refreshRecycleListner() {
        offeset = 1
        mvpPresenter.loginNet()
    }

    override fun loadMoreDataListener() {

        if (totalItem % 10 == 0 && hasMore) {
            // 可以加载更多
            mvpPresenter.loginNet()
        } else {
            myRecyclerView?.loadMoreEnd()
        }
    }

    override fun initView() {
        super.initView()
        tvTitleName?.text = "完成订单"
        ivBack?.visibility = View.GONE
    }


    override fun initdata() {
        adapter = CompleteOrderAdapter(context, goodsList)
        myRecyclerView?.adapter = adapter
        mvpPresenter?.loginNet()

    }

    override fun setOnClickListener() {
        super.setOnClickListener()

        llCompQuit?.setOnClickListener{

            context.toast("点击查询!")

        }


    }


    override fun loginSuccess(data: MutableList<CompleteOrder>) {


        if (offeset == 1) {
            goodsList.clear()

            //结束下拉刷新
            if (swipeRefreshLayout!!.isRefreshing) {
                swipeRefreshLayout!!.isRefreshing = false
                myRecyclerView!!.refreshComplete()
            }


            if (data.size == 0) {
                myRecyclerView!!.visibility = View.GONE
            } else {
                myRecyclerView!!.visibility = View.VISIBLE

            }
        } else {
            myRecyclerView!!.loadMoreComplete()
        }

        goodsList.addAll(data)

        hasMore = data!= null && data.size == 10

        offeset += data.size

        adapter.refresh(goodsList)


    }


}
