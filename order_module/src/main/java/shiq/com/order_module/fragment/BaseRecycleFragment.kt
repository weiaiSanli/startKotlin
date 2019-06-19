package shiq.com.order_module.fragment

import android.media.Image
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.order_title_layout.*
import shiq.com.common.base.basemvp.BasePresenter
import shiq.com.common.base.basemvp.MvpFragment
import shiq.com.common.base.basemvp.RecycleViewDivider
import shiq.com.common.utils.getResouceColor
import shiq.com.common.view.recycleview.LoadMoreListener
import shiq.com.common.view.recycleview.MyRecyclerView
import shiq.com.order_module.R
import shiq.com.order_module.contract.BaseRecycleFragContract

/**
 * created by shi on 2019/6/17/017
 */
abstract class BaseRecycleFragment<T : BasePresenter<*>> : MvpFragment<T>(), BaseRecycleFragContract.View {

    protected var myRecyclerView: MyRecyclerView? = null
    protected var swipeRefreshLayout: SwipeRefreshLayout? = null
    protected var ivBack: ImageView? = null
    protected var llCompQuit: LinearLayout? = null
    protected var tvRight: TextView? = null
    protected var tvTitleName: TextView? = null

    protected var offeset: Int = 1
    protected var totalItem: Int = 0

    protected var hasMore :Boolean = false //是否有更多


    override fun setOnClickListener() {

        swipeRefreshLayout?.setColorSchemeResources(
            R.color.refreshColor, R.color.refreshColor,
            R.color.refreshColor, R.color.refreshColor
        )
        swipeRefreshLayout?.setOnRefreshListener {
            //刷新数据的监听
            refreshRecycleListner()

        }

        // 加载更多
        myRecyclerView?.setLoadMoreListener(object : LoadMoreListener {
            override fun onLoadMore() {
                loadMoreDataListener()
            }
        })
    }

    protected abstract fun refreshRecycleListner()
    protected abstract fun loadMoreDataListener()


    override fun initView() {

        ivBack = findViewById(R.id.iv_back) as ImageView
        llCompQuit = findViewById(R.id.ll_comp_quit) as LinearLayout
        tvRight = findViewById(R.id.tv_right) as TextView
        tvTitleName = findViewById(R.id.tv_title_name) as TextView
        myRecyclerView = findViewById(R.id.myRecyclerView) as MyRecyclerView
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout) as SwipeRefreshLayout
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        myRecyclerView?.layoutManager = layoutManager

        //添加分割线
        myRecyclerView?.addItemDecoration(
            RecycleViewDivider(
                context, LinearLayoutManager
                    .HORIZONTAL, 1, context.getResouceColor(R.color.diver_line)
            )
        )
    }

    override fun setResourceId(): Int {
        return R.layout.fragment_outstanding_order
    }
}
